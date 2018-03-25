/**
 * JUnit tests for SimpleLinkedStack - a modification of Lab 3
 * SimpleLinkedStackTests.
 *
 * @author Kurt Mammen
 * @version 09/29/2012 Developed for CPE 103 Program 2 
 */
import static org.junit.Assert.*;
import org.junit.*;
import java.util.NoSuchElementException;
import java.lang.reflect.*;

public class SimpleLinkedStackAcceptanceTests
{
   @Test
   public void verifySuperClass()
   {
      assertTrue(SimpleLinkedStack.class.getSuperclass() == Object.class);
   }

   @Test
   public void verifyInterfaces()
   {
      Class[] faces = SimpleLinkedStack.class.getInterfaces();

      assertTrue(faces.length == 1);
      assertTrue(faces[0] == SimpleStack.class);
   }

   @Test
   public void verifyFields()
   {
      int nodeCount = 0;
      int intCount = 0;
      Field[] fields = SimpleLinkedStack.class.getDeclaredFields();

      assertTrue(fields.length == 2);

      for (int i = 0; i < fields.length; i++)
      {
         assertTrue(Modifier.isPrivate(fields[i].getModifiers()));

         if (fields[i].getType().toString().contains("SimpleLinkedStack$"))
         {
            nodeCount++;
         }
         else if (fields[i].getType() == int.class)
         {
            intCount++;
         }
      }

      assertTrue(nodeCount == 1);
      assertTrue(intCount == 1);
   }

   @Test
   public void verifyConstructors()
   {
      int count = 0;
      Constructor[] cons = SimpleLinkedStack.class.getDeclaredConstructors();

      assertTrue(cons.length == 1);
      assertTrue(Modifier.isPublic(cons[0].getModifiers()));

      Class[] params = cons[0].getParameterTypes();
     
      assertTrue(params.length == 0);
   }

   @Test
   public void verifyMethods()
   {
      int countPublic = 0;
      int countProtected = 0;
      int countPackage = 0;
      int countPrivate = 0;

      Method[] meths = SimpleLinkedStack.class.getDeclaredMethods();

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

      assertTrue(countPublic == 4);
      assertTrue(countProtected == 0);
      assertTrue(countPackage == 0);
   }

// KDM COMMENTED OUT - MY SOLUTION HAS ONLY ONE INNER CLASS, MOST
// STUDENT'S SOLUTIONS HAVE 2. If I remove my Node constructor I
// get two also! Because I don't know the names I can't be sure which
// one to check architecture for so, for now, I've commented this test
// out!
/*
   @Test
   public void verifyInnerClasses()
   {
      int count = 0;
      Class[] classes = SimpleLinkedStack.class.getDeclaredClasses();

      // Check for expected number of...
      assertTrue(classes.length == 1);

      // Make sure it is private...
      assertTrue(Modifier.isPrivate(classes[0].getModifiers()));

      // Check instance variables of inner class...
      Field[] fields = classes[0].getDeclaredFields();

      // Field count different if inner class is static or not...
      if (Modifier.isStatic(classes[0].getModifiers()))
      {
         // Static, no ref to enclosing class...
         assertTrue(fields.length == 2);
      }
      else
      {
         // Not static, invisible ref to enclosing class...
         assertTrue(fields.length == 3);
      }

      // Check types...
      int nodeCount = 0;
      int finalCount = 0;
      int objectCount = 0;

      for (int i = 0; i < fields.length; i++)
      {
         if (fields[i].getType().toString().contains("SimpleLinkedStack$"))
         {
            nodeCount++;
         }
         else if (fields[i].toString().contains("this")
               && fields[i].toString().contains("SimpleLinkedStack")
               && Modifier.isFinal(fields[i].getModifiers()))
         {
            finalCount++;
         }
         else if (fields[i].getType() == Object.class)
         {
            // Due to type erasure type of generic variable is Object...
            objectCount++;
         }
         else
         {
            // Should never get here!
            fail();
         }
      }

      assertTrue(nodeCount == 1);
      assertTrue(objectCount == 1);

      if (Modifier.isStatic(classes[0].getModifiers()))
      {
         // No ref to enclosing class if Node is static...
         assertTrue(finalCount == 0);
      }
      else
      {
         // Invisible ref to enclosing class if Node is not static...
         assertTrue(finalCount == 1);
      }
   }
*/   
   public void sizeAtConstruction()
   {
      SimpleLinkedStack<Integer> s = new SimpleLinkedStack<Integer>();
      assertTrue(s.size() == 0);
   }
   
   @Test(expected=NoSuchElementException.class)
   public void peekAtConstruction()
   {
      SimpleLinkedStack<Integer> s = new SimpleLinkedStack<Integer>();
      s.peek();
   }

   @Test(expected=NoSuchElementException.class)
   public void popAtConstruction()
   {
      SimpleLinkedStack<Integer> s = new SimpleLinkedStack<Integer>();
      s.pop();
   }

   @Test
   public void simplePush()
   {
      SimpleLinkedStack<Integer> s = new SimpleLinkedStack<Integer>();
    
      int[] a = new int[] {5, 10, 15, 20, 25, 30, 35, 40, 45, 50};

      for (int i = 0; i < a.length; i++)
      {
         s.push(a[i]);
         assertTrue(s.size() == i + 1);
         assertTrue(s.peek() == a[i]);
      }
   }

   @Test
   public void simplePop()
   {
      SimpleLinkedStack<Integer> s = new SimpleLinkedStack<Integer>();

      for (int i = 0; i < 10; i++)
      {
         s.push(i * 13);
      }

      for (int i = 9; i > -1; i--)
      {
         assertTrue(s.peek() == i * 13);
         assertTrue(s.pop() == i * 13);
         assertTrue(s.size() == i);
      }
   }

   @Test
   public void pushPopCombo()
   {
      SimpleLinkedStack<Integer> s = new SimpleLinkedStack<Integer>();

      s.push(55);
      s.push(66);
      s.pop();
      s.push(77);
      s.push(33);
      s.push(11);
      s.pop();
      s.push(-4);
      s.push(89);
      s.push(-521);
      s.push(333);
      s.push(-9);
      s.push(45);
      s.push(61);
      s.push(93);

      assertTrue(s.pop() == 93);
      assertTrue(s.pop() == 61);
      assertTrue(s.pop() == 45);
      assertTrue(s.pop() == -9);
      assertTrue(s.pop() == 333);
      assertTrue(s.pop() == -521);
      assertTrue(s.pop() == 89);
      assertTrue(s.pop() == -4);
      assertTrue(s.pop() == 33);
      assertTrue(s.pop() == 77);
      assertTrue(s.pop() == 55);
   }

   @Test
   public void testPushPopNulls()
   {
      SimpleLinkedStack<Integer> s = new SimpleLinkedStack<Integer>();

      s.push(null);
      s.push(66);
      s.pop();
      s.push(77);
      s.push(33);
      s.push(11);
      s.pop();
      s.push(-4);
      s.push(89);
      s.push(null);
      s.push(333);
      s.push(-9);
      s.push(45);
      s.push(61);
      s.push(null);

      assertTrue(s.pop() == null);
      assertTrue(s.pop() == 61);
      assertTrue(s.pop() == 45);
      assertTrue(s.pop() == -9);
      assertTrue(s.pop() == 333);
      assertTrue(s.pop() == null);
      assertTrue(s.pop() == 89);
      assertTrue(s.pop() == -4);
      assertTrue(s.pop() == 33);
      assertTrue(s.pop() == 77);
      assertTrue(s.pop() == null);
   }

   @Test(timeout=12)
   public void testOrderPush()
   {
      // This method test that the run time of the push method
      // is reasonable. The 10ms is about 3 times longer than
      // the code should take on a 2.4ghz machine.
      // KDM - 9/17/2012
      final int TEST_SIZE = 10000;

      SimpleLinkedStack<Integer> s = new SimpleLinkedStack<Integer>();

      //long start = System.currentTimeMillis();

      for (int i = 0; i < TEST_SIZE; i++)
      {
         s.push(i);
      }

      //long stop = System.currentTimeMillis();
      //System.out.println("\nRUNTIME: " + (stop - start) + "ms");
   }

   @Test(timeout=12)
   public void testOrderPop()
   {
      // This method test that the run time of the push method
      // is reasonable. The 10ms is about 3 times longer than
      // the code should take on a 2.4ghz machine - note that the
      // pop() calls are virtually free (see push test above)!
      // KDM - 9/17/2012
      final int TEST_SIZE = 10000;

      SimpleLinkedStack<Integer> s = new SimpleLinkedStack<Integer>();

      //long start = System.currentTimeMillis();

      for (int i = 0; i < TEST_SIZE; i++)
      {
         s.push(i);
      }

      for (int i = 0; i < TEST_SIZE; i++)
      {
         s.pop();
      }

      //long stop = System.currentTimeMillis();
      //System.out.println("\nRUNTIME: " + (stop - start) + "ms");
   }
}
