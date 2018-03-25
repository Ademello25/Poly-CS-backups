#include <stdio.h>

void RemoveBlanks(char* input);

void RemoveBlanks(char* input){

   char* ptr = input;
   while(*input = *ptr++) {
      if(*input++ == ' ') {
         input--;
      }
   }
}

int main() {
   char s1[] = "  ", s2[] = "", s3[] = "a  b  c  ", s4[] = " ab ",
   s5[] = " aaab  ab abbb ";

   RemoveBlanks(s1);
   RemoveBlanks(s2);
   RemoveBlanks(s3);
   RemoveBlanks(s4);
   RemoveBlanks(s5);

   printf("|%s| |%s| |%s| |%s| |%s|\n", s1, s2, s3, s4, s5);

   return 0;
}
