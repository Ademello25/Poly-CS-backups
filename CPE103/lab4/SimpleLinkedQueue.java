/**
 * Methods for a linked list implementation for a queue
 * 
 * @author Alexander DeMello
 * @version Lab 2
 */
import java.util.NoSuchElementException;

public class SimpleLinkedQueue <E> implements SimpleQueue<E>
{
   private Node head;
   private int size;
   
   public SimpleLinkedQueue()
   {
      head = new Node();
      head.next = head;
      head.prev = head;
   }
   
   
   private class Node
   {
      E data;
      Node next;
      Node prev;
   }
   
   public E dequeue()
   {
      if(size == 0)
      {
         throw new NoSuchElementException();
      }
      
      Node temp = head.next;
     
      
      temp.prev.next = temp.next;
      temp.next.prev = temp.prev;
      size--;
      
      return temp.data;
      
   }
   
   public void enqueue(E data)
   {
      Node temp = new Node();
      temp.data = data;
      head.prev.next = temp;
      temp.prev = head.prev;
      head.prev = temp;
      temp.next = head;
      size++;
   }
   
   public E peek()
   {
      if(size==0)
      {
         throw new NoSuchElementException();
      }
      
      Node temp = new Node();
      temp = head.next;
      
      E result = temp.data;
      
      return result;
   }
   
   public int size()
   {
      return size;
   }
}
