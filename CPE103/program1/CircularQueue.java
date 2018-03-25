/**
 * An array based circular queue
 * 
 * @author Alexander DeMello
 * @version 103 program 1
 */
import java.util.NoSuchElementException;

public class CircularQueue <E> implements SimpleQueue<E>
{
   public static final int INITIAL_LENGTH = 10;
   private Object[] q;
   private int front;
   private int back;
   private int size;

   public CircularQueue()
   {
      q = new Object[10];
      front = 0;
      back = 0;
      size = 0;
   }
   
   public E dequeue()
   {
      if (size==0)
      {
         throw new NoSuchElementException();
      }
      
      E result = (E)q[front];
      
      size--;
      front = (front +1) % q.length;
      return result;
   }
   
   public void enqueue(E element)
   {  
      if(size == q.length)
      {
         Object[] temp = new Object[(size)*2];
         
         for(int i = 0; i < size; i ++)
         {
            temp[i] = q[front];
            front = (front + 1) % q.length;
         }
         
         back = size;
         front = 0;
         q = temp;
 
      }
      
      q[back] = element;
      
      
      size++;
      
      back = (back +1) % q.length;
   }
   
   public E peek()
   {
      if (size==0)
      {
         throw new NoSuchElementException();
      }
      
      return (E)q[front];
   }
   
   public int size()
   {
      return size;
   }
   
   public Object[] unusualMethodForTestingPurposesOnly()
   {
      return q;
   }
}
