/*
 * Assignment: Program 2
 * Course: CPE101
 * Author: Alexander DeMello
 */

#include <stdio.h>
#include "geometry.h"

Rectangle makeRectangle(int llx, int lly, int rectw, int recth)
{
   Rectangle result;
   Point ll,
        upr;
   upr.x = llx + rectw;
   upr.y = lly + recth; 
   ll.x = llx;
   ll.y = lly;
   result.ll = ll;
   result.upr = upr;
   
   return result; 
}
int valid(Rectangle A)
{
   return (A.ll.x < A.upr.x && A.ll.y < A.upr.y);
}
int width(Rectangle A)
{
   if(valid(A))
   {
      return (A.upr.x - A.ll.x);
   }
   else
   {
      return 0;
   }
}
int height(Rectangle A)
{
   if(valid(A))
   {
      return (A.upr.y - A.ll.y);
   }
   else
   {
      return 0;
   }
}
int area(Rectangle A)
{
   if(valid(A))
   {
      return ((A.upr.y - A.ll.y) * (A.upr.x - A.ll.x));
   }
   else
   {
      return 0;
   }
}
int perimeter(Rectangle A)
{
   if(valid(A))
   {
      return ((2 * (A.upr.y - A.ll.y)) + (2 * (A.upr.x - A.ll.x)));
   }
   else
   {
      return 0;
   }
}
int intersects(Rectangle A, Rectangle B)
{
   return ((((A.ll.x<B.upr.x)&&(A.ll.y<B.upr.y)) &&((A.upr.x>B.ll.x)&&(A.upr.y>B.ll.y))) || 
           (((B.ll.x < A.upr.x)&&(B.ll.y<A.upr.y))&&((B.upr.x>A.ll.x)&&(B.upr.y>A.ll.y))));
}
Rectangle boundingRectangle(Rectangle A, Rectangle B)
{
   Rectangle result;
   if(!valid(A))
   {
      if(!valid(B))
      {
         return makeRectangle(0, 0, 0, 0);
      }
      else
      {
         return B;
      }
   } 
   else if(!valid(B)) return A;
   result.ll.x= (A.ll.x < B.ll.x) ? A.ll.x : B.ll.x;
   result.ll.y= (A.ll.y < B.ll.y) ? A.ll.y : B.ll.y;
   result.upr.x=(A.upr.x > B.upr.x) ? A.upr.x : B.upr.x;
   result.upr.y=(A.upr.y > B.upr.y) ? A.upr.y : B.upr.y;
   return result;
}

Rectangle intersection(Rectangle A, Rectangle B)
{
   Rectangle result;
   if(!valid(A) || (!valid(B)))
   {
      return makeRectangle(0,0,0,0);
   }
   
   result.ll.x= (A.ll.x > B.ll.x) ? A.ll.x : B.ll.x;
   result.ll.y= (A.ll.y > B.ll.y) ? A.ll.y : B.ll.y;
   result.upr.x=(A.upr.x < B.upr.x) ? A.upr.x : B.upr.x;
   result.upr.y=(A.upr.y < B.upr.y) ? A.upr.y : B.upr.y;
   
   return (valid(result))? result: makeRectangle(0,0,0,0);
}
