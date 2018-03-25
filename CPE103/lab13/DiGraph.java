
/**
 * A partial implementation of a directed graph class to explore Dijkstra's shortest-path
 * algorithm.
 *
 * @author Kurt Mammen - Provided partial solution.
 * @author ? - Completed by.
 * @version CPE 103 Lab 13
 */
import java.io.*;
import java.util.*;

public class DiGraph
{
   /* Provided/Required */

   private TreeMap<String, Vertex> adjList = new TreeMap<String, Vertex>();

   /* Provided/Required - should not need to modify */
   private class Vertex implements Comparable<Vertex>
   {

      private String name;
      private boolean known;
      private double distance = Double.POSITIVE_INFINITY;
      private Vertex prior;
      private TreeMap<String, Vertex> adjacencies = new TreeMap<String, Vertex>();

      public Vertex(String name)
      {
         this.name = name;
      }

      public void addAdjacency(Vertex adjacent)
      {
         // TreeMap put() returns null when no matching element in tree 
         if (adjacencies.put(adjacent.name, adjacent) != null)
         {
            throw new IllegalArgumentException("Duplicate edge");
         }
      }

      // Used by prioirity queue used in Dikstra's shortest path alrorithm.
      public int compareTo(Vertex that)
      {
         return ((Double) this.distance).compareTo(that.distance);
      }

      // For debugging purposes if and as necessary...
      public String toString()
      {
         String s = "Vertex name: " + name
                 + " known: " + known
                 + " prior: ";

         if (prior == null)
         {
            s += "null";
         }
         else
         {
            s += prior.name;
         }

         s += " distance: " + distance
                 + " adjacencies: ";

         for (Vertex v : adjacencies.values())
         {
            s += " " + v.name;
         }

         return s;
      }

   } // End class Vertex

   /**
    * Constructs a directed graph from a file containing the weighted edges. A weighted
    * edge is specified as three values, separated by whitespace, in the following order:
    * the from-vertex as a String, the to-vertex as a String, and the weight as a double.
    *
    * @param fileName The name of a file containing the weighted edges (see the method's
    * detailed description for the file's format).
    *
    * @throws FileNotFoundException When the file is not found.
    * @throws IllegalArgumentException <br>&nbsp&nbsp&nbspWhen the file is empty.
    * <br>&nbsp&nbsp&nbspWhen input is mal-formed. <br>&nbsp&nbsp&nbspWhen the graph
    * contains a negative edge.
    */
   public DiGraph(String fileName) throws FileNotFoundException
   {
      try
      {
         Scanner sc = new Scanner(new File(fileName));

         if (!sc.hasNext())
         {
            throw new IllegalArgumentException();
         }

         while (sc.hasNext())
         {
            String s1 = sc.next();
            String s2 = sc.next();
            double w = Double.parseDouble(sc.next());

            if (!adjList.containsKey(s1))
            {
               Vertex v1 = new Vertex(s1);
               Vertex v2 = new Vertex(s2);
               v2.distance = w;
               v1.addAdjacency(v2);
               adjList.put(s1, v1);
            }
            else
            {
               Vertex v2 = new Vertex(s2);
               v2.distance = w;
               adjList.get(s1).addAdjacency(v2);
            }

            if (!adjList.containsKey(s2))
            {
               Vertex v2 = new Vertex(s2);
               adjList.put(s2, v2);
            }
         }

         sc.close();
      }
      catch (NumberFormatException e)
      {
         throw new IllegalArgumentException();
      }
      catch (NoSuchElementException e)
      {
         throw new IllegalArgumentException();
      }
   }

   /**
    * Applies Dijkstra's shortest path algorithm to the graph using the specified vertex
    * as the starting vertex.
    *
    * @param from The name of the starting vertex.
    *
    * @throws IllegalArgumentException when<br> <br>When the specified vertex is not in
    * the graph.
    *
    */
   public void setStart(String from)
   {
      PriorityQueue<Vertex> q = new PriorityQueue<Vertex>();
      Vertex start = adjList.get(from);
      Iterator<Vertex> itr = adjList.values().iterator();

      while (itr.hasNext())
      {
         Vertex temp = itr.next();

         temp.known = false;
         temp.distance = Double.POSITIVE_INFINITY;
         temp.prior = null;
         q.add(temp);
      }

      q.remove(start);
      start.distance = 0;
      q.add(start);

      while (q.size() != 0)
      {
         Vertex v = q.poll();
         v.known = true;
         Iterator<Vertex> vItr = v.adjacencies.values().iterator();

         while (vItr.hasNext())
         {
            Vertex vAdj = vItr.next();
            Vertex adj = adjList.get(vAdj.name);

            if (!adj.known && (v.distance + vAdj.distance) < adj.distance)
            {
               q.remove(adj);
               adj.distance = v.distance + vAdj.distance;
               adj.prior = v;
               q.add(adj);

            }
         }
      }
   }

   /**
    * Provided: Returns a shortest path to the specified vertex from the current
    * start-vertex.
    *
    * @param to The to-vertex whose path you want.
    * @return The path from the current start-vertex to the specified to-vertex.
    */
   public String getPathTo(String to)
   {
      StringBuilder path = new StringBuilder();
      getPathTo(adjList.get(to), path);

      return path.toString().trim();
   }

   /*
    * Provided: Recursive helper method for public getPathTo().
    */
   private void getPathTo(Vertex v, StringBuilder path)
   {
      if (v.prior != null)
      {
         getPathTo(v.prior, path);
         path.append(" ");
      }

      path.append(v.name);
   }

   /**
    * Provided: returns the distance of the shortest path to the specified vertex to The
    * current start-vertex.
    *
    * @param to The to-vertex whose distance you want.
    * @return The distance from the current start-vertex to the specified to-vertex.
    */
   public double getDistanceTo(String to)
   {
      return getDistanceTo(adjList.get(to), 0);
   }

   /*
    * Provided: Recursive helper method for public getDistanceTo().
    */
   private double getDistanceTo(Vertex v, double distance)
   {
      if (v.prior != null)
      {
         getDistanceTo(v.prior, v.distance);
      }

      return distance + v.distance;
   }

}