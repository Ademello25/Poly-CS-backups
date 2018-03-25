/**
 * interface for the simpleQueue class
 * 
 * @author Alexander DeMello
 * @version Lab2
 */
public interface SimpleQueue<E>
{
   public E dequeue();
   
   public void enqueue(E element);
   
   public E peek();
   
   public int size();
}
