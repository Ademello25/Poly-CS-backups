#include <stdio.h>
#include <string.h>

#include "SmartAlloc.h"
#include "MyLib.h"
#include "LZWCmp.h"

#define TRACE_SPACE 0x10
#define RECYCLE_LIMIT 4096

typedef struct SinkState {
   FILE *outFile;
   int newLine;
} SinkState;

void CodeWrite(void *instate, UInt code, int done) {
   SinkState *state = instate;
   
   state->newLine++;
   if (!done) {
      fprintf(state->outFile, "%0*X", BITS_PER_BYTE, code);
      
      if (state->newLine == BITS_PER_BYTE) {
         fprintf(state->outFile, "\n");
         state->newLine = 0;
      }
      else
         fprintf(state->outFile, " ");
   }
   else {
      fprintf(state->outFile, "\n");
      state->newLine = 0;
   }

}

static int FlagSet(char *args) { 
   int flags = 0;
   
   if (*args++ == '-') {
      while (*args) {
         if (*args == 't')
            flags |= TRACE_TREE;
         else if (*args == 'b')
            flags |= TRACE_BUMPS;
         else if (*args == 'r')
            flags |= TRACE_RECYCLES;
         else if (*args == 'c')
            flags |= TRACE_CODES;
         else if (*args == 's')
            flags |= TRACE_SPACE;
         else
            printf("Bad argument: %c\n", *args);
         
         args++;
      }
   }
   
   return flags;
}

int main(int argc, char *argv[]) {
   int flags, symbol, reportSpace = FALSE;
   char **tempArgs;
   SinkState state;
   FILE *inFile;
   LZWCmp cmp;
   char *outFile, *fileEnd = ".Z";

   state.newLine = 0;
   state.outFile = NULL;
   
   if (argc > 1) {
      tempArgs = ++argv;
      flags = FlagSet(*tempArgs);
      
      if (flags & TRACE_SPACE)
         reportSpace = TRUE;
      if (flags)
         tempArgs++;
      
      while (*tempArgs) {
         if (**tempArgs == '-') {
            tempArgs++;
         }
         else {
            inFile = fopen(*tempArgs, "r");
            if (inFile) {
               outFile = malloc(strlen(*tempArgs) + strlen(fileEnd) + 1);
               memmove(outFile, *tempArgs, strlen(*tempArgs));
               memmove(outFile + strlen(*tempArgs), fileEnd,
                       strlen(fileEnd) + 1);
            
               state.outFile = fopen(outFile, "w");
               LZWCmpInit(&cmp, CodeWrite, &state, RECYCLE_LIMIT, flags);
          
               while (EOF != (symbol = fgetc(inFile))) {
                  LZWCmpEncode(&cmp, symbol);
               }
               fclose(inFile);
            
               LZWCmpStop(&cmp);
               fclose(state.outFile);
            
               if (reportSpace) {
                  printf("Space after LZWCmpStop for %s: %ld\n", *tempArgs,
                   report_space());
               }
            
               LZWCmpDestruct(&cmp);
               free(outFile);
            }
            else{
               if (**tempArgs != '-') {
                  printf("Cannot open %s\n", *tempArgs);
               }
            }
            tempArgs++;
         }
      }
   }
   LZWCmpClearFreelist();
   
   if (reportSpace)
      printf("Final space: %ld\n", report_space());
   
   return 0;
}

