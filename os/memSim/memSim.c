#include "memSim.h"

int maxFrames;
tlbIndex* tlb;
page* pTable;

int8_t readByte(uint8_t frameNum, uint8_t pOff, FILE * backFile) {
   uint8_t resultByte = 0;

   fseek(backFile, pOff + (256 * frameNum), SEEK_SET);
   fread(&resultByte, 1, 1, backFile);

   return resultByte;
}

u_char* readFrame(uint8_t frameNum, FILE * backFile) {
   u_char* resultFrame = calloc(256, sizeof(u_char));

   fseek(backFile, (256 * frameNum), SEEK_SET);
   fread(resultFrame, 1, 256, backFile);

   return resultFrame;
}

void updateTlb(uint8_t pageNum, uint8_t frameNum) {
   int i, found;
   static int count;
   
   for(i =0; i < 16; i++) {
      if(tlb[i].used == 0) {
         tlb[i].used = 1;
         tlb[i].tlbPageNum = pageNum;
         tlb[i].tlbFrameNum = frameNum;
         found = 1;
         break;
      }
   }
   if (found == 0) {
      tlb[count % 16].tlbPageNum = pageNum;
      tlb[count % 16].tlbFrameNum = frameNum;
      count++;
   }


}

void inputReader(FILE * inFile) {
   int inAddr, i, frameNum = 0, tlbMiss = 0;
   int tlbHits =0, pageFaults = 0, tlbMissCount = 0;
   int8_t resultByte;
   uint8_t pNum, pOff;
   uint16_t mask = 0x00FF;
   u_char* resultFrame;
   double tempResult = 0;
   FILE * backFile = fopen("BACKING_STORE.bin", "rw");


   while(fscanf(inFile, "%d", &inAddr) != EOF) {
      pNum = (inAddr & (mask << 8)) >> 8;
      pOff = inAddr & mask;
   
      for(i = 0; i < 16; i++) {
         if(pNum == tlb[i].tlbPageNum) {
            resultFrame = readFrame(tlb[i].tlbFrameNum, backFile);
            resultByte = readByte(tlb[i].tlbFrameNum, pOff, backFile);
            tlbHits++;
            tlbMiss = 0;
            break;
         }
         else {
            tlbMiss = 1;
         }
      }

      if(tlbMiss == 1) {
         tlbMissCount++;
         if(pTable[pNum].valid != 0) {
            resultByte = readByte(frameNum, pOff, backFile); 
            resultFrame = readFrame(frameNum, backFile);
         }
         else {
            //update the page table.
            pTable[pNum].valid = 1;
            pTable[pNum].frameNum = frameNum;
            resultByte = readByte(frameNum, pOff, backFile);
            resultFrame = readFrame(frameNum, backFile);
            memcpy(pTable[pNum].frame, resultFrame, 256); 
            pageFaults++;

            //update the tlb
            updateTlb(pNum, frameNum);
         }
      }
      //output stuff
      printf("%d, %d, %d, ", inAddr, resultByte, frameNum);
      for(i =0; i < 256; i++) {
         printf("%X", resultFrame[i]);
      }
      printf("\n\n");
   
      tlbMiss = 0;
      frameNum++;
      if (frameNum == maxFrames)
         frameNum = 0;
   }

   printf("Number of Translated Addresses = %d\n", frameNum);
   printf("Page Faults = %d\n", pageFaults);
   tempResult = (double)pageFaults / (double)frameNum;
   printf("Page Fault Rate = %.3f\n", tempResult);
   printf("TLB Hits = %d\n", tlbHits);
   printf("TLB Misses = %d\n", tlbMissCount);
   tempResult = (double)tlbHits / (double)frameNum;
   printf("TLB Hit Rate = %.3f\n", tempResult);

}

int main(int argc, char **argv) {
   FILE * inFile;
   tlb = calloc(16, sizeof(tlbIndex));
   pTable = calloc(256, sizeof(page));

   if(argc < 2) {
      printf("Wrong # of arguments\n");
      exit(-1);
   }

   if(argc > 2) {
      maxFrames = atoi(argv[2]);
   }
   else
      maxFrames = 256;

   inFile = fopen(argv[1], "r");
   inputReader(inFile);

   free(tlb);
   free(pTable);
   return 0;
}


