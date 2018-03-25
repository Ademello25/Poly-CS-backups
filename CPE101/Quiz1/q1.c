#define PI 3.14159265

double surfaceArea(double r, double s)
{
  return (((PI) * (r) * (s)) + ((PI) * (r * r)));
}
double volume(double r, double h)
{
  return ((1.0/3.0) * (PI) * (r * r) * (h));
}
