/**
 * Tools for finding prime numbers.
 * 
 * @author Alexander DeMello
 * @version lab9
 */
import java.util.NoSuchElementException;

public class PrimeTools 
{
   public static boolean isPrime(int value)
   {
      if(value ==2)
      {
         return true;
      }
      if(value%2 == 0)
      {
         return false;
      }
      
      for(int i =3; i*i<=value; i += 2)
      {
         if(value%i==0)
         {
            return false;
         }
      }
      return true;
   }
   
   public static int nextPrime(int value)
   {
      if(value == 2)
      {
         return 2;
      }
      else if(value < 0)
      {
         throw new IllegalArgumentException();
      }
      for(int i=value; i <= Integer.MAX_VALUE; i++)
      {
         if(isPrime(i))
         {
            return i;
         }
      }
      
      throw new NoSuchElementException();
   }
}
