#ifndef MULTI_DIM_H
#define MULTI_DIM_H

/*
 * Constants that define the size of each of the 5 dimensions.
 */
#define DIM_1 8
#define DIM_2 5
#define DIM_3 11
#define DIM_4 7
#define DIM_5 6 

/*
 * Structure to represent an index for each of the five dimensions.
 */
typedef struct
{
   int d1, d2, d3, d4, d5;
}
Index5D;

/* FUNCTION PROTOTYPE
 *
 * Function Name: average
 *
 * Description: Returns the average the of all values in the array.
 *
 * Preconditions: Array must have the specfied dimensions.
 *
 * Parameters:
 *
 *   a - The 5-dimensional array to return the average value of.
 *
 * Return: The average of all values in the array.
 */
double average(int a[DIM_1][DIM_2][DIM_3][DIM_4][DIM_5]);

/* FUNCTION PROTOTYPE
 *
 * Function Name: findFirst
 *
 * Description: Finds the first occurrence of the specified value in the array.
 *              Note that the first occurrence is considered to be the one with
 *              the lowest index in the first dimension, then lowest in second,
 *              then lowest in third, then lowest in fourth, then lowest in
 *              fifth. For example:
 *
 *              a[2][2][2][2][0] is a lower index than a[2][2][2][2][1]
 *              a[0][2][2][2][0] is a lower index than a[1][0][0][0][0]
 *              a[2][2][2][1][3] is a lower index than a[2][2][2][2][0]
 *
 *              To achieve this efficiently you must search foreward through
 *              the array vary the first index in the outer-most loop and the
 *              fifth dimesion in the inner most loop.
 *
 * Preconditions: Array must have the specfied dimensions.
 *
 * Parameters:
 *
 *       a - The 5-dimensional array to search.
 *   value - The value to find the first occurrence of.
 *
 * Return: An Index5D structure (defined in this header file) with its member
 *         fields set the the indecies of first occurrence of the specfied
 *         value OR all set to -1 if the value is not found in the array.
 */
Index5D findFirst(int a[DIM_1][DIM_2][DIM_3][DIM_4][DIM_5], int value);

/* FUNCTION PROTOTYPE
 *
 * Function Name: findLast
 *
 * Description: Finds the last occurrence of the specified value in the array.
 *              Note that the last occurrence is considered to be the one with
 *              the highest index in the first dimension, then highest in
 *              second, then highest in third, then highest in fourth, then
 *              highest in fifth. For example:
 *
 *              a[2][2][2][2][2] is a higher index than a[2][2][2][2][1]
 *              a[2][0][0][0][0] is a higher index than a[1][2][2][2][2]
 *              a[2][2][2][3][1] is a higher index than a[2][2][2][2][3]
 *
 *              To achieve this efficiently you must search backward through
 *              the array vary the first index in the outer-most loop and the
 *              fifth dimesion in the inner most loop.
 *
 * Preconditions: Array must have the specfied dimensions.
 *
 * Parameters:
 *
 *       a - The 5-dimensional array to search.
 *   value - The value to find the last occurrence of.
 *
 * Return: An Index5D structure (defined in this header file) with its member
 *         fields set the the indecies of last occurrence of the specfied
 *         value OR all set to -1 if the value is not found in the array.
 */
Index5D findLast(int a[DIM_1][DIM_2][DIM_3][DIM_4][DIM_5], int value);

/* FUNCTION PROTOTYPE
 *
 * Function Name: findMin
 *
 * Description: Finds and returns the minimum value in the array.
 *
 * Preconditions: Array must have the specfied dimensions.
 *
 * Parameters:
 *
 *   a - The 5-dimensional array to return the average value of.
 *
 * Return: The minimum value in the array.
 */
int findMin(int a[DIM_1][DIM_2][DIM_3][DIM_4][DIM_5]);

/* FUNCTION PROTOTYPE
 *
 * Function Name: countOfMins
 *
 * Description: Returns the number of times the minimum value occurs in the
 *              array.
 *
 * Preconditions: Array must have the specfied dimensions.
 *
 * Parameters:
 *
 *   a - The 5-dimensional array to return the average value of.
 *
 * Return: A count of the number of times the minimum value occurs in the
 *         array.
 */
int countOfMins(int a[DIM_1][DIM_2][DIM_3][DIM_4][DIM_5]);

#endif

