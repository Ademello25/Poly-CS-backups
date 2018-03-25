import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for Binary heap utilities
 * 
 * @author Alexander DeMello
 * @version lab 5
 */
public class BinaryHeapUtilitiesTests 
{


@Test
   public void isHeapTest() 
   {
      Integer[] heap = new Integer[16];
      for (int i=1; i<=15; i++)
      {
         heap[i] = i;
      }
      assertTrue(BinaryHeapUtilities.isHeap(heap, 15));
      heap[1] = 40;
      assertFalse(BinaryHeapUtilities.isHeap(heap, 15));
      
      heap[1] = 1;
      heap[6] = 15;
      assertFalse(BinaryHeapUtilities.isHeap(heap, 15));
   }
   
   @Test
   public void isHeapHeight1Test()
   {
      Integer[] heap = new Integer[2];
      heap[1] = 1;
      assertTrue(BinaryHeapUtilities.isHeap(heap, 1));
   }
   
   @Test
   public void emptyHeapTest()
   {
      Integer[] heap = new Integer[1];
      assertFalse(BinaryHeapUtilities.isHeap(heap, 0));
   }
   
   @Test
   public void heightTests()
   {
      assertTrue(BinaryHeapUtilities.height(15) == 3);
      assertTrue(BinaryHeapUtilities.height(1) == 0);
      assertTrue(BinaryHeapUtilities.height(3) == 1);
      assertTrue(BinaryHeapUtilities.height(5) == 2);
      assertTrue(BinaryHeapUtilities.height(22) == 4);
   }
   
   @Test(expected=IllegalArgumentException.class)
   public void heightException()
   {
      assertTrue(BinaryHeapUtilities.height(0) == 0);
   }
   
   @Test(expected=IllegalArgumentException.class)
   public void heightException2()
   {
      assertTrue(BinaryHeapUtilities.height(-1) == 0);
   }
   
   @Test
   public void parentTest()
   {
      Integer[] heap = new Integer[16];
      for (int i=1; i<=15; i++)
      {
         heap[i] = i;
      }
      assertTrue(BinaryHeapUtilities.parentOf(5, heap, 15) == 2);
      assertTrue(BinaryHeapUtilities.parentOf(15, heap, 15) == 7);
      assertTrue(BinaryHeapUtilities.parentOf(8, heap, 15) == 4);
      assertTrue(BinaryHeapUtilities.parentOf(2, heap, 15) == 1);
   }
   
   @Test(expected=NoSuchElementException.class)
   public void parentOfRoot()
   {
      Integer[] heap = new Integer[16];
      for (int i=1; i<=15; i++)
      {
         heap[i] = i;
      }
      BinaryHeapUtilities.parentOf(1, heap, 15);
   }
   
   @Test(expected=IllegalArgumentException.class)
   public void notHeapParent()
   {
      Integer[] heap = new Integer[4];
      heap[1] = 16;
      heap[2] = 40;
      heap[3] = 13;
      
      BinaryHeapUtilities.parentOf(3, heap, 3);
   }
   
   @Test(expected=IndexOutOfBoundsException.class)
   public void indexZeroTest()
   {
      Integer[] heap = new Integer[4];
      heap[1] = 14;
      heap[2] = 15;
      heap[3] = 16;
      
      BinaryHeapUtilities.parentOf(0, heap, 3);
   }
   
   @Test(expected=IndexOutOfBoundsException.class)
   public void indexNegativeTest()
   {
      Integer[] heap = new Integer[4];
      heap[1] = 14;
      heap[2] = 15;
      heap[3] = 16;
      
      BinaryHeapUtilities.parentOf(-1, heap, 3);
   }
   
   @Test
   public void childrenTests()
   {
      Integer[] heap = new Integer[16];
      for (int i=1; i<=15; i++)
      {
         heap[i] = i;
      }
      assertTrue(BinaryHeapUtilities.leftChildOf(1, heap, 15) == 2);
      assertTrue(BinaryHeapUtilities.rightChildOf(1, heap, 15) == 3);
      assertTrue(BinaryHeapUtilities.leftChildOf(6, heap, 15) == 12);
      assertTrue(BinaryHeapUtilities.rightChildOf(7, heap, 15) == 15);
      assertTrue(BinaryHeapUtilities.leftChildOf(5, heap, 15) == 10);
   }
   
   @Test(expected=NoSuchElementException.class)
   public void childrenException1()
   {
      Integer[] heap = new Integer[16];
      for (int i=1; i<=13; i++)
      {
         heap[i] = i;
      }
      BinaryHeapUtilities.leftChildOf(7, heap, 13);
   }
   
   @Test(expected=NoSuchElementException.class)
   public void childrenException2()
   {
      Integer[] heap = new Integer[16];
      for (int i=1; i<=13; i++)
      {
         heap[i] = i;
      }
      BinaryHeapUtilities.rightChildOf(7, heap, 13);
   }
   
   
   @Test(expected=NoSuchElementException.class)
   public void childrenException3()
   {
      Integer[] heap = new Integer[16];
      for (int i=1; i<=13; i++)
      {
         heap[i] = i;
      }
      BinaryHeapUtilities.leftChildOf(8, heap, 13);
   }
   
   @Test(expected=IllegalArgumentException.class)
   public void notHeapChild()
   {
      Integer[] heap = new Integer[4];
      heap[1] = 16;
      heap[2] = 40;
      heap[3] = 13;
      
      BinaryHeapUtilities.leftChildOf(1, heap, 3);
   }
   
   @Test(expected=IllegalArgumentException.class)
   public void emptyHeapChild()
   {
      Integer[] heap = new Integer[4];
      
      BinaryHeapUtilities.leftChildOf(1, heap, 0);
   }
   
   @Test(expected=IndexOutOfBoundsException.class)
   public void invalidIndexChildLeft()
   {
      Integer[] heap = new Integer[4];
      heap[1] = 14;
      heap[2] = 15;
      heap[3] = 16;
      
      BinaryHeapUtilities.leftChildOf(5, heap, 3);
   }
   
   @Test(expected=IndexOutOfBoundsException.class)
   public void invalidIndexChildRight()
   {
      Integer[] heap = new Integer[4];
      heap[1] = 14;
      heap[2] = 15;
      heap[3] = 16;
      
      BinaryHeapUtilities.rightChildOf(5, heap, 3);
   }
   
   @Test(expected=IndexOutOfBoundsException.class)
   public void zeroIndexChildLeft()
   {
      Integer[] heap = new Integer[4];
      heap[1] = 14;
      heap[2] = 15;
      heap[3] = 16;
      
      BinaryHeapUtilities.leftChildOf(0, heap, 3);
   }
   
   @Test(expected=IndexOutOfBoundsException.class)
   public void zeroIndexChildRight()
   {
      Integer[] heap = new Integer[4];
      heap[1] = 14;
      heap[2] = 15;
      heap[3] = 16;
      
      BinaryHeapUtilities.rightChildOf(0, heap, 3);
   }
   
   @Test(expected=IndexOutOfBoundsException.class)
   public void negativeIndexChildRight()
   {
      Integer[] heap = new Integer[4];
      heap[1] = 14;
      heap[2] = 15;
      heap[3] = 16;
      
      BinaryHeapUtilities.rightChildOf(-1, heap, 3);
   }
   
   @Test(expected=IndexOutOfBoundsException.class)
   public void negativeIndexChildLeft()
   {
      Integer[] heap = new Integer[4];
      heap[1] = 14;
      heap[2] = 15;
      heap[3] = 16;
      
      BinaryHeapUtilities.leftChildOf(-1, heap, 3);
   }

   
   
}