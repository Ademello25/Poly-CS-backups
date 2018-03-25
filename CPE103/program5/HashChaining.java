
import java.util.NoSuchElementException;

/**
 * Description
 *
 * @author Alexander DeMello
 * @version
 */
public class HashChaining<E> implements HashTable<E>, HashMetrics
{

   private HashEntry[] hashTable;
   private long collisions;
   private int size;
   private int tableSize;
   private int lastCollisions;
   private int maxCollisions;

   public HashChaining(int tableSize)
   {
      this.tableSize = nextPrime(tableSize);
      hashTable = new HashEntry[this.tableSize];
   }

   public static class HashEntry
   {

      Object element;
      HashEntry next;
   }
   

   public boolean contains(E element)
   {
      int hash = element.hashCode();
      int count = 0;
      hash = Math.abs(hash % (tableSize));
      if (hashTable[hash] != null)
      {
         if (element.equals(hashTable[hash].element))
         {
            return true;
         }
         else
         {
            count++;
            HashEntry tempHash = hashTable[hash];

            while (tempHash.next != null)
            {
               count++;
               tempHash = tempHash.next;

               if (element.equals(tempHash.element));
               {
                  
                  return true;
               }
            }
         }
      }
      lastCollisions = count;

      return false;

   }

   public void insert(E element)
   {
      if (contains(element))
      {
         return;
      }

      int count = 0;
      size++;
      HashEntry result = new HashEntry();
      result.element = element;

      int hash = element.hashCode();
      hash = Math.abs(hash % (tableSize));
      if (hashTable[hash] != null)
      {
         count++;
         HashEntry temp = hashTable[hash];
         while (temp.next != null)
         {
            count++;
            temp = temp.next; 
         }
         
         temp.next = result;
      }
      else
      {
         hashTable[hash] = result;
      }
      
      collisions += count;
      lastCollisions = count;
      if(count > maxCollisions)
      {
         maxCollisions = count;
      }
   }

   public double loadFactor()
   {
      return (((double) size) / ((double) tableSize));
   }

   public void remove(E element)
   {
      if(!contains(element))
      {
         return;
      }
      
      int count = 0;
      int hash = element.hashCode();
      hash = Math.abs(hash % (tableSize));
      
      if(hashTable[hash].element.equals(element))
      {
         hashTable[hash] = hashTable[hash].next;
      }
      else
      {
         removeHelp(hashTable[hash], element);
         
      }
   }

   public int size()
   {
      return this.size;
   }

   public int tableSize()
   {
      return this.tableSize;
   }

   public long collisions()
   {
      return this.collisions;
   }

   public int lastOpCollisions()
   {
      return this.lastCollisions;
   }

   public int maxCollisions()
   {
      return this.maxCollisions;
   }

   public int avgCollisions()
   {
      if(size == 0)
      {
         return 0;
      }
      return (int) (collisions / size);
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
   
   
   private void removeHelp(HashEntry en, E element)
   {
     /* if(en.next == null)
      {
         
      } */
      
      if(en.next.element.equals(element))
      {
         en.next = en.next.next;
      }
      
      else
      {
         removeHelp(en.next, element);
      }
   }

}
