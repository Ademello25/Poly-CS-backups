#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define LEN 20

typedef struct Node {
   char word[LEN+1];
   struct Node *next;
} Node;

int myCmp(const void *n1, const void *n2) {
   return strcmp((*(Node**)n1)->word, (*(Node**)n2)->word);
}


/*int main() {
   char str[LEN + 1];
   Node **arr = NULL;
   int size = 0;
   
   while (EOF != scanf(" %s", str)) {
      *arr = realloc(*arr, sizeof(char*));
      *arr += size;
      *arr = strdup(str);
      size++;
   }
   
   qsort(arr, size, sizeof(char *), myCmp);
   
   while (size--) {
      printf("%s ",arr*);
      arr++;
   }
}*/
void printEm(Node *head) {
   while (head->next) {
      printf("%s ", head->word);
      head = head->next;
   }
   printf("%s\n", head->word);
}

int main() {
   char str[LEN + 1];
   Node *head = NULL, *tmp, **blk;
   int cnt, size = 0;
   
   while (EOF != scanf(" %s", str)) {
      tmp = calloc(1, sizeof(Node));
      memmove(tmp->word, str, LEN);
      tmp->next = head;
      head = tmp;
      size++;
   }
   printEm(head);
   
   if (size) {
      blk = calloc(size, sizeof(Node *));
      for (cnt = 0; cnt < size; cnt++) {
         blk[cnt] = malloc(sizeof(Node*));
         memmove(blk[cnt], tmp, LEN + 1);
         tmp = tmp->next;
      }
      qsort(blk, size, sizeof(Node*), myCmp);
      
      head = *blk;
      for (cnt = 0; cnt < size-1; cnt++) {
         blk[cnt]->next = blk[cnt+1];
      }
      blk[cnt]->next = NULL;
      
      while (head->next) {
         printf("%s ", head->word);
         head = head->next;
      }
      printf("%s\n", head->word);
   }
}
