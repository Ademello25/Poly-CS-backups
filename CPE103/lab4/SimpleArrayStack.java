/**
 * Methods for a simplified array stack
 * 
 * @author Alexander DeMello
 * @version Lab 3
 */
import java.util.NoSuchElementException;

public class SimpleArrayStack <E> implements SimpleStack<E>
{
   private Object[] stack;
   private int size;
   
   public SimpleArrayStack()
   {
      this.size =0;
      stack = new Object[10];
   }
   
   public E peek()
   {
      if(size ==0)
      {
         throw new NoSuchElementException("There are no elements in the stack");
      }
      
      return (E)stack[size-1];
      
   }
   
   public E pop()
   {
      if(size == 0)
      {
         throw new NoSuchElementException("There are no elements in the stack");
      }
      
      
      E result = (E)stack[size-1];
      size--;
      return result;
      
   }
   
   public void push(E element)
   {
      int len = stack.length;
      
      if(size == len)
      {
         
         Object[] temp = new Object[len*2];
         
         for(int i=0; i < size; i++)
         {
            temp[i] = stack[i];
         }
         
         stack = temp;
      }
      
      if(size != 0)
      {
         stack[size-1] = element;
      }
      else
      {
         stack[0] = element;
      }
      
      size++;
      
   }
   
   public int size()
   {
      return size;
   }
      
   
}
