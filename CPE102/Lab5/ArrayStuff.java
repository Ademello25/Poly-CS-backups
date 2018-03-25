import java.util.ArrayList;

public class ArrayStuff
{
           
   private ArrayList<Integer> myInt;
   private ArrayList<Double> myDouble;
   private ArrayList<Boolean> myBoolean;
   private ArrayList<String> myString;
   
   public ArrayStuff()
   {
      myInt = new ArrayList<Integer>();
      myDouble = new ArrayList<Double>();
      myBoolean = new ArrayList<Boolean>();
      myString = new ArrayList<String>();
   }
   
   public void add(int a)
   {
      myInt.add(new Integer(a));
   }
   public void add(double d)
   {
      myDouble.add(new Double(d));
   }
   public void add(boolean b)
   {
      myBoolean.add(new Boolean(b));
   }
   public void add(String s)
   {
      myString.add(s);
   }
   public int minimumInt()
   {
      if(!myInt.isEmpty())
      {
         int min = myInt.get(0);
      
         for(int i : myInt)
         {
            if(i<min)
            {
               min = i;
            }
         }
         return min;
      }
      else
      {
         return 0;
      }
   }
   public double averageDouble()
   {
      int count = 0;
      double sum = 0;
      double result;
      if(!myDouble.isEmpty())
      {
         for(double d : myDouble)
         {
            count++;
            sum += d;
         }
         result = (sum/count);
         return result;
      }
      else
      {
         return 0;
      }
   }
   public int numberOfTrues()
   {
      int count = 0;
      for(boolean b : myBoolean)
      {
         if(b)
         {
            count++;
         }
      }
      return count;
   }
   public int numberOfStrings()
   {
      return myString.size();
   }
}