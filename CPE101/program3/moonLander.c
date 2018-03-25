/* Provided code for Moonlander. It is provided so that you don't have to
 * produce the starting splash screen - very tedious! After displaying the
 * splash it simply calls simulateLM() - one of the functions you must write
 * for this assignment!
 *
 * DO NOT MODIFY IN ANY WAY!
 *
 * Author: Kurt Mammen
 *
 * Version History:
 *
 * 05/21/11 - Initial revision
 *
 */
#include <stdio.h>
#include "landerUI.h"

int main()
{
   printf("********************************************************************************\n");
   printf("*                  Welcome to the Moonlander Simulator!                        *\n");
   printf("*                                                                              *\n");
   printf("*    You have been asked to pilot the Lunar Module from the Orbiter to the     *\n");
   printf("*    surface of the Moon using as little fuel as possible AND landing at a     *\n");
   printf("*     safe speed. Enter fuel rates between 0 and 9, inclusive to control       *\n");
   printf("*       your velocity but be sure to land before running out of fuel!          *\n");
   printf("*                                                                              *\n");
   printf("*                Good luck and may the Force be with you!                      *\n");
   printf("********************************************************************************\n");
   printf("\n");

   /* This function, among others,  is to be written by you for this assignment */
   simulateLM();

   return 0;
}
