#include "Problem2.h"

int findElement(void *vArr, int dim, void *vVal, int vBytes) {
   
   void *temp = vArr;
   int i =0, result;
   while(temp && i < dim) {
      if(*(int *)temp == *(int *)vVal)
         result = 0;
      if(result == 0) {
         return i;
      }
      temp = (char *)temp + vBytes;
      i++;
   }

   return -1;
}
