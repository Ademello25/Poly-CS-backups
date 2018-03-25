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

/* FUNCTION PROTOTYPE
 *
 * Function Name: char* strconcatenate
 *
 * Description: tacks the from string to the end of the to string
 *
 * Preconditions: Caller must ensure that the to string is large enough to hold the
 *                to & from strings
 *
 * Parameters:
 *    *to - the string that stays in place
 *  *from - the string tacked on the end
 *
 * Return: Returns a string that consists of to, then from
 */
char* strconcatenate(char *to, const char*from);

/* FUNCTION PROTOTYPE
 *
 * Function Name: char* strreplace
 *
 * Description: Finds characters and replaces them with "replaceWith"
 *
 * Preconditions: return type is character
 *
 * Parameters:
 *    *str - initial string
 *  *find - the character to be found
 *  *replaceWith - the character used to replace "*find"
 *
 * Return: a string with replaced letters
 */
char* strreplace(char *str, char find, char replaceWith);

/* FUNCTION PROTOTYPE
*
* Function Name: char* strcontains
*
* Description: determines if string 2 is within string 1 at any point
*
* Preconditions: return type is int
*
* Parameters:
*  *str1 - The overall string
*  *str2 - The string being searched for
*
* Return: a string with replaced letters
*/
int strcontains(const char *str1, const char *str2);

/* FUNCTION PROTOTYPE
 *
 * Function Name: char* strreverse
 *
 * Description: reverses the string it's given
 *
 * Preconditions: return type is a string
 *
 * Parameters:
 *  *str - the string to be reversed! 
 *
 * Return: a reversed string.
 */
char* strreverse(char *str);
#endif
