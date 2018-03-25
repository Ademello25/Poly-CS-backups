/*
 * Assignement: Lab 18
 * Course: CPE 101
 * Author: Alexander DeMello
 */
 
#include <stdio.h>
#include <math.h>

int countNumbersBetween(int * array, int size, int lower, int upper);

int countNumbersBetween(int * array, int size, int lower, int upper)
{
   int i, count;
   
   for(i = 0; i < size; i++)
   {
      if( *(array + i) < (upper + 1) && *(array + i) > (lower - 1))
      {
         count ++;
      }
   }
   return count;
}

int countDuplicates( double * array, int size, double find, double epsilon);

int countDuplicates( double * array, int size, double find, double epsilon)
{
   int i, count;
   count =0;

   
   for(i = 0; i < size; i++)
   {
      if(approxEq( *(array+i) , find, epsilon))
      {
         count ++;
      }
   }
   return count;
}

char* replaceChar (char* string, char search, char replace); 

char* replaceChar (char* string, char search, char replace)
{
   int i , length;
   
   for(length=0; *(string + length); length++)
   
   for(i = 0; i < (length + 1); i++)
   {
      if( *(string + i) == search)
      {
         *(string + i) = replace;
      }
   }
   return string;
}

int approxEq(double a, double b, double epsilon);

int approxEq(double a, double b, double epsilon)
{ 
   return ((fabs(a-b)) < epsilon);
}
