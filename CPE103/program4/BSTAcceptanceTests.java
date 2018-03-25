/**
 * JUnit tests for BST assignment.
 *
 * @author Kurt Mammen
 * @version 10/13/2012 Developed for CPE 103 Program 4 
 */
import static org.junit.Assert.*;
import org.junit.*;
import java.lang.reflect.*;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;
import java.util.Arrays;

public class BSTAcceptanceTests
{
   @Test
   public void verifySuperClass() // points = 5
   {
      assertTrue(BST.class.getSuperclass() == Object.class);
   }

   @Test
   public void verifyInterfaces() // points = 5
   {
      Class[] faces = BST.class.getInterfaces();
      assertTrue(faces.length == 1);
      assertTrue (faces[0] == Iterable.class);
   }

   @Test
   public void verifyFields() // points = 5
   {
      int nodeCount = 0;
      int intCount = 0;
      Field[] fields = BST.class.getDeclaredFields();

      assertTrue(fields.length == 2);

      for (int i = 0; i < fields.length; i++)
      {
         assertTrue(Modifier.isPrivate(fields[i].getModifiers()));

         if (fields[i].getType().toString().contains("BSTNode"))
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
   public void verifyConstructors() // points = 5
   {
      int count = 0;
      Constructor[] cons = BST.class.getDeclaredConstructors();

      assertTrue(cons.length == 1);
      assertTrue(Modifier.isPublic(cons[0].getModifiers()));

      Class[] params = cons[0].getParameterTypes();
     
      assertTrue(params.length == 0);
   }

   @Test
   public void verifyMethods() // points = 5
   {
      int countPublic = 0;
      int countProtected = 0;
      int countPackage = 0;
      int countPrivate = 0;

      Method[] meths = BST.class.getDeclaredMethods();

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

      assertTrue(countPublic == 11);
      assertTrue(countProtected == 0);
      
      // Removed test, KDM 10/21/2012
      //assertTrue(countPackage == 1);
   }

   @Test
   public void verifyInnerClasses() // points = 5
   {
      Class[] classes = BST.class.getDeclaredClasses();

      // Check for expected number of...
      assertTrue(classes.length == 5);
      
      // Make sure they have correct access and fields... 
      for (int i = 0; i < 5; i++)
      {
System.out.println(classes[i]);
         int m = classes[i].getModifiers();
         assertTrue(!Modifier.isProtected(m) && !Modifier.isPublic(m));
      }
     
   }

   @Test
   public void sizeAtConstruction() // points = 2 
   {
      BST<Integer> bst = new BST<Integer>();
      assertTrue(bst.size() == 0);
   }

   @Test
   public void containsAtConstruction() // points = 2 
   {
      BST<Integer> bst = new BST<Integer>();
      assertFalse(bst.contains(999));
   }

   @Test
   public void insertContains() // points = 2 
   {
      BST<Integer> bst = new BST<Integer>();
      int[] values = new int[] {99, -4, 167, 139, 55, -89, 13, 78, 128, 119};

      for (int i = 0; i < values.length; i++)
      {
         bst.insert(values[i]);

         assertTrue(bst.size() == i + 1);

         for (int j = 0; j <= i; j++)
         {
            assertTrue(bst.contains(values[j]));
            assertFalse(bst.contains(values[j] - 1));
            assertFalse(bst.contains(values[j] + 1));
         }
      }
   }
   
   @Test(expected = NoSuchElementException.class)
   public void maximumEmpty() // points = 2 
   {
      BST<Integer> bst = new BST<Integer>();
      bst.maximum();
   }
   
   @Test
   public void minimum() // points = 2 
   {
      BST<Integer> bst = new BST<Integer>();
      int[] values = new int[] {99, -4, 167, 139, 55, -89, 13, 78, 178, 174};
      
      for (int i = 0; i < values.length; i++)
      {
         bst.insert(values[i]);

         if (i < 1)
         {
            assertTrue(bst.minimum() == 99);
         }
         else if (i < 5)
         {
            assertTrue(bst.minimum() == -4);
         }
         else
         {
            assertTrue(bst.minimum() == -89);
         }
      }
   }

   @Test
   public void maximum() // points = 2 
   {
      BST<Integer> bst = new BST<Integer>();
      int[] values = new int[] {99, -4, 167, 139, 55, -89, 13, 78, 178, 174};
      
      for (int i = 0; i < values.length; i++)
      {
         bst.insert(values[i]);

         if (i < 2)
         {
            assertTrue(bst.maximum() == 99);
         }
         else if (i < 8)
         {
            assertTrue(bst.maximum() == 167);
         }
         else
         {
            assertTrue(bst.maximum() == 178);
         }
      }
   }

   @Test
   public void toSortedList() // points = 2 
   {
      BST<Integer> bst = new BST<Integer>();
      int[] values = new int[] {99, -4, 167, 139, 55, -89, 13, 78, 178, 174};
      
      for (int i = 0; i < values.length; i++)
      {
         bst.insert(values[i]);
      }

      ArrayList<Integer> list = new ArrayList<Integer>();
      bst.toSortedList(list);

      int[] expected = new int[] {-89, -4, 13, 55, 78, 99, 139, 167, 174, 178};

      for (int i = 0; i < values.length; i++)
      {
         assertTrue(expected[i] == list.get(i));
      }
   }

   /////////////////////////////////////////////////////////////////////////////
   // New tests for Lab 8
    
   @Test(expected = NoSuchElementException.class)
   public void removeAtConstruction() // points = 4
   {
     BST<Integer> bst = new BST<Integer>();
     bst.remove(99);
   }

   @Test
   public void removeListOfOne() // points = 4
   {
     BST<Integer> bst = new BST<Integer>();
     bst.insert(99);
     bst.remove(99);

     assertTrue(bst.size() == 0);
     assertFalse(bst.contains(99));
   }

   @Test
   public void removeListOfTwoLeft() // points = 4
   {
      BST<Integer> bst = new BST<Integer>();
     
      // Root first...
      bst.insert(99);
      bst.insert(88);

      bst.remove(99);
      assertTrue(bst.size() == 1);
      assertTrue(bst.contains(88));
      assertFalse(bst.contains(99));

      bst.remove(88);
      assertTrue(bst.size() == 0);
      assertFalse(bst.contains(88));
      assertFalse(bst.contains(99));
     
       // Leaf first...
      bst.insert(99);
      bst.insert(88);
     
      bst.remove(88);
      assertTrue(bst.size() == 1);
      assertTrue(bst.contains(99));
      assertFalse(bst.contains(88));
   
      bst.remove(99);
      assertTrue(bst.size() == 0);
      assertFalse(bst.contains(88));
      assertFalse(bst.contains(99));
   }

   @Test
   public void removeListOfTwoRight() // points = 4
   {
      BST<Integer> bst = new BST<Integer>();
     
      // Root first...
      bst.insert(99);
      bst.insert(108);

      bst.remove(99);
      assertTrue(bst.size() == 1);
      assertTrue(bst.contains(108));
      assertFalse(bst.contains(99));

      bst.remove(108);
      assertTrue(bst.size() == 0);
      assertFalse(bst.contains(108));
      assertFalse(bst.contains(99));
     
       // Leaf first...
      bst.insert(99);
      bst.insert(108);
     
      bst.remove(108);
      assertTrue(bst.size() == 1);
      assertTrue(bst.contains(99));
      assertFalse(bst.contains(108));
   
      bst.remove(99);
      assertTrue(bst.size() == 0);
      assertFalse(bst.contains(88));
      assertFalse(bst.contains(99));
   }

   @Test
   public void removeListOfThreeCompleteTree() // points = 4
   {
      BST<Integer> bst = new BST<Integer>();

      // Root, root, root... 
      bst.insert(88);
      bst.insert(77);
      bst.insert(99);

      bst.remove(77);
      assertTrue(bst.size() == 2);
      assertFalse(bst.contains(77));
      assertTrue(bst.contains(88));
      assertTrue(bst.contains(99));

      bst.remove(88);
      assertTrue(bst.size() == 1);
      assertFalse(bst.contains(77));
      assertFalse(bst.contains(88));
      assertTrue(bst.contains(99));
      
      bst.remove(99);
      assertTrue(bst.size() == 0);
      assertFalse(bst.contains(77));
      assertFalse(bst.contains(88));
      assertFalse(bst.contains(99));

      // Left, right, root...
      bst.insert(88);
      bst.insert(77);
      bst.insert(99);

      bst.remove(77);
      assertTrue(bst.size() == 2);
      assertFalse(bst.contains(77));
      assertTrue(bst.contains(88));
      assertTrue(bst.contains(99));

      bst.remove(99);
      assertTrue(bst.size() == 1);
      assertFalse(bst.contains(77));
      assertFalse(bst.contains(99));
      assertTrue(bst.contains(88));
      
      bst.remove(88);
      assertTrue(bst.size() == 0);
      assertFalse(bst.contains(77));
      assertFalse(bst.contains(99));
      assertFalse(bst.contains(88));

      // Right, left, root...
      bst.insert(88);
      bst.insert(77);
      bst.insert(99);

      bst.remove(99);
      assertTrue(bst.size() == 2);
      assertFalse(bst.contains(99));
      assertTrue(bst.contains(88));
      assertTrue(bst.contains(77));

      bst.remove(77);
      assertTrue(bst.size() == 1);
      assertFalse(bst.contains(77));
      assertFalse(bst.contains(99));
      assertTrue(bst.contains(88));
      
      bst.remove(88);
      assertTrue(bst.size() == 0);
      assertFalse(bst.contains(88));
      assertFalse(bst.contains(77));
      assertFalse(bst.contains(99));
   }

   @Test
   public void removeAllLargeTree() // points = 4
   {
      BST<Integer> bst = new BST<Integer>();
      int array[] = new int[]{34, 55, -33, 66, -87, 5, 7, 111, -49, -77, -3, 2, 8, -9, 11};

      for (int i = 0; i < array.length; i++)
      {
         bst.insert(array[i]);
      }

      for (int i = 0; i < array.length; i++)
      {
         bst.remove(array[i]);

         assertTrue(bst.size() == array.length - i - 1);
         assertFalse(bst.contains(array[i]));

         for (int j = i + 1; j < array.length; j++)
         {
            assertTrue(bst.contains(array[j]));
         }
      }
   }

   @Test
   public void treeHeightEmpty() // points = 1
   {
      BST<Integer> bst = new BST<Integer>();
      assertTrue(bst.treeHeight() == -1);
   }

   @Test
   public void treeHeightOne() // points = 1
   {
      BST<Integer> bst = new BST<Integer>();
      bst.insert(12);
      assertTrue(bst.treeHeight() == 0);
   }

   @Test
   public void treeHeightVariousGreaterThanOne() // points = 1
   {
      BST<Integer> bst = new BST<Integer>();

      // one Left (1)
      bst.insert(88);
      bst.insert(77);
      assertTrue(bst.treeHeight() == 1);
      
      // two left (2)
      bst.insert(66);
      assertTrue(bst.treeHeight() == 2);

      // two left and one right (2)
      bst.insert(95);
      assertTrue(bst.treeHeight() == 2);

      // two left and two right (2)
      bst.insert(97);
      assertTrue(bst.treeHeight() == 2);

      // complete tree of 6 three left (2)
      bst.insert(79);
      assertTrue(bst.treeHeight() == 2);

      // complete tree of 6 three right (2)
      bst.remove(79);
      bst.insert(93);
      assertTrue(bst.treeHeight() == 2);

      // treeHeight seven complete (2)
      bst.insert(79);
      assertTrue(bst.treeHeight() == 2);

      // treeHeight eight complete (right) (3)
      bst.insert(99);
      assertTrue(bst.treeHeight() == 3);

      // treeHeight eight complete (left) (3)
      bst.remove(99);
      bst.insert(55);
      assertTrue(bst.treeHeight() == 3);

      // treeHeight eight complete (left middle) (3)
      bst.remove(55);
      bst.insert(80);
      assertTrue(bst.treeHeight() == 3);

      // treeHeight eight complete (right middle) (3)
      bst.remove(80);
      bst.insert(91);
      assertTrue(bst.treeHeight() == 3);
   }
   
   @Test
   public void treeHeightRandomBig() // points = 1
   {
      BST<Integer> bst = makeTree(makeRandomArray(1000, -387));
      assertTrue(bst.treeHeight() == 20);
   }
    
   @Test
   public void internalPathLengthEmpty() // points = 1
   {
      BST<Integer> bst = new BST<Integer>();
      assertTrue(bst.internalPathLength() == -1);
   }

   @Test
   public void internalPathLengthOneElement() // points = 1
   {
      BST<Integer> bst = new BST<Integer>();
      bst.insert(99);
      assertTrue(bst.internalPathLength() == 0);
   }
   
   @Test
   public void internalPathLengthVariousGreaterThanOneElement() // points = 1
   {
      BST<Integer> bst = new BST<Integer>();

      // one Left
      bst.insert(88);
      bst.insert(77);
      assertTrue(bst.internalPathLength() == 1);

      // two left
      bst.insert(55);
      assertTrue(bst.internalPathLength() == 3);

      // one right
      bst = new BST<Integer>();
      bst.insert(88);
      bst.insert(95);
      assertTrue(bst.internalPathLength() == 1);

      // two right
      bst.insert(97);
      assertTrue(bst.internalPathLength() == 3);

      // three complete
      bst = new BST<Integer>();
      bst.insert(88);
      bst.insert(77);
      bst.insert(95);
      assertTrue(bst.internalPathLength() == 2);

      // four complete (left)
      bst.insert(66);
      assertTrue(bst.internalPathLength() == 4);

      // five complete (left-right)
      bst.insert(97);
      assertTrue(bst.internalPathLength() == 6);

      // six complete (left-right-middle right)
      bst.insert(93);
      assertTrue(bst.internalPathLength() == 8);

      // seven complete
      bst.insert(79);
      assertTrue(bst.internalPathLength() == 10);

      // eight complete (a middle node)
      bst.insert(92);
      assertTrue(bst.internalPathLength() == 13);
   }

   @Test
   public void internalPathLengthRandomBig() // points = 1
   {
      BST<Integer> bst = makeTree(makeRandomArray(1000, -4873));
      assertTrue(bst.internalPathLength() == 10162);
   }

   /*
    * Test developed on 2.4ghz machine - passes at 13ms, fails at 12ms.
    * Allow 4x to account for machine load and slightly less efficient code.
    */
   @Test(timeout = 50)
   public void insertBigOhNotTestedInLab07() // points = 4
   {
      BST<Integer> bst = makeTree(makeRandomArray(10000, -687));
   }
   
   /*
    * Test developed on 2.4ghz machine - passes at 12ms, fails at 10ms.
    * Allow 4x to account for machine load and slightly less efficient code.
    */
   @Test(timeout=44)
   public void containsBigOhNotTestedInLab07() // points = 4
   {
      int[] array = makeRandomArray(4000, -352);
      BST<Integer> bst = makeTree(array);

      for (int i = 0; i < array.length; i++)
      {
         bst.contains(array[i]);
      }
   }
   
   /*
    * Test developed on 2.4ghz machine - passes at 11ms, fails at 10ms.
    * Allow 4x to account for machine load and slightly less efficient code.
    */
   @Test(timeout=42)
   public void toSortedListBigOhNotTestedInLab07() // points = 4
   {
      int size = 8000;
      BST<Integer> bst = makeTree(makeRandomArray(size, -629));

      ArrayList<Integer> list = new ArrayList<Integer>(size);
      bst.toSortedList(list);
   }
   
   /*
    * Test developed on 2.4ghz machine - passes at 13ms, fails at 12ms.
    * Allow 4x to account for machine load and slightly less efficient code.
    */
   @Test(timeout=50)
   public void treeHeightBigOh() // points = 4
   {
      int size = 10000;
      BST<Integer> bst = makeTree(makeRandomArray(size, -85194));

      bst.treeHeight();
   }
   
   /*
    * Test developed on 2.4ghz machine - passes at 11ms, fails at 10ms.
    * Allow 4x to account for machine load and slightly less efficient code.
    */
   @Test(timeout=42)
   public void internalPathLengthBigOh() // points = 4
   {
      int size = 10000;
      BST<Integer> bst = makeTree(makeRandomArray(size, -85194));

      bst.internalPathLength();
   }
   
   /*
    * Test developed on 2.4ghz machine - passes at 11ms, fails at 10ms.
    * Allow 4x to account for machine load and slightly less efficient code.
    */
   @Test(timeout=42)
   public void removeBigOh() // points = 4
   {
      int[] array = makeRandomArray(3500, -771);
      BST<Integer> bst = makeTree(array);

      for (int value  : array)
      {
         bst.remove(value);
      }
   }

   ////////////////////////////////////////////////////////////////////////////
   // New tests for Program 4...

   @Test(expected = NoSuchElementException.class)
   public void getAtConstruction()
   {
      BST<Integer> bst = new BST<Integer>();
      bst.get(99);
   }

   @Test(expected = NoSuchElementException.class)
   public void getNoSuchElement()
   {
      BST<Integer> bst = new BST<Integer>();
      bst.insert(-44);
      bst.get(99);
   }

   @Test
   public void getBasic()
   {
      int[] array = new int[]{55, -88, 22, 66, 77, -11, 33, 44, 11, -22};
      BST<Integer> bst = makeTree(array);

      for (int i = 0; i < array.length; i++)
      {
         assertTrue(bst.get(new Integer(array[i])).equals(new Integer(array[i])));
      }
   }
   
   @Test
   public void getRandomBig()
   {
      int [] array = makeRandomArray(5000, -11);
      BST<Integer> bst = makeTree(array);

      for (int i = 0; i < array.length; i++)
      {
         // Made sure deep Integer objects work in getBasic test.
         assertTrue(bst.get(array[i]) == array[i]);
      }
   }

   /*
    * Test developed on 2.4ghz machine - passes at 10ms, fails at 9ms.
    * Allow 4x to account for machine load and slightly less efficient code.
    */
   @Test(timeout = 38)
   public void getBigOh()
   {
      int[] array = makeRandomArray(1100, -34);
      BST<Integer> bst = makeTree(array);

      for(int i = 0; i < array.length * 5; i++)
      {
         bst.get(array[i % array.length]);
      }
   }

   @Test
   public void iteratorEmpty()
   {
      BST<Integer> bst = new BST<Integer>();

      for(Integer i : bst)
      {
         fail();
      }
   }
  
   @Test
   public void iteratorBasic()
   {
      int[] array = new int[]{55, -88, 22, 66, 77, -11, 33, 44, 11, -22};
      BST<Integer> bst = makeTree(array);
      Arrays.sort(array);
      int i = 0;

      for(Integer element : bst)
      {
         assertTrue(element.equals(array[i]));
         i++;
      }

      assertTrue(i == array.length);

      Iterator<Integer> itOne = bst.iterator();
      Iterator<Integer> itTwo = bst.iterator();

      assertTrue(itOne != itTwo);
   }

   @Test
   public void iteratorRandomBig()
   {
      int [] array = makeRandomArray(5000, -117);
      BST<Integer> bst = makeTree(array);
      Arrays.sort(array);
      int i = 0;

      for(Integer element : bst)
      {
         assertTrue(element.equals(array[i]));
         i++;
      }
      
      assertTrue(i == array.length);
   }

   /*
    * Test developed on 2.4ghz machine - occational failures at 10ms.
    * Allow 4x to account for machine load and slightly less efficient code.
    *
    * This test should detect if code constructs an O(N) stack
    * at construction (should be O(logN) stack).
    */
   @Test(timeout = 40)
   public void iteratorConstructionBigOh()
   {
      int [] array = makeRandomArray(6900, -17);
      BST<Integer> bst = makeTree(array);

      for(int i = 0; i < 5000; i++)
      {
         Iterator<Integer> it = bst.iterator();
      }
   }

   /*
    * Test developed on 2.4ghz machine - occational failures at 10ms.
    * Allow 4x to account for machine load and slightly less efficient code.
    */
   @Test(timeout = 40)
   public void iteratorNextBigOh()
   {
      int [] array = makeRandomArray(2950, -333);
      BST<Integer> bst = makeTree(array);
      double sum = 0;

      for(int i = 0; i < 10; i++)
      {
         for (Integer element : bst)
         {
            sum += element;
         }
      }
   }

   @Test(expected = UnsupportedOperationException.class)
   public void iteratorRemove()
   {
      BST<Integer> bst = new BST<Integer>();
      bst.iterator().remove();
   }

   private int[] makeRandomArray(int size, int seed)
   {
      int[] array = new int[size];
      Random rand = new Random(seed);

      for (int i = 0; i < size; i++)
      {
         array[i] = rand.nextInt();
      }

      return array;
   }

   private BST<Integer> makeTree(int[] array)
   {
      BST<Integer> bst = new BST<Integer>();

      for (int i = 0; i < array.length; i++)
      {
         bst.insert(array[i]);
      }

      return bst;
   }
} 