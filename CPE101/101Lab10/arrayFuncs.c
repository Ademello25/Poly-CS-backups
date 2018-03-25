/*
 * Assignment: Lab 10
 * Course: CPE 101
 * Author: Alexander DeMello
 */

#include <stdio.h>

double sumArray(int array[], int size)
{
   int i;
   double result;
   result = 0.0;

   for(i = 0; i < size; i++)
   {
     result = array[i] + result;
   }
   return result;
}
double avgArray(int array[], int size)
{
  double result;
  result = sumArray(array, size);
  
  return result / size;
}
