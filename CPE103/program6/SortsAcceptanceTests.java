/**
 * JUnit tests for Sorts assignment.
 *
 * @author Kurt Mammen
 * @version 11/15/2012 Developed for CPE 103 Program 6a - merge and quick sort. 
 */
import static org.junit.Assert.*;
import org.junit.*;
import java.lang.reflect.*;
import java.util.*;

public class SortsAcceptanceTests
{
   private static final Class cls = Sorts.class;

   @Test
   public void verifySuperClass()
   {
      assertTrue(cls.getSuperclass() == Object.class);
   }

   @Test
   public void verifyInterfaces()
   {
      Class[] faces = cls.getInterfaces();
      assertTrue(faces.length == 0);
   }

   @Test
   public void verifyFields()
   {
      Field[] fields = cls.getDeclaredFields();
      assertTrue(fields.length == 0);
   }

   @Test
   public void verifyConstructors()
   {
      Constructor[] cons = cls.getDeclaredConstructors();
      assertTrue(cons.length <= 1);
   }

   @Test
   public void verifyMethods()
   {
      int countPublic = 0;
      int countProtected = 0;
      int countPackage = 0;
      int countPrivate = 0;

      Method[] meths = cls.getDeclaredMethods();

      for (Method m : meths)
      {
         if (Modifier.isPublic(m.getModifiers()))
         {
            countPublic++;
         }
         else if (Modifier.isProtected(m.getModifiers()))
         {
            countProtected++;
         }
         else if (Modifier.isPrivate(m.getModifiers()))
         {
            countPrivate++;
         }
         else
         {
            countPackage++;
         }
      }

      assertEquals(2, countPublic);
      assertEquals(0, countPackage);
      assertEquals(0, countProtected);
   }

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

      for(Integer i : src)
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
   public void mergeSortArrayListVerifyOrder()
   {
      ArrayList<Integer> list = new ArrayList<Integer>(randomList5K.size());      
      copyList(list, randomList5K);

      Sorts.mergeSort(list);

      assertEquals(expectList5K.size(), list.size());

      Iterator<Integer> expectIt = expectList5K.iterator();
      Iterator<Integer> actualIt = list.iterator();

      while(expectIt.hasNext())
      {
         assertEquals(expectIt.next(), actualIt.next());
      }
   }
   
   @Test
   public void mergeSortLinkedListVerifyOrder()
   {
      LinkedList<Integer> list = new LinkedList<Integer>();      
      copyList(list, randomList5K);

      Sorts.mergeSort(list);

      assertEquals(expectList5K.size(), list.size());

      Iterator<Integer> expectIt = expectList5K.iterator();
      Iterator<Integer> actualIt = list.iterator();

      while(expectIt.hasNext())
      {
         assertEquals(expectIt.next(), actualIt.next());
      }
   }

   @Test
   public void quickSortArrayListVerifyOrder()
   {
      ArrayList<Integer> list = new ArrayList<Integer>(randomList5K.size());      
      copyList(list, randomList5K);

      Sorts.quickSort(list);

      assertEquals(expectList5K.size(), list.size());

      Iterator<Integer> expectIt = expectList5K.iterator();
      Iterator<Integer> actualIt = list.iterator();

      while(expectIt.hasNext())
      {
         assertEquals(expectIt.next(), actualIt.next());
      }
   }
   
   @Test
   public void quickSortLinkedListVerifyOrder()
   {
      LinkedList<Integer> list = new LinkedList<Integer>();      
      copyList(list, randomList5K);

      Sorts.quickSort(list);

      assertEquals(expectList5K.size(), list.size());

      Iterator<Integer> expectIt = expectList5K.iterator();
      Iterator<Integer> actualIt = list.iterator();

      while(expectIt.hasNext())
      {
         assertEquals(expectIt.next(), actualIt.next());
      }
   }

   // Fails occationally at 150ms on a 2.4ghz machine.
   // Allow 4x for system load, sloppy code...
   @Test(timeout = 600)
   public void mergeSortArrayListPerformance()
   {
      ArrayList<Integer> list = new ArrayList<Integer>(randomList100K.size());      
      copyList(list, randomList100K);
      Sorts.mergeSort(list);
   }
  
   // Fails occationally at 175ms on a 2.4ghz machine.
   // Allow 4x for system load, sloppy code...
   @Test(timeout = 700)
   public void mergeSortLinkedListPerformance()
   {
      LinkedList<Integer> list = new LinkedList<Integer>();      
      copyList(list, randomList100K);
      Sorts.mergeSort(list);
   }
  
   // Fails occationally at 90 on a 2.4ghz machine.
   // Allow 4x for system load, sloppy code...
   @Test(timeout = 360)
   public void quickSortArrayListPerformance()
   {
      ArrayList<Integer> list = new ArrayList<Integer>(randomList100K.size());      
      copyList(list, randomList100K);
      Sorts.quickSort(list);
   }
   
   // Fails occationally at 130ms on a 2.4ghz machine.
   // Allow 4x for system load, sloppy code...
   @Test(timeout = 520)
   public void quickSortLinkedListPerformance()
   {
      LinkedList<Integer> list = new LinkedList<Integer>();      
      copyList(list, randomList100K);
      Sorts.quickSort(list);
   }
   
   // Fails occationally at 115ms on a 2.4ghz machine.
   // Allow 4x for system load, sloppy code...
   @Test(timeout = 460)
   public void quickSortInOrderPerformance()
   {
      ArrayList<Integer> list = new ArrayList<Integer>(expectList100K.size());      
      copyList(list, expectList100K);
      Sorts.quickSort(list);
   }
} 