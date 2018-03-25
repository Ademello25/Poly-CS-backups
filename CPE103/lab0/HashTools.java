/**
 * 
 * 
 * @author Alexander DeMello
 * @version lab 9
 */
public class HashTools 
{
   public static <E> int collisions(java.util.List<E> list, int tableSize, 
           Hashable<E> hashable)
   {
      boolean[] arr = new boolean[PrimeTools.nextPrime(tableSize)];
      int count = 0;
      for(int i = 0; i < list.size(); i++)
      {
         int hashVal = hashable.hash(list.get(i));
         if(arr[hashVal %tableSize] == true)
         {
            count++;
         }
         else
         {
            arr[hashVal %tableSize] = true;
         }
      }
      
      return count;
   }
   //NOT DONE WITH THIS ONE
   public static <E> int maxCollisions(java.util.List<E> list, int tableSize, 
           Hashable<E> hashable)
   {
      int[] arr = new int[PrimeTools.nextPrime(list.size())];
      int count = 0;
      for(int i = 0; i < list.size(); i++)
      {
         int hashVal = hashable.hash(list.get(i));
         arr[hashVal % tableSize]++;
      }
      
    
      
      return count;
   }
   
   public static <E> int unused(java.util.List<E> list, int tableSize, 
           Hashable<E> hashable)
   {
      boolean[] arr = new boolean[PrimeTools.nextPrime(tableSize)];
      int count = 0;
      for(int i = 0; i < list.size(); i++)
      {
         int hashVal = hashable.hash(list.get(i));
         arr[hashVal %tableSize] = true;
      }
      
      for(int i = 0; i < tableSize; i++)
      {
         if(arr[i]==false)
         {
            count++;
         }
      }
      
      return count;
   }
   
   public static <E> int avgCollisions(java.util.List<E> list, int tableSize, 
           Hashable<E> hashable)
   {
      boolean[] arr = new boolean[PrimeTools.nextPrime(tableSize)];
      int count1 = 0;
      int count2 = 0;
      for(int i = 0; i < list.size(); i++)
      {
         int hashVal = hashable.hash(list.get(i));
         arr[hashVal %tableSize] = true;
         count1++;
      }
      
      for(int i = 0; i < tableSize; i++)
      {
         if(arr[i] == true);
         count2++;
      }
      
      return count1/count2;
   }
}
