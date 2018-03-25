#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/mman.h>
#include <sys/types.h>

#ifdef DOUBLE
   typedef double myValue;
#else
   typedef float myValue;
#endif

#ifdef COLMAJ
   #define COLUMNMAJOR 25
#else
   #define COLUMNMAJOR 5
#endif

myValue** mmRead(FILE* inFile1, FILE* inFile2, int* f1rows, int* f1cols,
      int* f2rows, int*f2cols);
void mmWrite(myValue **result, int resultRow, int resultCol);
myValue** mmCompute(myValue** mat1, myValue** mat2, int m, int n, int p, int q);
#define SIZEOFDATA 4
