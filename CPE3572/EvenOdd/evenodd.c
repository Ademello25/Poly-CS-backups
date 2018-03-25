#include <stdio.h>
#include "SmartAlloc.h"

typedef struct Node
{
      struct Node *next;
         int data;
} Node;

void ReadIntegers(Node **pOdd, Node **pEven) {
   Node **listHead, *temp;
   int val, done, swap = 1;

   *pOdd = *pEven = NULL;
   scanf(" %d", &val);
   while(val) {
      if(val % 2)
         listHead = pOdd;
      else
         listHead = pEven;

      temp = calloc(1, sizeof(Node));
      temp->data = val;

      if(*listHead)
         temp->next = *listHead;
      *listHead = temp;
      scanf(" %d", &val);
   }
   for(done = 0; done < 2; done++, swap = 1) {
      while (swap) {
         swap = 0;
         if(done)
            temp = *pEven;
         else
            temp = *pOdd;
         if (temp) {
            while (temp->next) {
               if (temp->data > temp->next->data) {
                  val = temp->data;
                  temp->data = temp->next->data;
                  temp->next->data = val;
                  swap = 1;
               }
               temp = temp->next;
            }
         }
      }
   }
}

int main() {
   Node *odd, *even;
   Node *temp;
            
   ReadIntegers(&odd, &even);
               
   temp = odd;
   printf("Odd values:");
   while (temp) {
      printf(" %d", temp->data);
      temp = temp->next;
   }
                        
   temp = even;
   printf("\nEven values:");
   while (temp) {
      printf(" %d", temp->data);
      temp = temp->next;
   }
   printf("\nUsed %d bytes.\n", report_space());
}
