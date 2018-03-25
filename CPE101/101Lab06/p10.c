/* 
 * Assignment: Lab 06
 * Course: CPE 101
 * Author: Alexander DeMello
 */

char letter(int r, int c)
{
   if(c < 10)
   {
      if(r < 6)
      {
         if((2 * (r)) > c)
         {
	    return 'D';
         }
         else
         {
	    return 'A';
         }
      }
      else
      {
	 if((11 - (.5 * c) - (r + 1)) > 0)
         {
	    return 'B'; 
         }
         else
         {
	    return 'C';
         }
      }
   }
   else
   {
      if(r < 6)
      {
	 if((11 -(.5 *c) - (r + 1)) > 0)
	 {
	    return 'A';
         }
         else
	 {
	    return 'B';
	 }
      }
      else
      {
	if((2 * r) > c)
	{
	   return 'C';
        }
        else
	{
	   return 'D';
        }
      }
   }
}
