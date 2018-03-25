/*
 * Assingment: Lab 09
 * Course: CPE 101
 * Author: Alexander Demello
 */

#include <stdio.h>

int main()
{
   int row, col, trisize;
   trisize = -2;
   while(trisize > 21 || trisize < 3 || (trisize%2) == 0)
   {
      printf("Enter the width of the triangle: ");
      scanf("%d", &trisize);
      if(trisize > 21 || trisize < 3 || (trisize%2) == 0)
      {
         printf("The width must be an odd number and be between\n3 and 21, inclusive, please try again\n");
         printf("\n");
      }
   }
   for( row = 0; row < ((trisize/2)+1); row ++)
   {
      for( col = 0; col < trisize; col ++)
      {
         if(row==(trisize/2)) printf("*");
         else if((trisize/2 - row) == col || (trisize/2 + row) == col) printf("*");
         else printf(" ");
      }
      printf("\n");
   }
   return 0;
}
