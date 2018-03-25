/*
 * Assignment: Lab 09
 * Course: CPE 101
 * Author: Alexander DeMello
 */

#include <stdio.h>

int main()
{
   int input, row, col;
   input = -2;
   
   while(input > 20 || input < 1)
   {
      printf("Multiply to: ");
      scanf("%d", &input);
      if(input > 20 || input < 1)
      {
         printf("The limit must be between 1 and 20, inclusive, please try again\n");
         printf("\n");
      }
   }
      for (row = 0; row != (input); row ++)
      {
         for (col = 0; col != (input - 1); col ++)
         {
            printf("%4d", ((row + 1) * (col + 1)));
            
         }
         printf("%4d\n", ((row + 1) * (col + 1)));
      }
      return 0;
}
