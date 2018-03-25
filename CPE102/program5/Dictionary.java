import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
import java.lang.String;

public class Dictionary implements java.lang.Iterable<String>
{
   private ArrayList<String> dict;
   
   public Dictionary()
   {
      dict = new ArrayList<String>();
   }
   
   public Dictionary(String fileName) throws DictionaryException
   {
      dict = new ArrayList<String>();
      
      try
      {
         Scanner sc = new Scanner(new File(fileName));
         while(sc.hasNextLine())
         {
            dict.add(sc.nextLine());
         }
         sort(dict);
      }
      catch(FileNotFoundException f)
      {
         throw new DictionaryException("File not found");
      }
      System.out.println(dict.get(dict.size() -1));
   }
   
   
   
   public Iterator<String> iterator()
   {
      return dict.iterator();
   }
   
   public boolean lookUp(String word)
   {
      try
      {
         int r = search(word);
      }
      catch(RuntimeException r)
      {
         return true;
      }
      return false;
   }
   
   public void write(String fileName) throws DictionaryException
   {
      
      try
      {
         FileOutputStream fo = new FileOutputStream(fileName);
         PrintStream conduit = new PrintStream(fo);
         for(int i = 0; i < dict.size(); i++)
         {
            conduit.println(dict.get(i));
         }
         conduit.close();
         fo.close();
      }
      catch(FileNotFoundException f)
      {  
         throw new DictionaryException("Could not write to the file");
      }
      catch(IOException e)
      {
         throw new DictionaryException("Could not write to the file");
      }  
   }
   
   public void add(String word)
   {
      try
      {
         int r = search(word);
         dict.add(r, word);
      }
      catch(RuntimeException r){}
   }
   
   private static void sort(ArrayList<String> arr)
   {
      // End recursion
      if (arr.size() < 2)
      {
         return;
      }

      // Calculate the middle of the list to split it in half
      int mid = arr.size() / 2;

      // Allocate memory for the lower "left" half
      ArrayList<String> left = new ArrayList<String>(mid + 1);

      // Copy the first half to the "left"
      int i;
      
      for (i = 0; i < mid; i++)
      {
         left.add(arr.get(i));
      }

      // Allocate memory for the upper "right" half
      ArrayList<String> right = new ArrayList<String>(arr.size() - mid);

      // Copy the second half to the "right"
      for ( ; i < arr.size(); i++)
      {
         right.add(arr.get(i));
      }

      // Recursively sort each half
      sort(left);
      sort(right);

      // Merge left and right as the recursion unwinds...
      merge(arr, left, right);
   }

   private static void merge(ArrayList<String> result, ArrayList<String> left, ArrayList<String> right)
   {
      int i, l, r;

      i = l = r = 0;

      // Merge the two halves into an orderd whole...
      while (l < left.size() && r < right.size())
      {
         if (left.get(l).compareTo(right.get(r)) <= 0)
         {
            result.set(i, left.get(l));
            l++;
         }
         else
         {
            result.set(i, right.get(r));
            r++;
         }
   
         i++;
      }

      // Append rest of the values in the left half, if any...
      while (l < left.size())
      {
         result.set(i, left.get(l));
         l++;
         i++;
      }

      // Append rest of the values in the right half, if any...
      while (r < right.size())
      {
         result.set(i, right.get(r));
         r++;
         i++;
      }
   }    
   
   private int search(String value)
   {
       int lo = 0;
       int mid = 0;
       int hi = dict.size() - 1;

       while (lo <= hi)
       {
           mid = (lo + hi) / 2;
           int c = value.compareTo(dict.get(mid));
             
           if (c < 0)
           {
               hi = mid - 1;
           }
           else if (c > 0)
           {
               lo = mid + 1;
           }
           else
           {
               throw new RuntimeException();
           }
       }

       return lo;
   }

}
