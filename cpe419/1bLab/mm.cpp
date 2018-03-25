/* Alexander DeMello
 * CPE 419 lab 01 v2.1
 * Matrix Multiplication using CUDA/CUBLAS
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
   
   // NEEDS TO BE UPDATED
   output = fopen("result.out", "w");
   //write result matrix to an output file
   for(i =0; i < resultCol; i++) {
      for(j = 0; j < resultRow; j++) {
         fprintf(output, "%.2f ", *(result + (j*resultCol + i)));
      }
      fprintf(output, "\n");
   }
   fclose(output);
   free(result);
}


myValue* mmRead(FILE *inFile1, FILE*inFile2, int* f1rows, int* f1cols,
      int* f2rows, int* f2cols) {
   myValue *mat1, *mat2, *result;
   int i, j;
   
   //allocate memory for storing infile values
   mat1 = (myValue*)calloc((*f1rows) * (*f1cols), sizeof(myValue));
   
   mat2 = (myValue*)calloc((*f2rows) * (*f2cols), sizeof(myValue));

   //read matrices into now allocated 2d arrays in col major order.
   for(i = 0; i < (*f1cols); i++) {
      for(j = 0; j < (*f1rows); j++) {
         fseek(inFile1, SIZEOFDATA * (DATAOFFSET+(j* (*f1cols)+i)), SEEK_SET);
         fread(mat1 + (i * (*f1cols) + j), 4, 1, inFile1);
      }
   }

   //second matrix in calumn major
   for(i = 0; i < (*f2cols); i++) {
      for(j = 0; j < (*f2rows); j++) {
         fseek(inFile2, SIZEOFDATA * (DATAOFFSET+(j* (*f2cols)+i)), SEEK_SET);
         fread(mat2 + (i * (*f2cols) + j), 4, 1, inFile2);
      }
   }
   
   //close files that are no longer needed
   fclose(inFile1);
   fclose(inFile2);

   //call mmcompute
   result = mmCompute(mat1, mat2, *f1rows, *f1cols, *f2rows, *f2cols);
   //free remaining matricies that are no longer needed
   free(mat1);
   free(mat2);
   return result;

}

myValue* mmCompute(myValue* mat1H, myValue* mat2H, int m, int n, int p, int q) {
   myValue* resultH, *resultD, *mat1D, *mat2D;
   myValue sum;
   int i, j, k;
   cublasHandle_t handle;
   float alp = 1;
   float bet = 1;
   double dalp = 1;
   double dbet = 1;
   float* alpha = &alp;
   float* beta = &bet;
   double* doubleAlpha = &dalp;
   double* doubleBeta = &dbet;

   //initialize variables
   cublasCreate(&handle);
   sum = 0;

   //allocate result matrix memory on host and device
   //host
   resultH = (myValue*)calloc(m * q, sizeof(myValue));
   //device
   cudaMalloc((void **)&resultD, m * q * sizeof(myValue));
   cudaMalloc((void **)&mat1D, m * n * sizeof(myValue));
   cudaMalloc((void **)&mat2D, p * q * sizeof(myValue));

   //put host memory on device to be calculated
   cudaMemcpy(mat1D, mat1H, m * n * sizeof(myValue), cudaMemcpyHostToDevice);
   cudaMemcpy(mat2D, mat2H, p * q * sizeof(myValue), cudaMemcpyHostToDevice);
   cudaMemcpy(resultD, resultH, m*q*sizeof(myValue), cudaMemcpyHostToDevice);

   //calculate using sgemm or dgemm (deterministic). 
   //if ISDOUBLE == 0 use floating point, if ISDOUBLE == 5 use DOUBLE
   if(ISDOUBLE == 0) {
      cublasSgemm(handle, CUBLAS_OP_N, CUBLAS_OP_N, m, q, n, alpha, 
            (float*)mat1D, m, (float*)mat2D, p, beta, (float*)resultD, m);
   }
   else if(ISDOUBLE == 5) {
      cublasDgemm(handle, CUBLAS_OP_N, CUBLAS_OP_N, m, q, n, doubleAlpha,
            (double*)mat1D, m, (double*)mat2D, p, doubleBeta, (double*)resultD, m);
   }
   else {
      fprintf(stderr, "ISDOUBLE not an expected value");
   }
   //bring back memory to host and free gfx mem
   cudaMemcpy(resultH, resultD, m*q*sizeof(myValue), cudaMemcpyDeviceToHost);
   cudaFree(mat1D);
   cudaFree(mat2D);
   cudaFree(resultD);

   cublasDestroy(handle);

   return resultH;
}



















