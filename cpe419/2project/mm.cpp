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

   //read matrix 1 in row major order
   for(i = 0; i < (*f1rows); i++) {
      for(j = 0; j < (*f1cols); j++) { 
         fread(mat1 + (i * (*f1cols) + j), 4, 1, inFile1);
      }
   }

   //second matrix in calumn major
   for(i = 0; i < (*f2rows); i++) {
      for(j = 0; j < (*f2cols); j++) {
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

myValue* mmCompute(myValue* mat1, myValue* mat2, int m, int n, int p, int q) {
   myValue* result;
   myValue sum;

   //initialize variables
   sum = 0;

   //allocate result matrix memory
   result = (myValue*)calloc(m * q, sizeof(myValue));

#pragma omp parallel for reduction(+:sum) num_threads(28)
   for(int_fast32_t i = 0; i < m; i++) {
      for(int_fast32_t j = 0; j < q; j++) {
#pragma omp simd
#pragma vector aligned
         for(int_fast32_t k = 0; k < p; k++) {
            sum = sum + mat1[i * n + k] * mat2[k * q + j];
         }
            result[i * q + j] = sum;
         sum =0;
      }
   }

   return result;
}



















