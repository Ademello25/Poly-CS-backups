
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alexander DeMello
 * @version lab9
 */
public class StringHashTests
{

   /**
    * Test of hash method, of class StringHash.
    */
   @Test
   public void testHash()
   {
      System.out.println("hash");
      String input = "Halo";
      StringHash instance = new StringHash();
      int expResult = 2241628;
      int result = instance.hash(input);
      assertEquals(expResult, result);
   }
   
   @Test
   public void testHash2()
   {
      System.out.println("hash");
      String input = "Foxhound";
      StringHash instance = new StringHash();
      int expResult = 706299701;
      int result = instance.hash(input);
      assertEquals(expResult, result);
   }
   
}
