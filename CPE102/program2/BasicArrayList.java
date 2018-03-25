/**
 * BasicListArray Class
 * 
 * @author Alexander DeMello
 * @version Program 2
 */
import java.util.NoSuchElementException;
        
public class BasicArrayList<E> implements BasicList<E>
{
   private int size;
   private Object[] arr;
   public static final int DEFAULT_CAPACITY = 10;
   
   public BasicArrayList()
   {
      this(DEFAULT_CAPACITY);
   }
   
   public BasicArrayList(int capacity)
   {
      if(capacity < DEFAULT_CAPACITY)
      {
         capacity = DEFAULT_CAPACITY;
      }
      arr = new Object[capacity];
      size = 0;
   }
   
   public void add(E element)
   {
      if(size >= arr.length)
      {
         increaseSize();
      }
      arr[size] = element;
      size++;
   }
   
   public void add(int index, E element)
   {
      System.out.println("" + index +" " + size);
      if((index > size) || index < 0)
      {
         throw new IndexOutOfBoundsException();
      }
      if((size >= arr.length))
      {
         increaseSize();
      }
      
      for(int i = size; i > index; i--)
      {
         arr[i] = arr[i -1];
      }
      arr[index] = element;
      size++;
   }
   
   private void increaseSize()
   {
      int newSize = arr.length*2;
      Object[] newArr = new Object[newSize];
      System.arraycopy(arr, 0, newArr, 0, arr.length);
      arr = newArr;
   }
   
   public int capacity()
   {
      return arr.length;
   }
   
   public void clear()
   {
      arr = new Object[DEFAULT_CAPACITY];
      size = 0;
   }
   
   public boolean contains(E element)
   {
      for(int i = 0; i < arr.length; i++)
      {
         if((element == null ? arr[i]==null: element.equals(arr[i])))
         {
            return true;
         }
      }
      return false;
   }
   
   @SuppressWarnings("unchecked")
   public E get(int index)
   {
      if((index > size - 1) || index < 0)
      {
         throw new IndexOutOfBoundsException();
      }
      return (E)arr[index];
   }
   
   public int indexOf(E element)
   {
      for(int i = 0; i < arr.length; i++)
      {
         if((element == null ? arr[i]==null: element.equals(arr[i])))
         {
            return i;
         }
      }
      throw new NoSuchElementException();
   }
   
   @SuppressWarnings("unchecked")
   public E remove(int index)
   {
       
      if((index > size -1) || index < 0)
      {
         throw new IndexOutOfBoundsException();
      }
      
      Object result = arr[index];
      
      {
         int numElts = arr.length - ( index + 1 ) ;
         System.arraycopy( arr, index + 1, arr, index, numElts ) ;
      }
      //for(int i = index; i > size - 1; i++)
      //{
      //   arr[i] = arr[i+1];
      //}
      
      size--;
      
      return (E)result;
   }
   
   @SuppressWarnings("unchecked")
   public E set(int index, E element)
   {
      E temp = (E)arr[index];
      if((index > size-1) || index < 0)
      {
         throw new IndexOutOfBoundsException();
      }
      arr[index] = element;
      return temp;
   }
   
   public int size()
   {
      return size;
   }
   
   public void trimToSize()
   {
      int newSize = size;
      if(newSize < DEFAULT_CAPACITY)
      {
         newSize = DEFAULT_CAPACITY;
      }
      
      Object[] newArr = new Object[newSize];
      System.arraycopy(arr, 0, newArr, 0, size);
      arr = newArr;
   }
   
   
   public java.lang.Object[] unusualMethodForTestDriverOnly()
   {
      return arr;
   }
}

