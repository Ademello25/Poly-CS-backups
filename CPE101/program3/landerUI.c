/*
 * Assignment: Program 3
 * Course: CPE 101
 * Author: Alexander DeMello
 */

#include <stdio.h>
#include "landerFuncs.h"
#include "landerUI.h"


void simulateLM(void)
{
   int time;
   LMState cstate;
   cstate.fuelLevel = INITIAL_FUEL_LEVEL;
   cstate.altitude = INITIAL_ALTITUDE;
   cstate.velocity = INITIAL_VELOCITY;
   cstate.fuelRate = INITIAL_FUEL_RATE;
   
   for(time = 0; ; time ++)
   {
      displayLMState(time, cstate);
      if(cstate.altitude<=0){
         displayLandingStatus(cstate.velocity);
         return;
      }
      cstate.fuelRate = getFuelRate(cstate.fuelLevel);
      cstate.acceleration = acceleration(cstate.fuelRate);
      cstate.altitude = altitude(cstate.altitude, cstate.velocity, cstate.acceleration);
      cstate.velocity = velocity(cstate.velocity, cstate.acceleration);
      cstate.fuelLevel = fuelLevel(cstate.fuelLevel, cstate.fuelRate);

   }
}

int getFuelRate(int currfuel)
{
   int result;
   if (currfuel <= 0)
   {
      printf("\n");
      return 0;
   }
   
   scanf("%d", &result);
   result = ((result < currfuel) ? result : currfuel);
   result = (result > 9) ? 9: result;
   result = (result < 0) ? 0: result;
   
   return result;
}

void displayLMState(int time, LMState cstate)
{
   printf("Time:%4d Altitude: %7.2f Velocity: %7.2f Fuel: %3d Rate: %1d New Rate: ", time, cstate.altitude, cstate.velocity, cstate.fuelLevel, cstate.fuelRate);
}
void displayLandingStatus(double vfinal)
{
      if(vfinal > -5.0)
      {
         printf("\n\nThe Eagle has landed!\n");
      }
      else if(vfinal <= -5.0 && vfinal > -10.0)
      {
         printf("\n\nSerious damage to LM - one-way ticket - enjoy your oxygen while it lasts\n");
      }
      else
      {
         printf("\n\nOuch, that hurt! I guess you won't be making that one small step for man...\n");
      }
}