#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/mman.h>
#include <sys/types.h>
#include <omp.h>
#include <stdint.h>

#ifdef DOUBLE
   #define ISDOUBLE 5
   typedef double myValue;
#else
   #define ISDOUBLE 0
   typedef float myValue;
#endif

myValue* mmRead(FILE* inFile1, FILE* inFile2, int* f1rows, int* f1cols,
      int* f2rows, int*f2cols);
void mmWrite(myValue *result, int resultRow, int resultCol);
myValue* mmCompute(myValue* mat1, myValue* mat2, int m, int n, int p, int q);
#define SIZEOFDATA 4
#define DATAOFFSET 2
