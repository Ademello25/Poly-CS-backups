/**
 * An interace for the a simple linked list class
 *
 *@author Alexander DeMello
 *@version Lab 1
 */

public interface SimpleList<E>
{
   public void add(E data);
   
   public void add(int index, E element);
   
   public E get(int index);
   
   public E remove(int index);
   
   public int size();
}
