#include <stdio.h>
#include <stdlib.h>

int main() {

   int count = 0, curr = 0, i, result = 1, limit = 0;
   int *arr = calloc(50, sizeof(int));
   
   while(scanf("%d", &curr) != EOF) {
      if(curr == -1)
         break;
      count++;
      arr[count] = curr;
   }
   limit = count;
   if(count < 3)
      printf("Two or fewer numbers entered.");
   else {
      for(i = 0; i < (limit - 2); i++) {
         if(arr[count] != (arr[count-1] + arr[count-2])){
               result = 0;
               break;
         }
         count--;
      }
      if(result == 1)
         printf("It's fibonacci!");
      else
         printf("Nope!");
   }

   return 0;
}
