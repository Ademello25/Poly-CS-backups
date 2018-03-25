import java.util.Iterator;
import java.util.NoSuchElementException;

public class BasicLinkedList<E> implements BasicList<E>, Iterable<E>
{
   private int size;
   private Node head;
   
   private class Node
   {
      public E data;
      public Node prev;
      public Node next;
   }
   
   public BasicLinkedList()
   {
      head = new Node();
      head.next = head;
      head.prev = head;
   }
   
   public void add(E data)
   {
      Node temp = new Node();
      temp.data = data;
      head.prev.next = temp;
      temp.prev = head.prev;
      head.prev = temp;
      temp.next = head;
      size++;
   }
  
   public void add(int index, E data)
   {
      if(index > size || index < 0)
      {
         throw new IndexOutOfBoundsException();
      }
      Node temp = head.next;
      int count = 0;

      while(count < index)
      {
         temp = temp.next;
         count ++;
      }
      
      Node result = new Node();
      result.data= data;
      temp.prev.next = result;
      result.prev = temp.prev;
      temp.prev = result;
      result.next = temp;
      
      size++;
      
   }
   
   public E get(int index)
   {
      Node temp = head.next;
      int count =0;
      E result;
      
      if(index < 0 || index >= size)
      {
         throw new IndexOutOfBoundsException();
      }
      else if(size ==0)
      {
         return temp.data;
      }
      
      while((temp.next != head) && count < index)
      {
         temp = temp.next;
         count++;
      }
      
      result = temp.data;
      return result;
      
   }
   
   
   public int size()
   {
      return size;
   }
   
   public void clear()
   {
      head.next = head;
      head.prev = head;
      size = 0;
   }
   
   public boolean contains(E element)
   {
      
      Node temp = head.next;
      while(temp != head)
      {
         if(temp.data.equals(element))
         {
            return true;
         }
         temp = temp.next;
      }
      return false;
   }
   
   public int indexOf(E element)
   {
      int count = 0;
      Node temp = head.next;
      while(temp != head)
      {  
         if(temp.data.equals(element))
         {
            return count;
         }
         
         temp = temp.next;
         count ++;
      }
      throw new NoSuchElementException();
   }
   
   public E remove(int index)
   {
      if(index >= size || index < 0)
      {
         throw new IndexOutOfBoundsException();
      }
      E result;
      Node temp = head.next;
      int count = 0;

      while(count < index)
      {
         temp = temp.next;
         count ++;
      }
      
      result = temp.data;
      temp.prev.next = temp.next;
      temp.next.prev = temp.prev;
      
      size--;
      
      return result;
   }
   
   public E set(int index, E element)
   {
      Node temp = head.next;
      int count =0;
      E result;
      
      if(index < 0 || index >= size)
      {
         throw new IndexOutOfBoundsException();
      }
      
      while((temp != head) && count < index)
      {
         temp = temp.next;
         count++;
      }
      
      result = temp.data;
      temp.data = element;
      return result;
   }
   
   
   public BasicListIterator<E> basicListIterator()
   {
      return new iterate();
   }
   
   public Iterator<E> iterator()
   {
      return new iterate();
   }
   
   private class iterate implements BasicListIterator<E>
   {
      private Node cursor = head;
      
      public boolean hasNext()
      {
         return (cursor.next !=head);
      }
      
      public E next()
      {
         if(!hasNext())
         {
            throw new NoSuchElementException();
         }
         
         cursor = cursor.next;
         return cursor.data;
      }
      
      public void remove()
      {
         throw new UnsupportedOperationException("The remove function is not implemented.");
      }
      
      public boolean hasPrevious()
      {
         return (cursor != head);
      }
      
      public E previous()
      {
         if(!hasPrevious())
         {
            throw new NoSuchElementException();
         }
         
         E result = cursor.data;
         cursor = cursor.prev;
         return result;
      }
   }
}