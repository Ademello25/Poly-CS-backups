

import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for a simplified Array stack class
 * 
 * @author kurokaze22
 * @version Lab 3
 */
public class SimpleArrayStackTests 
{
   
   /**
    * Test of peek method, of class SimpleArrayStack.
    */
   @Test
   public void testPeek() 
   {
      System.out.println("peek");
      SimpleArrayStack stack = new SimpleArrayStack();
      Object expResult = 5;
      stack.push(expResult);
      Object result = stack.peek();
      assertTrue(stack.size() == 1);
      assertEquals(expResult, result);
      
   }


   /**
    * Test of push method, of class SimpleArrayStack.
    */
   
   @Test
   public void testPush() 
   {
      System.out.println("push");
      SimpleArrayStack stack = new SimpleArrayStack();
      Object expResult = "Alex";
      stack.push(expResult);
      Object result = stack.peek();
      assertTrue(stack.size() == 1);
      assertEquals(expResult, result);
      
   }

   /**
    * Test of size method, of class SimpleArrayStack.
    */
   @Test
   public void testSizeAtCon() 
   {
      System.out.println("size");
      SimpleArrayStack instance = new SimpleArrayStack();
      int expResult = 0;
      int result = instance.size();
      assertEquals(expResult, result);
   }
   
   @Test
   public void testSizeAftAdd() 
   {
      System.out.println("size");
      SimpleArrayStack instance = new SimpleArrayStack();
      instance.push(5);
      int expResult = 1;
      int result = instance.size();
      assertEquals(expResult, result);
   }
   
   @Test 
   public void testSizeAftRem() 
   {
      System.out.println("size");
      SimpleArrayStack instance = new SimpleArrayStack();
      instance.push(5);
      instance.push("Alex");
      instance.pop();
      int expResult = 1;
      int result = instance.size();
      assertEquals(expResult, result);
   }
   
   @Test(expected = NoSuchElementException.class)
   public void testPeekException()
   {
      System.out.println("peeking on an empty list");
      SimpleArrayStack stack = new SimpleArrayStack();
      stack.peek();
   }
   
   @Test(expected = NoSuchElementException.class)
   public void testPopException()
   {
      System.out.println("peeking on an empty list");
      SimpleArrayStack stack = new SimpleArrayStack();
      stack.pop();
   }
   
}
