#include <stdio.h>
#include <string.h>
#include "SmartAlloc.h"

#include "StrInsert.h"

static const int BUFFER = 100;
static const int STACKSTOMP = 1000;

void StompStack() {
  long content[STACKSTOMP];
  int i;

  for (i = 0; i < STACKSTOMP; i++)
     content[i] = 0xDEADBEEF;
}

void DoTest(char *str, char *insert, char where)
{
   char * temp = StrInsert(str, insert, where);
   StompStack();

   printf("'%s' with '%s' inserted at '%c' is '%s'\n", str, insert, where, temp);
   printf("%d bytes allocated\n", report_space());
   free(temp);

   if (report_space() != 0)
      printf("Expected 0 space, found %d\n", report_space());

   printf("\n");
}

int main() {
   char str[BUFFER], insert[BUFFER], where;

   while (EOF != scanf("%s %s %c", str, insert, &where)) {
      DoTest(str, insert, where);
   }

   return 0;
}
