/*
 * Assignment: Lab 06
 * Course: CPE 101
 * Author: Alexander DeMello
 */

char letter(int r, int c)
{
   if(r < 10)
   {
     if((2 * r) <= c)
      {
	 return 'S';
      }
      else
      {
	 return'V';
      }
   }
   else
   {
      if(r > 9)
      {
	if((.5 * c) < (20 - r - 1))
         {
	    return 'V';
         }
         else
	 {
	    return 'S';
         }
      }
   }
   return 0;
}
      
       
