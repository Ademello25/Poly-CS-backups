#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "SmartAlloc.h"

#define IDIM1 10
#define IDIM2 4

int* insert(int* Blk, int* toInsert, int dim1, int dim2, int offset){

   int i;
   int* result = malloc((dim1 + dim2)* sizeof(int));

   memmove(result, Blk, sizeof(int) * offset);
   
   memmove(result + offset, toInsert, sizeof(int) * dim2);

   memmove(result +offset + dim2, Blk + offset, sizeof(int) * (dim1 - offset));

   free(Blk);
   return result;
}

void scanInts(int *arr, int size) {
      int i;
         
         for (i = 0; i < size; i++)
                  scanf("%d", arr+i);
}

void printInts(char *name, int *arr, int size) {
      int i;
         
         printf("%s:", name);
            for (i = 0; i < size; i++)
                     printf(" %d", arr[i]);
               printf("\n");
}

int main() {
      int *iArr1 = malloc(sizeof(int) * IDIM1);
         int *iArr2 = malloc(sizeof(int) * IDIM2);
            
            scanInts(iArr1, IDIM1);
               scanInts(iArr2, IDIM2);
                  iArr1 = insert(iArr1, iArr2, IDIM1, IDIM2, IDIM1/2);
                     iArr2 = insert(iArr2, iArr1, IDIM2, IDIM1 + IDIM2, IDIM2/2);

                        printInts("iArr1", iArr1, IDIM1 + IDIM2);
                           printInts("iArr2", iArr2, IDIM1 + 2*IDIM2);
                              
                              free(iArr1);
                                 free(iArr2);
                                    printf("Final space: %ld\n", report_space());
}
