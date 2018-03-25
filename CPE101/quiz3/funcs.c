/*
 * Assignment: Quiz 3
 * Course: CPE 101
 * Author: Alexander DeMello
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>


char* strCat(char* string1, char* string2);

char* strCat(char* string1, char* string2)
{
   int i,length1, length2;
   
   length1 = (int)strlen(string1);
   length2 = (int)strlen(string2);
   
   for(i = 0; i < length2 + 1; i++)
   {
      string1[length1 + i] = string2[i];
   }
   string1[length1 + i] = 0;
   
   return string1;
   
}


int order(int array1[],int size);

int order(int array1[], int size)
{
   int i, ascend, descend;
   ascend = 5;
   descend = -5;
   
   if (size == 0 || size == 1)
   {
      return 0;
   }
      
   for(i = 0; i < size; i++)
   {
      if(array1[i - 1] >= array1[i])
      {
         ascend = 0;
      }
      else if(array1[i] <= array1[i+1])
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

int removeDups(int array[], int size, int singarray);

int removeDups(int array[], int size, int singarray)
{
   return 0;
}
