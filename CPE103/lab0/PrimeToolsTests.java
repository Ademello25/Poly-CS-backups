
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the prime tools.
 * 
 * @author Alexander DeMello
 * @version lab9
 */
public class PrimeToolsTests
{
   @Test
   public void testIsPrime()
   {
      System.out.println("isPrime");
      int value = 4;
      boolean expResult = false;
      boolean result = PrimeTools.isPrime(value);
      assertEquals(expResult, result);
   }
   
   @Test
   public void testIsPrime1()
   {
      System.out.println("isPrime");
      int value = 2;
      boolean expResult = true;
      boolean result = PrimeTools.isPrime(value);
      assertEquals(expResult, result);
   }
   
   @Test
   public void testIsPrime2()
   {
      System.out.println("isPrime");
      int value = 997;
      boolean expResult = true;
      boolean result = PrimeTools.isPrime(value);
      assertEquals(expResult, result);
   }
   
   @Test
   public void testIsPrime3()
   {
      System.out.println("isPrime");
      int value = 942;
      boolean expResult = false;
      boolean result = PrimeTools.isPrime(value);
      assertEquals(expResult, result);
   }

   /**
    * Test of nextPrime method, of class PrimeTools.
    */
   @Test
   public void testNextPrime()
   {
      System.out.println("nextPrime");
      int value = 2;
      int expResult = 2;
      int result = PrimeTools.nextPrime(value);
      assertEquals(expResult, result);
   }
   
   @Test
   public void testNextPrime1()
   {
      System.out.println("nextPrime");
      int value = 975;
      int expResult = 977;
      int result = PrimeTools.nextPrime(value);
      assertEquals(expResult, result);
   }
   
}
