import java.lang.reflect.*;
import java.util.*;
public class RecursiveListTestDriver
{
   private static final String RESULTS_FOR = "Results for RecursiveList";
   
   public static void main(String[] args) throws ClassNotFoundException
   {
      boolean pass = true;
      
      printHeader(args);

      pass &= testRecursiveListArch();
      pass &= testEmptyNodeArch();
      pass &= testListNodeArch();
      
      System.out.println();

      pass &= testListOfZero();
      pass &= testListOfOne();
      pass &= testListOfTwo();
      pass &= testListOfThree();
      pass &= testIterator();
      
      printResults(pass);
   }
   
   private static boolean testRecursiveListArch() throws ClassNotFoundException
   {
      System.out.println("RecursiveList architecture tests...");

      boolean pass = true;
      int cnt;
      Class cl = RecursiveList.class;
      Class[] temp;

      pass &= test(cl.getSuperclass() == Class.forName("java.lang.Object"),
                   "Class extends something other than Object");
      pass &= test(cl.getConstructors().length == 1,
                   "Incorrect number of constructors");
      
      temp = cl.getInterfaces();
      pass &= test(temp.length == 1,
              "Expected 1 interfaces, found " + temp.length);

      if(temp.length == 1)
      {
         String expect = "interface java.lang.Iterable";
         pass &= test(expect.equals(temp[0].toString()),
                      "Expected " + expect +
                      ", found " + temp[0].toString());
      }

      String[] names = {"isEmpty","size", "add", "add","get","set","indexOf",
                        "remove", "equals", "iterator", "getStackTrace"};

      cnt = countModifiers(cl.getDeclaredMethods(), Modifier.PUBLIC);     
      pass &= test(cnt == names.length, "Incorrect number of public methods");
      pass &= test(verifyNames(cl.getDeclaredMethods(), Modifier.PUBLIC, names),
                   "Unspecified method name(s)");
      
      cnt = cl.getFields().length;
      pass &= test(cnt == 0, "public instance fields declared");
      
      cnt = countModifiers(cl.getDeclaredFields(), Modifier.PROTECTED);
      pass &= test(cnt == 0, "Protected instance fields declared");
      
      cnt = countModifiers(cl.getDeclaredFields(), Modifier.PRIVATE);
      pass &= test(cnt == 2, "Incorrect number of instance fields declared");
      
      // Count and test number of of PACKAGE fields
      cnt = cl.getDeclaredFields().length
          - countModifiers(cl.getDeclaredFields(), Modifier.PRIVATE)
          - countModifiers(cl.getDeclaredFields(), Modifier.PROTECTED)
          - countModifiers(cl.getDeclaredFields(), Modifier.PUBLIC);
      pass &= test(cnt == 0, "package fields or constants declared");

      return pass;
   }
   
   private static boolean testEmptyNodeArch() throws ClassNotFoundException
   {
      System.out.println("EmptyNode architecture tests...");
      boolean pass = true;
      int cnt;
      Class cl = null;
      Class[] temp;

      try
      {
         cl = Class.forName("RecursiveList$EmptyNode");
      }
      catch(ClassNotFoundException e)
      {
         test(false, "Missing inner-class EmptyNode");
      }
      
      pass &= test(cl.getSuperclass() == Class.forName("java.lang.Object"),
              "Class extends something other than Object");

      // No declared constructors specified - because they are inner-classes
      // you get private constructors for outer-class connections
      pass &= test(cl.getDeclaredConstructors().length == 2,
              "Incorrect number of constructors");

      // Check for implemented interface ListState
      temp = cl.getInterfaces();
      pass &= test(temp.length == 1, "Wrong number of interfaces implemented");
      pass &= test(temp[0].getName().equals("RecursiveList$Node"),
         "Wrong interface implemented");
      
      // Check that ListState interface is private
      pass &= test(Modifier.isPrivate(temp[0].getModifiers()), "Interface not private");

      String[] names = {"isEmpty","size", "add", "add","get","set","indexOf",
                        "equals", "remove"};
      cnt = countModifiers(cl.getDeclaredMethods(), Modifier.PUBLIC);     
      pass &= test(cnt == names.length, "Incorrect number of public methods");
      pass &= test(verifyNames(cl.getDeclaredMethods(), Modifier.PUBLIC, names),
                   "Unspecified method name(s)");
      
      cnt = cl.getFields().length;
      pass &= test(cnt == 0, "public instance fields declared");
      
      cnt = countModifiers(cl.getDeclaredFields(), Modifier.PROTECTED);
      pass &= test(cnt == 0, "Protected instance fields declared");
      
      cnt = countModifiers(cl.getDeclaredFields(), Modifier.PRIVATE);
      pass &= test(cnt == 0, "Too many instance fields declared");
      
      // Count and test number of of PACKAGE fields
      cnt = cl.getDeclaredFields().length
          - countModifiers(cl.getDeclaredFields(), Modifier.PRIVATE)
          - countModifiers(cl.getDeclaredFields(), Modifier.PROTECTED)
          - countModifiers(cl.getDeclaredFields(), Modifier.PUBLIC);

      // "this" is a package variable for inner classes - so cnt == 1 expected.
      pass &= test(cnt == 1, "package instance fields declared");
      
      return pass;
   }
   
   private static boolean testListNodeArch() throws ClassNotFoundException
   {
      System.out.println("ListNode architecture tests...");
      boolean pass = true;
      int cnt;
      Class cl = null;
      Class[] temp;

      try
      {
         cl = Class.forName("RecursiveList$ListNode");
      }
      catch(ClassNotFoundException e)
      {
         test(false, "Missing inner-class ListNode");
      }
      
      pass &= test(cl.getSuperclass() == Class.forName("java.lang.Object"),
              "Class extends something other than Object");

      // No declared constructors specified - because they are inner-classes
      // you get private constructors for outer-class connections
      pass &= test(cl.getDeclaredConstructors().length == 2,
              "Incorrect number of constructors");

      // Check for implemented interface ListState
      temp = cl.getInterfaces();
      pass &= test(temp.length == 1, "Wrong number of interfaces implemented");
      pass &= test(temp[0].getName().equals("RecursiveList$Node"),
         "Wrong interface implemented");
      
      // Check that ListState interface is private
      pass &= test(Modifier.isPrivate(temp[0].getModifiers()), "Interface not private");

      String[] names = {"isEmpty","size", "add", "add","get","set","indexOf",
                        "equals", "remove"};
      cnt = countModifiers(cl.getDeclaredMethods(), Modifier.PUBLIC);     
      pass &= test(cnt == names.length, "Incorrect number of public methods");
      pass &= test(verifyNames(cl.getDeclaredMethods(), Modifier.PUBLIC, names),
                   "Unspecified method name(s)");
      
      cnt = cl.getFields().length;
      pass &= test(cnt == 2, "public instance fields declared");
      
      cnt = countModifiers(cl.getDeclaredFields(), Modifier.PROTECTED);
      pass &= test(cnt == 0, "Protected instance fields declared");
      
      cnt = countModifiers(cl.getDeclaredFields(), Modifier.PRIVATE);
      pass &= test(cnt == 0, "Too many instance fields declared");
      
      // Count and test number of of PACKAGE fields
      cnt = cl.getDeclaredFields().length
          - countModifiers(cl.getDeclaredFields(), Modifier.PRIVATE)
          - countModifiers(cl.getDeclaredFields(), Modifier.PROTECTED)
          - countModifiers(cl.getDeclaredFields(), Modifier.PUBLIC);

      // "this" is a package variable for inner classes - so cnt == 1 expected.
      pass &= test(cnt == 1, "package instance fields declared");
      
      return pass;
   }  

   private static boolean testListOfZero()
   {
      System.out.println("Testing a RecursiveList<Integer> of zero element...");  
      return testListOfZero(new RecursiveList<Integer>());
   }
   
   private static boolean testListOfZero(RecursiveList<Integer> list)
   {
      boolean pass = true;
      int startingDepth = (new Throwable()).getStackTrace().length;
      
      pass &= test(list.isEmpty(), "isEmpty()");
      pass &= test(list, startingDepth + 2);
      pass &= test(list.size() == 0, "size()");
      pass &= test(list, startingDepth + 2);
      pass &= test(list.equals(new RecursiveList<Integer>()), "equals(Object)");
      pass &= test(list, startingDepth + 2);
      
      pass &= testBadAdds(list);
      pass &= testBadGets(list);
      pass &= testBadSets(list);
      pass &= testBadIndexOf(list);
      pass &= testBadRemoves(list);
      
      return pass;
   }
   
   private static boolean testListOfOne()
   {
      System.out.println("Testing a RecursiveList<Integer> of one element...");
      
      int startingDepth = (new Throwable()).getStackTrace().length;
      boolean pass = true;
      
      RecursiveList<Integer> list = new RecursiveList<Integer>();
      list.add(0, new Integer(-10));
      pass &= test(list, startingDepth + 2, startingDepth + 3);
      pass &= testListOfOne(list, -10);
      
      return pass;
   }

   private static boolean testListOfOne(RecursiveList<Integer> list, int expected)
   {
      boolean pass = true;
      int startingDepth = (new Throwable()).getStackTrace().length;
   
      pass &= test(!list.isEmpty(), "isEmpty()");
      pass &= test(list.size() == 1, "size()");
      pass &= test(list, startingDepth + 3);
      pass &= test(list.get(0) == expected, "get(0)");
      pass &= test(list, startingDepth + 2);
     
      RecursiveList<Integer> list2 = new RecursiveList<Integer>();
      pass &= test(!list.equals(list2), "equals(Object) - lists of different length");
      pass &= test(list, startingDepth + 2);
      pass &= test(!list2.equals(list), "equals(Object) - lists of different length");
      pass &= test(list2, startingDepth + 2);
      
      list2.add(expected);
      pass &= test(list2, startingDepth + 2);
      
      pass &= test(list.equals(list2), "equals(Object) - identical lists");
      pass &= test(list, startingDepth + 3);
      pass &= test(list2.equals(list), "equals(Object) - identical lists");
      pass &= test(list2, startingDepth + 3);

      list2 = new RecursiveList<Integer>();
      list2.add(expected + 1);
      pass &= test(list2, startingDepth + 2);

      pass &= test(!list.equals(list2), "equals(Object) - different lists with same length");
      pass &= test(list, startingDepth + 2);

      pass &= test(list.set(0, new Integer(99)) == expected, "list.set(0, value)");
      pass &= test(list, startingDepth + 2);
      pass &= test(list.get(0) == 99, "get(0)");
      pass &= test(list, startingDepth + 2);

      try
      {
         pass &= test(list.indexOf(new Integer(99)) == 0, "indexOf(valueInList)");
         pass &= test(list, startingDepth + 2);
      }
      catch(IndexOutOfBoundsException e)
      {
         pass &= test(false, "indexOf(valueInList) != 0");
      }

      try
      {
         list.indexOf(new Integer(expected));
         pass &= test(false, "indexOf(new Integer(expected))");
      }
      catch(java.util.NoSuchElementException e)
      {}
      
      pass &= testBadAdds(list);
      pass &= testBadGets(list);
      pass &= testBadSets(list);
      pass &= testBadIndexOf(list);
      pass &= testBadRemoves(list);

      list.remove(0);
      pass &= test(list, startingDepth + 2);
      pass &= testListOfZero(list);
      
      return pass;
   }

   private static boolean testListOfTwo()
   {
      System.out.println("Testing a RecursiveList<Integer> of two element...");
      boolean pass = true;
      int startingDepth = (new Throwable()).getStackTrace().length;
      
      // Case 1 - add 0th, 1st positions...
      RecursiveList<Integer> list = new RecursiveList<Integer>();
      list.add(0, new Integer(-10));
      list.add(1, new Integer(7));
      pass &= test(list, startingDepth + 3, startingDepth + 4);
      
      // NOTE: remove(1) tested in testListOfTwo(list, int[])
      pass &= testListOfTwo(list, new int[] {-10, 7});
            
      // Case 2 - add 0th, 0th position
      list = new RecursiveList<Integer>();
      list.add(0, new Integer(-10));
      list.add(0, new Integer(7));
      pass &= test(list, startingDepth + 2, startingDepth + 3);
      
      pass &= testListOfTwo(list,new int[] {7, -10});
 
       // Case 3 - remove 0th item and call testListOfOne(...)...
      list = new RecursiveList<Integer>();
      list.add(0, new Integer(-10));
      list.add(1, new Integer(7));
      pass &= test(list, startingDepth + 3, startingDepth + 4);
       
      list.remove(0);
      pass &= test(list, startingDepth + 2);
      
      pass &= testListOfOne(list, 7);
      
      return pass;
   }

   private static boolean testListOfTwo(RecursiveList<Integer> list, int[] expected)
   {
      boolean pass = true;
      int startingDepth = (new Throwable()).getStackTrace().length;
      
      pass &= test(!list.isEmpty(), "isEmpty()");
      pass &= test(list, startingDepth + 2);
      
      pass &= test(list.size() == 2, "size()");
      pass &= test(list, startingDepth + 4);
      
      for (int i = 0; i < 2; i++)
      {
         pass &= test(list.get(i) == expected[i], "get(i)");
         pass &= test(list, startingDepth + 2 + i);        
         pass &= test(list.indexOf(expected[i]) == i, "indexOf(i)");
         pass &= test(list, startingDepth + 2 + i);        
      }

      RecursiveList<Integer> list2 = new RecursiveList<Integer>();
      pass &= test(!list.equals(list2), "equals(Object) - lists of different length");
      pass &= test(list, startingDepth + 2);
      pass &= test(!list2.equals(list), "equals(Object) - lists of different length");
      pass &= test(list2, startingDepth + 2);

      list2.add(expected[0]);
      pass &= test(!list.equals(list2), "equals(Object) - lists of different length");
      pass &= test(list, startingDepth + 3);
      pass &= test(!list2.equals(list), "equals(Object) - lists of different length");
      pass &= test(list2, startingDepth + 3);
         
      list2.add(expected[1]);
      pass &= test(list.equals(list2), "equals(Object) - identical lists");
      pass &= test(list, startingDepth + 4);
      pass &= test(list2.equals(list), "equals(Object) - identical lists");
      pass &= test(list2, startingDepth + 4);

      list2 = new RecursiveList<Integer>();
      list2.add(expected[0]);
      list2.add(expected[1] + 1);
      pass &= test(!list.equals(list2), "equals(Object) - different lists with same length");
      pass &= test(list, startingDepth + 3);

      pass &= test(list.set(1, new Integer(99)) == expected[1], "list.set(1, value)");
      pass &= test(list, startingDepth + 3);        

      pass &= test(list.set(1, expected[1]) == 99, "list.set(1, value)");
      pass &= test(list, startingDepth + 3);        

      pass &= test(list.get(1) == expected[1], "get(1)");
      pass &= test(list, startingDepth + 3);        

      pass &= testBadGets(list);
      pass &= testBadAdds(list);
      pass &= testBadIndexOf(list);
      pass &= testBadSets(list);
      pass &= testBadRemoves(list);

      list.remove(1);
      pass &= test(list, startingDepth + 3);        

      pass &= test(list.get(0) == expected[0], "get(0) after remove(1)");
      pass &= test(list, startingDepth + 2);        

      pass &= testListOfOne(list, expected[0]);
      
      return pass;
   }

   private static boolean testListOfThree()
   {
      System.out.println("Testing a RecursiveList<Integer> of three element...");
      boolean pass = true;
      RecursiveList<Integer> list = null;
      int startingDepth = (new Throwable()).getStackTrace().length;
      
      // All possible add index orders for three elements...
      int[][] index = {{0,0,0},
                       {0,0,1},
                       {0,0,2},
                       {0,1,0},
                       {0,1,1},
                       {0,1,2}};
                       
      int[][] expected = {{30, 20, 10},
                          {20, 30, 10},
                          {20, 10, 30},
                          {30, 10, 20},
                          {10, 30, 20},
                          {10, 20, 30}};

      for (int i = 0; i < index.length; i++)
      {
         list = new RecursiveList<Integer>();
         
         // Populate the list adding to all possible combinations of valid
         // indices...
         for (int j = 0; j < index[i].length; j++)
         {
            // Adds 10, 20, and 30 to the list in position 0, 1, or 2.
            list.add(index[i][j], (j + 1) * 10);
         }
         
         // Note: Tests everything PLUS tests remove from end...
         pass &= testListOfThree(list, expected[i]);
      }
      
      // Test remove from middle...
      list = new RecursiveList<Integer>();
      list.add(0, 10);
      list.add(1, 20);
      list.add(2, 30);
      list.remove(1);
      pass &= test(list, startingDepth + 3);        
 
      pass &= testListOfTwo(list, new int[] {10, 30});
     
      // test remove from beginning...
      list = new RecursiveList<Integer>();
      list.add(0, 10);
      list.add(1, 20);
      list.add(2, 30);
      list.remove(0);
      pass &= test(list, startingDepth + 2);        
      
      pass &= testListOfTwo(list, new int[] {20, 30});
      
      return pass;
   }
   
   private static boolean testListOfThree(RecursiveList<Integer> list, int[] expected)
   {  
      boolean pass = true;
      int startingDepth = (new Throwable()).getStackTrace().length;

      pass &= test(!list.isEmpty(), "isEmpty()");
      pass &= test(list, startingDepth + 2);
      pass &= test(list.size() == 3, "size()");
      pass &= test(list, startingDepth + 5);
            
      for (int i = 0; i < 3; i++)
      {
         pass &= test(list.get(i) == expected[i], "get(i)");
         pass &= test(list, startingDepth + 2 + i);        
         pass &= test(list.indexOf(expected[i]) == i, "indexOf(i)");
         pass &= test(list, startingDepth + 2 + i);        
      }
      
      RecursiveList<Integer> list2 = new RecursiveList<Integer>();
      pass &= test(!list.equals(list2), "equals(Object) - lists of different length");
      pass &= test(list, startingDepth + 2);
      pass &= test(!list2.equals(list), "equals(Object) - lists of different length");
      pass &= test(list2, startingDepth + 2);

      list2.add(expected[0]);
      pass &= test(!list.equals(list2), "equals(Object) - lists of different length");
      pass &= test(list, startingDepth + 3);
      pass &= test(!list2.equals(list), "equals(Object) - lists of different length");
      pass &= test(list2, startingDepth + 3);
         
      list2.add(expected[1]);
      pass &= test(!list.equals(list2), "equals(Object) - lists of different length");
      pass &= test(list, startingDepth + 4);
      pass &= test(!list2.equals(list), "equals(Object) - lists of different length");
      pass &= test(list2, startingDepth + 4);

      list2.add(expected[2]);
      pass &= test(list.equals(list2), "equals(Object) - identical lists");
      pass &= test(list, startingDepth + 5);
      pass &= test(list2.equals(list), "equals(Object) - identical lists");
      pass &= test(list2, startingDepth + 5);

      list2 = new RecursiveList<Integer>();
      list2.add(expected[0]);
      list2.add(expected[1]);
      list2.add(expected[2] + 1);
      pass &= test(!list.equals(list2), "equals(Object) - different lists with same length");
      pass &= test(list, startingDepth + 4);

      pass &= test(list.set(2, new Integer(99)) == expected[2], "list.set(2, value)");
      pass &= test(list, startingDepth + 4);        

      pass &= test(list.set(2, expected[2]) == 99, "list.set(2, value)");
      pass &= test(list, startingDepth + 4);        

      pass &= test(list.get(2) == expected[2], "get(2)");
      pass &= test(list, startingDepth + 4);        

      pass &= testBadGets(list);
      pass &= testBadAdds(list);
      pass &= testBadIndexOf(list);
      pass &= testBadSets(list);
      pass &= testBadRemoves(list);

      list.remove(2);
      pass &= test(list, startingDepth + 4);        
      pass &= test(list.get(0) == expected[0], "get(0) after remove(2)");
      pass &= test(list, startingDepth + 2);        
      pass &= test(list.get(1) == expected[1], "get)1) after remove(2)");
      pass &= test(list, startingDepth + 3);        
      
      int[] xpect = new int[2];
      xpect[0] = expected[0];
      xpect[1] = expected[1];
      
      pass &= testListOfTwo(list, xpect);
      
      return pass;
   }
   
   private static boolean testIterator()
   {
      System.out.println("Testing Iterator<E>...");
      boolean pass = true;
      boolean caught;
      Iterator<Integer> it;
      RecursiveList<Integer> list;
      long start;

      // Test an empty list...
      list = new RecursiveList<Integer>();
      it = list.iterator();

      pass &= test(!it.hasNext(), "Expected false, found true");
     
      try
      {
         caught = false;
         it.next();
      }
      catch (NoSuchElementException e)
      {
         caught = true;
      }

      pass &= test(caught, "Did not throw expected exception");

      try
      {
         caught = false;
         it.remove();
      }
      catch (UnsupportedOperationException e)
      {
         caught = true;
      }

      pass &= test(caught, "Did not throw expected exception");

      int size = 100;
      int[] array = new int[size];
      Random random = new Random(-17);

      start = System.nanoTime();

      for (int i = 0; i < size && pass; i++)
      {
         array[i] = random.nextInt();
         list.add(array[i]);

         it = list.iterator();

         int j = 0;
         while (it.hasNext() && pass)
         {
            int actual = it.next();

            pass &= test(actual == array[j],
                         "Expected " + array[j] + ", found " + actual);
            j++;
         }
      }
      
      if (!pass)
      {
         testNoStack(pass, "Aborted iterator test early to reduce output");
         return pass;
      }
 
      // Check for O(1) performance of Iterator's next method...
      long time = System.nanoTime() - start;
      // System.out.println("Time is " + time);
      pass &= test(time < 150000000, "next() does not appear to be O(1)");

      // Check behavior at end...
      pass &= test(!it.hasNext(), "Expected false, found true");
      
      try
      {
         caught = false;
         it.next();
      }
      catch (NoSuchElementException e)
      {
         caught = true;
      }

      pass &= test(caught, "Did not throw expected exception");

      try
      {
         caught = false;
         it.remove();
      }
      catch (UnsupportedOperationException e)
      {
         caught = true;
      }

      return pass;
   }

   private static boolean testBadGets(RecursiveList<Integer> list)
   {
      boolean pass = true;
      int[] badIndex = new int[4];
      
      badIndex[0] = -99;
      badIndex[1] = -1;
      badIndex[2] = list.size();
      badIndex[3] = list.size() + 99;
      
      for (int i = 0; i < badIndex.length; i++)
      {
         try
         {
            list.get(badIndex[i]);
            pass = test(false, "get(badIndex)");
         }
         catch(IndexOutOfBoundsException e)
         {}
      }
      
      return pass;
   }
   
   private static boolean testBadAdds(RecursiveList<Integer> list)
   {
      boolean pass = true;
      int[] badIndex = new int[4];
      
      badIndex[0] = -99;
      badIndex[1] = -1;
      badIndex[2] = list.size() + 1;
      badIndex[3] = list.size() + 99;
      
      for (int i = 0; i < badIndex.length; i++)
      {
         try
         {
            list.add(badIndex[i], new Integer(77));
            pass = test(false, "add(badIndex, value)");
         }
         catch(IndexOutOfBoundsException e)
         {}
      }
      
      return pass;
   }
   
   private static boolean testBadIndexOf(RecursiveList<Integer> list)
   {
      boolean pass = true;

      try
      {
         list.indexOf(new Integer(9358279));
         pass = test(false, "indexOf(valNotInList)");
      }
      catch(NoSuchElementException e)
      {}
      
      return pass;
   }
   
   private static boolean testBadSets(RecursiveList<Integer> list)
   {
      boolean pass = true;
      int[] badIndex = new int[4];
      
      badIndex[0] = -99;
      badIndex[1] = -1;
      badIndex[2] = list.size();
      badIndex[3] = list.size() + 99;
      
      for (int i = 0; i < badIndex.length; i++)
      {
         try
         {
            list.set(badIndex[i], new Integer(65830847));
            pass = test(false, "set(badIndex, value)");
         }
         catch(IndexOutOfBoundsException e)
         {}
      }
      
      return pass;
   }

   private static boolean testBadRemoves(RecursiveList<Integer> list)
   {
      boolean pass = true;
      int[] badIndex = new int[4];
      
      badIndex[0] = -99;
      badIndex[1] = -1;
      badIndex[2] = list.size();
      badIndex[3] = list.size() + 99;
      
      for (int i = 0; i < badIndex.length; i++)
      {
         try
         {
            list.remove(badIndex[i]);
            pass = test(false, "remove(badIndex)");
         }
         catch(IndexOutOfBoundsException e)
         {}
      }
      
      return pass;
   }

   private static void printHeader(String[] args)
   {
      if (args.length == 1)
      {
         System.out.println(args[0]);
      }
      
      System.out.println(RESULTS_FOR + "\n");
   }
   
   private static void printResults(boolean pass)
   {
      String msg;
      
      if(pass)
      {
         msg = "\nCongratulations, you passed all the tests!\n\n";
         /* 
            + "Your grade will be based on when you turn in your functionally\n"
            + "correct solution and any deductions for the quality of your\n"
            + "implementation.  Quality is based on, but not limited to,\n"
            + "coding style, documentation requirements, compiler warnings,\n"
            + "and the efficiency and elegance of your code.\n";
         */
      }
      else
      {
         msg = "\nNot done yet - you failed one or more tests!\n";
      }
      
      System.out.print(msg);       
   }
   
   private static int countModifiers(Field[] fields, int modifier)
   {
      int count = 0;
      
      for (Field f : fields)
      {
         if (f.getModifiers() == modifier)
         {
            count++;
         }
      }
      
      return count;
   }
   
   private static int countModifiers(Method[] methods, int modifier)
   {
      int count = 0;
      
      for (Method m : methods)
      {
         if (m.getModifiers() == modifier)
         {
            count++;
         }
      }
      
      return count;
   }
   
   private static boolean approx(double a, double b, double epsilon)
   {
      return Math.abs(a - b) < epsilon;
   }
   
   private static boolean verifyNames(Method[] methods, int modifier, String[] names)
   {
      boolean pass = true;
      Arrays.sort(names);
      
      for (Method m : methods)
      {
         if (m.getModifiers() == modifier)
         {
            if (Arrays.binarySearch(names, m.getName()) < 0)
            {
               System.out.print("      Class contains unspecified public ");
               System.out.println("method: " + m.getName());
               pass &= false;
            }
         }
      }
      
      return pass;
   }
      
   private static boolean test(boolean pass, String msg)
   {
      if (!pass)
      {
         System.out.println("   Failed " + msg);
         (new Throwable()).printStackTrace();
      }
      
      return pass;
   }
   
   private static boolean testNoStack(boolean pass, String msg)
   {
      if (!pass)
      {
         System.out.println("   Failed " + msg);
      }
      
      return pass;
   }
  
   /* Added this to allow students to call add(e) from add(index, e) OR NOT */
   private static boolean test(RecursiveList list, int expectedDepth1, int expectedDepth2)
   {
      Throwable stackTrace = list.getStackTrace();
      
      try
      {
         int actualStackDepth = stackTrace.getStackTrace().length;
      
         boolean pass = testNoStack((actualStackDepth == expectedDepth1
                           || actualStackDepth == expectedDepth2),
                           " - Found stack depth of " + actualStackDepth +
                           ", expected " + expectedDepth1 +
                           " or " + expectedDepth2 +
                           " (See stack trace below for details)");

         if (!pass)
         {   
            stackTrace.printStackTrace();
         }
   
         return pass;
      }
      catch (NullPointerException e)
      {
         return test(false, "Unexpected null stackTrace encountered - did you set it?");
      }
   }

   private static boolean test(RecursiveList list, int expectedStackDepth)
   {
      Throwable stackTrace = list.getStackTrace();
      
      try
      {
         int actualStackDepth = stackTrace.getStackTrace().length;
         
         boolean pass = testNoStack(actualStackDepth == expectedStackDepth,
                           " - Found stack depth of " + actualStackDepth +
                           ", expected " + expectedStackDepth +
                           " (See stack trace below for details)");
         
         if (!pass)
         {   
            stackTrace.printStackTrace();
         }
      
         return pass;
      }
      catch (NullPointerException e)
      {
         return test(false, "Unexpected null stackTrace encountered - did you set it?");
      }
   }
}
