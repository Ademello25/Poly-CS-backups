/*
 * Assingment: Lab 09
 * Course: CPE 101
 * Author: Alexander Demello
 */

#include <stdio.h>

int main()
{
   int row, col, sqsize;
   sqsize = -2;
   while(sqsize > 20 || sqsize < 2)
   {
      printf("Enter the size of the square: ");
      scanf("%d", &sqsize);
      if(sqsize > 20 || sqsize < 2)
      {
         printf("The size must be between 2 and 20, inclusive, please try again\n");
         printf("\n");
      }
   }
   for( row = 0; row < sqsize; row ++)
   {
      for( col = 0; col < sqsize; col ++)
      {
         if(row == 0 || row == sqsize - 1)printf("*");
         else if(col == 0 || col == sqsize -1)printf("*");
         else printf(" ");
      }
      
      printf("\n");
   }
   return 0;
}
