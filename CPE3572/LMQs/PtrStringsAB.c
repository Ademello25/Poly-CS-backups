#include <stdio.h>

#define strchr
#define strlen


void TrStr(char *target, char *fmt) {
   char *temp;
   for(;*target; target++) {
      for(temp = fmt; *temp && *target != *temp; temp += 2);

      if(*temp){
         *target = temp[1];
      }
   }
}


int main() {
   char str1[20], str2[20], *test = "";

   TrStr(test, "abcd");
   printf("Empty translates to |%s|\n", test);

   while (EOF != scanf("%s %s", str1, str2)) {
      TrStr(str1, "");
      printf("%s -> ", str1);
      TrStr(str1, str2);
      printf("%s\n", str1);
   }
                  
   return 0;
}
