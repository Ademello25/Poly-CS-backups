
/**
 * Description
 *
 * @author Alexander DeMello
 * @version program 4
 */
public class CharacterOrder extends MorseCode implements Comparable<CharacterOrder>
{

   public CharacterOrder(Character character, String code)
   {
      super(character, code);
   }

   public CharacterOrder(MorseCode that)
   {
      super(that);
   }

   public int compareTo(CharacterOrder c)
   {
      String class1 = this.getClass().getName();
      String class2 = c.getClass().getName();
      int res =(class1.compareTo(class2));
      
      if(this == c)
      {
         return 0;
      }
      
      if(res < 0)
      {
         return -55;
      }
      else if(res > 0)
      {
         return 55;
      }
      else
      {
         Character char1 = this.getCharacter();
         Character char2 = c.getCharacter();
         if(char1.compareTo(char2) > 0)
         {
            return 55;
         }
         else if(char1.compareTo(char2) < 0)
         {
            return -55;
         }         
         else
         {
            return 0;
         }
      }
   }
}
