
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kurokaze22
 */
public class TextToMorseTest
{

   @Test
   public void testGetBST()
   {
      System.out.println("getBST");
      TextToMorse instance = new TextToMorse();
      BST expResult = null;
      BST result = instance.getBST();
      assertEquals(0, 0);

   }

   /**
    * Test of translate method, of class TextToMorse.
    */
   @Test
   public void testTranslate()
   {
      System.out.println("translate");
      String s = "";
      TextToMorse instance = new TextToMorse();
      String expResult = "";
      String result = instance.translate(s);
      assertEquals(0, 0);
   }
   
}
