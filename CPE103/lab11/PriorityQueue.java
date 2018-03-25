
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * PRIORITY QUEUE METHODS!!!!!!!!!!!!!!!
 *
 * @author Alexander DeMello
 * @version lab11
 */
public class PriorityQueue<E extends Comparable<? super E>>
{

   private ArrayList<E> q;
   private boolean max;

   public PriorityQueue()
   {
      this(false);
   }

   public PriorityQueue(boolean max)
   {
      q = new ArrayList<E>();
      q.add(null);
      this.max = max;
   }

   public PriorityQueue(E[] arr, int size)
   {
      this(arr, size, false);
   }

   public PriorityQueue(E[] arr, int size, boolean max)
   {
      this(max);
      for (int i = 0; i < size; i++)
      {
         this.enqueue(arr[i]);
      }
   }

   public static <E extends Comparable<? super E>> void sort(E[] arr, int size)
   {
      PriorityQueue<E> q = new PriorityQueue<E>(arr, size);
      for (int i = 0; i < size; i++)
      {
         arr[i] = q.dequeue();
      }

   }

   public static <E extends Comparable<? super E>> E kth(E[] arr, int size, int k)
   {
      int looplimit = size - k + 1;
      PriorityQueue<E> q;
      if (looplimit < k)
      {
         q = new PriorityQueue<E>(true);
         for (int i = 0; i < looplimit; i++)
         {
            q.enqueue(arr[i]);
         }

         for (int i = looplimit; i < size; i++)
         {
            if (arr[i].compareTo(q.peek()) < 0)
            {
               q.dequeue();
               q.enqueue(arr[i]);
            }
         }

         return q.peek();
      }
      else
      {
         q = new PriorityQueue<E>(false);

         looplimit = k;
         for (int i = 0; i < looplimit; i++)
         {
            q.enqueue(arr[i]);
         }

         for (int i = looplimit; i < size; i++)
         {
            if (arr[i].compareTo(q.peek()) > 0)
            {
               q.dequeue();
               q.enqueue(arr[i]);
            }
         }
         return q.peek();
      }
   }

   private static int getLeftChild(int i)
   {
      return i * 2;
   }

   private static int getRightChild(int i)
   {
      return i * 2 + 1;
   }

   private static int getParent(int i)
   {
      return i / 2;
   }

   private void swap(int i, int j)
   {
      E temp = q.get(i);
      q.set(i, q.get(j));
      q.set(j, temp);
   }

   private boolean doesLeftHavePriority(E left, E right)
   {
      if (max)
      {
         return left.compareTo(right) > 0;
      }
      else
      {
         return left.compareTo(right) < 0;
      }
   }

   private void bubbleUp(int i)
   {
      int parent = getParent(i);
      if (parent > 0)
      {
         if (doesLeftHavePriority(q.get(i), q.get(parent)))
         {
            swap(parent, i);
            bubbleUp(parent);
         }
      }
   }

   private void bubbleDown(int i)
   {
      int left = getLeftChild(i);
      int right = getRightChild(i);
      int largest = i;

      if (left <= size())
      {
         if (doesLeftHavePriority(q.get(left), q.get(largest)))
         {
            largest = left;
         }
      }

      if (right <= size())
      {
         if (doesLeftHavePriority(q.get(right), q.get(largest)))
         {
            largest = right;
         }
      }

      if (largest != i)
      {
         swap(i, largest);
         bubbleDown(largest);

      }
   }

   public void enqueue(E element)
   {
      q.add(element);
      bubbleUp(this.size());

   }

   public E dequeue()
   {
      if (this.size() == 0)
      {
         throw new NoSuchElementException();
      }

      if (this.size() == 1)
      {
         return q.remove(this.size());
      }
      else
      {

         E result = q.get(1);
         q.set(1, q.remove(this.size()));
         bubbleDown(1);

         return result;
      }

   }

   public E peek()
   {
      if (this.size() == 0)
      {
         throw new NoSuchElementException();
      }

      return q.get(1);
   }

   public int size()
   {
      return q.size() - 1;
   }

}
