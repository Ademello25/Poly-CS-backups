
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * tests for a basic BST class
 *
 * @author Alexander DeMello
 * @version lab 7
 */
public class BSTTest
{

   /**
    * Test of insert method, of class BST.
    */
   @Test
   public void testInsert()
   {
      System.out.println("inserting stff");
      BST<Integer> instance = new BST<Integer>();
      instance.insert(5);
      instance.insert(6);
      instance.insert(3);
      instance.insert(0);
      assertTrue(instance.contains(5));

   }

   @Test
   public void testMin()
   {
      BST<Integer> instance = new BST<Integer>();
      instance.insert(3);
      instance.insert(2);
      instance.insert(5);
      instance.insert(4);
      instance.insert(1);
      instance.insert(10);
      instance.insert(6);

      int result = instance.minimum();

      assertEquals(1, result);
   }
   
   @Test
   public void testMax()
   {
      BST<Integer> instance = new BST<Integer>();
      instance.insert(3);
      instance.insert(2);
      instance.insert(5);
      instance.insert(4);
      instance.insert(1);
      instance.insert(10);
      instance.insert(6);

      int result = instance.maximum();

      assertEquals(10, result);
   }
   
   @Test
   public void testListSort()
   {
      BST<Integer> instance = new BST<Integer>();
      instance.insert(3);
      instance.insert(1);
      instance.insert(5);
      instance.insert(4);
      instance.insert(2);
      
      
   }

}
