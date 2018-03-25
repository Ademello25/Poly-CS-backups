/**
 * Description
 * 
 * @author Alexander DeMello
 * @version Program 5
 */
public interface HashTable <E>
{
   public boolean contains(E element);
   public void insert(E element);
   public double loadFactor();
   public void remove(E element);
   public int size();
   public int tableSize();
}
