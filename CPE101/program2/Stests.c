#include <stdio.h>
#include <math.h>

/* Test Driver for geometry.c functions
 * Author: Kurt Mammen
 * Change Log:
 *
 *   09/??/2011 - Initial revision.
 *   01/29/2012 - Added tests to verify students are returning correct
 *                value when passed an invalid rectangle.
 *              - Changed testBoolean to testTrue and testFalse for clarity.
 *
 * Can't use student's geometry.h because I don't know variable names they
 * used! They were, however, required to have certains types in certain orders
 * so my defintions below will work. Also included prototypes of their
 * functions - this could mask errors if they incorrectly prototyped in their
 * geometry.h.
 */

typedef struct
{
  int x;
  int y;
}
  Point;

typedef struct
{
  Point ll; /* Lower Left corner */
  Point ur; /* Upper Right corner */
}
  Rectangle;

/* For Lab 6 */
double distance(Point a, Point b);
int area(Rectangle r);

/* Added for Program 3 */
Rectangle makeRectangle(int lowerLeftX, int lowerLeftY, int width, int height);
int width(Rectangle r);
int height(Rectangle r);
int perimeter(Rectangle r);
int valid(Rectangle r);
int intersects(Rectangle a, Rectangle b);
Rectangle boundingRectangle(Rectangle a, Rectangle b);
Rectangle intersection(Rectangle a, Rectangle b);

/* Local Prototypes (prototypes for functions found in this file). */
/* NOTE: These are not static to avoid compiler warning if I don't use them. */
int testDouble(double expect, double actual, double epsilon, int line);
int testInt(int expect, int actual, int line);
int testTrue(int value, int line);
int testFalse(int value, int line);
int testRect(Rectangle expect, Rectangle actual, int line);

static void results(int pass);

static int testMakeRectangle();
static int testValid();
static int testWidth();
static int testHeight();
static int testArea();
static int testPerimeter();
static int testIntersects();
static int testBoundingRectangle();
static int testIntersection();

/* Notice the automatic initialization of the arrays of Rectangles below.
 * 
 * Don't be confused by the syntax! Recall that a Rectangle is made up
 * from two Point structures and a Point is made up of two int values.
 * The compile reads {{1, 2}, {5, 7}} as the four int values required for
 * a Rectangle structure. The first two int values initialize the x and y
 * values of the first Point and the second two int values initialize the
 * x and y values of the second Point in the Rectangle.
 *
 * Each set of Rectangle values is separated by a comma. Each set initializes
 * a Rectangle structure in an array (group) of Rectangle structures. We will
 * be learning about arrays very soon!
 */

/* Reusable invalid Rectangle */
Rectangle invalidRect = {{3, 3}, {-1,-1}};

/* Intersecting test rectangles */
static Rectangle ir[] = {{{-2, -2}, { 2,  2}}, /* Primary rectangle */
                         {{-2, -2}, { 2,  2}}, /* identical */
                         {{-1, -1}, { 1,  1}}, /* smaller and completely bounded */
                         {{ 1,  1}, { 3,  3}}, /* intersects upper right corner */
                         {{ 1, -3}, { 3, -1}}, /* intersects lower right corner */
                         {{-3, -3}, {-1, -1}}, /* intersects lower left corner */
                         {{-3,  1}, {-1,  3}}, /* intersects upper left corner */
                         {{-3, -1}, { 3,  1}}, /* intersects horizontally and through */
                         {{-1, -3}, { 1,  3}}, /* intersects veritcally and through */
                         {{-1,  1}, { 1,  3}}, /* intersects vertically and up */
                         {{ 1, -1}, { 3,  1}}, /* intersects horizontall and right */
                         {{-1, -3}, { 1, -1}}, /* intersects veritally and down */
                         {{-3, -1}, {-1,  1}}, /* intersects horizontally and left */
                         {{-3, -3}, {-1,  3}}, /* intersects left side and through */
                         {{ 1, -3}, { 3,  3}}, /* intersects right side and through */
                         {{-3,  1}, { 3,  3}}, /* intersects top side and through */
                         {{-3, -3}, { 3, -1}}, /* intersects bottom side and through */
                         {{ 0,  0}, { 0,  0}}  /* TERMINATOR */
};

/* Non-intersecting test rectangles */
static Rectangle nir[] = {{{-2, -2}, { 2,  2}}, /* Primary rectangle */
                          {{ 2,  2}, { 4,  4}}, /* Touching upper right corner */
                          {{ 2, -2}, { 4,  2}}, /* Touching right side */
                          {{ 2, -4}, { 4, -2}}, /* Touching lower right corner */
                          {{-2, -4}, { 2, -2}}, /* Touching bottom side */
                          {{-4, -4}, {-2, -2}}, /* Touching lower left corner */
                          {{-4, -2}, {-2,  2}}, /* Touching left side */
                          {{-4,  2}, {-2,  4}}, /* Touching upper left corner */
                          {{-2,  2}, { 2,  4}}, /* Touching top side */
                          {{ 4, -4}, { 6, -2}}, /* Outside lower right */
                          {{-2, -6}, { 2, -4}}, /* Outside bottom */
                          {{-6, -6}, {-4, -4}}, /* Outside lower left */
                          {{-6, -2}, {-4,  2}}, /* Outside left */
                          {{-6,  4}, {-2,  6}}, /* Outside upper left */
                          {{-2,  4}, { 2,  6}}, /* Outside top */
                          {{ 4,  4}, { 6,  6}}, /* Outside upper right */
                          {{ 4, -2}, { 6,  2}}, /* Outside right */
                          {{ 0,  0}, { 0,  0}}  /* TERMINATOR */
};

/* Expected rectangles of intersection for intersecting rectangles */
static Rectangle eir[] = {{{-2, -2}, { 2,  2}}, /* Primary rectangle */
                          {{          {{-2, -2}, {-1, -1}}, /* intersects lower left corner */
				      {{-2,  1}, {-1,  2}}, /* intersects upper left corner */
				      {{ 0,  0}, { 0,  0}}  /* TERMINATOR */
			    };

			   /* Expected bounding rectangles for intersecting rectangles */
			   static Rectangle ebir[] = {{{-2, -2}, { 2,  2}}, /* Primary rectangle */
						      {{-2, -2}, { 2,  2}}, /* identical */
						      {{-2, -2}, { 2,  2}}, /* sma  2}}, /* intersects horizontall and right */
						      {{-2, -3}, { 2,  2}}, /* intersects veritally and down */
						      {{-3, -2}, { 2,  2}}, /* intersects horizontally and left */
						      {{-3, -3}, { 2,  3}}, /* intersects left side and through */
						      {{-2, -3}, { 3,  3}}, /* intersects right side and through */
						      {{-3, -2}, { 3,  3}}, /* intersects top side and through */
 Touching lower right corner */
						      {{-2, -4}, { 2,  2}}, /* Touching bottom side */
						      {{-4, -4}, { 2,  2}}, /* Touchin          {{ 0,  0}, { 0,  0}}  /* TERMINATOR */
                           };

			   /* 
			    * The beginning of the program!
			    */
			   int main()
			   {
			     int pass = 1;

			     printf("\nPerforming tests...\n");

			     /* TODO: Add tests for bad inputs to all! */

			     pass &= testMakeRectangl= testInt(0, width(invalidRect), __LINE__);

			     return pass;
			   }

			   static int testHeight()
			   {
			     int pass = 1;

			     Rectangle r = makeRectangle (-10, -20, 2, 4);
			     pass &= testInt(4, height(r), __LINE__);
   
			     pass &= testFalse(valid(invalidRect), __LINE__);
			     pass &= testInt(0, heighr.ll.y = 2;
					     r.ur.x = 0;
					     r.ur.y = 5;
					     pass &= testFalse(valid(r), __LINE__);

					     r.ll.x = 1;
					     r.ll.y = 2;
					     r.ur.x = 5;
					     r.ur.y = 1;
					     pass &= testFalse(valid(r), __LINE__);

					     return pass;
					     }

			     static int testIntersects()
			     {
			       int pass = 1;
			       int i;

			       /* Test all intersections */
			       i = 1;

			       while ( (ir[i].ll.x ||     pass &= testFalse(valid(invalidRect), __LINE__);
					pass &= testFalse( intersects(invalidRect, ir[0]), __LINE__ );

					pass &= testFalse(valid(invalidRect), __LINE__);
					pass &= testFalse( intersects(invalidRect, invalidRect), __LINE__ );

      for all intersecting rectangles */
					i = 1;

					while ( (ebir[i].ll.x || ebir[i].ll.y || ebir[i].ur.x || ebir[i].ur.y) )
					  {
					    /* Make sure rectangles have not been corrupted by student's code */
					    /* NOTE: Not a perfect test as they could LINE__ );
      pass &= testTrue( valid(nir[i]), __LINE__ );
      pass &= testTrue( valid(ebnir[i]), __LINE__ );

      /* Test bounding rectangles */
					    pass &= testRect(ebnir[i], boundingRectangle(nir[0], nir[i]), __LINE__);
					    pass &= testRect(ebnir[i], boundingRectangle(nir[i], n__LINE__);
							     pass &= testRect(eir[i], intersection(eir[i], eir[0]), __LINE__);
							     i++;
							     }

					      /* Test rectangles of intersection for all non intersecting rectangles */
					      i = 1;

					    while ( (nir[i].ll.x || nir[i].ll.y ||, your grade will be based on when you hand it in, minus\n");
      printf("any deductions for the quality of you implementation. Quality includes,\n");
      printf("but is not limited to, meeting the required coding style guidelines,\ntest at line %d, expected true, found false\n", line);

      return 0;
   }

   /* Must return 1, NOT value, because not all true values (non-zero)
    * have low-order bit set and &= operator uses the low-or(stderr,
              "   Failed test at line %d, expected %d, found %d\n",
              line,
              expect,
              actual);

      return 0;
   }

   return 1;
}

int testRect(Rectangle expect, Rectangl
