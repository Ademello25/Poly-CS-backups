/*
 * Assignment: Lab 08
 * Course: CPE 101
 * Author: Alexander DeMello
 */

#include <stdio.h>

int main()
{
  int initial, final, increment;

   printf("Count from (int value): ");
   scanf("%d", &initial);
   printf("Count to (int value): ");
   scanf("%d", &final);
   printf("Count by (int value): ");
   scanf("%d", &increment);

   for(initial = initial; initial <= final; initial += increment)
   {
     printf("%d\n", initial);
   } 
return 0;
} 
