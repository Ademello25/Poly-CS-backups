import java.util.NoSuchElementException;
import java.util.Iterator;

/** 
 * A recursive singly linked list.
 *
 * @author Kurt Mammen

 * Objectives:
 *
 *   1) Exposure to recursion.
 *   2) Exposure to structural recursion.
 *
 * @version 2.0 - Added Iterable<E>
 */

/* Class RecursiveList
 * 
 * THE ONLY MODIFICATONS YOU MAY MAKE TO THIS CLASS ARE:
 * 
 *    1) Completing the methods of the private inner class ListNode
 *    2) Changes necessary to implement the Iterable<E> interface.
 */
public class RecursiveList<E> implements Iterable<E>
{
   private Node<E> head = new EmptyNode<E>();
   private Throwable stackTrace;
   
      private class iterate implements Iterator<E>
      {
         private Node<E> cursor = head;
         public boolean hasNext()
         {
            return !(cursor.isEmpty());
         }
         public E next()
         {
            if(hasNext())
            {
               E result = (E)((ListNode)cursor).element;
               cursor = ((ListNode)cursor).next;
               return result;
            }
            throw new NoSuchElementException();
         }
         public void remove()
         {
            throw new UnsupportedOperationException("Remove is not featured!");
         }
         
      }
      
      public Iterator<E> iterator()
      {
         return new iterate();
      }

   /** Used to determine if the list is empty or not.
    * @return true if empty, otherwise false
    */
   public boolean isEmpty()
   {
      return head.isEmpty();
   }
   
   /** Used to access the number of elements in the list - O(N) operation.
    * @return The number of elements in the list.
    */
   public int size()
   {
      return head.size();
   }
   
   /** Adds the specified element to the end of the list - O(N) operation.
    * @param element The element to add to the list.
    */
   public void add(E element)
   {
      head = head.add(element);
   }

   /** Used to access the specified element of the list.
    * @param index The zero-based index of the desired element.
    * @throws IndexOutOfBoundsException if the index is not valid.
    * @return The requested element
    */
   public E get(int index)
   {
      return head.get(index);
   }
   
   /** Used to obtain the zero-based index of the first element that is equal
    * to the specified element using its equals(Object) method.
    * @param element The element to search for in the list.
    * @throws java.util.NoSuchElementException() if there is not element in
    * the list that is equal to the specified element.
    * @return The zero-based index of the first element equal to the
    * specfied element using its equals(Object) method.
    */
   public int indexOf(E element)
   {
      return head.indexOf(element);
   }

   /** Used to modify the element at the specified index.
    * @param index The zero-based index of the element to modify.
    * @param element The new element
    * @throws IndexOutOfBoundsException if the index is not valid.
    * @return The old element at the specified index.
    */
   public E set (int index, E element)
   {
      return head.set(index, element);
   }
   
   /** Using the list element's equals method this method determines if two
    * lists have the same items in the same order - NOTE: You must return
    * false at the earliest (least amount of work) opportunity!
    *
    * @param other The other list to check for equality with this list.
    * @return true if the lists contain the same objects (logical equality)
    * in the same order, otherwise false.
    */
   public boolean equals(Object other)
   {
      if (other == null )
      {
         return false;
      }

      if (getClass() != other.getClass())
      {
         return false;
      }

      return head.equals(((RecursiveList)other).head);
   }
   
   /** Adds the specified element to list in the specified location.
    * The element at that position, if any, is not overwritten.  This method
    * may be used to add elements to the end of the list by specifying an index
    * equal to the list's current size.
    * @param index The zero-based index of the position to add the element.
    * @param element The element to add to the list.
    * @throws IndexOutOfBoundsException if the index is not valid.
    */
   public void add(int index, E element)
   {
      head = head.add(index, element);
   }

   /** Used to remove the element at the specified postion in the list.
    * @param index The zero-based index of the element to remove from the list.
    * @throws IndexOutOfBoundsException if the index is not valid.
    */   
   public void remove (int index)
   {
      head = head.remove(index);
   }
   
   /** Used retrieve a stack trace of the previous method call which is used to
    * verify the correct recursive behavior of the implementation.
    * @return A reference to the Throwable object constructed as the first line
    * OF ALL ListNode and EmptyNode methods.
    */
   public Throwable getStackTrace()
   {
      return stackTrace;
   }
   
   /* The private inner interface that represents a List Node. There are two
    * kinds of Nodes, EmptyNode and ListNode. The EmptyNode class is complete
    * and you will complete the ListNode class.
    *
    * DO NOT MODIFY THIS INTERFACE!
    */
   private interface Node<E>
   {
      // Fundamental and/or easier methods - some/all demonstrated in class...
      boolean isEmpty();
      int size();
      Node<E> add(E element);
      E get(int index);

      // Easier methods... 
      int indexOf(E element);
      E set (int index, E element);

      // More challenging methods...
      boolean equals(Object other);
      Node<E> add(int index, E element);
      Node<E> remove (int index);
   }
   
   /* This class is complete and correct - DO NOT MODIFY THIS CLASS! 
    */
   private class EmptyNode<E> implements Node<E>
   {
      public boolean isEmpty()
      {
         stackTrace = new Throwable();
         return true;
      }
      
      public int size()
      {
         stackTrace = new Throwable();
         return 0;
      }

      public Node<E> add(E element)
      {
         stackTrace = new Throwable();

         ListNode<E> n = new ListNode<E>();
         n.element = element;
         n.next = this;
         
         return n;
      }
      
      public E get(int index)
      {
         stackTrace = new Throwable();
         throw new IndexOutOfBoundsException();
      }
      
      public E set(int index, E element)
      {
         stackTrace = new Throwable();
         throw new IndexOutOfBoundsException();
      }
      
      public int indexOf(E element)
      {
         stackTrace = new Throwable();
         throw new java.util.NoSuchElementException();
      }
      
      public boolean equals(Object node)
      {
         stackTrace = new Throwable();
         return node instanceof EmptyNode;
      }
      
      public Node<E> add(int index, E element)
      {
         stackTrace = new Throwable();
         
         if (index != 0)
         {
            throw new IndexOutOfBoundsException();
         }
         
         return add(element);
      }
            
      public Node<E> remove (int index)
      {
         stackTrace = new Throwable();
         throw new IndexOutOfBoundsException();
      }
   } // End of EmptyNode inner class
   
   /* TODO: Complete this class following these restrictions and requirements:
    *  
    *   1) Do not add any instance variables.
    *   2) Do not add any constructors.
    *   3) Do not add any methods (except as necessary to support Iterable<E>.
    *   4) Always initialize the stackTrace in the first line of each method.
    *      This is required to pass the test driver.
    */ 
   private class ListNode<E> implements Node<E>
   {
      public E element;
      public Node<E> next;
      
      public boolean isEmpty()
      {
         stackTrace = new Throwable();
         return false;
      }
      
      public int size()
      {
         stackTrace = new Throwable();
         return 1 + next.size();
      }
      
      public Node<E> add(E element)
      {
         stackTrace = new Throwable();
         next = next.add(element);
         return this;
      }
      
      public E get(int index)
      {
         stackTrace = new Throwable();

         if (index == 0)
         {
            return element;
         }

         return next.get(index - 1);
      }

      public int indexOf(E element)
      {
         stackTrace = new Throwable();
         
         
         if(this.element.equals(element))
         {
            return 0;
         }
         else
         {
            
            return 1 + this.next.indexOf(element);
         }
      }
      
      public E set (int index, E element)
      {
         stackTrace = new Throwable();
         E result;
         
         
         if(index == 0)
         {
            result = this.element;
            this.element = element;
            return result;
         }
         
         return next.set(index-1, element);
         
      }
      
      public boolean equals(Object node)
      {
         stackTrace = new Throwable();
         
         if(this.getClass() != node.getClass())
         {
            return false;
         }
         
         if(node.getClass() == EmptyNode.class)
         {
            return true;
         }
                  
         if((this.element).equals(((ListNode)node).element))
         {
            return next.equals(((ListNode)node).next);
         }
         else
         {
            return false;
         }
         
         
      }

      public Node<E> add(int index, E element)
      {
         stackTrace = new Throwable();
         int count  = 0;
         ListNode<E> n = new ListNode<E>();
         
         if(index <0)
         {
            throw new IndexOutOfBoundsException();
         }
                  
         if(index == 0)
         {
            n.next = this;
            n.element = element;
            return n;
         }

         next = next.add(index - 1, element);
         
         return this;

      }
      
      public Node<E> remove (int index)
      {
         stackTrace = new Throwable();
         if(index < 0)
         {
            throw new IndexOutOfBoundsException();
         }
         
         if(index == 0)
         {
            return next;
         }
         
         next = next.remove(index -1);
         
         return this;
         
      }
      
      
   } // End of ListNode inner class
} // End of RecursiveList class
