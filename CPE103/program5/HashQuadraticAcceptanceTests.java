/**
 * JUnit tests for hashQuadratic assignment.
 *
 * @author Kurt Mammen
 * @version 11/04/2012 Developed for CPE 103 Program 5 
 */
import static org.junit.Assert.*;
import org.junit.*;
import java.lang.reflect.*;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;
import java.util.Arrays;

public class HashQuadraticAcceptanceTests
{
   private static final Class cls = HashQuadratic.class;

   @Test
   public void verifySuperClass() // points = 5
   {
      assertTrue(cls.getSuperclass() == Object.class);
   }

   @Test
   public void verifyInterfaces() // points = 5
   {
      Class[] faces = cls.getInterfaces();
      assertTrue(faces.length == 2);

      for (Class c : faces)
      {
         Method[] methods = c.getDeclaredMethods();

         if (c == HashMetrics.class)
         {
            assertEquals(4, methods.length);
         }
         else if (c == HashTable.class)
         {
            assertEquals(6, methods.length);
         }
         else
         {
            fail();
         }
      }
   }

   @Test
   public void verifyFields() // points = 5
   {
      Field[] fields = cls.getDeclaredFields();

      for (Field f : fields)
      {
         assertTrue(Modifier.isPrivate(f.getModifiers()));
      }
   }

   @Test
   public void verifyConstructors() // points = 5
   {
      Constructor[] cons = cls.getDeclaredConstructors();
      assertTrue(cons.length == 1);
   }

   @Test
   public void verifyMethods() // points = 5
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

      assertEquals(10, countPublic);
      assertEquals(0, countPackage);
      assertEquals(0, countProtected);
   }

   @Test
   public void sizeAtConstruction()
   {
      HashQuadratic<Integer> tbl = new HashQuadratic<Integer>(100);
      assertEquals(0, tbl.size());
   }

   @Test
   public void tableSizeAtConstruction()
   {
      HashQuadratic<Integer> tbl = new HashQuadratic<Integer>(100);
      assertEquals(101, tbl.tableSize());
   }

   @Test
   public void basicInsertSize()
   {
      HashQuadratic<Integer> tbl = new HashQuadratic<Integer>(100);

      int[] a = new int[] {10, -20, 40, -80, 50};

      for (int i = 0; i < a.length; i++)
      {
         tbl.insert(a[i]);
         assertEquals(i + 1, tbl.size());
      }
   }

   @Test
   public void basicLoadFactor()
   {
      HashQuadratic<Integer> tbl = new HashQuadratic<Integer>(100);

      int[] a = new int[] {10, -20, 40, -80, 50};

      for (int i = 0; i < a.length; i++)
      {
         tbl.insert(a[i]);
         assertEquals((i + 1.0) / tbl.tableSize(), tbl.loadFactor(), 0.000001);
      }
      
      for (int i = 0; i < a.length; i++)
      {
         tbl.insert(a[i]);
         assertEquals(5.0 / tbl.tableSize(), tbl.loadFactor(), 0.000001);
      }
   }

   @Test
   public void basicInsertCollisions()
   {
      HashQuadratic<Integer> tbl = new HashQuadratic<Integer>(100);

      int[] a = new int[] {10, -20, 111, -80, -5767};

      tbl.insert(a[0]);
      assertEquals(0, tbl.collisions());

      tbl.insert(a[1]);
      assertEquals(0, tbl.collisions());

      tbl.insert(a[2]);
      assertEquals(1, tbl.collisions());

      tbl.insert(a[3]);
      assertEquals(1, tbl.collisions());

      tbl.insert(a[4]);
      assertEquals(3, tbl.collisions());

      tbl.insert(a[4]);
      assertEquals(5, tbl.collisions());
   }
   
   @Test
   public void lastOpCollisionsInsert()
   {
      HashQuadratic<Integer> tbl = new HashQuadratic<Integer>(100);

      int[] a = new int[] {10, -20, 111, -80, -5767};

      tbl.insert(a[0]);
      assertEquals(0, tbl.lastOpCollisions());

      tbl.insert(a[1]);
      assertEquals(0, tbl.lastOpCollisions());

      tbl.insert(a[2]);
      assertEquals(1, tbl.lastOpCollisions());

      tbl.insert(a[3]);
      assertEquals(0, tbl.lastOpCollisions());

      tbl.insert(a[4]);
      assertEquals(2, tbl.lastOpCollisions());

      tbl.insert(a[4]);
      assertEquals(2, tbl.lastOpCollisions());
   }

   @Test
   public void basicContains()
   {
      HashQuadratic<Integer> tbl = new HashQuadratic<Integer>(100);

      int[] a = new int[] {10, -20, 40, -80, 50};

      for (int i = 0; i < a.length; i++)
      {
         for (int j = i; j < a.length; j++)
         {
            assertFalse(tbl.contains(a[j]));
         }

         tbl.insert(a[i]);

         for (int j = 0; j < i + 1; j++)
         {
            assertTrue(tbl.contains(a[j]));
         }
      }
   }
   
   @Test
   public void lastOpCollisionsContains()
   {
      HashQuadratic<Integer> tbl = new HashQuadratic<Integer>(100);

      int[] a = new int[] {10, -20, 111, -80, -5767};

      for (int i = 0; i < a.length; i++)
      {
         tbl.insert(a[i]);
      }

      tbl.contains(99);
      assertEquals(0, tbl.lastOpCollisions());

      tbl.contains(a[0]);
      assertEquals(0, tbl.lastOpCollisions());

      tbl.contains(a[1]);
      assertEquals(0, tbl.lastOpCollisions());

      tbl.contains(a[2]);
      assertEquals(1, tbl.lastOpCollisions());

      tbl.contains(a[3]);
      assertEquals(0, tbl.lastOpCollisions());

      tbl.contains(a[4]);
      assertEquals(2, tbl.lastOpCollisions());

      tbl.contains(a[4]);
      assertEquals(2, tbl.lastOpCollisions());
      
      tbl.contains(a[0]);
      assertEquals(0, tbl.lastOpCollisions());
   }
   
   @Test
   public void basicRemoveContains()
   {
      HashQuadratic<Integer> tbl = new HashQuadratic<Integer>(100);

      int[] a = new int[] {10, -20, 111, -80, -5767};

      for (int i = 0; i < a.length; i++)
      {
         tbl.insert(a[i]);
      }

      for (int i = 0; i < a.length; i++)
      {
         assertEquals(a.length - i, tbl.size());
         assertTrue(tbl.contains(a[i]));

         tbl.remove(a[i]);

         assertFalse(tbl.contains(a[i]));
         assertEquals(a.length - i - 1, tbl.size());
      }
   }
   
   @Test
   public void lastOpCollisionsRemove()
   {
      HashQuadratic<Integer> tbl = new HashQuadratic<Integer>(100);

      int[] a = new int[] {10, -20, 111, -80, -5767};

      for (int i = 0; i < a.length; i++)
      {
         tbl.insert(a[i]);
      }

      tbl.remove(99);
      assertEquals(0, tbl.lastOpCollisions());

      tbl.remove(a[0]);
      assertEquals(0, tbl.lastOpCollisions());

      tbl.remove(a[1]);
      assertEquals(0, tbl.lastOpCollisions());

      tbl.remove(a[2]);
      assertEquals(1, tbl.lastOpCollisions());

      tbl.remove(a[3]);
      assertEquals(0, tbl.lastOpCollisions());

      tbl.remove(a[4]);
      assertEquals(2, tbl.lastOpCollisions());

      tbl.remove(a[4]);
      assertEquals(2, tbl.lastOpCollisions());
      
      tbl.remove(a[0]);
      assertEquals(0, tbl.lastOpCollisions());
   }

   @Test
   public void maxCollisions()
   {
      HashQuadratic<Integer> tbl = new HashQuadratic<Integer>(100);
      int[] a = new int[] {10, -20, 111, -80, -5767};

      assertEquals(0, tbl.maxCollisions());

      tbl.insert(a[0]);
      assertEquals(0, tbl.maxCollisions());
      
      tbl.insert(a[1]);
      assertEquals(0, tbl.maxCollisions());

      tbl.insert(a[2]);
      assertEquals(1, tbl.maxCollisions());

      tbl.insert(a[3]);
      assertEquals(1, tbl.maxCollisions());

      tbl.insert(a[4]);
      assertEquals(2, tbl.maxCollisions());

      tbl.insert(a[0]);
      assertEquals(2, tbl.maxCollisions());

      tbl = new HashQuadratic<Integer>(12);

      tbl.insert(70);
      assertEquals(0, tbl.maxCollisions());
      tbl.insert(57);
      assertEquals(1, tbl.maxCollisions());
      tbl.insert(44);
      assertEquals(2, tbl.maxCollisions());
      tbl.insert(31);
      assertEquals(3, tbl.maxCollisions());
      tbl.insert(18);
      assertEquals(4, tbl.maxCollisions());
   }

   @Test
   public void avgCollisions()
   {
      HashQuadratic<Integer> tbl = new HashQuadratic<Integer>(100);
      int[] a = new int[] {10, -20, 111, -80, -5767};

      assertEquals(0, tbl.avgCollisions());

      tbl.insert(a[0]);
      assertEquals(0, tbl.avgCollisions());

      tbl.insert(a[1]);
      assertEquals(0, tbl.avgCollisions());

      tbl.insert(a[2]);
      assertEquals(0, tbl.avgCollisions());

      tbl.insert(a[3]);
      assertEquals(0, tbl.avgCollisions());

      tbl.insert(a[4]); // 5 inserts, 3 collisions
      assertEquals(0, tbl.avgCollisions());

      tbl.insert(a[4]); // 6 inserts, 5 collisions
      assertEquals(0, tbl.avgCollisions());
      
      tbl.insert(a[4]); // 7 inserts, 7 collisions
      assertEquals(1, tbl.avgCollisions());
      
      tbl = new HashQuadratic<Integer>(12);

      tbl.insert(70); // 1 insert, 0 collisions
      assertEquals(0, tbl.avgCollisions());
      tbl.insert(57); // 2 inserts, 1 collision
      assertEquals(0, tbl.avgCollisions());
      tbl.insert(44); // 3 inserts, 3 collisions
      assertEquals(1, tbl.avgCollisions());
      tbl.insert(31); // 4 inserts, 6 collisions
      assertEquals(1, tbl.avgCollisions());
      tbl.insert(18); // 5 inserts, 10 collisions
      assertEquals(2, tbl.avgCollisions());
   }

   @Test
   public void hashLoadFactorExceptionNotExpected()
   {
      HashQuadratic<Integer> tbl = new HashQuadratic<Integer>(12);

      // Should be able to add 6 elements...
      for(int i = 0; i < 6; i++)
      {
         tbl.insert(i);
      }
   }

   @Test(expected = HashLoadFactorException.class)
   public void hashLoadFactorExceptionExpected()
   {
      HashQuadratic<Integer> tbl = new HashQuadratic<Integer>(12);

      // Should NOT be able to add 7 elements...
      for(int i = 0; i < 7; i++)
      {
         tbl.insert(i);
      }
   }

   @Test
   public void insertContainsBig()
   {
      int size = 10000;
      HashQuadratic<Double> tbl = new HashQuadratic<Double>(2 * size);
      Double[] a = new Double[size];

      // To avoid duplicates, must use a seed...
      Random random = new Random(-1497353);

      for (int i = 0; i < size; i++)
      {
         a[i] = random.nextDouble() * 123456789;

         assertFalse(tbl.contains(a[i]));
         tbl.insert(a[i]);
         assertTrue(tbl.contains(a[i]));
      }
   }

   @Test
   public void insertRemoveContainsBig()
   {
      int size = 10000;
      HashQuadratic<Double> tbl = new HashQuadratic<Double>(2 * size);
      Double[] a = new Double[size];

      for (int i = 0; i < size; i++)
      {
         a[i] = Math.random() * 123456789;
         tbl.insert(a[i]);
      }

      for (int i = 0; i < size; i++)
      {
         tbl.remove(a[i]);
         assertFalse(tbl.contains(a[i]));
      }
   }
   
   // Fails occationally at 10ms on a 2.4ghz machine.
   // Allow 4x for system load, sloppy code...
   @Test(timeout = 40)
   public void insertBigOh()
   {
      int size = 10000;
      HashQuadratic<Double> tbl = new HashQuadratic<Double>(2 * size);
      Double[] a = new Double[size];

      for (int i = 0; i < size; i++)
      {
         a[i] = Math.random() * 123456789;
         tbl.insert(a[i]);
      }
   }
  
   // Fails occationally at 10ms on a 2.4ghz machine.
   // Allow 4x for system load, sloppy code...
   @Test(timeout = 40)
   public void containsBigOh()
   {
      int size = 8250;
      HashQuadratic<Double> tbl = new HashQuadratic<Double>(2 * size);
      Double[] a = new Double[size];

      for (int i = 0; i < size; i++)
      {
         a[i] = Math.random() * 123456789;
         tbl.insert(a[i]);
      }
      
      for (int i = 0; i < size; i++)
      {
         //System.out.println(i);
         tbl.contains(a[i]);
      }
   }
   
   // Fails occationally at 10ms on a 2.4ghz machine.
   // Allow 4x for system load, sloppy code...
   @Test(timeout = 40)
   public void removeBigOh()
   {
      int size = 8250;
      HashQuadratic<Double> tbl = new HashQuadratic<Double>(2 * size);
      Double[] a = new Double[size];

      for (int i = 0; i < size; i++)
      {
         a[i] = Math.random() * 123456789;
         tbl.insert(a[i]);
      }
      
      for (int i = 0; i < size; i++)
      {
         tbl.remove(a[i]);
      }
   }
   
   // Fails occationally at 10ms on a 2.4ghz machine.
   // Allow 4x for system load, sloppy code...
   @Test(timeout = 40)
   public void collisionsBigOh()
   {
      int size = 8500;
      HashQuadratic<Double> tbl = new HashQuadratic<Double>(2 * size);
      Double[] a = new Double[size];

      for (int i = 0; i < size; i++)
      {
         a[i] = Math.random() * 123456789;
         tbl.insert(a[i]);
         tbl.collisions();
      }
   }
   
   // Fails occationally at 10ms on a 2.4ghz machine.
   // Allow 4x for system load, sloppy code...
   @Test(timeout = 40)
   public void maxCollitionsBigOh()
   {
      int size = 8500;
      HashQuadratic<Double> tbl = new HashQuadratic<Double>(2 * size);
      Double[] a = new Double[size];

      for (int i = 0; i < size; i++)
      {
         a[i] = Math.random() * 123456789;
         tbl.insert(a[i]);
         tbl.maxCollisions();
      }
   }
   
   // Fails occationally at 10ms on a 2.4ghz machine.
   // Allow 4x for system load, sloppy code...
   @Test(timeout = 40)
   public void avgCollisionsBigOh()
   {
      int size = 8500;
      HashQuadratic<Double> tbl = new HashQuadratic<Double>(2 * size);
      Double[] a = new Double[size];

      for (int i = 0; i < size; i++)
      {
         a[i] = Math.random() * 123456789;
         tbl.insert(a[i]);
         tbl.avgCollisions();
      }
   }
   
   // Fails occationally at 10ms on a 2.4ghz machine.
   // Allow 4x for system load, sloppy code...
   @Test(timeout = 40)
   public void lastOpCollisionsBigOh()
   {
      int size = 8500;
      HashQuadratic<Double> tbl = new HashQuadratic<Double>(2 * size);
      Double[] a = new Double[size];

      for (int i = 0; i < size; i++)
      {
         a[i] = Math.random() * 123456789;
         tbl.insert(a[i]);
         tbl.lastOpCollisions();
      }
   }
} 