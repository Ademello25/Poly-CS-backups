/*
 * Assignment: Quiz 2
 * Course: CPE101
 * Author: Alexander DeMello
 */

#include <stdio.h>

int inOrder(double a[], int size);

int inOrder(double a[], int size)
{
   int i;
   
   for( i = 1; i < size; i++)
   {
      if(a[i -1] > a[i])
      {
         return 0;
      }
   }
   return 5;
}

int maxRepeats(int a[], int size);

int maxRepeats(int a[], int size)
{
   int i, o, count, max;
   
   max = 1;
   
   for(i = 0; i < size; i++)
   {
      count = 1;
      for( o = i + 1; o < size; o++)
      {
         if( a[i] == a[o])
         {
         count ++;
         }
         if(count > max)
         {
            max = count;
         }
      }
   }
   return max;
}

int order(int a[], int size);

int order(int a[], int size)
{
   int i, ascend, descend;
   ascend = 5;
   descend = -5;
   
   for(i = 0; i < size; i++)
   {
      if(a[i - 1] > a[i])
      {
         ascend = 0;
      }
      else if(a[i] < a[i+1])
      {
         descend = 0;
      }
   }
   if(ascend == 5)
   {
      return 5;
   }
   else if(descend == -5)
   {
      return -5;
   }
   else
   {
      return 0;
   }
}
