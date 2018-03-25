import java.util.NoSuchElementException;
/**
 * A singly linked simple version of a linked stack.
 * 
 * @author Alexander DeMello
 * @version program 2
 */
public class SimpleLinkedStack <E> implements SimpleStack <E>
{
   private Node head;
   private int size;
   
   public SimpleLinkedStack()
   {
      this.head = new Node();
      head.next = head;
      this.size = 0;
   }
   
   private class Node
   {
      public E data;
      public Node next;
   }
   
   public E peek()
   {
      if(size == 0)
      {
         throw new NoSuchElementException();
      }
      
      Node temp = new Node();
      temp = head;
      
      E result = temp.data;
      
      return result;
   }
   
   public E pop()
   {
      if(size == 0)
      {
         throw new NoSuchElementException();
      }
      
      E result;
      //System.out.println(head.data);
      result = head.data;
      
      
      Node temp = new Node();
      
      temp = head.next;
      head = temp;
      size--;
      
      return result;
   }
   
   public void push(E element)
   {
         Node temp = new Node();
         
         temp.next = head;
         temp.data = element;
         head = temp;
         
         size++;
   }
      
   
   public int size()
   {
      return size;
   }
           
   
}
