#include <stdio.h>


// GetTwoInts function fills two int values by scanning from input, and returns
// a result like that of scanf
int GetTwoInts(int *i1, int *i2){
   printf("Enter two ints: "); 
   return scanf("%d %d", i1, i2);
}

// FindMinMax function call fills in two int values for min and max of two
// other int values.
void FindMinMax(int i1, int i2, int *max, int *min){
   if (i1 > i2){ 
      *max = i1; 
      *min = i2;
   } 
   else { 
      *max = i2; 
      *min = i1;
   }
}


// Sort is passed two ints and puts them in increasing order, by calling
// FindMinMax.
void Sort(int *i1, int *i2){
   FindMinMax(*i1, *i2, i2, i1);
}


int main() {
   int i1, i2, min, max;
         
   while (EOF != GetTwoInts(&i1, &i2)) {
      FindMinMax(i1, i2, &max, &min);
      printf("Min/max of %d %d. Min: %d  Max: %d\n", i1, i2, min, max);
      Sort(&i1, &i2);
      printf("In sorted order: %d %d\n", i1, i2);
   }
}

