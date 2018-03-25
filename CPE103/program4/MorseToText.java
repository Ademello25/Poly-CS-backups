
/**
 * Description
 *
 * @author Alexander DeMello
 * @version program 4
 */
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class MorseToText implements BSTTranslator<MorseOrder>
{

   private BST<MorseOrder> cypher;

   private void insertHelper(int left, int right, MorseOrder[] arr)
   {
      if (left > right)
      {
         return;
      }
      cypher.insert(arr[(left + right) / 2]);
      insertHelper(left, ((left + right) / 2) - 1, arr);
      insertHelper(((left + right) / 2) + 1, right, arr);
   }

   public MorseToText()
   {
      MorseOrder[] tmpArr = new MorseOrder[MorseOrder.size()];
      for (int i = 0; i < tmpArr.length; i++)
      {
         tmpArr[i] = new MorseOrder(MorseOrder.get(i));
      }
      Arrays.sort(tmpArr);
      cypher = new BST<MorseOrder>();
      insertHelper(0, tmpArr.length - 1, tmpArr);
   }

   public BST getBST()
   {
      return cypher;
   }

   public String translate(String s)
   {
      Scanner sc = new Scanner(s);
      sc.useDelimiter("[ ]");
      String result = new String();

      while (sc.hasNext())
      {
         String str = sc.next();
         if (str.equals(""))
         {
            result += " ";
         }
         else
         {
            result += cypher.get(new MorseOrder(null, str)).getCharacter();
         }
      }

      return result;
   }

}
