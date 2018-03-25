#include <stdio.h>
#define MAX 100

typedef struct {
   int val1;
   int val2;
} Pair;

void Swap(Pair *p1, Pair *p2){
   Pair Temp;
   if(p2->val1 < p1->val1 || p2->val1 == p1->val1 && p2->val2 < p1->val2){
      Temp = *p1;
      *p1 = *p2;
      *p2 = Temp;
   }
}

void SortThree(Pair *p1, Pair *p2, Pair *p3){
   Swap(p1, p2);
   Swap(p1, p3);
   Swap(p2, p3);
}


int main() {
   Pair pair1 = {4, 2}, pair2 = {3, 2}, pair3 = {4, 1};
   SortThree(&pair1, &pair2, &pair3);
   printf("pair1: %d %d  pair2: %d %d  pair3: %d %d\n", pair1.val1, pair1.val2, pair2.val1,
    pair2.val2, pair3.val1, pair3.val2);

   SortThree(&pair3, &pair2, &pair1);
   printf("pair1: %d %d  pair2: %d %d  pair3: %d %d\n", pair1.val1, pair1.val2, pair2.val1,
    pair2.val2, pair3.val1, pair3.val2);
}
