#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define IDIM 10
#define SDIM 6
#define DDIM 12

typedef struct {
      int id;
         float gpa;
} GPARecord;

void fillArray(void *arr, int dim, void *val, int eleSize){
   while(dim--){
      memcpy(arr, val, eleSize);
      arr = (char *)arr + eleSize;
   }
}


int main() {
   int ndx, iVal = 42, *iArr = malloc(sizeof(int) * IDIM);
   double dVal = 3.1415, *dArr = malloc(sizeof(double) * DDIM);
   GPARecord sVal = {42, 4.2}, *sArr = malloc(sizeof(GPARecord) * SDIM);

   fillArray(iArr, IDIM, &iVal, sizeof(int));
   fillArray(dArr, DDIM, &dVal, sizeof(double));
   fillArray(sArr, SDIM, &sVal, sizeof(GPARecord));
                        
   printf("iArr:");
   for (ndx = 0; ndx < IDIM; ndx++)
      printf(" %d", iArr[ndx]);
   printf("\ndArr:");
   for (ndx = 0; ndx < DDIM; ndx++)
      printf(" %0.2f", dArr[ndx]);
   printf("\nsArr:");
   for (ndx = 0; ndx < SDIM; ndx++)
      printf(" {%d, %0.2f}", sArr[ndx].id, sArr[ndx].gpa);
   printf("\n");
}
