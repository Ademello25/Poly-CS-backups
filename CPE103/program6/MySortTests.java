
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 * Tests for modified sort to be compared to standard library sort.
 *
 * @author Kurt Mammen, Slight modifications by Alex DeMello
 */
public class MySortTests
{

   private static ArrayList<Integer> randomList5K = new ArrayList<Integer>(5000);
   private static ArrayList<Integer> expectList5K = new ArrayList<Integer>(5000);
   private static ArrayList<Integer> randomList100K = new ArrayList<Integer>(100000);
   private static ArrayList<Integer> expectList100K = new ArrayList<Integer>(100000);

   @BeforeClass
   public static void before()
   {
      makeRandom(randomList5K, 5000, -123);
      copyList(expectList5K, randomList5K);
      Collections.sort(expectList5K);

      makeRandom(randomList100K, 100000, -876);
      copyList(expectList100K, randomList100K);
      Collections.sort(expectList100K);
   }

   // Makes a deep copy of the list...
   private static void copyList(List<Integer> dst, List<Integer> src)
   {
      dst.clear();

      for (Integer i : src)
      {
         dst.add(new Integer(i));
      }
   }

   private static void makeRandom(List<Integer> list, int size, int seed)
   {
      Random random = new Random(seed);
      list.clear();

      for (int i = 0; i < size; i++)
      {
         list.add(random.nextInt());
      }
   }

   @Test
   public void quickSortArrayListVerifyOrder()
   {
      ArrayList<Integer> list = new ArrayList<Integer>(randomList5K.size());
      copyList(list, randomList5K);

      MySort.sort(list);

      assertEquals(expectList5K.size(), list.size());

      Iterator<Integer> expectIt = expectList5K.iterator();
      Iterator<Integer> actualIt = list.iterator();

      while (expectIt.hasNext())
      {
         assertEquals(expectIt.next(), actualIt.next());
      }
   }

   @Test
   public void quickSortLinkedListVerifyOrder()
   {
      LinkedList<Integer> list = new LinkedList<Integer>();
      copyList(list, randomList5K);

      MySort.sort(list);

      assertEquals(expectList5K.size(), list.size());

      Iterator<Integer> expectIt = expectList5K.iterator();
      Iterator<Integer> actualIt = list.iterator();

      while (expectIt.hasNext())
      {
         assertEquals(expectIt.next(), actualIt.next());
      }
   }

   @Test(timeout = 360)
   public void quickSortArrayListPerformance()
   {
      ArrayList<Integer> list = new ArrayList<Integer>(randomList100K.size());
      copyList(list, randomList100K);
      MySort.sort(list);
   }

   @Test(timeout = 520)
   public void quickSortLinkedListPerformance()
   {
      LinkedList<Integer> list = new LinkedList<Integer>();
      copyList(list, randomList100K);
      MySort.sort(list);
   }

   @Test(timeout = 460)
   public void quickSortInOrderPerformance()
   {
      ArrayList<Integer> list = new ArrayList<Integer>(expectList100K.size());
      copyList(list, expectList100K);
      MySort.sort(list);
   }

   //These tests written to test MySort vs std sort
   @Test
   public void testOne()
   {
      ArrayList<Integer> list1 = new ArrayList<Integer>();
      copyList(list1, randomList100K);


      long start1 = System.nanoTime();
      MySort.sort(list1);
      long end1 = System.nanoTime();
      double time1 = end1 - start1;
      System.out.println(time1);


   }

   @Test
   public void testOther()
   {

      ArrayList<Integer> list2 = new ArrayList<Integer>();
      copyList(list2, randomList100K);
      
      long start2 = System.nanoTime();
      Collections.sort(list2);
      long end2 = System.nanoTime();
      double time2 = end2 - start2;
      System.out.println(time2);
   }
}
