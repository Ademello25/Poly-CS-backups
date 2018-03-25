
import java.util.NoSuchElementException;

/**
 * Description
 *
 * @author Alexander DeMello
 * @version program 5
 */
public class HashQuadratic<E> implements HashTable<E>, HashMetrics
{

   private HashEntry[] hashTable;
   private long collisions;
   private int size;
   private int tableSize;
   private int lastCollisions;
   private int maxCollisions;

   public HashQuadratic(int tableSize)
   {
      this.tableSize = nextPrime(tableSize);
      hashTable = new HashEntry[this.tableSize];
   }

   private static class HashEntry
   {

      Object element;
      boolean used;
   }

   public int avgCollisions()
   {
      if (size == 0)
      {
         return 0;
      }
      return (int) collisions / size;
   }

   public int maxCollisions()
   {
      return maxCollisions;
   }

   public int lastOpCollisions()
   {
      return lastCollisions;
   }

   public long collisions()
   {
      return collisions;
   }

   public int tableSize()
   {
      return tableSize;
   }

   public int size()
   {
      return size;
   }

   public double loadFactor()
   {
      return ((double) size) / ((double) tableSize);
   }

   public void insert(E element)
   {
      if (loadFactor() >= .5)
      {
         throw new HashLoadFactorException();
      }
      //else if(contains(element))
      //{
      //   return;
      //}

      int i = 0, count = 0, hashkey;
      hashkey = Math.abs(element.hashCode());
      int hash = Math.abs(hashkey % tableSize);

      HashEntry result = new HashEntry();
      result.element = element;
      result.used = true;

      while (true)
      {
         if (hashTable[hash] == null || hashTable[hash].element.equals(element))
         {

            hashTable[hash] = result;

            break;
         }
         else
         {
            count++;
            i++;
            hash = Math.abs(((hashkey + (i * i)) % tableSize));
         }
      }
      if (count > maxCollisions)
      {
         maxCollisions = count;
      }
      collisions += count;
      lastCollisions = count;
      size++;
   }

   public void remove(E element)
   {
      int i = 0, count = 0, hashkey;
      hashkey = Math.abs(element.hashCode());
      int hash = hashkey & tableSize;

      while (true)
      {
         if(hashTable[hash] == null)
         {
            return;
         }

         else if (hashTable[hash].element.equals(element))
         {
            size--;
            hashTable[hash].used = false;
         }
         else
         {
            count++;
            i++;
            hash = Math.abs((hashkey + (i * i)) % tableSize);
         }
      }
   }

   public boolean contains(E element)
   {
      int i = 0, count = 0, hashk;
      hashk = Math.abs(element.hashCode());
      int hash = hashk % (tableSize);

      while (true)
      {
         if (hashTable[hash] == null)
         {
            lastCollisions = count;
            return false;
         }
         /*else if (hashTable[hash].used == false)
         {
            lastCollisions = count;
            return false;
         }*/
         else if (hashTable[hash].element.equals(element) && hashTable[hash].used == true)
         {
            lastCollisions = count;
            return true;
         }
         else if (hashTable[hash].element.equals(element) && hashTable[hash].used == false)
         {
            lastCollisions = count;
            return false;
         }
         else
         {
            count++;
            i++;
            hash = Math.abs((hashk + (i * i)) % tableSize);
         }
      }
   }

   private static boolean isPrime(int value)
   {
      if (value == 2)
      {
         return true;
      }
      if (value % 2 == 0)
      {
         return false;
      }

      for (int i = 3; i * i <= value; i += 2)
      {
         if (value % i == 0)
         {
            return false;
         }
      }
      return true;
   }

   private static int nextPrime(int value)
   {
      if (value < 0)
      {
         throw new IllegalArgumentException();
      }
      if (value == 2)
      {
         return 2;
      }
      else if (value < 0)
      {
         throw new IllegalArgumentException();
      }
      for (int i = value; i <= Integer.MAX_VALUE; i++)
      {
         if (isPrime(i))
         {
            return i;
         }
      }

      throw new NoSuchElementException();
   }

}
