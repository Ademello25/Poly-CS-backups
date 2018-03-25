#include <stdio.h>
#include <stdlib.h>
#include "multiDim.h"

/*
 *Prototype of function written in this file
 */
void initArray(int a[DIM_1][DIM_2][DIM_3][DIM_4][DIM_5]);

/*
 * The beginning of the program!
 */
int main()
{
   int find;
   
   /* The constants DIM_1 through DIM_5 are defined in multiDim.h */
   int array[DIM_1][DIM_2][DIM_3][DIM_4][DIM_5];

   /* The struct Index5D is defined in multiDim.h */
   Index5D index;

   /* Call to provided function in this file. You might want to examine
    * the code as an example of how tow work with a 5-dimensional array!
    */
   initArray(array);
 
   /* Write this function in multiDim.c - see multiDim.h for details! */
   printf("\nThe average of all values is %f\n", average(array));

   printf("\nEnter a value to find the first occurance of: ");
   scanf("%d", &find);

   /* Write this function in multiDim.c - see multiDim.h for details! */
   index = findFirst(array, find);

   if (index.d1 < 0)
   {
      printf("The value %d was not found!\n", find);
   }
   else
   {
      printf("Found %d at array[%d][%d][%d][%d][%d]\n",
             find, index.d1, index.d2, index.d3, index.d4, index.d5);
   }
    
   printf("\nEnter a value to find the last occurance of: ");
   scanf("%d", &find);

   /* Write this function in multiDim.c - see multiDim.h for details! */
   index = findLast(array, find);

   if (index.d1 < 0)
   {
      printf("The value %d was not found!\n", find);
   }
   else
   {
      printf("Found %d at array[%d][%d][%d][%d][%d]\n",
             find, index.d1, index.d2, index.d3, index.d4, index.d5);
   }

   /* Write these function in multiDim.c - see multiDim.h for details! */
   printf("\nThe minimum value is %d and it occurs %d times in the array.\n",
          findMin(array),
          countOfMins(array));

   return 0;
}

void initArray(int a[DIM_1][DIM_2][DIM_3][DIM_4][DIM_5])
{
   int i, j, k, l, m, seed, numOfValues;

   printf("Enter a seed value for random number generation: ");
   scanf("%d", &seed);
   srand(seed);

   numOfValues = DIM_1 * DIM_2 * DIM_3 * DIM_4 * DIM_5;

   for (i = 0; i < DIM_1; i++)
   {
      for(j = 0; j < DIM_2; j++)
      {
         for(k = 0; k < DIM_3; k++)
         {
            for(l = 0; l < DIM_4; l++)
            {
               for(m = 0; m < DIM_5; m++)
               {
                  a[i][j][k][l][m] = (rand() % numOfValues) + 1;
               }
            }
         }
      }      
   }

   printf("\nThe 5 dimensional array has been initialized with %d\n", numOfValues);
   printf("psuedo-random numbers between 1 and %d, inclusive.\n", numOfValues);
}
