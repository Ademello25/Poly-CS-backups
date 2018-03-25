
/**
 * Merge sort and quick sort methods.
 *
 * @author Alexander DeMello
 * @version Program 6
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class Sorts
{

   @SuppressWarnings("unchecked")
   public static <E extends Comparable<? super E>> void mergeSort(List<E> list)
   {
      int size = list.size();
      E[] arr = (E[]) new Comparable[size];

      list.toArray(arr);

      mergeSort(arr);

      ListIterator l = list.listIterator();
      for (int i = 0; i < size; i++)
      {
         l.next();
         l.set(arr[i]);
      }
   }
   
   @SuppressWarnings("unchecked")
   private static <E extends Comparable<? super E>> void mergeSort(E[] arr)
   {

      // End recursion
      if (arr.length < 2)
      {
         return;
      }

      // Calculate the middle of the list to split it in half
      int mid = arr.length/  2;

      // Allocate memory for the lower "left" half
      E[] left =(E[]) new Comparable[mid];

      // Copy the first half to the "left"
      int i;

      for (i = 0; i < mid; i++)
      {
         left[i] = arr[i];
      }

      // Allocate memory for the upper "right" half
      E[] right = (E[]) new Comparable[arr.length - mid];

      // Copy the second half to the "right"
      for (; i < arr.length; i++)
      {
         right[i-mid] = arr[i];
      }

      // Recursively sort each half
      mergeSort(left);
      mergeSort(right);

      // Merge left and right as the recursion unwinds...
      merge(arr, left, right);
   }

   @SuppressWarnings("unchecked")
   private static <E extends Comparable<? super E>> void merge(E[] result, E[] left, E[] right)
   {
      int i, l, r;

      i = l = r = 0;

      // Merge the two halves into an orderd whole...
      while (l < left.length && r < right.length)
      {
         if (left[l].compareTo(right[r]) <= 0)
         {
            result[i] = left[l];
            l++;
         }
         else
         {
            result[i] = right[r];
            r++;
         }

         i++;
      }

      // Append rest of the values in the left half, if any...
      while (l < left.length)
      {

         result[i] = left[l];
         l++;
         i++;
      }

      // Append rest of the values in the right half, if any...
      while (r < right.length)
      {
         result[i] = right[r];
         r++;
         i++;
      }
   }

   @SuppressWarnings("unchecked")
   public static <E extends Comparable<? super E>> void quickSort(List<E> list)
   {
      int size = list.size();
      E[] arr = (E[]) new Comparable[size];

      list.toArray(arr);

      quickSort(arr, 0, size - 1);

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
}
