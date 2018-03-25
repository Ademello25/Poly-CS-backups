import java.util.Scanner;

public class Driver
{
   public static void main(String[] args)
   {
      Scanner scanner = new Scanner(System.in);
      
      System.out.print("What is your name? ");
      
      String name = scanner.nextLine();
      
      Greeter greeter = new Greeter(name);
      
      String greeting = greeter.greet();
      
      System.out.println(greeting);
   }
}