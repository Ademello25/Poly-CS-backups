
/**
 * Description
 *
 * @author Alexander DeMello
 * @version lab10
 */
public class QuadraticSorts
{

   public static <E extends Comparable<? super E>> void bubbleSort1(E[] array)
   {
      boolean swapped = true;

      while (swapped)
      {
         swapped = false;

         for (int i = 1; i <= array.length - 1; i++)
         {
            if (array[i - 1].compareTo(array[i]) > 0)
            {
               E temp = array[i - 1];
               array[i - 1] = array[i];
               array[i] = temp;
               swapped = true;

            }
         }
      }
   }

   public static <E extends Comparable<? super E>> void bubbleSort2(E[] array)
   {
      boolean swapped = true;
      int len = array.length;

      while (swapped)
      {
         swapped = false;
         for (int i = 1; i <= len - 1; i++)
         {
            if (array[i - 1].compareTo(array[i]) > 0)
            {
               E temp = array[i - 1];
               array[i - 1] = array[i];
               array[i] = temp;
               swapped = true;
            }
         }

         len = len - 1;
      }
   }

   public static <E extends Comparable<? super E>> void bubbleSort3(E[] array)
   {
      int newn;
      int n = array.length;

      do
      {
         newn = 0;
         for (int i = 1; i <= (n - 1); i++)
         {
            if (array[i - 1].compareTo(array[i]) > 0)
            {
               E temp = array[i - 1];
               array[i - 1] = array[i];
               array[i] = temp;
               newn = i;
            }
         }
         n = newn;
      }while(n != 0);
   }

   public static <E extends Comparable<? super E>> void insertionSort(E[] array)
   {
      for (int i = 1; i < array.length - 1; i++)
      {
         E temp = array[i];
         int hole = i;

         while (hole > 0 && (array[hole - 1].compareTo(temp) > 0))
         {
            array[hole] = array[hole - 1];
            hole = hole - 1;
         }
         array[hole] = temp;
      }
   }

}
