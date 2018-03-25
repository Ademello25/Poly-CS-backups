#include "geometry.h"

int approxEq( double a, double b, double epsilon);

int main()
{
   double disExpect, 
          disResult;
     int areaExpect,
         areaResult;
       Point p1, p2;
        Rectangle A;

   p1.x = 2;
   p1.y = 5;
   p2.x = 15;
   p2.y = 19;

   A.ll = p1;
   A.upr = p2;

   disExpect = 19.1049731; 
   disResult = distance(p1, p2);

   if(!approxEq(disExpect, disResult, .000001))
   {
      printf("Error distance: Expected %f, found %f\n", disExpect, disResult);
   }

   areaExpect = 182;
   areaResult = area(A);

   if(!approxEq(areaExpect, areaResult, 1))
   {
      printf("Error distance: Expected %d, found %d\n", areaExpect, areaResult);
   }
   return 0;
}
int approxEq(double a, double b, double epsilon)
{
   if(((a - b) > epsilon) || ((b - a) > epsilon))
   {
      return 0;
   }
   else
   { 
      return 2;
   }
}
