import java.util.ArrayList;
import java.io.*;

public class BinaryFileTools
{
   public static ArrayList<Object> read(String fileName) throws FileFormatException, IOException, FileNotFoundException
   {
      ArrayList<Object> arr = new ArrayList<Object>();
      FileInputStream in = new FileInputStream(new File(fileName));
      DataInputStream din = new DataInputStream(in);

      while(true)
      {
         try
         {
            int val = din.readInt();

            if(val ==1)
            {
               arr.add(din.readBoolean());
            }
            else if(val==2)
            {
               arr.add(din.readFloat());
            }

            else if(val ==3)
            {
               arr.add(din.readLong());
            }
            else
            {
               throw new FileFormatException();
            }
         }
         catch(EOFException e)
         {
            break;
         }
      }
      
      in.close();
      din.close();
      return arr;
      
   }
   
   public static void write(String fileName, ArrayList<Object> arr) throws FileFormatException,
           IOException, FileNotFoundException, EOFException
   {
     for(int i = 0; i < arr.size(); i++)
      {
         if(arr.get(i).getClass() != boolean.class || 
                 arr.get(i).getClass() != float.class || arr.get(i).getClass() != long.class)
         {
            throw new IllegalArgumentException();
         }
      }
      try
      {
         
 
         FileOutputStream out = new FileOutputStream(new File(fileName));
         DataOutputStream dout = new DataOutputStream(out);

         for(int i =0; i < arr.size(); i++)
         {
            if(arr.get(i).getClass() == boolean.class)
            {
               dout.writeInt(1);
               //dout.writeBoolean();
            }
            else if(arr.get(i).getClass() == float.class)
            {
               dout.write(2);
               //dout.writeFloat();
            }
            else if(arr.get(i).getClass() == long.class)
            {
               dout.write(3);
               //dout.writeLong();
            }
         }
               out.close();
      dout.close();
      }
      catch(FileNotFoundException f)
      {
         
      }
      
      
   }
}