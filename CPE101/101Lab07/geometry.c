#include "geometry.h"

double distance(Point p1, Point p2)
{
   return sqrt(((p2.x - p1.x) * (p2.x - p1.x)) + ((p2.y - p1.y) * (p2.y - p1.y)));
}
int area(Rectangle A)
{
   return abs((A.upr.x - A.ll.x) * (A.upr.y - A.ll.y));
}
