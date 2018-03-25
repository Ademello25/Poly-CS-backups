
import org.junit.Test;
import java.util.Random;
import java.util.*;
import static org.junit.Assert.*;

/**
 * Tests for merge and quick sort.
 * @author Alexander DeMello
 * @version Program 6
 */
public class SortsTests
{

   @Test
   public void testMergeArrayList()
   {
      ArrayList<Integer> arr = new ArrayList<Integer>(20);
      arr.add(15);
      arr.add(8);
      arr.add(6);
      arr.add(19);
      arr.add(81);
      arr.add(61);
      arr.add(100);
      arr.add(6);
      arr.add(9);
      arr.add(10);

      Sorts.mergeSort(arr);

      assertEquals(6, (int) arr.get(0));
      assertEquals(6, (int) arr.get(1));
      assertEquals(8, (int) arr.get(2));
      assertEquals(9, (int) arr.get(3));
      assertEquals(10, (int) arr.get(4));
      assertEquals(15, (int) arr.get(5));
      assertEquals(19, (int) arr.get(6));
      assertEquals(61, (int) arr.get(7));
      assertEquals(81, (int) arr.get(8));
      assertEquals(100, (int) arr.get(9));
   }

   @Test
   public void testMergeLinkedList()
   {
      LinkedList<Integer> ll = new LinkedList<Integer>();
      ll.add(15);
      ll.add(8);
      ll.add(6);
      ll.add(19);
      ll.add(81);
      ll.add(61);
      ll.add(100);
      ll.add(6);
      ll.add(9);
      ll.add(10);

      Sorts.mergeSort(ll);

      assertEquals(6, (int) ll.get(0));
      assertEquals(6, (int) ll.get(1));
      assertEquals(8, (int) ll.get(2));
      assertEquals(9, (int) ll.get(3));
      assertEquals(10, (int) ll.get(4));
      assertEquals(15, (int) ll.get(5));
      assertEquals(19, (int) ll.get(6));
      assertEquals(61, (int) ll.get(7));
      assertEquals(81, (int) ll.get(8));
      assertEquals(100, (int) ll.get(9));
   }

   @Test
   public void testQuickArrayList()
   {
      ArrayList<Integer> arr = new ArrayList<Integer>(20);
      arr.add(15);
      arr.add(8);
      arr.add(6);
      arr.add(19);
      arr.add(81);
      arr.add(61);
      arr.add(100);
      arr.add(6);
      arr.add(9);
      arr.add(10);

      Sorts.quickSort(arr);

      assertEquals(6, (int) arr.get(0));
      assertEquals(6, (int) arr.get(1));
      assertEquals(8, (int) arr.get(2));
      assertEquals(9, (int) arr.get(3));
      assertEquals(10, (int) arr.get(4));
      assertEquals(15, (int) arr.get(5));
      assertEquals(19, (int) arr.get(6));
      assertEquals(61, (int) arr.get(7));
      assertEquals(81, (int) arr.get(8));
      assertEquals(100, (int) arr.get(9));


   }

   @Test
   public void testQuickLinkedList()
   {
      LinkedList<Integer> ll = new LinkedList<Integer>();
      ll.add(15);
      ll.add(8);
      ll.add(6);
      ll.add(19);
      ll.add(81);
      ll.add(61);
      ll.add(100);
      ll.add(6);
      ll.add(9);
      ll.add(10);

      Sorts.quickSort(ll);

      assertEquals(6, (int) ll.get(0));
      assertEquals(6, (int) ll.get(1));
      assertEquals(8, (int) ll.get(2));
      assertEquals(9, (int) ll.get(3));
      assertEquals(10, (int) ll.get(4));
      assertEquals(15, (int) ll.get(5));
      assertEquals(19, (int) ll.get(6));
      assertEquals(61, (int) ll.get(7));
      assertEquals(81, (int) ll.get(8));
      assertEquals(100, (int) ll.get(9));
   }
}
