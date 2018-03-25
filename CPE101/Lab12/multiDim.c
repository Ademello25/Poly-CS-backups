/*
 * Assignment: Lab 12
 * Course: CPE 101
 * Author: Alexander DeMello
 */

#include <stdio.h>
#include "multiDim.h"

double average(int array[DIM_1][DIM_2][DIM_3][DIM_4][DIM_5])
{
   int a,b,c,d,e;
   int sum=0,count=0;
   for(a = 0; a<DIM_1;a++)
   {
      for(b=0;b<DIM_2;b++)
      {
         for(c=0;c<DIM_3;c++)
         {
            for(d=0;d<DIM_4;d++)
            {
               for(e=0;e<DIM_5;e++)
               {
                  count++;
                  sum+=array[a][b][c][d][e];
               }
            }
         }
      }
   }
   return ((double)sum)/count;
}
Index5D findFirst(int array[DIM_1][DIM_2][DIM_3][DIM_4][DIM_5], int value)
{
   int a,b,c,d,e;
   Index5D result;
   for(a = 0; a<DIM_1;a++)
   {
      for(b=0;b<DIM_2;b++)
      {
         for(c=0;c<DIM_3;c++)
         {
            for(d=0;d<DIM_4;d++)
            {
               for(e=0;e<DIM_5;e++)
               {
                  if(value == array[a][b][c][d][e])
                  {
                     result.d1 = a;
                     result.d2 = b;
                     result.d3 = c;
                     result.d4 = d;
                     result.d5 = e;
                     return result;
                  }
               }
            }
         }
      }
   } 
   result.d1 = -1;
   result.d2 = -1;
   result.d3 = -1;
   result.d4 = -1;
   result.d5 = -1;
   return result;
}
Index5D findLast(int array[DIM_1][DIM_2][DIM_3][DIM_4][DIM_5], int value)

{
   int a,b,c,d,e;
   Index5D result;
   for(a = (DIM_1 - 1); a >= 0;a--)
   {
      for(b = (DIM_2 - 1);b >= 0;b--)
      {
         for(c = (DIM_3 - 1);c >= 0;c--)
         {
            for(d = (DIM_4 - 1);d >= 0;d--)
            {
               for(e = (DIM_5 -1);e >= 0;e--)
               {
                  if(value == array[a][b][c][d][e])
                  {
                     result.d1 = a;
                     result.d2 = b;
                     result.d3 = c;
                     result.d4 = d;
                     result.d5 = e;
                     return result;
                  }
               }
            }
         }
      }
   }
   result.d1 = -1;
   result.d2 = -1;
   result.d3 = -1;
   result.d4 = -1;
   result.d5 = -1;
   return result;
}
int findMin(int array[DIM_1][DIM_2][DIM_3][DIM_4][DIM_5])
{
   int a,b,c,d,e;
   int result;
   result = array[0][0][0][0][0];
   
   for(a = 0; a<DIM_1;a++)
   {
      for(b=0;b<DIM_2;b++)
      {
         for(c=0;c<DIM_3;c++)
         {
            for(d=0;d<DIM_4;d++)
            {
               for(e=0;e<DIM_5;e++)
               {
                  if(array[a][b][c][d][e] < result)
                  {
                     result = array[a][b][c][d][e];
                  }
                  
                  
               }
            }
         }
      }
   }
   return result;
}
int countOfMins(int array[DIM_1][DIM_2][DIM_3][DIM_4][DIM_5])
{
   int a,b,c,d,e, count;
   int result;
   result = array[0][0][0][0][0];
   count = 0;
   for(a = 0; a<DIM_1;a++)
   {
      for(b=0;b<DIM_2;b++)
      {
         for(c=0;c<DIM_3;c++)
         {
            for(d=0;d<DIM_4;d++)
            {
               for(e=0;e<DIM_5;e++)
               {
                  if(array[a][b][c][d][e] < result)
                  {
                     result = array[a][b][c][d][e];
                     count = 1;
                  }
                  else if(array[a][b][c][d][e] == result)
                  {
                     count ++; 
                  }
               }
            }
         }
      }
   }
   return count;
}
