/*
 * Assignment: Program 3
 * Course: CPE 101
 * Author: Alexander DeMello
 */

#include <stdio.h>
#include "landerFuncs.h"

double acceleration(int nfrate)
{
   return G * ((nfrate/5.0) -1);
}

double altitude(double oldalt, double oldv, double newacc)
{
   double result = (oldalt + oldv +(newacc/2));
   return ((result < 0) ? 0 : result);
}

double velocity(double oldv, double newacc)
{
   return (oldv + newacc);
}

int fuelLevel(int oldfuel, int nfrate)
{
   return (((oldfuel - nfrate) < 0) ? 0 : (oldfuel -nfrate));
}
