
import java.util.Random;

/**
 * Description
 * 
 * @author Alexander DeMello
 * @version lab 10
 */
public class SortDriver 
{
   static int values = 500000;
   static int seed = 1205;
   
   public static void main(String[] args)
   {
       /*
      Integer[] temp1 = makeRandomArray(values, seed);
      double start1 = System.currentTimeMillis();
      ShellSorts.shell(temp1);
      double end1 = System.currentTimeMillis();
      double time1 = end1 - start1;
      System.out.println(values + " values used. shell used, time taken: " + time1+"ms");
       
      Integer[] temp2 = makeRandomArray(values, seed);
      double start2 = System.currentTimeMillis();
      ShellSorts.hibbard(temp2);
      double end2 = System.currentTimeMillis();
      double time2 = end2 - start2;
      System.out.println(values + " values used. hibbard used, time taken: " + time2+"ms");
       */
      Integer[] temp3 = makeRandomArray(values, seed);
      double start3 = System.currentTimeMillis();
      ShellSorts.sedgewick(temp3);
      double end3 = System.currentTimeMillis();
      double time3 = end3 - start3;
      System.out.println(values + " values used. sedgewick used, time taken: " + time3+"ms");
      
      Integer[] temp4 = makeRandomArray(values, seed);
      double start4 = System.currentTimeMillis();
      PriorityQueue.sort(temp4, values);
      double end4 = System.currentTimeMillis();
      double time4 = end4 - start4;
      System.out.println(values + " values used. heapSort used, time taken: " + time4+"ms");
      
      
   }
   
   private static Integer[] makeRandomArray(int size, int seed)
   {
      Integer[] array = new Integer[size];
      Random rand = new Random(seed);

      for (int i = 0; i < size; i++)
      {
         array[i] = rand.nextInt();
      }

      return array;
   }
}
