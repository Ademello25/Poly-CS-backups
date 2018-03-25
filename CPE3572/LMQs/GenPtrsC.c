#include <stdio.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define IDIM 10
#define DDIM 8
#define FDIM 6


scanArray(void * dest, int dim, int eleSize, char * fmt) {
   while(dim--) {
      scanf(fmt, dest);
      dest = (char *)dest + eleSize;   
   }      
}


int main() {
   int ndx, iArr[IDIM];
   double dArr[DDIM];
   float fArr[FDIM];
  
   scanArray(iArr, IDIM, sizeof(int), "%d");
   scanArray(dArr, DDIM, sizeof(double), "%lf");
   scanArray(fArr, FDIM, sizeof(float), "%f");
                        
   printf("iArr:");
   for (ndx = 0; ndx < IDIM; ndx++)
      printf(" %d", iArr[ndx]);
   printf("\ndArr:");
   for (ndx = 0; ndx < DDIM; ndx++)
      printf(" %0.2f", dArr[ndx]);
   printf("\nfArr:");
   for (ndx = 0; ndx < FDIM; ndx++)
      printf(" %0.2f", fArr[ndx]);
   printf("\n");
}
