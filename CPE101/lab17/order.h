/*
 * Assignment: Lab 17
 * Course: CPE101
 * Author: Alexander DeMello
 */

#ifndef Lab_17_Header_h
#define Lab_17_Header_h

/* FUNCTION PROTOTYPE
 *
 * Function Name: swap
 *
 * Description: if the first value is larger than the second value it swaps them
 *
 * Preconditions: The two input values are pointers
 *
 * Parameters:
 *    val1: The pointer to the first value
 *    val2: The pointer to the second value
 *
 * Return: Returns the same two pointers with the values rearanged, or not.
 */
void swap(int* val1, int* val2);

/* FUNCTION PROTOTYPE
 *
 * Function Name: order
 *
 * Description: puts all values in acending order
 *
 * Preconditions: The three parameters are pointers to values
 *
 * Parameters:
 *    value1: The pointer to the first value
 *    value2: The pointer to the second value
 *    value3: The pointer to the third value
 *
 * Return: Returns all the values in ascending order.
 */
void order(int* value1, int * value2, int * value3);

#endif
