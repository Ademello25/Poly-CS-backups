/* 
 * Assignment: Lab 13
 * Course: CPE 101
 * Author: ALexander Demello
 */

#ifndef Lab_13_strings_h
#define Lab_13_strings_h

/* FUNCTION PROTOTYPE
 *
 * Function Name: strlength
 *
 * Description: Returns the length of the string, for example the number of characters in the string
 *              not including NULL
 *
 * Preconditions: must input valid string
 *
 * Parameters:
 *    *str - a pointer to a string
 *
 * Return: the length of the input string
 */
int strlength(const char *str);

/* FUNCTION PROTOTYPE
 *
 * Function Name: strequals
 *
 * Description: Returns whether or not the two strings are the same or not
 *
 * Preconditions: both strings must be valid
 *
 * Parameters:
 *    *str1 - the first string
 *    *str2 - the second string
 *
 * Return: Returns a non zero int for true and 0 for false
 */
int strequals(const char *str1, const char *str2);

/* FUNCTION PROTOTYPE
 *
 * Function Name: strcompare
 *
 * Description: compares the values of the two strings
 *
 * Preconditions: both strings must be valid
 *
 * Parameters:
 *    str1 - the first string
 *    str2 - the second string
 *
 * Return: Returns a negative int when str1 is less the str2 0 when they are equal and a positive
 *         int if str2 is bigger
 */
int strcompare(const char *str1, const char *str2);

/* FUNCTION PROTOTYPE
 *
 * Function Name: char* strcopy
 *
 * Description: copies one string to another
 *
 * Preconditions: Caller must ensure that the to string is large enough to hold the from string
 *
 * Parameters:
 *    *to - the place that is being copied to
 *  *from - the place that is being copied from
 *
 * Return: Returns a string identical to "*from"
 */
char* strcopy(char *to, const char *from);

#endif
