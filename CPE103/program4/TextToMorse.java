
/**
 * Description
 *
 * @author Alexander DeMello
 * @version program 4
 */
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class TextToMorse implements BSTTranslator<CharacterOrder>
{

   private BST<CharacterOrder> cypher;

   private void insertHelper(int left, int right, CharacterOrder[] arr)
   {
      if (left > right)
      {
         return;
      }
      cypher.insert(arr[(left + right) / 2]);
      insertHelper(left, ((left + right) / 2) - 1, arr);
      insertHelper(((left + right) / 2) + 1, right, arr);
   }

   public TextToMorse()
   {
      CharacterOrder[] tmpArr = new CharacterOrder[CharacterOrder.size()];
      for (int i = 0; i < tmpArr.length; i++)
      {
         tmpArr[i] = new CharacterOrder(CharacterOrder.get(i));
      }
      Arrays.sort(tmpArr);
      cypher = new BST<CharacterOrder>();
      insertHelper(0, tmpArr.length - 1, tmpArr);
   }

   public BST getBST()
   {
      return cypher;
   }

   public String translate(String s)
   {
      Scanner sc = new Scanner(s);
      //sc.useDelimiter("[ ]");
      String result = new String();

      while (sc.hasNext())
      {
         String str = sc.next();
         /* if (str.equals(" "))
          {
          result += "  ";
          }
          else
          { */
         for (char c : str.toCharArray())
         {
            result += (cypher.get(new CharacterOrder(c, null)).getCode() + " ");

         }
         result += " ";
         // }
      }

      return result.trim();
   }

}
