/*
 * Assignment: Lab 06
 * Course: CPE 101
 * Author: Alexander DeMello
 */

char letter(int r, int c)
{
   if( r == c)
   {
      return 'X';
   }
   if((r + c) == 6)
   {
      return 'X';
   }
   else
   {
      return 'O';
   }
}
