/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import org.junit.Test;
import static org.junit.Assert.*;

/**
 * TESTS FOR PRIORITY QUEUE METHODS!!!!!!!!!!
 * 
 * @author Alexander DeMello
 * @version program 3
 */
public class PriorityQueueTests<E>
{
   
   @Test
   public void testSizeAtCon1()
   {
      Integer[] arrr = new Integer[] {1};
      PriorityQueue<Integer> q = new PriorityQueue<Integer>(arrr, 1);
      assertTrue(q.size() == 1);
   }
   
   @Test
   public void testSizeAtCon2()
   {
      PriorityQueue<Integer> q = new PriorityQueue<Integer>(true);
      assertTrue(q.size() == 0);
   }
   
   @Test
   public void testSizeAtCon3()
   {
      PriorityQueue<Integer> q = new PriorityQueue<Integer>();
      assertTrue(q.size() == 0);
   }
   
   @Test
   public void testSizeAtCon5()
   {
      Integer[] arr = new Integer[] {1};
      PriorityQueue<Integer> q = new PriorityQueue<Integer>(arr, 1, true);
      assertTrue(q.size() == 1);
   }
   

   /**
    * Test of enqueue method, of class PriorityQueue.
    */
   @Test
   public void testEnqueue() {
      System.out.println("enqueue");
      PriorityQueue q = new PriorityQueue();
      q.enqueue(55);
      q.enqueue(26);
      q.enqueue(39);
      q.enqueue(45);
      q.enqueue(21);
      q.enqueue(43);
      q.enqueue(26);
      q.enqueue(37);
      q.enqueue(19);
      assertEquals(19, q.peek());
      
      
   }

   /**
    * Test of dequeue method, of class PriorityQueue.
    */
   @Test
   public void testDequeue() 
   {
      System.out.println("dequeue min");
      PriorityQueue q = new PriorityQueue();
      q.enqueue(55);
      q.enqueue(26);
      q.enqueue(39);
      q.enqueue(45);
      q.enqueue(21);
      q.enqueue(43);
      q.enqueue(26);
      q.enqueue(37);
      q.enqueue(19);
      assertEquals(19, q.dequeue());
      assertEquals(21, q.dequeue());
      assertEquals(26, q.dequeue());
      assertEquals(26, q.dequeue());
      assertEquals(37, q.dequeue());
      assertEquals(39, q.dequeue());
      assertEquals(43, q.dequeue());
      assertEquals(45, q.dequeue());
      assertEquals(55, q.dequeue());

   }
   
   @Test
   public void testPeek()
   {
      PriorityQueue q = new PriorityQueue();
      q.enqueue("alex");
      Object result = (Object)q.peek();
      
      assertEquals("alex",result);
   }
   
   @Test
   public void testSize()
   {
      PriorityQueue q = new PriorityQueue();
      q.enqueue(1);
      q.enqueue(2);
      q.enqueue(3);
      q.enqueue(4);
      int result = q.size();
      assertEquals(4, result);
   }
   
   @Test
   public void testSortMin()
   {
      Integer[] arr = new Integer[] {11, 24, 7, 25, 3, 1, 31, 6, 97};
      Integer[] exp = new Integer[] {1, 3, 6, 7, 11, 24, 25, 31, 97};
      PriorityQueue<Integer> q = new PriorityQueue<Integer>(arr, arr.length, false);
       
      for (int i = 0; i < exp.length; i++)
      {
         assertEquals(q.size(), exp.length - i);
         assertEquals(q.peek(), exp[i]);
         assertEquals(q.dequeue(), exp[i]);
      }

      assertTrue(q.size() == 0);
   }
   
   public void testSortMax()
   {
      Integer[] arr = new Integer[] {11, 24, 7, 25, 3, 1, 31, 6, 97};
      Integer[] exp = new Integer[] {97, 31, 25, 24, 11, 7, 6, 3, 1};
      PriorityQueue<Integer> q = new PriorityQueue<Integer>(arr, arr.length, true);
       
      for (int i = 0; i < exp.length; i++)
      {
         assertEquals(q.size(), exp.length - i);
         assertEquals(q.peek(), exp[i]);
         assertEquals(q.dequeue(), exp[i]);
      }

      assertTrue(q.size() == 0);
   }
   
   @Test
   public void testKth()
   {
      Integer[] arr = new Integer[] {11, 24, 7, 25, 3, 1, 31, 6, 97};
      
      assertTrue(PriorityQueue.kth(arr, arr.length, 1) == 97);
      assertTrue(PriorityQueue.kth(arr, arr.length, 2) == 31);
      assertTrue(PriorityQueue.kth(arr, arr.length, 3) == 25);
      assertTrue(PriorityQueue.kth(arr, arr.length, 4) == 24);
      assertTrue(PriorityQueue.kth(arr, arr.length, 5) == 11);
      assertTrue(PriorityQueue.kth(arr, arr.length, 6) == 7);
      assertTrue(PriorityQueue.kth(arr, arr.length, 7) == 6);
      assertTrue(PriorityQueue.kth(arr, arr.length, 8) == 3);
      assertTrue(PriorityQueue.kth(arr, arr.length, 9) == 1);
   }


}
