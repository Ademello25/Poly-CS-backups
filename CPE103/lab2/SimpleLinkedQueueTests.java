import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for a linked list implementation for a queue
 * 
 * @author Alexander DeMello
 * @version Lab 2
 */

public class SimpleLinkedQueueTests
{
   
   @Test
   public void testPeek()
   {
      System.out.println("testing peek");
      SimpleLinkedQueue list = new SimpleLinkedQueue();
      Object expResult = 10;
      list.enqueue(expResult);
   }

   @Test
   public void testEnqueueInt() 
   {
      System.out.println("appending an integer");
      int data = 5;
      SimpleLinkedQueue instance = new SimpleLinkedQueue();
      instance.enqueue(data);
      instance.enqueue(5);
      int result = (Integer)instance.peek();
      assertTrue(result == data);
   }
   
      public void testEnqueueString()
   {
      System.out.println("Testing enqueue");
      SimpleLinkedQueue list = new SimpleLinkedQueue();
      Object s = "Alex";
      list.enqueue(s);
      Object result = list.peek();
      assertTrue(result == s);
   }
   
   @Test
   public void testDequeueString()
   {
      System.out.println("testing Dequeue");
      SimpleLinkedQueue list = new SimpleLinkedQueue();
      Object expResult = "Alex";
      list.enqueue(expResult);
      Object result = list.dequeue();
      System.out.println(result);
      
      assertTrue(result == expResult);
      assertTrue(list.size() == 0);
      
   }
   @Test
   public void testdequeueMultiple()
   {
      System.out.println("Testing Dequeueing");
      SimpleLinkedQueue list = new SimpleLinkedQueue();
      Object expResult1 = "Alex";
      Object expResult2 = 55;
      list.enqueue(expResult1);
      list.enqueue(expResult2);
      
      System.out.println(list.peek());
      Object result1 = list.dequeue();
      System.out.println(list.peek());
      Object result2 = list.dequeue();
      assertTrue(expResult1 == result1);
      assertTrue(result2 == expResult2);
   }
   
   @Test(expected = NoSuchElementException.class)
   public void testDequeueException()
   {
      System.out.println("dequeing on an empty list");
      SimpleLinkedQueue q = new SimpleLinkedQueue();
      q.dequeue();
   }
   
   @Test(expected = NoSuchElementException.class)
   public void testPeekException()
   {
      System.out.println("peeking on an empty list");
      SimpleLinkedQueue q = new SimpleLinkedQueue();
      q.peek();
   }
   
   @Test
   public void testSizeAtConstruction()
   {
      System.out.println("testing size");
      SimpleLinkedQueue q = new SimpleLinkedQueue();
      int result = q.size();
      assertTrue(result == 0);
   }
   
   @Test
   public void testSizeAfterAction()
   {
      System.out.println("testing size more");
      SimpleLinkedQueue q = new SimpleLinkedQueue();
      q.enqueue(5);
      q.enqueue(55);
      q.dequeue();
      int result = q.size();
      assertTrue(result == 1);
   }
   

}
