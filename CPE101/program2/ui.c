/*
 * Assignment: Program 2
 * Course: CPE101
 * Author: Alexander DeMello
 */

#include "geometry.h"

int main()
{
   Rectangle A,
            BR,
            IR,
             B;
   int arectw,
       arecth,
       brectw,
       brecth,
          allx,
          ally,
          bllx,
          blly;
   printf("Enter the location and dimensions of two rectangles...\n\n");
   printf("   First rectangle\n      x-coordinate of the lower-left corner: ");
   scanf("%d", &allx);
   printf("      y-coordinate of the lower-left corner: ");
   scanf("%d", &ally);
   printf("      width : ");
   scanf("%d", &arectw);
   printf("      height: ");
   scanf("%d", &arecth);

   A = makeRectangle(allx, ally, arectw, arecth);

   if((!valid(A)))
   {  
      printf("\nSorry, you specified an invalid rectangle.\n");
      printf("Terminating program - goodbye!\n");
      
      return 0;
   }
      
   printf("\n");
   printf("   Second rectangle\n");
   printf("      x-coordinate of the lower-left corner: ");
   scanf("%d", &bllx);
   printf("      y-coordinate of the lower-left corner: ");
   scanf("%d", &blly);
   printf("      width : ");
   scanf("%d", &brectw);
   printf("      height: ");
   scanf("%d", &brecth);
   
   B = makeRectangle(bllx, blly, brectw, brecth);
   
   if((!valid(B)))
   {
      printf("\nSorry, you specified an invalid rectangle.\n");
      printf("Terminating program - goodbye!\n");
      
      return 0;
   }
   
   printf("\nThe first rectangle has a perimeter of %d and an area of %d.\n",
          perimeter(A), area(A));
   printf("The second rectangle has a perimeter of %d and an area of %d.\n",
          perimeter(B), area(B));
   BR = boundingRectangle(A, B);
   IR = intersection(A, B);
   
   printf("\n");
   printf("The bounding rectangle is as follows...\n\n");
   printf("   Bounding Rectangle\n");
   printf("      x-coordinate of the lower-left corner: %d\n", BR.ll.x);
   printf("      y-coordinate of the lower-left corner: %d\n", BR.ll.y);
   printf("      width : %d\n", (BR.upr.x - BR.ll.x));
   printf("      height: %d\n\n", (BR.upr.y - BR.ll.y));
   
   if(!intersects(A, B))
   {
      printf("The rectangles do not intersect!\n");
      return 0;
   }
   printf("The intersection rectangle is as follows...\n\n");
   printf("   Intersection Rectangle\n");
   printf("      x-coordinate of the lower-left corner: %d\n", IR.ll.x);
   printf("      y-coordinate of the lower-left corner: %d\n", IR.ll.y);
   printf("      width : %d\n", (IR.upr.x - IR.ll.x));
   printf("      height: %d\n\n", (IR.upr.y - IR.ll.y));
   
   
   
 
   
   return 0;
   
   
}

