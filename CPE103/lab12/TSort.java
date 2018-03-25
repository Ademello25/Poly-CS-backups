
/**
 * Provided starting point for Java-based tsort.
 *
 * @author Kurt Mammen - Starting point.
 * @author ? - Completed by.
 *
 * @version CPE 103 Lab 12
 */
import java.io.*;
import java.util.*;

public class TSort
{
   // Hides the constructor form javadoc utility and users.

   private TSort()
   {
   }

   /**
    * Performs a topological sort of the specified edges of a Directed Acyclic Graph (DAG)
    * matching the Unix/Linux tsort command.
    *
    * @param edges The edges of the DAG specified as vertex-pairs.
    * @return A topological ordering for the specified DAG.
    * @throws IllegalArgumentException <br><br>When edges is emtpy with the message "No
    * edges specified" <br>When edges has an odd number of vertices (incomplete pair) with
    * the message "Odd number of vertices". <br>When the graph contains a cycle with the
    * message "Cycle found in graph".
    */
   public static String tsort(String edges)
   {
      ArrayList<LinkedList<String>> adj = new ArrayList<LinkedList<String>>();
      Scanner sc = new Scanner(edges);

      String result;
      String vertex;
      String vertex2 = new String();

      while (sc.hasNext())
      {
         vertex = sc.next();
         System.out.println("vertex " + vertex);
         if (sc.hasNext())
         {
            String tempStr = sc.next();
            if (tempStr.equals(vertex))
            {
               sc.next();
            }
            else
            {
               vertex2 = sc.next();
            }
         }
         else
         {
            throw new IllegalArgumentException("Odd number of vertices");
         }

         LinkedList<String> ll = new LinkedList<String>();
         if (adj.isEmpty())
         {
            ll.add(vertex);
            if(vertex2.equals("") == false)
            {
               ll.add(vertex2);
            }
            vertex2 = "";
            adj.add(ll);
         }
         else
         {
            for (int i = 0; i < adj.size(); i++)
            {
               if (vertex.equals(adj.get(i).get(0)))
               {
                  adj.get(i).add(vertex2);
               }
               else
               {
                  ll.add(vertex);
                  ll.add(vertex2);
               }
            }
            adj.add(ll);
         }

      }

      System.out.println("Adjacency List");
      for (int i = 0; i < adj.size() -1; i++)
      {
         System.out.println(i);
         for (int j = 0; j < adj.get(i).size(); j++)
         {
            System.out.println(adj.get(i).get(j));
         }
         
         System.out.println();
      }

      return "hi";
   }

   /**
    * Entry point for the Java-based tsort utility allowing the user to specify a file,
    * redirect a file, or specify the input manually followed by cntl-d to signal the end
    * of input.
    */
   public static void main(String[] args)
   {
      Scanner scanner = null;

      if (args.length == 0)
      {
         scanner = new Scanner(System.in);
      }
      else if (args.length == 1)
      {
         try
         {
            scanner = new Scanner(new File(args[0]));
         }
         catch (FileNotFoundException e)
         {
            System.out.println("TSort: Could not find " + args[0]);
            System.exit(1);
         }
      }
      else
      {
         System.out.println("TSort: Unexpected number of arguments");
         System.exit(1);
      }

      StringBuffer input = new StringBuffer();

      while (scanner.hasNext())
      {
         input.append(scanner.next() + " ");
      }
      //System.out.println(input);

      try
      {
         System.out.println(tsort(input.toString()));
      }
      catch (IllegalArgumentException e)
      {
         System.out.println("TSort: " + e.getMessage());
         System.exit(1);
      }
   }

}