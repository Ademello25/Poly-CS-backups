#include "packerFuncs.h"
#include  <stdio.h>

int main()
{
  double l,
         w,
         h,
        th,
         d,
        di,
         r,
       bth,
        bd,
  blweight,
 ttlweight,
emptyspace,
 usedspace,
    volume,
 intvolume,
   bweight;
   int blsfit;
printf("**********************************\n");
printf("*    Welcome to Packer! (tm)     *\n");
printf("* The amazing ball-packing tool! *\n");
printf("**********************************\n") ;      
printf("\n");
printf("Enter the box's exterior dimensions\n");
printf("     Length (inches): ");
scanf("%lf", &l);
printf("      Width (inches): ");
scanf("%lf", &w);
printf("     Height (inches): ");
scanf("%lf", &h);
printf("\n");
printf("Enter the thickness and density of the material the box is made from\n");
printf("     Thickness (inches): ");
scanf("%lf", &th);
printf("     Density (pounds per cubic inch): ");
scanf("%lf", &d);
printf("\n");
printf("Enter the diameter of the balls you want to pack\n");
printf("     Diameter (inches): ");
scanf("%lf", &di);
r = (di / 2.0);
printf("\n");
printf("Enter the thickness and density of the material the balls are made from\n");
printf("     Thickness (inches): ");
scanf("%lf", &bth);
printf("     Density (pounds per cubic inch): ");
scanf("%lf", &bd);
printf("\n");

bweight = boxWeight(l, w, h, th, d);
printf("The weight of the empty box is %.2f pounds.\n", bweight);
blweight = ballWeight(r, bth, bd);
printf("The weight of one ball is %.2f pounds.\n", blweight);
blsfit = ballsFit(r, l, w, h, th);
volume = boxVolume(l, w, h);
intvolume = iVolume(l, w ,h, th);
usedspace = usedSpace(di, l, w, h, blsfit, intvolume);
emptyspace = (intvolume - usedspace);   
printf("The box holds %d balls with %.2f cubic inches of wasted space.\n", blsfit, usedspace);  
ttlweight = ((blweight * blsfit) + bweight);
printf("The total shipping weight of the box and balls is %.2f pounds.\n", ttlweight);
 printf("\n");
 printf("Thanks for using Packer! See you next time...\n");

return 0;
}
double iVolume(double l, double w, double h, double th)
{
  return ((l - (2 * (th))) * (w - (2 * (th))) * (h - (2 * (th))));
}
double usedSpace(double di, double l, double w, double h, int blsfit, double intvolume)
{
  return (intvolume - (((di) * (di) * (di)) * blsfit));
}
