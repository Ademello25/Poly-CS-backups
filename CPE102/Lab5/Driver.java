import java.util.Scanner;

public class Driver
{
   public static void main(String[] args)
   {
      ArrayStuff myArrayStuff = new ArrayStuff();
      Scanner sc = new Scanner(System.in);
      int minInt, numTrue, numString;
      double avg;
      
      while(true)
      {
         System.out.println("Please input an integer, double, boolean, or String");
         
         if(sc.hasNextInt())
         {
            myArrayStuff.add(sc.nextInt());
         }
         else if(sc.hasNextDouble())
         {
            myArrayStuff.add(sc.nextDouble());
         }
         else if(sc.hasNextBoolean())
         {
            myArrayStuff.add(sc.nextBoolean());
         }
         else
         {
            String temp = sc.next();
            if(temp.equals("quit"))
            {
               break;
            }
            else
            {
               myArrayStuff.add(temp);
            }
         }
      }
      minInt = myArrayStuff.minimumInt();
      avg = myArrayStuff.averageDouble();
      numTrue = myArrayStuff.numberOfTrues();
      numString = myArrayStuff.numberOfStrings();
      System.out.println("The minimum integer value is: " + minInt);
      System.out.println("The average all double values is: " +avg);
      System.out.println("The number of true values is: " +numTrue);
      System.out.println("The number of Strings is: " +numString);
      
   }
}