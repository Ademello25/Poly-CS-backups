/**
 * Several classes to make a Linked list function
 * 
 * @author Alexander DeMello
 * @version Lab1
 */

public class SimpleLinkedList <E> implements SimpleList<E>
{
   private int size;
   private Node head;
   
   public SimpleLinkedList()
   {
      head = new Node();
      head.next = head;
      head.prev = head;
      
   }
   
   private class Node
   {
      public E data;
      public Node next;
      public Node prev;
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
         count++;
      }
      Node result = new Node();
      result.data = data;
      temp.prev.next = result;
      result.prev = temp.prev;
      temp.prev = result;
      result.next = temp;
      size++;
      
   }
   
   public E get(int index)
   {
      Node temp = head.next;
      int count = 0;
      E result;
      
      if(index < 0 || index >= size)
      {
         throw new IndexOutOfBoundsException();
      }
      
      else if (size == 0)
      {
         return temp.data;
      }
      
      while((temp.next != head) && (count < index))
      {
         temp = temp.next;
         count++;
      }
      result = temp.data;
      return result;
      
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
         count++;
      }
      
      result = temp.data;
      temp.prev.next = temp.next;
      temp.next.prev = temp.prev;
      
      size--;
      return result;
      
   }
   
   public int size()
   {
      return this.size;
   }
   

}
