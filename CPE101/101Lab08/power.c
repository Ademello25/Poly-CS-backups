/*
 * Assignment: Lab 08
 * Course: CPE 101
 * Author: Alexander DeMello
 */

#include <stdio.h>

int main()
{
  int base, powerf, poweri, result;

   printf("Base (int value): ");
   scanf("%d", &base);
   printf("To power (int value): ");
   scanf("%d", &powerf);

   for(poweri = 0; poweri <= powerf; poweri += 1)
   {
      if(poweri == 0)
      {
        result = 1;
      }
      else if(poweri == 1)
      {
	 result = base;
      }
      else
      {
	 result *= base;
      }

     printf("%d to the %d power is %d\n", base, poweri, result);
   }
   
   return 0;
}  