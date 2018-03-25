/*Alexander DeMello
 * CPE 419 Project 2
 * OpenMP Matrix Multiplication
 */

#include "mm.h"

int main (int argc, char*argv[]) {
   int resultRow, resultCol;
   int *f1rows, *f1cols, *f2rows, *f2cols;
   FILE *inFile1, *inFile2;
   myValue** result;

   //open files to be read into 2d arrays
   inFile1 = fopen(argv[1], "rb");
   inFile2 = fopen(argv[2], "rb");

   f1rows = malloc(sizeof(int));
   f1cols = malloc(sizeof(int));
   f2rows = malloc(sizeof(int));
   f2cols = malloc(sizeof(int));
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

void mmWrite(myValue **result, int resultRow, int resultCol) {
   FILE *output;
   int i, j;

   output = fopen("result.out", "w");

   for(i =0; i < resultRow; i++) {
      for(j = 0; j < resultCol; j++) {
         fprintf(output, "%.2f ", result[i][j]);
      }
      fprintf(output, "\n");
   }
   fclose(output);
   free(*result);
   free(result);
}


myValue** mmRead(FILE *inFile1, FILE*inFile2, int* f1rows, int* f1cols,
      int* f2rows, int* f2cols) {
   myValue** mat1, **mat2, **result;
   myValue* mat1Ex, *mat2Ex;
   int i, j;
   
   //allocate memory for storing infile values
   mat1 = calloc(*f1rows, sizeof(myValue*));
   mat1Ex = calloc(*f1rows * (*f1cols), sizeof(myValue));
   for(i=0; i < (*f1rows); i++) {
      mat1[i] = mat1Ex + (i * (*f1cols));
   }
   
   mat2 = calloc(*f2rows, sizeof(myValue*));
   mat2Ex = calloc(*f2rows * (*f2cols), sizeof(myValue));
   for(i=0; i < (*f2rows); i++) {
     mat2[i] = mat2Ex + (i * (*f2cols));
   } 


   //read matrices into now allocated 2d arrays.
   for(i = 0; i < (*f1rows); i++) {
      for(j = 0; j < (*f1cols); j++) {
         fread(&mat1[i][j], 4, 1, inFile1);
      }
   }

  if(COLUMNMAJOR == 5) {
      for(i = 0; i < (*f2rows); i++) {
         for(j = 0; j < (*f2cols); j++) {
            fread(&mat2[i][j], 4, 1, inFile2);
         }
      }
   }

   //colmajor storage
   else if(COLUMNMAJOR == 25) {
      //rewind(inFile2);
      for(i = 0; i < (*f2cols); i++) {
         for(j =0; j < (*f2rows); j++) {
            fseek(inFile2,4*(2+(j * (*f2cols)+i)),SEEK_SET);
            fread(&mat2[i][j], 4, 1, inFile2);
            //rewind(inFile2);
         }
      }
   }

   fclose(inFile1);
   fclose(inFile2);

   //call mmcompute
   result = mmCompute(mat1, mat2, *f1rows, *f1cols, *f2rows, *f2cols);
   free(mat1);
   free(mat1Ex);
   free(mat2);
   free(mat2Ex);
   return result;

}

myValue** mmCompute(myValue** mat1, myValue** mat2, int m, int n, int p, int q) {
   myValue** result;
   myValue* resultEx;
   myValue sum;
   int i, j, k;
   sum = 0;

   //allocate result matrix memory
   result = calloc(m, sizeof(myValue*));
   resultEx = calloc(m * q, sizeof(myValue));
   for(i = 0; i < m; i++) {
      result[i] = resultEx + (i * q);
   }

   //calculate. 
   //found at http://www.programmingsimplified.com/c-program-multiply-matrices
   for(i = 0; i < m; i++) {
      for(j = 0; j < q; j++) {
         for(k = 0; k < p; k++) {
            sum = sum + mat1[i][k] * mat2[k][j];
         }
         result[i][j] = sum;
         sum =0;
      }
   }
   return result;
}



















