/*
 * Assignment: Program 3
 * Course: CPE 101
 * Author: Alexander DeMello
 */

#ifndef Program_3_landerUI_h
#define Program_3_landerUI_h

#define INITIAL_ALTITUDE 1300
#define INITIAL_VELOCITY 0
#define INITIAL_FUEL_LEVEL 500
#define INITIAL_FUEL_RATE 0

typedef struct
{
   double acceleration,
              altitude,
              velocity;
   int fuelLevel,
        fuelRate;
}LMState;

/* FUNCTION PROTOTYPE
 *
 * Function Name: simulateLM
 *
 * Description: simulates the status of the LM
 *
 * Preconditions: N/A
 *
 * Parameters: void
 *
 * Return: void
 */
void simulateLM(void);

/* FUNCTION PROTOTYPE
 *
 * Function Name: getFuelRate
 *
 * Description: scans fuel rate from user
 *
 * Preconditions: integer parameter
 *
 * Parameters:
 *    currfuel - the current amount of fuel in the lunar module
 *
 * Return: The new fuel rate
 */
int getFuelRate(int currfuel);

/* FUNCTION PROTOTYPE
 *
 * Function Name: display LMstate
 *
 * Description: prints the state of the lunar module
 *
 * Preconditions: cstate is a struct defined above
 *
 * Parameters:
 *   Time - the amount of time that has passed since the launch of the LM (in seconds)
 *   cstate - a structure consisting of acceleration, velocity, altitude, fuel level, and fuel rate
 *
 * Return: prints the state of the LM at the current time
 */
void displayLMState(int time, LMState cstate);

/* FUNCTION PROTOTYPE
 *
 * Function Name: displayLandingStatus
 *
 * Description: Displays whether or not the LM landed safely based on landing velocity
 *
 * Preconditions: vfinal is a double, do not expect accurate results if it is not of this type
 *                on input
 *
 * Parameters:
 *   vfinal - the final velocity of the LM
 *
 * Return: void type, prints the LM's status at landing
 */
void displayLandingStatus(double vfinal);


#endif
