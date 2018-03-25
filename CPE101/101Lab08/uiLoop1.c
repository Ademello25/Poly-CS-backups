/*
 * Assignment: Lab 08
 * Course: CPE 101
 * Author: Alexander DeMello
 */
#include <stdio.h>

int main()
{
  int lower, upper, within;

   printf("Enter the lower boundry (int value): ");
   scanf("%d", &lower);
   printf("Enter the upper boundry (int value): ");
   scanf("%d", &upper);
   printf("Enter a value between %d and %d (int value, inclusive): ", lower, upper);
   scanf("%d", &within);

   while((within > upper) || (within < lower))
   {
      printf("Whoops, %d is not between %d and %d (inclusive), please try again!\n",within, lower, upper);
      printf("Enter a value between %d and %d (int value, inclusive): ", lower, upper);
      scanf("%d", &within);
   }

      printf("\n");
      printf("Well done, %d is between %d and %d (inclusive), thanks!\n", within, lower, upper);
return 0;
}
