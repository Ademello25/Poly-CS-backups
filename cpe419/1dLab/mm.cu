/* Alexander DeMello
 * CPE 419 lab 01 v3
 * Matrix Multiplication using CUDA
 */

#include "mm.h"

int main (int argc, char*argv[]) {
   int resultRow, resultCol;
   int *f1rows, *f1cols, *f2rows, *f2cols;
   FILE *inFile1, *inFile2;
   myValue* result;

   //open files to be read into 2d arrays
   inFile1 = fopen(argv[1], "rb");
   inFile2 = fopen(argv[2], "rb");

   f1rows = (int*)malloc(sizeof(int));
   f1cols = (int*)malloc(sizeof(int));
   f2rows = (int*)malloc(sizeof(int));
   f2cols = (int*)malloc(sizeof(int));
   //read the values of rows/col of file 1/2 and store them
   fread(f1rows, 4, 1, inFile1);
   fread(f1cols, 4, 1, inFile1);
   fread(f2rows, 4, 1, inFile2);
   fread(f2cols, 4, 1, inFile2);

   resultRow = *f1rows;
   resultCol = *f2cols;

   if((*f1cols) != (*f2rows)) {
      printf("Cannot multiply these matrices");
      exit(0);
   }

   //call mmRead and return result array (call compute in mmRead)
   result = mmRead(inFile1, inFile2, f1rows, f1cols, f2rows, f2cols);

   //call mmWrite to write result array to output file
   mmWrite(result, resultRow, resultCol);
   
   //free memory and close files
   free(f1rows);
   free(f1cols);
   free(f2rows);
   free(f2cols);
   
   return 0;
}

void mmWrite(myValue *result, int resultRow, int resultCol) {
   FILE *output;
   int i, j;
   
   output = fopen("result.out", "w");
   //write result matrix to an output file
   for(i =0; i < resultRow; i++) {
      for(j = 0; j < resultCol; j++) {
         fprintf(output, "%.2f ", *(result + (i*resultCol + j)));
      }
      fprintf(output, "\n");
   }
   fclose(output);
   free(result);
}


myValue* mmRead(FILE *inFile1, FILE*inFile2, int* f1rows, int* f1cols,
      int* f2rows, int* f2cols) {
   myValue *mat1H, *mat2H, *result;
   int i, j;
   
   //allocate memory for storing infile values
   mat1H = (myValue*)calloc((*f1rows) * (*f1cols), sizeof(myValue));
   
   mat2H = (myValue*)calloc((*f2rows) * (*f2cols), sizeof(myValue));

   //read matrices into now allocated 2d arrays in row major
   for(i = 0; i < (*f1rows); i++) {
      for(j = 0; j < (*f1cols); j++) {
         fread(mat1H + (i * (*f1cols) + j), 4, 1, inFile1);
      }
   }

   //second matrix in row major
   for(i = 0; i < (*f2rows); i++) {
      for(j = 0; j < (*f2cols); j++) {
         fread(mat2H + (i * (*f2cols) + j), 4, 1, inFile2);
      }
   }
   
   //close files that are no longer needed
   fclose(inFile1);
   fclose(inFile2);

   //call mmcompute after some setup for outside help
   result = (myValue*)calloc((*f1rows) * (*f2cols), sizeof(myValue));
   mmGpuSetup(mat1H, mat2H, result, *f1rows, *f1cols, *f2rows, *f2cols);

   return result;

}

void mmGpuSetup(myValue* mat1H, myValue* mat2H, myValue* resultH,
      int m, int n, int p, int q) {
   myValue *resultD, *mat1D, *mat2D;

   //allocate result matrix memory on host and device
   //device
   cudaMalloc((void **)&resultD, m * q * sizeof(myValue));
   cudaMalloc((void **)&mat1D, m * n * sizeof(myValue));
   cudaMalloc((void **)&mat2D, p * q * sizeof(myValue));

   //put host memory on device to be calculated
   cudaMemcpy(mat1D, mat1H, m * n * sizeof(myValue), cudaMemcpyHostToDevice);
   cudaMemcpy(mat2D, mat2H, p * q * sizeof(myValue), cudaMemcpyHostToDevice);
   cudaMemcpy(resultD, resultH, m*q*sizeof(myValue), cudaMemcpyHostToDevice);

   //calculate using threads on GPU
   //setup kernel call
   //matrix multiply cuda

   dim3 dimBlock(TILE_WIDTH, TILE_WIDTH);
   dim3 dimGrid((q + dimBlock.x -1)/dimBlock.x, (m + dimBlock.y -1)/dimBlock.y);
   mmCompute<<<dimGrid, dimBlock>>>(mat1D, mat2D, resultD, m, n, p, q);
   
   //bring back memory to host and free gfx mem
   cudaMemcpy(resultH, resultD, m*q*sizeof(myValue), cudaMemcpyDeviceToHost);
   cudaFree(mat1D);
   cudaFree(mat2D);
   cudaFree(resultD);

}

__global__ void mmCompute(myValue* mat1D, myValue* mat2D, myValue* resultD,
      int m, int n, int p, int q) {
   int row, col, k;
   myValue sum;
   row = blockIdx.y * blockDim.y + threadIdx.y;
   col = blockIdx.x * blockDim.x + threadIdx.x;
   sum = 0;

   //calculate using threads on GPU
   for(k = 0; k < n; ++k) {
      sum += mat1D[row * n + k] * mat2D[k * q + col];
   }
   resultD[row * q + col] = sum;
}
      















