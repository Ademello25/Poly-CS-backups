
/**
 * Provided BST class skeleton for students to complete. This class makes use of Object
 * Oriented structural recursion to solve the problem.
 *
 * Original Revision:
 *
 * @author Kurt Mammen
 * @version Lab 8
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

      public BSTNode<E> remove(E element);
      
      public int treeHeight();
      
      public long internalPathLength(long x);

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

      return root.minimum(((Node) root).element);
   }

   public E maximum()
   {
      if (size == 0)
      {
         throw new NoSuchElementException();
      }

      return root.maximum(((Node) root).element);
   }

   public void toSortedList(List<E> list)
   {
      root.toSortedList(list);
   }

   public int size()
   {
      return size;
   }

   public void remove(E element)
   {
      if(size == 0)
      {
         throw new NoSuchElementException();
      }
      size--;
      root = root.remove(element);
   }
   
   public int treeHeight()
   {
      if(size ==0)
      {
         return -1;
      }
      
      return root.treeHeight() -1;
   }
   
   public long internalPathLength()
   {
      if(size == 0)
      {
         return -1;
      }
      
      return root.internalPathLength(0);
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

      public BSTNode<E> remove(E element)
      {
         throw new NoSuchElementException();
      }
      
      public int treeHeight()
      {
         return 0;
      }
      
      public long internalPathLength(long x)
      {
         return 0;
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
         if (element.compareTo(this.element) > 0)
         {
            this.right = this.right.insert(element);
         }
         else if (element.compareTo(this.element) < 0)
         {
            this.left = this.left.insert(element);
         }

         return this;
      }

      public boolean contains(E element)
      {
         if (element.compareTo(this.element) == 0)
         {
            return true;
         }
         else if (element.compareTo(this.element) > 0)
         {
            return right.contains(element);
         }
         else if (element.compareTo(this.element) < 0)
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

      public BSTNode<E> remove(E element)
      {

         if (element.compareTo(this.element) == 0)
         {
            if (left.getClass() == EmptyNode.class && right.getClass() == EmptyNode.class)
            {
               return new EmptyNode();
            }
            else if (right.getClass() == EmptyNode.class && left.getClass() == Node.class)
            {
               return left;
            }
            else if (right.getClass() == Node.class && left.getClass() == EmptyNode.class)
            {
               return right;
            }
            else
            {
               this.element = right.minimum(element);
               right.remove(right.minimum(element));
            }
         }

         else if (element.compareTo(this.element) > 0)
         {
            right = right.remove(element);
         }
         
         else
         {
            left = left.remove(element);
         }
         
         return this;


      }
      
      public int treeHeight()
      {
         return (1 + Math.max(left.treeHeight(), right.treeHeight()));
      }
      
      public long internalPathLength(long x)
      {
         return (x + left.internalPathLength(x+1) + right.internalPathLength(x+1));
      }
   }

}


