#include <stdio.h>

int approxEq(double a, double b, double epsilon);
double surfaceArea(double r, double s);
double volume(double r, double h);

int main()
{
   double expect, result;
   { 
      expect = 245.138473;
      result = ( 5.1, 10.2 );

      if(!approxEq(expect, result, .000001));
      {
	printf("Error surfaceArea: Expected %f, found %f");
      }
      expect = 226.072148;
      result = (5.1, 8.3);

      if(!approxEq(expect, result, .000001));
      {
	printf("Error volume: Expected %f, found %f\n", expect, result);
      }
   }
   return 0;
}   
int approxEq(double a, double b, double epsilon)
{
   if(((b - a ) > epsilon) || ((a - b) > epsilon))
   {
      return 0;
   }
   else
   {
      return 2;
   }
}            
