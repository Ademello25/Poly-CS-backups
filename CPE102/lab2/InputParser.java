import java.util.Scanner;

public class InputParser
{
   public static void main(String[] args)
   {
      System.out.print("How many values would you like to parse? ");
      Scanner sc = new Scanner(System.in);
      int count = sc.nextInt();
      int intCount = 0, doubleCount = 0, stringCount = 0;
      int intSum = 0;
      double doubleSum = 0.0;
      String stringSum= "";
      System.out.print("Please input the values that you would like to parse ");
      
      for(int i=0; i<count; i++)
      {
         if(sc.hasNextInt())
         {  
            intCount++;
            intSum += sc.nextInt();
         }
         else if(sc.hasNextDouble())
         {
            doubleCount++;
            doubleSum += sc.nextDouble();
         }
         else
         {
            stringCount++;
            stringSum += sc.next();
         }
      }
      System.out.println("Num of ints:" + intCount+", sum:" + intSum);
      System.out.println("Num of doubles: " + doubleCount+", sum:" + doubleSum);
      System.out.println("Num of strings: " + stringCount+", sum:" + stringSum);
   }
}
      
      
      