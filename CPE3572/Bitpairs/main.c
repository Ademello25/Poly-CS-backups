#include <stdio.h>

#define BITS_PER_SHORT 16

// remember to check edge cases
void main() {
   int odd = 0, rtn = 0, cnt, i;
   unsigned short val;
   
   while (EOF != scanf("%hu", &val)) {
      if (val >> BITS_PER_SHORT - 1 && odd) {
         rtn++;
      }
      odd = val % 2;
      for (cnt = i = 0; i < BITS_PER_SHORT; i++) {
         if (val & 1) {
            cnt++;
         }
         else
            cnt = 0;
         if (cnt > 1)
            rtn++;
         val = val >> 1;
      }
   }
   printf("%hu pairs\n", rtn);
}
