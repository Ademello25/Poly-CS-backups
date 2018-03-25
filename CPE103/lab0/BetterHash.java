/**
 * 
 * 
 * @author Alexander DeMello
 * @version lab 9
 */
public class BetterHash implements Hashable <String>
{
   public BetterHash()
   {
      
   }
   
   public int hash(String input)
   {
      int hash = 0;
      int len = input.length();
      
      for(int i = 0; i<len;i++)
      {
         hash = hash*97 + input.charAt(i);
      }
      
      return hash;
   }
}
