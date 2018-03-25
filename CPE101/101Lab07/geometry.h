#ifndef GEOMETRY_H
#define GEOMETRY_H

#include <stdio.h>
#include <math.h>
#include <stdlib.h>

typedef struct
{
   int x,
       y;
}Point;

typedef struct
{
   Point ll,
        upr;
}Rectangle;

/* FUNCTION PROTOTYPE
 * 
 * Function Name: distance
 *
 * Preconditions: Caller should know what distance means
 *
 * Parameters:
 *    Point p1 - The first point
 *    Point p2 - The second point
 *
 * Return: The distance the two points
 */
double distance(Point p1, Point p2);

/* FUNCTION PROTOTYPE
 * 
 * Function Name: area
 *
 * Preconditions: Caller should know what area means
 *
 * Parameters: 
 *    A: The information output from previous functions
 *
 * Return: The area of the input rectangle
 */
int area(Rectangle A);

#endif
    
