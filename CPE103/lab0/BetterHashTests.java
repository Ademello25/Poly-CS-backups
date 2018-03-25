
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alexander DeMello
 * @version lab 9
 */
public class BetterHashTests
{
   

   /**
    * Test of hash method, of class BetterHash.
    */
   @Test
   public void testHash()
   {
      System.out.println("hash");
      String input = "Halo";
      BetterHash instance = new BetterHash();
      int expResult = 66635716;
      int result = instance.hash(input);
      assertEquals(expResult, result);
   }
   
   @Test
   public void testHash1()
   {
      System.out.println("hash");
      String input = "Foxhound";
      BetterHash instance = new BetterHash();
      int expResult = -1065069653;
      int result = instance.hash(input);
      assertEquals(expResult, result);
   }
   
}
