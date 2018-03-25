import java.util.Scanner;

public class MainCatching 
{
   public static void main(String[] args)
   {
      Catching z = new Catching();
      Scanner sc = new Scanner(System.in);
      try
      {
         System.out.println("Please enter a value between 1 and 3");
         int i = sc.nextInt();
         if(i ==1)
         {
            z.whichException(1);
         }
         if(i ==2)
         {
           z.whichException(2);
         }
         if(i ==3)
         {
           z.whichException(3);
         }
      }
      catch(NullPointerException n)
      {
         System.out.println("Caught a NullPointerException");
      }
      catch(ClassCastException c)
      {
         System.out.println("Caught a ClassCastException");
      }
      catch(ArrayIndexOutOfBoundsException a)
      {
         System.out.println("Caught a IndexOutOfBoundsException");
      }
      finally
      {
         System.out.println("FINALLY! ");
      }
      
   }
}
