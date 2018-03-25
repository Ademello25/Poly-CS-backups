/*
 * Assignment: Program 2
 * Course: CPE101
 * Author: Alexander DeMello
 */

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
 * Function Name: makeRectangle
 *
 * Description: This function creates a rectangle
 *
 * Precondition: Caller must use valid input values
 *
 * Parameters:
 *    int llx - The x coordinate of the lower left corner of the rectangle
 *    int lly - The y coordinate of the lower left corner of the rectangle
 *       rectw - The width of the rectangle
 *       recth - The height of the rectangle
 *
 * Return: The function returns a Rectangle structure
 */
Rectangle makeRectangle(int llx, int lly, int rectw, int recth);

/* FUNCTION PROTOTYPE
 *
 * Function Name: valid
 *
 * Description: This function tells whether or not a rectangle is valid
 *
 * Precondition: Caller must call a rectangle
 *
 * Parameters:
 *    Rectangle A - A rectangle   
 * 
 * Return: Returns a non zero int if the rectangle is valid, or a 0 if the rectangle is invalid
 */
int valid(Rectangle A);

/* FUNCTION PROTOTYPE
 *
 * Function Name: width
 *
 * Description: If input is valid returns the width of a rectangle
 *
 * Precondition: Caller must input valid values
 *
 * Parameters:
 *    Rectangle A - A rectangle
 *
 * Return: The function returns the width of a rectangle, returns a 0 if the rectangle is invalid
 */
int width(Rectangle A);

/* FUNCTION PROTOTYPE
 *
 * Function Name: height
 *
 * Description: If input is valid returns the height of a rectangle
 *
 * Precondition: Caller must input valid values
 *
 * Parameters:
 *    Rectangle A - A rectangle
 *
 * Return: The function returns the height of a rectangle, returns a 0 if the rectangle is invalid
 */
int height(Rectangle A);

/* FUNCTION PROTOTYPE
 *
 * Function Name: area
 *
 * Description: This function takes in a Rectangle structure and returns the area of said rectangle
 *
 * Precondition: Caller must use valid input values
 *
 * Parameters:
 *    Rectangle - A rectangle
 *
 * Return: The function returns the area of a rectangle, returns a 0 if the rectangle is invalid
 */
int area(Rectangle A);

/* FUNCTION PROTOTYPE
 *
 * Function Name: perimeter
 *
 * Description: This function takes in a Rectangle structure and returns the perimeter of said
 *              rectangle
 *
 * Precondition: Caller must use valid input values
 *
 * Parameters:
 *    Rectangle - A rectangle
 *
 * Return: The function returns the structure of a rectangle, returns a 0 if the rectangle is invalid
 */
int perimeter(Rectangle A);

/* FUNCTION PROTOTYPE
 *
 * Function Name: intersects
 *
 * Description: Tells whether or not two rectangles intersect
 *
 * Precondition: Caller must use valid input rectangles
 *
 * Parameters:
 *    RectangleA - A rectangle
 *    RectangleB - A rectangle
 *
 * Return: The function returns whether or not the rectangles intersect, if false will return 0
 */
int intersects(Rectangle A, Rectangle B);

/* FUNCTION PROTOTYPE
 *
 * Function Name: boundingRectangle
 *
 * Description: Returns the smallest rectangle to encompass both rectangles
 *
 * Precondition: Caller must use valid rectangles
 *
 * Parameters:
 *    RectangleA - A rectangle
 *    RectangleB - A rectangle
 *
 * Return: The function returns the smallest rectangles possible to encompass both input rectangles
 *         of a rectangle, returns a 0 if the rectangle is invalid
 */
Rectangle boundingRectangle(Rectangle A, Rectangle B);

/* FUNCTION PROTOTYPE
 *
 * Function Name: intersection
 *
 * Description: Returns a rectangle formed by the intersection of the two rectangles
 *
 * Precondition: Caller must use valid rectangles
 *
 * Parameters:
 *    RectangleA - A rectangle
 *    RectangleB - A rectangle
 *
 * Return: The function returns the rectangle created by the intersection of two rectanles, returns 0
 *         if false
 */
Rectangle intersection(Rectangle A, Rectangle B);


#endif
