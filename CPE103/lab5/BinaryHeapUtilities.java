/**
 * Utilies for a binary heap
 * 
 * @author Alexander DeMello
 * @version lab 5
 */
import java.util.NoSuchElementException;
public class BinaryHeapUtilities <E>
{
   public static int height(int size)
   {
      if(size == 0 || size < 0)
      {
         throw new IllegalArgumentException("size cannot be 0 or less than 0");
      }
      
      return ((int)Math.log(size)/(int)Math.log(2));
   }
   
   public static <E extends java.lang.Comparable<? super E>> boolean isHeap(E[] heap, 
           int size)
   {
      if(size < 1)
      {
         return false;
      }
      
      for(int i = size + 1; i > 1; i --)
      {
         if(heap[i].compareTo(heap[i-1]) < 0)
         {
            return false;
         }
      }
      
      
      return true;
   }
   
   public static <E extends java.lang.Comparable<? super E>> E parentOf(int index, 
           E[] heap, int size)
   {
      if(!isHeap(heap, size))
      {
         throw new IllegalArgumentException("not a heap");
      }
      
      else if(index < 1 || index > size)
      {
         throw new IndexOutOfBoundsException();
      }
      
      else if(index == 1)
      {
         throw new NoSuchElementException();
      }
      
      return heap[index/2];
      
      
   }
   
   public static <E extends java.lang.Comparable<? super E>> E leftChildOf(int index,
           E[] heap, int size)
   {
      if(!isHeap(heap, size))
      {
         throw new IllegalArgumentException("not a heap");
      }
      
      else if(index < 1 || index > size)
      {
         throw new IndexOutOfBoundsException();
      }
      
      else if(index*2 > heap.length)
      {
         throw new NoSuchElementException();
      }
      
      return heap[index*2];
   }
   
   public static <E extends java.lang.Comparable<? super E>> E rightChildOf(int index,
           E[] heap, int size)
   {
      if(!isHeap(heap, size))
      {
         throw new IllegalArgumentException("not a heap");
      }
      
      else if(index < 1 || index > size)
      {
         throw new IndexOutOfBoundsException();
      }
      
      else if(index*2 + 1 > heap.length)
      {
         throw new NoSuchElementException();
      }
      
      return heap[index*2 + 1];
   }
}
