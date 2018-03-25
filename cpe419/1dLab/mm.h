#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/mman.h>
#include <sys/types.h>
#include </usr/local/cuda/include/cuda_runtime.h>
#include </usr/local/cuda/include/cublas_v2.h>

#define TILE_WIDTH 16

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
void mmGpuSetup(myValue* mat1, myValue* mat2, myValue* resultH,
      int m, int n, int p, int q);
__global__ void mmCompute(myValue* mat1D, myValue* mat2D, myValue* resultD, 
      int m, int n, int p, int q);
#define SIZEOFDATA 4
#define DATAOFFSET 2
