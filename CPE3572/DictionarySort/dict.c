#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define LEN 20

typedef struct Node {
   char word[LEN+1];
   struct Node *next;
} Node;

Node *order(Node *root, Node *node) {
}

int main() {
   int i, cnt = 0;
   Node *root, *temp1, *temp2, *scanin= calloc(sizeof(Node), 1);
   char enc;
   while (scanf("%s", scanin->word) != EOF) {   
      cnt++;
      if (root) {
         if (strcmp(root->word, scanin->word) > 0) {
            scanin->next = root;
            root = scanin;
         }
         else {
            temp1 = root; temp2 = root->next; 
            i = i;
            while (temp1 && temp2 && i) {
               if (strcmp(scanin->word, temp2->word) < 0) {
                  scanin->next = temp2;
                  temp1->next = scanin;
                  i = 0;
               }
               else {
                  temp1 = temp2;
                  temp2 = temp2->next;
               }
            }
            if (i)
               temp1->next = scanin;
         }
            
      }
      else 
         root = scanin;
      scanin = calloc(sizeof(Node), 1);
   
      temp1 = root;
      for (i = 0; i < cnt; i++) {
         //printf("%s ", temp1->word);
         temp1 = temp1->next;
      }
      temp1->next = NULL;
      //printf("\n");      

   }
   for (i = 0; i < cnt - 1; i++) {
      printf("%s ", root->word);
      root = root->next;
   }
   printf("%s\n", root->word);
}
