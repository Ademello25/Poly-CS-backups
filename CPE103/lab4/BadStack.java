
import java.util.NoSuchElementException;

/**
 * A bad implementation of a stack.
 * 
 * @author Alexander DeMello
 * @version lab4
 */
public class BadStack <E>
{
   private Object[] stack;
   private int size;
   
   public BadStack()
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
      
      return (E)stack[0];
      
   }
   
   public E pop()
   {
      if(size == 0)
      {
         throw new NoSuchElementException("There are no elements in the stack");
      }
      
      E result = (E)stack[0];
      
      for(int i= 0; i< size-1; i++)
      {
         stack[i]= stack[i+1];
      }
      
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
      
      for(int i =0; i<size-1; i++)
      {
         stack[i+1] = stack[i];
      }
      stack[0] = element;
      
      size++;
      
   }
   
   public int size()
   {
      return size;
   }
      
   
}


