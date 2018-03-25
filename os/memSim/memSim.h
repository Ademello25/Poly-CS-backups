#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
#include <string.h>

typedef struct page {
   char valid;
   uint8_t frameNum;
   u_char frame[256];
}page;

typedef struct tlbIndex {
   int used;
   uint8_t tlbPageNum;
   uint8_t tlbFrameNum;
} tlbIndex;
