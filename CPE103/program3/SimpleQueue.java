/**
 * Description
 * 
 * @author Alexander DeMello
 * @version program 3 
 */
public interface SimpleQueue <E>
{
   public E dequeue();
   
   public void enqueue(E element);
   
   public E peek();
   
   public int size();
   
}
