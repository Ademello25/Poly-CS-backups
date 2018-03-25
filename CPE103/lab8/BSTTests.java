/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for BST class
 * 
 * @author Alexander DeMello
 * @version Lab 8
 */
public class BSTTests
{
   @Test
   public void testRemove1()
   {
      BST<Integer> b = new BST<Integer>();
      b.insert(10);
      b.insert(5);
      b.insert(14);
      b.insert(13);
      b.insert(18);
      b.insert(11);
      b.insert(16);
      b.insert(1);
      b.insert(7);
      b.insert(6);
      
      b.remove(11);
      assertTrue(!b.contains(11));
   }
   
   @Test
   public void testRemove2()
   {
      BST<Integer> b = new BST<Integer>();
      b.insert(10);
      b.insert(5);
      b.insert(14);
      b.insert(13);
      b.insert(18);
      b.insert(11);
      b.insert(19);
      b.insert(1);
      b.insert(7);
      b.insert(6);
      
      b.remove(18);
      assertTrue(!b.contains(18));
   }
   
   @Test
   public void testRemove3()
   {
      BST<Integer> b = new BST<Integer>();
      b.insert(10);
      b.insert(5);
      b.insert(14);
      b.insert(13);
      b.insert(18);
      b.insert(11);
      b.insert(19);
      b.insert(1);
      b.insert(7);
      b.insert(6);
      
      b.remove(7);
      assertTrue(!b.contains(7));
   }
   
   @Test
   public void testRemove4()
   {
      BST<Integer> b = new BST<Integer>();
      b.insert(10);
      b.insert(5);
      b.insert(14);
      b.insert(13);
      b.insert(18);
      b.insert(11);
      b.insert(19);
      b.insert(1);
      b.insert(7);
      b.insert(6);
      
      b.remove(5);
      assertTrue(!b.contains(5));
   }
   
   @Test public void testInternalPathLengthy()
   {
      BST<Integer> temp = new BST<Integer>();
      temp.insert(20);
      temp.insert(19);
      temp.insert(23);
      temp.insert(18);
      temp.insert(21);
      temp.insert(40);
      temp.insert(17);
      temp.insert(15);
      temp.insert(14);
      temp.insert(22);
      temp.insert(100);
      assertTrue(temp.internalPathLength() == 26);
   }

   @Test public void testInternalPathLengthx()
   {
      BST<Integer> temp = new BST<Integer>();
      temp.insert(55);
      temp.insert(50);
      temp.insert(60);
      temp.insert(45);
      temp.insert(40);
      temp.insert(35);
      temp.insert(53);
      temp.insert(52);
      temp.insert(51);
      temp.insert(57);
      temp.insert(59);
      temp.insert(65);
      temp.insert(63);
      temp.insert(70);
      temp.insert(75);
      temp.insert(80);
      assertTrue(temp.treeHeight() == 5);
      assertTrue(temp.internalPathLength() == 42);
   }
}
