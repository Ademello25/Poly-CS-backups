#include "Problem2.h"

#define IDIM 6
#define DDIM 5
#define SDIM 3

int main() {
   int ndx, iVal = 42, iArr[] = {1, 2, 3, 42, 4, 42};
   double dVal = 3.1415, dArr[] = {3, 5, 7, 9, 11};
   GPARecord sVal = {42, 4.2}, sArr[] = {{21, 2.1}, {19, 3.7}, {42, 4.2}};

   printf("iArr %d\n", findElement(iArr, IDIM, &iVal, sizeof(int)));
   printf("dArr %d\n", findElement(dArr, DDIM, &dVal, sizeof(double)));
   printf("sArr %d\n", findElement(sArr, SDIM, &sVal, sizeof(GPARecord)));

   return 0;
}
