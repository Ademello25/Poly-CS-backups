import java.util.ArrayList;
import java.io.*;

public class Binary
{
   public static ArrayList<Object> read(String fileName) throws IOException
   {
      ArrayList<Object> arr = new ArrayList<Object>();
      try
      {
         FileInputStream in = new FileInputStream(new File(fileName));
         DataInputStream din = new DataInputStream(in);
         
         while(true)
         {
            try
            {
               int val = din.readInt();
               arr.add(val);
               
               for(int i =0; i < val; i++)
               {
                  arr.add(din.readDouble());
               }
               
            }
            catch(EOFException e)
            {
               break;
            }
         }
            
      }
      catch(FileNotFoundException f)
      {
         System.out.println("T1: File not found!");
      }
      
      return arr;
   }
   
   public static void write(String fileName, ArrayList<Object> list) throws IOException
   {
      for(int i =0; i< list.size(); i++)
      {
         int count = 0;
         
         if((list.get(i).getClass() != Integer.class))
         {
            throw new IllegalArgumentException("The arraylist may only contain Int & double values");
         }
         int val = (Integer)list.get(i);
         
         for(int f = 0; f < val; f++)
         {
            if(list.get(i).getClass() != Double.class)
            {
               throw new IllegalArgumentException("Bad integer-double group");
            }
            count++;
         }
         if(count != val)
         {
            throw new IllegalArgumentException("Bad integer-double group");
         }
      }
      
      try
      {
         FileOutputStream out = new FileOutputStream(new File(fileName));
         DataOutputStream dout = new DataOutputStream(out);
         
         for(int i = 0; i < list.size(); i++)
         {

            if(list.get(i).getClass() == Integer.class)
            {
               dout.writeInt((Integer)list.get(i));
            }
            else
            {
               dout.writeDouble((Double)list.get(i));
            }

         }
         
             
      }
      catch(FileNotFoundException f)
      {
         System.out.println("T1: File cannot be found!");
      }
   }
}