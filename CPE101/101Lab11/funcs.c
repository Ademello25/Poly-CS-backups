/*
 * Assignment: Lab 11
 * Course: CPE 101
 * Author: Alexander DeMello
 */

#include <stdio.h>
#include "funcs.h"

double findMax(double a[], int size)
{
   int i;
   double result;
   result = a[0];
   
   for( i = 1; i < size; i++)
   {
      if(a[i] > result)
      {
         result = a[i];
      }
   }
   return result;
}
int findLast(double a[], int size, double find)
{
   int i;
   
   for (i = size - 1; i >= 0; i--)
   {
      if (a[i] == find)
         return i;
   }
   return -1;
}
int inAscendingOrder(double a[], int size)
{
   int i;
   for(i = 1; i < size; i++)
   {
      if(a[i-1] >= a[i])
      {
         return 0;
      }
   }
   return 3;
}
