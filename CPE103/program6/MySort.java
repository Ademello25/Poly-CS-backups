
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Alexander DeMello
 * @version
 */
public class MySort
{
   @SuppressWarnings("unchecked")
   public static <E extends Comparable<? super E>> void sort(List<E> list)
   {
      int size = list.size();
      E[] arr = (E[]) new Comparable[size];

      list.toArray(arr);
      if(size < 10)
      {
         insertionSort(arr);
      }
      else
      {
      quickSort(arr, 0, size - 1);
      }
      
      ListIterator l = list.listIterator();
      for (int i = 0; i < size; i++)
      {
         l.next();
         l.set(arr[i]);
      }
      

   }

   @SuppressWarnings("unchecked")
   private static <E extends Comparable<? super E>> int partition(E[] arr, int left,
           int right, int pivotIndex)
   {
      E pivotValue = arr[pivotIndex];

      arr[pivotIndex] = arr[right];
      arr[right] = pivotValue;
      int storeIndex = left;
      for (int i = left; i < right; i++)
      {
         if (arr[i].compareTo(pivotValue) < 0)
         {
            E temp = arr[i];
            arr[i] = arr[storeIndex];
            arr[storeIndex] = temp;
            storeIndex++;
         }
      }
      E temp = arr[storeIndex];
      arr[storeIndex] = arr[right];
      arr[right] = temp;

      return storeIndex;
   }

   @SuppressWarnings("unchecked")
   private static <E extends Comparable<? super E>> int median(E[] arr, int left,
           int right)
   {
      int center = left + (right - left) / 2;
      if (arr[center].compareTo(arr[left]) > 0 && arr[center].compareTo(arr[right]) < 0)
      {
         return center;
      }
      else if (arr[center].compareTo(arr[left]) < 0 && arr[center].compareTo(arr[right]) > 0)
      {
         return center;
      }
      else if (arr[right].compareTo(arr[left]) < 0 && arr[right].compareTo(arr[center]) > 0)
      {
         return right;
      }
      else if (arr[right].compareTo(arr[left]) > 0 && arr[right].compareTo(arr[center]) < 0)
      {
         return right;
      }
      else if (arr[left].compareTo(arr[right]) < 0 && arr[left].compareTo(arr[center]) > 0)
      {
         return left;
      }
      else if (arr[left].compareTo(arr[right]) > 0 && arr[left].compareTo(arr[center]) < 0)
      {
         return left;
      }

      return center;
   }

   @SuppressWarnings("unchecked")
   private static <E extends Comparable<? super E>> void quickSort(E[] arr, int left,
           int right)
   {
      if (left < right)
      {
         int pivotIndex = median(arr, left, right);

         int newPivot = partition(arr, left, right, pivotIndex);

         quickSort(arr, left, newPivot - 1);
         quickSort(arr, newPivot + 1, right);
      }
   }
   
   @SuppressWarnings("unchecked")
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
