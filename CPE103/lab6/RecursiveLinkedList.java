/**
 * A provided, partially complete, simple, recursive, singly-linked list
 * for CPE 103 Lab 6.
 *
 * @author Kurt Mammen (Provided skeleton).
 *
 * Insert you name as the second author below as the person that completed
 * the class.
 *
 * @author Alexander DeMello
 * @version CPE 103 Lab 6
 */

// Class invariants:
//
// The head reference is never null.
// The head node's next field will be null when the list is empty.
// The next field of the last node in the list is always null.
//
public class RecursiveLinkedList<E> implements SimpleList<E>
{
   private class Node
   {
      Node next;
      E e;

      Node()
      {}

      Node(Node next, E e)
      {
          this.next = next;
          this.e = e;
      }
   }

   private Node head;
   private Throwable stackTrace;

   public RecursiveLinkedList()
   {
      head = new Node();
   }

/*   public void goodAdd(E e)
   {
      stackTrace = new Throwable();
      if(head == null)
      {
         head = new Node(null,e);
      }
      else
      {
         goodAddHelper(head,e);
      }
   }

   public void goodAddHelper(Node n, E e)
   {
      stackTrace = new Throwable();
      if(n.next==null)
      {
         n.next= new Node(null,e);
      } 
      else
      {
         goodAddHelper(n.next,e);
      }
   } */

   public void add(E element)
   {
      head.next = add(head.next, element);
   }

   public void add(int index, E element)
   {
      if(index < 0) 
      {
         throw new IndexOutOfBoundsException();
      }
        
        head.next = add(head.next, index, element);
   }

   public E get(int index)
   {
      return get(head,index);
   }
    
   private E get(Node n, int index)
   {            
      if(n==null)
      {
         throw new IndexOutOfBoundsException();
      }
      
      if(index>0)
      {
         return get(n.next, index -1);
      }
      
      else if(index < 0)
      {
         throw new IndexOutOfBoundsException();
      }
      
      return n.e;

   }

   public void remove(int index)
   {
      stackTrace = new Throwable();
      if(head == null)
      {
         throw new IndexOutOfBoundsException();
      }
      else if(index == 0)
      {
         head = head.next;
      }
      else
      {
         remove(head,index);
      }
   }
   
   public void remove(Node n, int index)
   {
      stackTrace = new Throwable();
      if(n.next == null)
      {
         throw new IndexOutOfBoundsException();
      }
      
      if(index == 0)
      {
         n.next = n.next.next;
      }
      
      else
      {
         remove(n.next, index - 1);
      }
   }

   public int size()
   {
      return size(head.next);
   }

   public Throwable stackTrace()
   {
      return stackTrace;
   }

   // Private recursive helper method for public add(E)
   private Node add(Node node, E e)
   {
      stackTrace = new Throwable();

      if(node == null)
      {
         return new Node(null, e);
      }
      else
      {
         node.next = add(node.next, e);
      }

      return node;
   }

   // Private recursive helper method for public add(int,E)
   private Node add(Node node, int index, E e)
   {
      stackTrace = new Throwable();
      if(index==0)
      {
         return new Node(node,e);
      }
     
      else if(node == null)
      {
         throw new IndexOutOfBoundsException();
      }
     
      else
      {
         node.next = add(node.next, --index, e);
      }

      return node;
   }

   // Private recursive helper method for public get(int) 
   private int size(Node node)
   {
      stackTrace = new Throwable();

      if (node == null)
      {
         return 0;
      }

      return 1 + size(node.next);
    }
}