/**
 * Interface for a simplified array stack class
 * 
 * @author Alexander DeMello
 * @version Lab 3 
 */
public interface SimpleStack <E>
{
   
   public E peek();
   public E pop();
   public void push(E element);
   public int size();
   
}
