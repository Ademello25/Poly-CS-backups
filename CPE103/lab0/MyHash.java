/**
 * 
 * 
 * @author Alexander DeMello
 * @version lab 9
 */
public class MyHash implements Hashable<String>
{
   public MyHash()
   {
      
   }
   public int hash(String input)
   {
      int hash  = 0;
      int len = input.length();
      
      for(int i =0; i < len; i ++)
      {
         hash = hash*53 + input.charAt(i);
      }
      
      return hash;
   }
   
}
