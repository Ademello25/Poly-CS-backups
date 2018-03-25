#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "SmartAlloc.h"
#include "LZWCmp.h"
#include "CodeSet.h"
#include "MyLib.h"

#define BITS_PER_INT 32

static TreeNode *FreeList = NULL;
static int NumEntries = 0, NumRuns = 0;

static TreeNode *NewNode(int cNum) {
   TreeNode *result;
   
   if (FreeList != NULL) {
      result = FreeList;
      FreeList = result->right;
   }
   else
      result = calloc(1, sizeof(TreeNode));
   
   result->cNum = cNum;
   result->left = NULL;
   result->right = NULL;
   
   return result;
}

static void FreeNode(TreeNode *toFree) {
   toFree->right = FreeList;
   FreeList = toFree;
}

static void RecycleAll(TreeNode *freeHead) {
   
   if (freeHead == NULL)
      return;
      
   RecycleAll(freeHead->left);
   RecycleAll(freeHead->right);
   freeHead->right = FreeList;
   FreeList = freeHead;
}

static int CodeCompare(LZWCmp *cmp, Code toAdd, int other) {
   int result, size1, size2;
   Code otherTemp = GetCode(cmp->cst, other);
   
   size1 = toAdd.size;
   size2 = otherTemp.size; 
   
   if (size1 < size2)
      result = memcmp(toAdd.data, otherTemp.data, size1);
   else
      result = memcmp(toAdd.data, otherTemp.data, size2);
   
   FreeCode(cmp->cst, other);
   
   if (result == 0)
      return (size1 - size2);
   else
      return result;
}

static int BstSearchCode(LZWCmp *cmp, Code toFind) {
   int result;
   
   while (TRUE) {
      result = CodeCompare(cmp, toFind, cmp->curLoc->cNum);
      if (result == 0) {
         return TRUE;
      }
      else if (result < 0) {
         if (cmp->curLoc->left == NULL) {
            return TRUE;
         }
         else {
            cmp->curLoc = cmp->curLoc->left;
         }
      }
      else if (result > 0) {
         if (cmp->curLoc->right == NULL) {
            return TRUE;
         }
         else {
            cmp->curLoc = cmp->curLoc->right;
         }
      }
   }
}

static int CodeIntCompare(LZWCmp *cmp, int toAdd, int other) {
   int result, size1, size2;
   Code toAddTemp = GetCode(cmp->cst, toAdd);
   Code otherTemp = GetCode(cmp->cst, other);
   
   size1 = toAddTemp.size;
   size2 = otherTemp.size; 

   if (size1 < size2)
      result = memcmp(toAddTemp.data, otherTemp.data, size1);
   else
      result = memcmp(toAddTemp.data, otherTemp.data, size2);

   FreeCode(cmp->cst, toAdd);
   FreeCode(cmp->cst, other);

   if (result == 0)
      return (size1 - size2);
   else
      return result;
}


static void BstAdd(LZWCmp *cmp, TreeNode **head, int val) {
   TreeNode *temp = NULL;

   if (!(*head)) {
      temp = NewNode(val);
      *head = temp;
   }
   else if (CodeIntCompare(cmp, val, (*head)->cNum) < 0) {
      BstAdd(cmp, &(*head)->left, val);
   }
   else if (CodeIntCompare(cmp, val, (*head)->cNum) > 0) {
      BstAdd(cmp, &(*head)->right, val);
   }

}

static void PackBits(LZWCmp *cmp, UInt symbol) {
   int leftOver, shift = BITS_PER_INT - cmp->bitsUsed - cmp->numBits;
   
   if (shift > 0) {
      cmp->nextInt |= symbol << shift;
   }
   else {
      cmp->nextInt |= symbol >> abs(shift);
   }
   
   leftOver = cmp->numBits - (BITS_PER_INT - cmp->bitsUsed);
   
   if (leftOver >= 0) {
      cmp->sink(cmp->sinkState, cmp->nextInt, 0);
      if (leftOver > 0) {
         cmp->nextInt = symbol << (BITS_PER_INT - leftOver);
         cmp->bitsUsed = leftOver;
      }
      else {
         cmp->nextInt = 0;
         cmp->bitsUsed = 0;
      }
      
   }
   else
      cmp->bitsUsed += cmp->numBits;
}

static void TraverseTree(LZWCmp *cmp, TreeNode *node) {
   int i;
   Code tempCode;
   
   if (node->left)
      TraverseTree(cmp, node->left);
      
   tempCode = GetCode(cmp->cst, node->cNum);
   
   printf("|");
   for (i = 0; i < tempCode.size; i++) {
      printf("%d", tempCode.data[i]);
         
      if (i != tempCode.size - 1) {
         printf(" ");
      }
   }
      
   FreeCode(cmp->cst, node->cNum);
   
   if (node->right)
      TraverseTree(cmp, node->right);
}

static void RecycleLZW(LZWCmp *cmp, int lastCode) {
   int nextInt;
   int bitsUsed;
   
   if (cmp->traceFlags & TRACE_RECYCLES) {
      printf("Recycling dictionary...\n");
   }
   
   nextInt = cmp->nextInt;
   bitsUsed = cmp->bitsUsed;
   
   RecycleAll(cmp->root);
   DestroyCodeSet(cmp->cst);
   
   free(cmp->pCode.data);
   
   LZWCmpInit(cmp, cmp->sink, cmp->sinkState, 
    cmp->recycleCode, cmp->traceFlags);
   cmp->nextInt = nextInt;
   cmp->bitsUsed = bitsUsed;
}

/* Initialize a LZWCmp given the CodeSink to which to send completed codes. */
void LZWCmpInit(LZWCmp *cmp, CodeSink sink, void *sinkState, int recycleCode,
 int traceFlags) {

   void *codeBase;
   int cnt;

   cmp->sink = sink;
   cmp->recycleCode = recycleCode;
   cmp->sinkState = sinkState;
   cmp->traceFlags = traceFlags;
   NumEntries = 0;
  
   cmp->nextInt = 0;
   cmp->bitsUsed = 0;
   
   cmp->numBits = BITS_PER_BYTE + 1;
   cmp->maxCode = (1 << cmp->numBits) - 1;
   cmp->pCode.data = calloc(1, SIZE_INCR);
   cmp->pCode.size = 0;
   cmp->pCodeLimit = SIZE_INCR; 

   cmp->root = NewNode(0);
   cmp->root->cNum = 0;
   cmp->curLoc = cmp->root;

   codeBase = CreateCodeSet(recycleCode + 1);
   cmp->cst = codeBase;
   NewCode(codeBase, 0);

   for (cnt = 1; cnt <= NUM_SYMS; cnt++) {
      NewCode(codeBase, cnt);
   }
   
   for (cnt = 1; cnt < NUM_SYMS; cnt++) {
      BstAdd(cmp, &cmp->root, cnt);
   } 
}

void BumpPcodeSize(LZWCmp *cmp) {
   char *pCodeTemp;
   
   cmp->pCodeLimit += SIZE_INCR;
   pCodeTemp = calloc(1, cmp->pCodeLimit);
   memmove(pCodeTemp, cmp->pCode.data, cmp->pCode.size);
   free(cmp->pCode.data);
   cmp->pCode.data = pCodeTemp;
}

void AddCodel(LZWCmp *cmp, int outCode, UChar sym) {
   int newNdx;
   TreeNode *newTreeNode;
   
   newNdx = ExtendCode(cmp->cst, outCode);
   SetSuffix(cmp->cst, newNdx, sym);
   
   if (cmp->traceFlags & TRACE_CODES) {
      printf("Sending code %d\n", outCode);
   }
   
   newTreeNode = NewNode(newNdx);
   
   PackBits(cmp, outCode);
   cmp->curLoc->left = newTreeNode;
   
   if (cmp->traceFlags & TRACE_TREE) {
      TraverseTree(cmp, cmp->root);
      printf("|\n\n");
   }
   
   cmp->curLoc = cmp->root;
   cmp->pCode.size = 0;
   
   if (newNdx == cmp->recycleCode) {
      RecycleLZW(cmp, sym);
   }
   else  if (newNdx > cmp->maxCode) {
      cmp->numBits++;
      cmp->maxCode = (1 << cmp->numBits) - 1;
      
      if (cmp->traceFlags & TRACE_BUMPS)
         printf("Bump numBits to %d\n", cmp->numBits);
   }
   
   LZWCmpEncode(cmp, sym);
}

void AddCoder(LZWCmp *cmp, int outCode, UChar sym) {
   int newNdx;
   TreeNode *newTreeNode;
   
   newNdx = ExtendCode(cmp->cst, outCode);
   SetSuffix(cmp->cst, newNdx, sym);
   newTreeNode = NewNode(newNdx);
   
   if (cmp->traceFlags & TRACE_CODES) {
      printf("Sending code %d\n", outCode);
   }
   
   PackBits(cmp, outCode);
   cmp->curLoc->right = newTreeNode;
   
   if (cmp->traceFlags & TRACE_TREE) {
      TraverseTree(cmp, cmp->root);
      printf("|\n\n");
   }
   
   cmp->curLoc = cmp->root;
   cmp->pCode.size = 0;
   
   if (newNdx == cmp->recycleCode) {
      RecycleLZW(cmp, sym);
   }
   else if (newNdx > cmp->maxCode) {
      cmp->numBits++;
      cmp->maxCode = (1 << cmp->numBits) - 1;
      
      if (cmp->traceFlags & TRACE_BUMPS)
         printf("Bump numBits to %d\n", cmp->numBits);
   }
   LZWCmpEncode(cmp, sym);
}

/* Encode "sym" using LZWCmp. Zero or more calls of the code sink
 *  * may result */
void LZWCmpEncode(LZWCmp *cmp, UChar sym) {
   int outCode, cmpResult;
   
   NumRuns++;
   
   if (cmp->pCode.size == 0) {
      *cmp->pCode.data = sym;
      cmp->pCode.size = 1;
      BstSearchCode(cmp, cmp->pCode);
   }
   
   else {
      if (cmp->pCode.size + 1 ==  cmp->pCodeLimit) {
         BumpPcodeSize(cmp);
      }
      
      cmp->pCode.data[cmp->pCode.size] = sym;
      cmp->pCode.data[cmp->pCode.size + 1] = '\0';
      cmp->pCode.size++;
      
      outCode = cmp->curLoc->cNum;
      BstSearchCode(cmp, cmp->pCode);
      cmpResult = CodeCompare(cmp, cmp->pCode, cmp->curLoc->cNum);
      
      if (cmpResult < 0) {
         AddCodel(cmp, outCode, sym);      
      }
      else if (cmpResult > 0) {
         AddCoder(cmp, outCode, sym);
      }
   }
}

/* Mark end of encoding (send next code value to code sink) */
void LZWCmpStop(LZWCmp *cmp) {
   
   if (NumRuns) {
      PackBits(cmp, cmp->curLoc->cNum);

      if (cmp->traceFlags & TRACE_CODES) {
         printf("Sending code %d\n", cmp->curLoc->cNum);
      }
   
      if (cmp->traceFlags & TRACE_TREE) {
         TraverseTree(cmp, cmp->root);
         printf("|\n\n");
      }
   }
   
   if (cmp->traceFlags & TRACE_CODES) {
      printf("Sending code %d\n", NUM_SYMS);
   }
      
   PackBits(cmp, NUM_SYMS);
   
   if (cmp->traceFlags & TRACE_TREE) {
      TraverseTree(cmp, cmp->root);
      printf("|\n\n");
   }
   if (cmp->bitsUsed) {
      cmp->sink(cmp->sinkState, cmp->nextInt, FALSE);
   }
   
   FreeCode(cmp->cst, cmp->curLoc->cNum);
   
   cmp->sink(cmp->sinkState, 0, TRUE);
}

/* Free all storage associated with LZWCmp (not the sinkState, though,
 *  * which is "owned" by the caller */
void LZWCmpDestruct(LZWCmp *cmp) {
   DestroyCodeSet(cmp->cst);
   free(cmp->pCode.data);
   cmp->pCode.size = 0;
   cmp->pCodeLimit = SIZE_INCR;
   RecycleAll(cmp->root);
}

/* Clear freelist.  This method is static because the freelist is shared
 *  * among all LZWCmp objects, and should be cleared only when all LZWCmp
 *   * objects are destroyed. */
void LZWCmpClearFreelist() {
   TreeNode *temp;
   
   while (FreeList) {
      temp = FreeList;
      FreeList = FreeList->right;
      free(temp);
   }
   FreeList = NULL;
}
