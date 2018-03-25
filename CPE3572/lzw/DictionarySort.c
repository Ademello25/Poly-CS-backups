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

int main() {
   char str[LEN + 1];
   Node *head = NULL, *tmp, **blk;
   int cnt, size = 0;
   
   while (EOF != scanf(" %s", str)) {
      tmp = calloc(1, sizeof(Node));
      memmove(tmp->word, str, LEN + 1);
      tmp->next = head;
      head = tmp;
      size++;
   }
   
   if (size) {
      blk = calloc(size, sizeof(Node *));
      for (cnt = 0; cnt < size; cnt++) {
         blk[cnt] = malloc(sizeof(Node*));
         memmove(blk[cnt], tmp, strlen(tmp->word));
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
