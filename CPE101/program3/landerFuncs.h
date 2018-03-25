/*
 * Assignment: Program 3
 * Course: CPE 101
 * Author: Alexander DeMello
 */

#ifndef Program_3_landerFuncs_h
#define Program_3_landerFuncs_h

#define G 1.62
/* FUNCTION PROTOTYPE
 * 
 * Function Name: acceleration
 * 
 * Description: when given a fuel rate gives the acceleration of the LM
 *
 * Preonditions: Must input an int that represents the new fuel rate (0-9)
 *
 * Parameters:
 *    nfrate - The new rate of fuel consumption
 *
 * Return: Returns the new acceleration of the lunar module
 */
double acceleration(int nfrate);

/* FUNCTION PROTOTYPE
 * 
 * Function Name: altitude
 * 
 * Description: when given the old altitude, old velocity, and new acceleration,
 *              calculates the altitude of the LM
 *
 * Preonditions: all parameters are doubles
 *
 * Parameters:
 *    oldalt- the old altitude of the LM
 *     oldv - the old velocity of the LM
 *   newacc - the new acceleration of the LM
 *
 * Return: Returns the altitude of the LM
 */
double altitude(double oldalt, double oldv, double newacc);

/* FUNCTION PROTOTYPE
 * 
 * Function Name: velocity
 * 
 * Description: when given the old velocity and new acceleration of LM, returns the velocity
 *
 * Preonditions: all parameters are double
 *
 * Parameters:
 *    oldv - The old velocity of the LM
 *  newacc - The new acceleration of the 
 *
 * Return: Returns the new velocity of the lunar module
 */
double velocity(double oldv, double newacc);

/* FUNCTION PROTOTYPE
 * 
 * Function Name: fuelLevel
 * 
 * Description: when given a fuel rate gives the remaining fuel of the LM
 *
 * Preonditions: Must input an int that represents the new/old fuel rate (0-9)
 *
 * Parameters:
 *    nfrate - The new rate of fuel consumption
 *   oldfuel - The old fuel rate of the LM
 *
 * Return: Returns the new fuel level of the lunar module
 */
int fuelLevel(int oldfuel, int nfrate);


#endif
