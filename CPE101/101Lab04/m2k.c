/*
 *   Converts distances in miles to kilometers
 */
#include <stdio.h>             /* printf, scanf definitions */
double milesToKilometers (double miles);

int main()
{
   double miles,      /* input - distance in miles */
    kms;              /* output - distance in kilometers */

   /* Get the distance in miles */
   printf("Enter the distance in miles> ");
   scanf("%lf", &miles);

   /* Convert the distance to kilometers */
   kms = milesToKilometers(miles);

   /* Display the distance in kilometers */
   printf("That equals %.2f kilometers.\n", kms);

   return (0);
}
