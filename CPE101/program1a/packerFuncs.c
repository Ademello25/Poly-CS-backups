/*
 * Assignment: Program 1a
 * Course: CPE 101
 * Author: Alexander DeMello
 */

#define PI 3.14159265

double boxVolume(double l, double w, double h)
{
   return (l * w * h);
}
double boxWeight(double l, double w, double h, double th, double d)
{
   return ((2.0 * (th * h * w) ) + (2.0 * (th * (l - (2.0 * th) ) * h) ) + (2.0 * (th * (l - (2.0 * th) ) * (w - (2.0 * th) ) ) )) * d;
}
double ballVolume(double r)
{
   return ((4.0 / 3.0) * (PI) * (r) * (r) * (r));
}
double ballWeight(double r, double th, double d)
{
   return (((4.0 / 3.0) * (PI) * (r) * (r) * (r)) - ((4.0 / 3.0) * (PI) * (r - th) * (r - th) * (r - th))) * d;
}  
int ballsFitl(double r, double l, double th)
{
   return ((l - (2 * th)) / (2 * r));
}
int ballsFitw(double r, double w, double th)
{
   return ((w - (2 * th)) / (2 * r));
}
int ballsFith(double r, double h, double th)
{
   return ((h - (2 * th)) / (2 * r));
}
int ballsFit(double r, double l, double w, double h, double th)
{
   return (ballsFitl(r, l, th) * ballsFitw(r, w, th) * ballsFith(r, h, th));
}
