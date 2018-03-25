import java.io.*;
import java.util.Scanner;

public class Poem
{
   public static void main(String[] args)
   {
      try
      {
         FileOutputStream fo = new FileOutputStream("//Users//kurokaze22//Documents//Untitled");
         PrintStream conduit = new PrintStream(fo);
         conduit.println("Mary had a little lamb");
         conduit.println("Its fleece was white as snow");
         conduit.println("And everywhere that Mary went");
         conduit.println("The lamb was sure to go!");
         conduit.close();
         fo.close();
      }
      catch(FileNotFoundException f)
      {  
      }
      catch(IOException e)
      {
      }  
      Scanner sc = new Scanner("//Users//kurokaze22//Documents//Untitled");
      while(sc.hasNextLine())
      {
         String poem = sc.nextLine();
         System.out.println(""+poem);
      }

   }
}