/*
 *   Converts distances in miles to kilometers
 */
#include <stdio.h>               /* printf, scanf definition */
#define KMS_PER_MILE 1.609       /* converstion constant  */

int main ()
{
   double miles,                 /* input - distances in miles */
            kms;                 /* output- distances in kilometers */

   /* Get the distance in miles */
   printf("Enter the distance in miles> ");
   scanf("%lf" , &miles);
 
   /* Convert the distance to kilometers */
   kms = KMS_PER_MILES * miles;

   /* Display the distance in kilometers */
   printf("That equals %.2f kilometeres.\n", kms);

  return(0);
}
