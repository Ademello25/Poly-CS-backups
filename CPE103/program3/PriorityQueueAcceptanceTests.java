/**
 * JUnit acceptance tests for PriorityQueue.
 *
 * @author Kurt Mammen
 * @version 10/15/2012 Developed for CPE 103 Program 3.
 */
import static org.junit.Assert.*;
import org.junit.*;
import java.lang.reflect.*;
import java.util.NoSuchElementException;
import java.util.Random;

public class PriorityQueueAcceptanceTests
{
   @Test
   public void verifySuperClass()
   {
      assertTrue(PriorityQueue.class.getSuperclass() == Object.class);
   }

   @Test
   public void verifyInterfaces()
   {
      Class[] faces = PriorityQueue.class.getInterfaces();

      assertTrue(faces.length == 1);
      assertTrue(faces[0] == SimpleQueue.class);
   }

   @Test
   public void verifyFields()
   {
      int listCount = 0;
      int booleanCount = 0;
      Field[] fields = PriorityQueue.class.getDeclaredFields();

      assertTrue(fields.length == 2);

      for (int i = 0; i < fields.length; i++)
      {
         assertTrue(Modifier.isPrivate(fields[i].getModifiers()));

         if (fields[i].getType() == java.util.ArrayList.class)
         {
            listCount++;
         }
         else if (fields[i].getType() == boolean.class)
         {
            booleanCount++;
         }
      }

      assertTrue(listCount == 1);
      assertTrue(booleanCount == 1);
   }

   @Test
   public void verifyConstructors()
   {
      int count = 0;
      Constructor[] cons = PriorityQueue.class.getDeclaredConstructors();

      assertTrue(cons.length == 4);

      for (int i = 0; i < cons.length; i++)
      {
         assertTrue(Modifier.isPublic(cons[i].getModifiers()));
         Class[] params = cons[i].getParameterTypes();
         assertTrue(params.length >= 0 && params.length <= 3);

         // Could count types and verify correctness a bit further
         // but my test will call and verify they exit.
      }
   }

   @Test
   public void verifyMethods()
   {
      int countPublic = 0;
      int countProtected = 0;
      int countPackage = 0;
      int countPrivate = 0;

      Method[] meths = PriorityQueue.class.getDeclaredMethods();

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

      // Get shadow methods for enqueue, dequeue, and peek due to generics
      assertTrue(countPublic == 9);
      assertTrue(countProtected == 0);
      assertTrue(countPackage == 0);
   }

   @Test
   public void sizeAtConstruction1()
   {
      PriorityQueue<Integer> q = new PriorityQueue<Integer>();
      assertTrue(q.size() == 0);
   }

   @Test
   public void sizeAtConstruction2()
   {
      PriorityQueue<Integer> q = new PriorityQueue<Integer>(true);
      assertTrue(q.size() == 0);

      q = new PriorityQueue<Integer>(false);
      assertTrue(q.size() == 0);
   }

   @Test
   public void sizeAtConstruction3()
   {
      // Note to self, don't test empty array - I did not clearly specify
      // what to do in this case.
      Integer[] a = new Integer[] {1};
      PriorityQueue<Integer> q = new PriorityQueue<Integer>(a, 1);
      assertTrue(q.size() == 1);
      
      a = new Integer[] {1, 2, 3};
      q = new PriorityQueue<Integer>(a, 3);
      assertTrue(q.size() == 3);
      
      a = new Integer[] {1, 2, 3, -1, -1, -1};
      q = new PriorityQueue<Integer>(a, 3);
      assertTrue(q.size() == 3);
   }

   @Test
   public void sizeAtConstruction4()
   {
      // Note to self, don't test empty array - I did not clearly specify
      // what to do in this case.
      Integer[] a = new Integer[] {1};
      PriorityQueue<Integer> q = new PriorityQueue<Integer>(a, 1, true);
      assertTrue(q.size() == 1);
      
      a = new Integer[] {1, 2, 3};
      q = new PriorityQueue<Integer>(a, 3, true);
      assertTrue(q.size() == 3);
      
      a = new Integer[] {1, 2, 3, -1, -1, -1};
      q = new PriorityQueue<Integer>(a, 3, true);
      assertTrue(q.size() == 3);
      
      a = new Integer[] {1};
      q = new PriorityQueue<Integer>(a, 1, false);
      assertTrue(q.size() == 1);
      
      a = new Integer[] {1, 2, 3};
      q = new PriorityQueue<Integer>(a, 3, false);
      assertTrue(q.size() == 3);
      
      a = new Integer[] {1, 2, 3, -1, -1, -1};
      q = new PriorityQueue<Integer>(a, 3, false);
      assertTrue(q.size() == 3);
   }

   @Test(expected = NoSuchElementException.class)
   public void dequeueAtConstruction1()
   {
      PriorityQueue<String> q = new PriorityQueue<String>();
      q.dequeue();
   }

   @Test(expected = NoSuchElementException.class)
   public void dequeueAtConstruction2True()
   {
      PriorityQueue<String> q = new PriorityQueue<String>(true);
      q.dequeue();
   }
   
   @Test(expected = NoSuchElementException.class)
   public void dequeueAtConstruction2False()
   {
      PriorityQueue<String> q = new PriorityQueue<String>(false);
      q.dequeue();
   }

   public void dequeueAtConstruction3()
   {
      String[] array = new String[] { "Hi", "There" };
      PriorityQueue<String> q = new PriorityQueue<String>(array, array.length);
      assertEquals(array[0], q.dequeue());
      assertEquals(array[1], q.dequeue());
   }

   public void dequeueAtConstruction4True()
   {
      String[] array = new String[] { "Hi", "There" };
      PriorityQueue<String> q = new PriorityQueue<String>(array, array.length, true);
      assertEquals(array[1], q.dequeue());
      assertEquals(array[0], q.dequeue());
   }

   public void dequeueAtConstruction4False()
   {
      String[] array = new String[] { "Hi", "There" };
      PriorityQueue<String> q = new PriorityQueue<String>(array, array.length, false);
      assertEquals(array[0], q.dequeue());
      assertEquals(array[1], q.dequeue());
   }
   
   @Test(expected = NoSuchElementException.class)
   public void peekAtConstruction1()
   {
      PriorityQueue<String> q = new PriorityQueue<String>();
      q.peek();
   }

   @Test(expected = NoSuchElementException.class)
   public void peekAtConstruction2True()
   {
      PriorityQueue<String> q = new PriorityQueue<String>(true);
      q.peek();
   }

   @Test(expected = NoSuchElementException.class)
   public void peekAtConstruction2False()
   {
      PriorityQueue<String> q = new PriorityQueue<String>(false);
      q.peek();
   }

   public void peekAtConstruction3()
   {
      String[] array = new String[] { "Hi", "There" };
      PriorityQueue<String> q = new PriorityQueue<String>(array, array.length);
      assertEquals(array[0], q.peek());
   }

   public void peekAtConstruction4True()
   {
      String[] array = new String[] { "Hi", "There" };
      PriorityQueue<String> q = new PriorityQueue<String>(array, array.length, true);
      assertEquals(array[1], q.peek());
   }

   public void peekAtConstruction4False()
   {
      String[] array = new String[] { "Hi", "There" };
      PriorityQueue<String> q = new PriorityQueue<String>(array, array.length, false);
      assertEquals(array[0], q.peek());
   }

   @Test
   public void enqueueSizePeekMinQ()
   {
      PriorityQueue<Integer> q = new PriorityQueue<Integer>();

      q.enqueue(39);
      assertTrue(q.size() == 1);
      assertTrue(q.peek() == 39);

      q.enqueue(-8);
      assertTrue(q.size() == 2);
      assertTrue(q.peek() == -8);

      q.enqueue(17);
      assertTrue(q.size() == 3);
      assertTrue(q.peek() == -8);
   }

   @Test
   public void dequeueSizePeekMinQ()
   {
      Integer[] a = new Integer[] {17, 8, 55, -7, 39};
      PriorityQueue<Integer> q = new PriorityQueue<Integer>(a, a.length);

      assertTrue(q.size() == 5);
      assertTrue(q.peek() == -7);
      
      assertTrue(q.dequeue() == -7);
      assertTrue(q.size() == 4);
      assertTrue(q.peek() == 8);
      
      assertTrue(q.dequeue() == 8);
      assertTrue(q.size() == 3);
      assertTrue(q.peek() == 17);
      
      assertTrue(q.dequeue() == 17);
      assertTrue(q.size() == 2);
      assertTrue(q.peek() == 39);
      
      assertTrue(q.dequeue() == 39);
      assertTrue(q.size() == 1);
      assertTrue(q.peek() == 55);
      
      assertTrue(q.dequeue() == 55);
      assertTrue(q.size() == 0);
   }

   @Test
   public void enqueueDequeueAdvancedMinQ()
   {
      PriorityQueue<Integer> q = new PriorityQueue<Integer>();

      q.enqueue(-937);

      assertTrue(q.size() == 1);
      assertTrue(q.peek() == -937);

      assertTrue(q.dequeue() == -937);
      assertTrue(q.size() == 0);

      q.enqueue(-999);
      q.enqueue(33);

      assertTrue(q.size() == 2);
      assertTrue(q.peek() == -999);

      q.enqueue(-1111);

      assertTrue(q.size() == 3);
      assertTrue(q.peek() == -1111);

      assertTrue(q.dequeue() == -1111);
      assertTrue(q.size() == 2);
      assertTrue(q.peek() == -999);

      q.enqueue(-1000);
      assertTrue(q.size() == 3);
      assertTrue(q.peek() == -1000);

      q.enqueue(-1001);
      assertTrue(q.size() == 4);
      assertTrue(q.peek() == -1001);

      assertTrue(q.dequeue() == -1001);
      assertTrue(q.size() == 3);
      assertTrue(q.peek() == -1000);

      assertTrue(q.dequeue() == -1000);
      assertTrue(q.size() == 2);
      assertTrue(q.peek() == -999);

      assertTrue(q.dequeue() == -999);
      assertTrue(q.size() == 1);
      assertTrue(q.peek() == 33);

      assertTrue(q.dequeue() == 33);
      assertTrue(q.size() == 0);
   }

   @Test
   public void enqueueSizePeekMaxQ()
   {
      PriorityQueue<Integer> q = new PriorityQueue<Integer>(true);

      q.enqueue(39);
      assertTrue(q.size() == 1);
      assertTrue(q.peek() == 39);

      q.enqueue(-8);
      assertTrue(q.size() == 2);
      assertTrue(q.peek() == 39);

      q.enqueue(17);
      assertTrue(q.size() == 3);
      assertTrue(q.peek() == 39);
   }

   @Test
   public void dequeueSizePeekMaxQ()
   {
      Integer[] a = new Integer[] {17, 8, 55, -7, 39};
      PriorityQueue<Integer> q = new PriorityQueue<Integer>(a, a.length, true);

      assertTrue(q.size() == 5);
      assertTrue(q.peek() == 55);
      
      assertTrue(q.dequeue() == 55);
      assertTrue(q.size() == 4);
      assertTrue(q.peek() == 39);
      
      assertTrue(q.dequeue() == 39);
      assertTrue(q.size() == 3);
      assertTrue(q.peek() == 17);
      
      assertTrue(q.dequeue() == 17);
      assertTrue(q.size() == 2);
      assertTrue(q.peek() == 8);
      
      assertTrue(q.dequeue() == 8);
      assertTrue(q.size() == 1);
      assertTrue(q.peek() == -7);
      
      assertTrue(q.dequeue() == -7);
      assertTrue(q.size() == 0);
   }

   @Test
   public void enqueueDequeueAdvancedMaxQ()
   {
      PriorityQueue<Integer> q = new PriorityQueue<Integer>(true);

      q.enqueue(-937);

      assertTrue(q.size() == 1);
      assertTrue(q.peek() == -937);

      assertTrue(q.dequeue() == -937);
      assertTrue(q.size() == 0);

      q.enqueue(-999);
      q.enqueue(33);

      assertTrue(q.size() == 2);
      assertTrue(q.peek() == 33);

      q.enqueue(-1111);

      assertTrue(q.size() == 3);
      assertTrue(q.peek() == 33);

      assertTrue(q.dequeue() == 33);
      assertTrue(q.size() == 2);
      assertTrue(q.peek() == -999);

      q.enqueue(-1000);
      assertTrue(q.size() == 3);
      assertTrue(q.peek() == -999);

      q.enqueue(-1001);
      assertTrue(q.size() == 4);
      assertTrue(q.peek() == -999);

      assertTrue(q.dequeue() == -999);
      assertTrue(q.size() == 3);
      assertTrue(q.peek() == -1000);

      assertTrue(q.dequeue() == -1000);
      assertTrue(q.size() == 2);
      assertTrue(q.peek() == -1001);

      assertTrue(q.dequeue() == -1001);
      assertTrue(q.size() == 1);
      assertTrue(q.peek() == -1111);

      assertTrue(q.dequeue() == -1111);
      assertTrue(q.size() == 0);
   }

   @Test
   public void sortMinQ()
   {
      Integer[] a = new Integer[] {13, 26, 9, 27, 5, 3, 33, 8, 99};
      Integer[] e = new Integer[] {3, 5, 8, 9, 13, 26, 27, 33, 99};
      PriorityQueue<Integer> q = new PriorityQueue<Integer>(a, a.length, false);

      verifyQueue(q, e);
   }

   @Test
   public void sortMaxQ()
   {
      Integer[] a = new Integer[] {13, 26, 9, 27, 5, 3, 33, 8, 99};
      Integer[] e = new Integer[] {99, 33, 27, 26, 13, 9, 8, 5, 3};
      PriorityQueue<Integer> q = new PriorityQueue<Integer>(a, a.length, true);

      verifyQueue(q, e);
   }

   @Test
   public void kthBasic()
   {
      Integer[] a = new Integer[] {13, 26, 9, 27, 5, 3, 33, 8, 99};

      assertTrue(PriorityQueue.kth(a, a.length, 1) == 99);
      assertTrue(PriorityQueue.kth(a, a.length, 2) == 33);
      assertTrue(PriorityQueue.kth(a, a.length, 3) == 27);
      assertTrue(PriorityQueue.kth(a, a.length, 4) == 26);
      assertTrue(PriorityQueue.kth(a, a.length, 5) == 13);
      assertTrue(PriorityQueue.kth(a, a.length, 6) == 9);
      assertTrue(PriorityQueue.kth(a, a.length, 7) == 8);
      assertTrue(PriorityQueue.kth(a, a.length, 8) == 5);
      assertTrue(PriorityQueue.kth(a, a.length, 9) == 3);
   }

   /* Times out off and on on a 2.4ghz processor at 17ms. Allow 
    * double for load...
    */
   @Test(timeout = 68)
   public void enqueueBigOh()
   {
      int size = 5000;
      PriorityQueue<Integer> q = new PriorityQueue<Integer>();
      Random rand = new Random(-13555);

      for (int i = 0; i < size; i++)
      {
         q.enqueue(rand.nextInt());
      }

      assertTrue(q.size() == size);
   }

   /* Times out off and on on a 2.4ghz processor at 15ms. Allow 
    * double for load...
    */
   @Test(timeout = 60)
   public void dequeueBigOh()
   {
      int size = 4000;
      PriorityQueue<Integer> q = new PriorityQueue<Integer>();
      Random rand = new Random(17519);

      for (int i = 0; i < size; i++)
      {
         q.enqueue(rand.nextInt());
      }

      assertTrue(q.size() == size);
      
      for (int i = 0; i < size; i++)
      {
         q.dequeue();
      }

      assertTrue(q.size() == 0);
   }

   /* Times out off and on on a 2.4ghz processor at 14ms. Allow 
    * double for load...
    */
   @Test(timeout = 56)
   public void peekBigOh()
   {
      int size = 25000;
      PriorityQueue<Integer> q = new PriorityQueue<Integer>();
      Random rand = new Random(1953);

      for (int i = 0; i < size; i++)
      {
         q.enqueue(rand.nextInt());
      }

      assertTrue(q.size() == size);
      
      for (int i = 0; i < size; i++)
      {
         q.peek();
      }

      assertTrue(q.size() == size);
   }


   /* Times out off and on on a 2.4ghz processor at 15ms. Allow 
    * double for load...
    */
   @Test(timeout = 60)
   public void sortBigOh()
   {
      int size = 8000;
      Integer[] array = new Integer[size];
      Random rand = new Random(-8723);

      for (int i = 0; i < size; i++)
      {
         array[i] = rand.nextInt();
      }

      PriorityQueue.sort(array, size);

      // verify in order...
      for (int i = 1; i < size; i++)
      {
         assertTrue(array[i-1] <= array[i]);
      }
   }

   /* Times out off and on on a 2.4ghz processor at 120ms. Allow 
    * double for load...
    */
   @Test(timeout = 480)
   public void smallKthBigOh()
   {
      int size = 500000;
      Integer[] array = new Integer[size];
      Random rand = new Random(-9999);

      for (int i = 0; i < size; i++)
      {
         array[i] = rand.nextInt();
      }

      int kth = PriorityQueue.kth(array, size, 50);

      //System.out.println("kth is " + kth);
      assertTrue(kth == 2147016677);
   }

   /* Times out off and on on a 2.4ghz processor at 155-160ms when
    * code is not optimal.
    *
    * Times out off and on on a 2.4ghz processor at  when
    * code is optimal.
    *
    * double for load...
    */
   @Test(timeout = 620)
   public void largeKthBigOh()
   {
      int size = 500000;
      Integer[] array = new Integer[size];
      Random rand = new Random(-9999);

      for (int i = 0; i < size; i++)
      {
         array[i] = rand.nextInt();
      }

      int kth = PriorityQueue.kth(array, size, size - 50);

      //System.out.println("kth is " + kth);
      assertTrue(kth == -2147026670);
   }

   private void verifyQueue(PriorityQueue<Integer> q, Integer[] expected)
   {
      for (int i = 0; i < expected.length; i++)
      {
         assertTrue(q.size() == expected.length - i);
         assertTrue(q.peek() == expected[i]);
         assertTrue(q.dequeue() == expected[i]);
      }

      assertTrue(q.size() == 0);
   }
}
