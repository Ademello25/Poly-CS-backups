/**
 * Provided BST class skeleton for students to complete. This class makes
 * use of Object Oriented structural recursion to solve the problem.
 *
 * Original Revision:
 * @author Kurt Mammen
 * @version Lab 7
 *
 * Completed By:
 * @author Alexander DeMello
 */
import java.util.List;
import java.util.NoSuchElementException;

public class BST<E extends Comparable<? super E>>
{
   // Polymorphic BST node type!
   private interface BSTNode<E>
   {
      public BSTNode<E> insert(E element);
      public boolean contains(E element);
      public E minimum(E minimum);
      public E maximum(E maximum);
      public void toSortedList(List<E> list);
   }

   // Instance variables for BST class.
   // These are the only ones allowed!
   private BSTNode<E> root = new EmptyNode();
   private int size;

   ////////////////////////////////////////////////////////////////////////////
   // BST class methods...
   //
   public void insert(E element)
   {
      if (element == null)
      {
         throw new IllegalArgumentException();
      }

      root = root.insert(element);
   }

   public boolean contains(E element)
   {
      if (element == null)
      {
         return false;
      }

      return root.contains(element);
   }
   
   public E minimum()
   {
      if (size == 0)
      {
         throw new NoSuchElementException();
      }

      return root.minimum(((Node)root).element);
   }

   public E maximum()
   {
      if (size == 0)
      {
         throw new NoSuchElementException();
      }

      return root.maximum(((Node)root).element);
   }
 
   public void toSortedList(List<E> list)
   {
      root.toSortedList(list);
   }

   public int size()
   {
      return size;
   }

   ////////////////////////////////////////////////////////////////////////////
   // private EmptyNode class...
   //
   private class EmptyNode implements BSTNode<E>
   {
      // No instance variables needed or allowed!

      public BSTNode<E> insert(E element)
      {
         Node n = new Node();
         EmptyNode er = new EmptyNode();
         EmptyNode el = new EmptyNode();
         
         n.left = el;
         n.right = er;
         n.element = element;
         
         size++;
         return n;
         
      }

      public boolean contains(E element)
      {
         return false;
      }
      
      public E minimum(E element)
      {
         return element;
      }

      public E maximum(E element)
      {
         return element;
      }

      public void toSortedList(List<E> list)
      {
      }
   }

   ////////////////////////////////////////////////////////////////////////////
   // private Node class...
   //
   private class Node implements BSTNode<E>
   {
      // These are the only instance variables needed
      // and the only ones allowed!
      E element;
      BSTNode<E> left, right;

      public BSTNode<E> insert(E element)
      {
         if(element.compareTo(this.element) > 0)
         {
            this.right = this.right.insert(element);
         }
         
         else if(element.compareTo(this.element) < 0)
         {
            this.left = this.left.insert(element);
         }
         
         return this;
      }

      public boolean contains(E element)
      {
         if(element.compareTo(this.element) == 0)
         {
            return true;
         }
         
         else if(element.compareTo(this.element) > 0)
         {
            return right.contains(element);
         }
         else if(element.compareTo(this.element) < 0)
         {
            return left.contains(element);
         }
         
         return false;
      }
      
      public E minimum(E element)
      {
         return left.minimum(this.element);
      }
      
      public E maximum(E element)
      {
         return right.maximum(this.element);
      }
      
      public void toSortedList(List<E> list)
      {
         left.toSortedList(list);
         list.add(this.element);
         right.toSortedList(list);
      }
   }
}
