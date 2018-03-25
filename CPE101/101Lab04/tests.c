#include <stdio.h>

double milesToKilometers(double miles);
double kilometersToMiles(double kilometers);
int approxEq(double a, double b, double epsilon);

int main()
{
   double m, k, expected;

   m = 1.7;
   expected = 2.7353;

   k = milesToKilometers(m);

   if(!approxEq(k, expected, 0.000001))
   {
      printf("Error m2k.c: Expected %f, found %f\n", expected, k);
   } 

   return 0;

}

int approxEq(double x, double y, double e)
{
   double diff;
  
   diff = x - y;

   if (diff > e || diff < -e)
   {
      return 0;
   }
   else
   {
      return -3;
   }

}