/**
 * Interface for a simple queue class
 * 
 * @author Alexander DeMello
 * @version  program 1
 */
public interface SimpleQueue <E>
{
   public E dequeue();
   public void enqueue(E element);
   public E peek();
   public int size();
}
