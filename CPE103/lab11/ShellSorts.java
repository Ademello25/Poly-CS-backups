
/**
 * Description
 *
 * @author Alexander DeMello
 * @version
 */
public class ShellSorts
{

   public static <E extends Comparable<? super E>> void shell(E[] array)
   {
      int i, j;
      int gaps[] =
      {
         128, 64, 32, 16, 8, 4, 2, 1
      };
      for (int gap : gaps)
      {
         for (i = gap; i < array.length; i++)
         {
            E temp = array[i];
            for (j = i; (j >= gap) && (array[j - gap].compareTo(temp) > 0); j -= gap)
            {
               array[j] = array[j - gap];
            }
            array[j] = temp;
         }
      }
   }

   public static <E extends Comparable<? super E>> void hibbard(E[] array)
   {
      {
         int i, j;
         int gaps[] =
         {
            255, 127, 63, 31, 15, 7, 3, 1
         };
         
         for (int gap : gaps)
         {
            for (i = gap; i < array.length; i++)
            {
               E temp = array[i];
               for (j = i; (j >= gap) && (array[j - gap].compareTo(temp) > 0); j -= gap)
               {
                  array[j] = array[j - gap];
               }
               array[j] = temp;
            }
         }
      }
   }

   public static <E extends Comparable<? super E>> void sedgewick(E[] array)
   {
      {
         int i, j;
         int gaps[] =
         {
            16577, 4193, 1073, 281, 77, 23, 8, 1
         };
         for (int gap : gaps)
         {
            for (i = gap; i < array.length; i++)
            {
               E temp = array[i];
               for (j = i; (j >= gap) && (array[j - gap].compareTo(temp) > 0); j -= gap)
               {
                  array[j] = array[j - gap];
               }
               array[j] = temp;
            }
         }
      }
   }

}
