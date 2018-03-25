
/**
 *
 *
 * @author Alexander DeMello
 * @version program 4
 */
public class MorseOrder extends MorseCode implements Comparable<MorseOrder>
{

   public MorseOrder(Character character, String code)
   {
      super(character, code);
   }

   public MorseOrder(MorseCode that)
   {
      super(that);
   }

   public int compareTo(MorseOrder element)
   {
      String class1 = this.getClass().getName();
      String class2 = element.getClass().getName();
      int res = (class1.compareTo(class2));

      if (this == element)
      {
         return 0;
      }

      if (res < 0)
      {
         return -55;
      }
      else if (res > 0)
      {
         return 55;
      }
      else
      {
         String code1 = this.getCode();
         String code2 = element.getCode();
         if (code1.compareTo(code2) > 0)
         {
            return 55;
         }
         else if (code1.compareTo(code2) < 0)
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
