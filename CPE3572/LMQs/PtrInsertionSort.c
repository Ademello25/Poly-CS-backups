#include <stdio.h>

#define MAX 100

// Implementation of insertion sort that uses pure pointers, and this thus more
// // compact.  A conventional indexing-based insertion sort is given here for reference:
// // void InsertSort(int vals[], int size) {
// //   int ndx, insNdx;
// //   int toInsert;
// //
// //   for (ndx = 1; ndx < size; ndx++) {
// //      toInsert = vals[ndx];
// //      insNdx = ndx;
// //      while (insNdx > 0 && toInsert <= vals[insNdx-1]) {
// //         vals[insNdx] = vals[insNdx-1];
// //         insNdx--;
// //    }
// //    vals[insNdx] = toInsert;
// //  }
// //}

void InsertSort(int *vals, int size) {
   int *next = vals + 1, *loc, *pLoc, *valsEnd = vals + size;
   int toInsert;

            

}

int main() {
   int vals[MAX], numVals, ndx;
         
   scanf("%d", &numVals);
   for (ndx = 0; ndx < numVals; ndx++)
      scanf("%d", &vals[ndx]);

   InsertSort(vals, numVals);
                  
   for (ndx = 0; ndx < numVals; ndx++)
      printf("%d ", vals[ndx]);
   printf("\n");
}
