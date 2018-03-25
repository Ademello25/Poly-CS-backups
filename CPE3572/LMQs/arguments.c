#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "SmartAlloc.h"

void printArgs(char **args) {
   while (*args) {
      printf("%s\n", *args++);
   }
}

void burnArgs(char **args, int argc) {
   while (argc--) {
      **args = 'X';
      *args++ = NULL;
   }
}

void copyArgs(char ***dup, char ***argv) {
   int count = 0;
   char **argvCopy = *argv, **dupCopy;
            
   while (*argvCopy++)
      ++count;
   argvCopy = *argv+1;
                  
   *dup = malloc(sizeof(char *) * count--);
   dupCopy = *dup;
   while (count--) {
      *dupCopy = malloc(strlen(*argvCopy) + 1);
      //printf("   arg: %s @ %u\n", *argvCopy, argvCopy);
      strcpy(*dupCopy++, *argvCopy++);
   }
   *dupCopy = NULL;
                              
}


void main(int argc, char **argv) {
   char **dupArgs;
   copyArgs(&dupArgs, &argv);
   burnArgs(argv, argc);
   printArgs(dupArgs);
   printf("Space: %d\n", report_space());
}
