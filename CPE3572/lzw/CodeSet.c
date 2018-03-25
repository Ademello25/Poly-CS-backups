#include <stdio.h>
#include <assert.h>
#include <string.h>
#include "SmartAlloc.h"
#include "CodeSet.h"

typedef struct CodeEntry {
   char value;
   struct CodeEntry *prev;
   int gets;
   char *got;
} CodeEntry;

typedef struct CodeSet {
   CodeEntry *dynArray;
   int rem, size;
} CodeSet;


void *CreateCodeSet(int numCodes) {
   CodeSet *codeSet = malloc(sizeof(CodeSet));

   codeSet->rem = numCodes;
   codeSet->dynArray =  malloc(numCodes * sizeof(CodeEntry));
   codeSet->size = 0;
   return codeSet;
}

int NewCode(void *codeSet, char val) {
   CodeSet *temp = codeSet;
   int result;

   temp->dynArray[temp->size].value = val;
   temp->dynArray[temp->size].prev = NULL;
   temp->dynArray[temp->size].gets = 0;
   temp->dynArray[temp->size].got = NULL;

   result = temp->size;
   temp->size++;
   temp->rem--;
   return result;
}

int ExtendCode(void *codeSet, int oldCode) {
   CodeSet *temp = codeSet;
   int result;

   result = NewCode(temp, 0);
   temp->dynArray[result].prev = &temp->dynArray[oldCode];
   return result;
}

void SetSuffix(void *codeSet, int code, char suffix) {
   CodeSet *temp = codeSet;
   CodeEntry *valueSetter = &temp->dynArray[code];
   int size;

   if (temp->dynArray[code].gets > 0) {
      for (size = 0; valueSetter->prev; size++) {
         valueSetter = valueSetter->prev;
      }
      temp->dynArray[code].got[size] = suffix;
   }
   temp->dynArray[code].value = suffix;
}

Code GetCode(void *codeSet, int code) {
   CodeSet *cSet = codeSet;
   Code temp;
   int size;

   CodeEntry *sizeFinder = &cSet->dynArray[code];

   for (size = 1; sizeFinder->prev; size++) {
      sizeFinder = sizeFinder->prev;
   }

   if (cSet->dynArray[code].got && (cSet->dynArray[code].gets > 0)) {
      temp.data = cSet->dynArray[code].got;
      temp.size = size;
      cSet->dynArray[code].gets++;
      return temp;
   }

   cSet->dynArray[code].got = malloc(size);
   temp.size = size;
   sizeFinder = &cSet->dynArray[code];

   while (sizeFinder) {
      cSet->dynArray[code].got[--size] = sizeFinder->value;
      sizeFinder = sizeFinder->prev;
   }

   temp.data = cSet->dynArray[code].got;
   cSet->dynArray[code].gets++;
   return temp;
}

void FreeCode(void *codeSet, int code) {
   CodeSet *cSet = codeSet;

   cSet->dynArray[code].gets--;
   if (cSet->dynArray[code].gets == 0) {
      free(cSet->dynArray[code].got);
      cSet->dynArray[code].gets = 0;
   }
}
 
void DestroyCodeSet(void *codeSet) {
   CodeSet *temp = codeSet;
   int i, size = temp->size;
   CodeEntry *deleter = temp->dynArray;
  
   while (size--) {
      if (temp->dynArray[size].gets > 0)
         free(deleter[size].got);
   }
   
   free(temp->dynArray);
   free(temp);
}   
