/**
 * 
 * 
 * @author Alexander DeMello
 * @version lab 9
 */
public class StringHash implements Hashable <String>
{
   public StringHash()
   {
      
   }
   
   public int hash(String input)
   {
      int result = 0;
      int len = input.length();
      
      for(int i=0; i < len; i++)
      {
         result = 31*result+input.charAt(i);
         
      }
      
      return result;
   }
}
