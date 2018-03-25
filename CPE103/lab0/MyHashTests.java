import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kurokaze22
 */
public class MyHashTests
{

   /**
    * Test of hash method, of class MyHash.
    */
   @Test
   public void testHash()
   {
      System.out.println("hash");
      String input = "";
      MyHash instance = new MyHash();
      int expResult = 0;
      int result = instance.hash(input);
      assertEquals(expResult, result);

   }
   
}
