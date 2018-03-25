#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <math.h>

#define CHUNK_SIZE 65536
#define WORD_SIZE 16
#define BUFFER_SIZE 75
#define HEADER_SIZE() \
   ((int)ceil((double)sizeof(MemNode) / WORD_SIZE) * WORD_SIZE)

/*HEADER_SIZE() is a macro that guarentees blocks of 16
 * by dividing the value that you want by 16 and rounding up to 1
 * (or more) then multiplying by 16 and creating your value */
typedef struct MemNode {
   int isFree;
   int size;
   struct MemNode *next;
} MemNode;

static MemNode *head = NULL;

void* malloc(size_t size) {
   MemNode *cursor, *temp;
   int overflow;
   int alignedSize = ceil((double)size / WORD_SIZE) * WORD_SIZE;
   void *blkStart;

   /* This case occurs as the base case when nothing has previously 
    * been mallocd */
   if(!head) {
      if(0 > (int)(overflow = alignedSize - CHUNK_SIZE + HEADER_SIZE())) {
         overflow = 0;
      }
      if((void*)-1 == (head = sbrk(CHUNK_SIZE + overflow))) {
         return NULL;
      }
      head->isFree = 1;
      head->size = CHUNK_SIZE + overflow - HEADER_SIZE();
      head->next = NULL;
   }

   cursor = head;
   while(cursor->next && (!cursor->isFree || cursor->size < alignedSize)) {
      cursor = cursor->next;
   }
   /*in the case that you can find data that has been freed use it*/
   if(cursor->next) {
      temp = cursor->next;
      cursor->next = (MemNode*)((char*)cursor+HEADER_SIZE()+alignedSize);
         
      if(cursor->next < temp) {
         cursor->next->size = cursor->size - alignedSize - HEADER_SIZE();
         cursor->next->next = temp;
         cursor->next->isFree = 1;
      }
   }
   /* making new space to be used by malloc*/
   else {
      if(0>(overflow = ceil((double)(alignedSize - CHUNK_SIZE + 
         HEADER_SIZE()) / WORD_SIZE) * WORD_SIZE)){
      overflow = 0;
      }
      cursor->next = (MemNode*)((char*)cursor+HEADER_SIZE()+alignedSize);
      if((char*)cursor->next + HEADER_SIZE() > (char*)sbrk(0)) {
         if((void*)-1 ==sbrk(CHUNK_SIZE + overflow - cursor->size)) {
            return NULL;
         }
      }
      cursor->next->next = NULL;
      cursor->next->isFree = 1;
      cursor->next->size = (char*)sbrk(0) - (char*)cursor->next-HEADER_SIZE();
   }

   cursor->size = alignedSize;
   cursor->isFree = 0;
   blkStart = (char*)cursor + HEADER_SIZE();

   return blkStart;
}
/*calloc just calls malloc and makes all the data 0 good for arrays*/
void* calloc(size_t nmemb, size_t size) {
   int alignedSize = ceil((double)(size * nmemb) / WORD_SIZE) *WORD_SIZE;
   void *blkStart = malloc(alignedSize);

   memset(blkStart, 0, alignedSize);

   return blkStart;
}

/* realloc the pointer given by the user into memory of size_t size.
 * assumes blocks of free memory are merged. Merge free blocks to make sure
 * this holds true*/
void* realloc(void*ptr, size_t size) {
   MemNode *cursor = head, *temp;
   int alignedSize = ceil((double)size / WORD_SIZE) * WORD_SIZE;
   int i;
   void *newBlk;

   if(!ptr) {
      return malloc(size);
   }

   while(cursor && (void*)cursor->next < ptr) {
      cursor = cursor->next;
   }

   if(!cursor || (char*)cursor + HEADER_SIZE() > (char*)ptr) {
      fprintf(stderr, "pointer was never malloced, realloc won't work here",
            ptr);
      exit(EXIT_FAILURE);
   }
   /*working in place to use neighboring space*/
   if(cursor->size > size || (cursor->next->isFree && cursor->size + 
            cursor->next->size + HEADER_SIZE() >= alignedSize)) {
      temp = cursor->next;
      cursor->next = (MemNode*)((char*)cursor+HEADER_SIZE()+alignedSize);

      if(cursor->next < temp) {
            cursor->next->size = cursor->size - alignedSize - HEADER_SIZE();
            if(temp->isFree) {
                  cursor->next->size += temp->size + HEADER_SIZE();
                  cursor->next->next = temp->next;
            }
            else {
               cursor->next->isFree = 1;
            }
            cursor->next->isFree = 1;
      }
      else if(cursor->next < temp->next) {
         cursor->next->size = temp->size - alignedSize + cursor->size;
         cursor->next = temp->next;
      }
   }
   /* start work in a new block */
   else {
      if(NULL == (newBlk = malloc(alignedSize))) {
         return NULL;
      }
      memcpy(newBlk, (char*)cursor + HEADER_SIZE(), cursor->size);
      free(ptr);
      cursor = (MemNode*)((char*)newBlk - HEADER_SIZE());
   }

   cursor->size = alignedSize;
   cursor->isFree = 0;

   return (char*)cursor + HEADER_SIZE();
}

void free(void *ptr) {
   MemNode *cursor = head, *last = NULL;
   
   if(!ptr) {
      return;
   }
   
   while(cursor && (void*)cursor->next < ptr) {
      last = cursor;
      cursor = cursor->next;
   }

   if(!cursor || (char*)cursor + HEADER_SIZE() > (char*)ptr) {
      fprintf(stderr, "ptr not allocated, cannot free");
      exit(EXIT_FAILURE);
   }

   cursor->isFree = 1;

   if(last && last->isFree) {
      last->size += cursor->size + HEADER_SIZE();
      last->next = cursor->next;
      cursor = last;
   }
   if(cursor->next && cursor->next->isFree) {
      cursor->size += cursor->next->size + HEADER_SIZE();
      cursor->next = cursor->next->next;
   }
}
                  

