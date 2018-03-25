/**
 * Modified SimpleList interface from Lab 1 - notice that the remove method's
 * return type has changed to void to accomodate the recursive nature of the
 * lab!
 *
 * @author Kurt Mammen
 * @version 10/11/2012 CPE 103 Lab 6
 */

public interface SimpleList<E>
{
   /**
    * Appends the specified element to the end of this list.
    *
    * Allows null elements to be added to the list.
    *
    * @param element The element to be appended to this list.
    */
   public void add(E element);

   /**
    * Inserts the specified index at the specified position in the list
    * by shifting any existing and subsequent elements to the right by
    * one (numerically greater).
    *
    * @param index The index at which the element is to be inserted.
    * @param element The element to be inserted
    *
    * @throws IndexOutOfBoundsException If the index is less than zero or
    *         greater than the size() of the list.
    */
   public void add(int index, E element);

   /**
    * Redturns the element at the specfied position in this list.
    *
    * @param index The index of the element to return
    *
    * @return The element at the specified position in this list
    *
    * @throws IndexOutOfBoundsException If the index is less than zero or
    *         greater than or equal to the size() of the list.
    */
   public E get(int index);

   /**
    * Removes the specified element from this list and shifts any subsequent
    * elements to the left by one (numerically lower index).
    *
    * @param index The index of the element to be removed
    *
    * @throws IndexOutOfBoundsException If the index is less than zero or
    *         greater than or equal to the size() of the list.
    */
   public void remove(int index);

   /**
    * Returns the number of elements in this list (elements that have been
    * added by the creater/user of the list).
    *
    * @return The number of elements in this list
    */
   public int size();
}