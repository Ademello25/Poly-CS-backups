/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for a recursive linked list.
 * 
 * @author Alexander DeMello
 * @version lab 6
 */
public class RecursiveLinkedListTests 
{
   @Test
   public void testAddIndex()
   {
      System.out.println("testing adding at an index");
      RecursiveLinkedList r = new RecursiveLinkedList();
      
      r.add(1);
      r.add(3);
      r.add(1, 2);
      Object result = r.get(2);
      Object expResult = 2;
      assertEquals(expResult, result);
   }
   
   @Test
   public void testAddIndex2()
   {
      System.out.println("testing adding at an index");
      RecursiveLinkedList r = new RecursiveLinkedList();
      
      r.add(1);
      r.add(3);
      r.add(4);
      r.add(1, 2);
      r.remove(1);
      Object result = r.get(2);
      Object expResult = 3;
      assertEquals(expResult, result);
   }
   
   @Test(expected = IndexOutOfBoundsException.class)
   public void exceptionTest1()
   {
      System.out.println("testing exceptions");
      RecursiveLinkedList r = new RecursiveLinkedList();
      
      r.add(1, "alex");
   }
   
   @Test(expected = IndexOutOfBoundsException.class)
   public void exceptionTest2()
   {
      System.out.println("testing exceptions");
      RecursiveLinkedList r = new RecursiveLinkedList();
      
      r.add(-5, "alex");
   }
   
   @Test(expected = IndexOutOfBoundsException.class)
   public void exceptionTest3()
   {
      System.out.println("testing exceptions");
      RecursiveLinkedList r = new RecursiveLinkedList();
      
      r.get(1);
   }
   
   @Test(expected = IndexOutOfBoundsException.class)
   public void exceptionTest4()
   {
      System.out.println("testing exceptions");
      RecursiveLinkedList r = new RecursiveLinkedList();
      
      r.get(-1);
   }
   
   @Test(expected = IndexOutOfBoundsException.class)
   public void exceptionTest5()
   {
      System.out.println("testing exceptions");
      RecursiveLinkedList r = new RecursiveLinkedList();
      
      r.remove(1);
   }
   
   @Test(expected = IndexOutOfBoundsException.class)
   public void exceptionTest6()
   {
      System.out.println("testing exceptions");
      RecursiveLinkedList r = new RecursiveLinkedList();
      
      r.remove(-1);
   }

}
