import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for a circular array based queue
 *
 * @author Alexander DeMello
 * @version program 1
 */
public class CircularQueueTests 
{

   @Test
   public void testDequeue() 
   {
      System.out.println("dequeue");
      CircularQueue qu = new CircularQueue();
      Object expResult = "Alex";
      qu.enqueue(expResult);
      Object result = qu.dequeue();
      assertEquals(expResult, result);
   }
   
   @Test(expected = NoSuchElementException.class)
   public void testRemException()
   {
      System.out.println("peeking on an empty list");
      CircularQueue stack = new CircularQueue();
      stack.dequeue();
   }
   /**
    * Test of enqueue method, of class CircularQueue.
    */
   @Test
   public void testEnqueue() 
   {
      System.out.println("enqueue");
      Object element = "Alex";
      CircularQueue instance = new CircularQueue();
      instance.enqueue(element);
      Object result = instance.peek();
      assertEquals(element, result);
 
   }
   
   @Test
   public void testEnqueueWrap() 
   {
      System.out.println("enqueue");
      CircularQueue instance = new CircularQueue();
      instance.enqueue(1);
      instance.enqueue(2);
      instance.enqueue(3);
      instance.enqueue(4);
      instance.enqueue(5);
      instance.enqueue(6);
      instance.enqueue(7);
      instance.enqueue(8);
      instance.enqueue(9);
      instance.dequeue();
      instance.dequeue();
      instance.enqueue(10);
      instance.enqueue(11);
      Object[] resq = instance.unusualMethodForTestingPurposesOnly();
      Object result = resq[0];
      assertEquals(11, result);
 
   }
   
   @Test
   public void testEnqueueWrapTwo() 
   {
      System.out.println("enqueue");
      CircularQueue instance = new CircularQueue();
      instance.enqueue(1);
      instance.enqueue(2);
      instance.enqueue(3);
      instance.enqueue(4);
      instance.enqueue(5);
      instance.enqueue(6);
      instance.enqueue(7);
      instance.enqueue(8);
      instance.enqueue(9);
      instance.enqueue(10);
      instance.enqueue(11);
      instance.dequeue();
      instance.enqueue(12);
      instance.enqueue(13);
      instance.enqueue(14);
      instance.enqueue(15);
      instance.enqueue(16);
      instance.enqueue(17);
      instance.enqueue(18);
      instance.enqueue(19);
      instance.enqueue(20);
      instance.enqueue(21);
      Object[] resq = instance.unusualMethodForTestingPurposesOnly();
      Object result = resq[11];
      Object result2 = resq[0];
      assertEquals(12, result);
      assertEquals(21, result2);
 
   }

   /**
    * Test of peek method, of class CircularQueue.
    */
   @Test
   public void testPeek() 
   {
      System.out.println("peek");
      CircularQueue instance = new CircularQueue();
      Object expResult = 5;
      instance.enqueue(expResult);
      instance.enqueue(10);
      Object result = instance.peek();
      assertEquals(5, result);
   }
   
   @Test(expected = NoSuchElementException.class)
   public void testPeekException()
   {
      System.out.println("peeking on an empty list");
      CircularQueue stack = new CircularQueue();
      stack.peek();
   }

   /**
    * Test of size method, of class CircularQueue.
    */
   @Test
   public void testSizeAtConstruction() 
   {
      System.out.println("size");
      CircularQueue qu = new CircularQueue();
      int expResult = 0;
      int result = qu.size();
      assertEquals(expResult, result);
   }
   
   @Test
   public void testSizeAftAdd() 
   {
      System.out.println("size");
      CircularQueue qu = new CircularQueue();
      qu.enqueue(5);
      qu.enqueue(2);
      int expResult = 2;
      int result = qu.size();
      assertEquals(expResult, result);
   }
   
   @Test
   public void testSizeAftremove() 
   {
      System.out.println("size");
      CircularQueue qu = new CircularQueue();
      qu.enqueue(5);
      qu.enqueue(2);
      qu.dequeue();
      int expResult = 1;
      int result = qu.size();
      assertEquals(expResult, result);
   }
   
   @Test
   public void testSizeAftincrease() 
   {
      System.out.println("size");
      CircularQueue qu = new CircularQueue();
      qu.enqueue(5);
      qu.enqueue(2);
      qu.enqueue(10);
      qu.enqueue(15);
      qu.enqueue(1);
      qu.enqueue(-5);
      qu.enqueue(100);
      qu.enqueue("alex");
      qu.enqueue(60);
      qu.enqueue("hi");
      qu.enqueue(55);
      
      int expResult = 11;
      int result = qu.size();
      assertEquals(expResult, result);
   }

}
