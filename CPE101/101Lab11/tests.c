/*
 * Assignment: Lab 11
 * Course: CPE 101
 * Author: Alexander DeMello
 */

#include <stdio.h>
#include "funcs.h"

int main ()
{
   int x;
   double array1[5], array2[7];

   for(x = 0; x < 5; x++)
   {
      array1[x] = x * 2.5;
   }
   for(x=0; x < 7; x++)
   {
      array2[x] = x % 3;
   }
   
   if(!approxEq(findMax(array1, 5), 10.0, 0.000001))
   {
      printf("Error findMax: Expected 10, found %f\n", findMax(array1, 5) );
   }
   
   if(!intExpect(findLast(array2, 7, 0), 6))
   {
      printf("Error findLast: Expected 6, found %d\n", findLast (array2,7,0) );
   }
   if(!intExpect(inAscendingOrder(array1, 5), 3))
   {
      printf( "Error inAscendingOrder: Expected 3, found %d\n", inAscendingOrder(array1, 5));
   }
   if(!intExpect(inAscendingOrder(array2, 7), 0))
   {
      printf( "Error inAscendingOrder: Expected 0, found %d\n", inAscendingOrder(array2, 7));
   }
   return 0;
}

int intExpect(int expect, int result)
{
   if(expect != result)
   {
      return 0;
   }
   else
   {
      return 12;
   }
   return 0;
}

double approxEq(double a, double b, double epsilon)
{
   if( (a - b) > epsilon || (b - a) > epsilon)
   {
      return 0;
   }
   else
   {
      return 1;
   }
   return 0;
}

