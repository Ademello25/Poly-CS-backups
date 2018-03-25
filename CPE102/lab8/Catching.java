public class Catching
{
   private int arr[];
   
   public Catching()
   {
      arr = new int[10];
   }
   public void throwNull()
   {
      throw null;
   }
   
   public void throwClassCastException()
   {
      Object x = new Integer(0);
      System.out.println((String)x);
   }
   
   public void throwArrayIndexOutOfBoundsException()
   {
      for(int i=0; i<100; i++)
      {
         int x = arr[i];
      }
   }
   
   public void whichException(int num)
   {
      if(num ==1)
      {
         throwNull();
      }
      else if(num ==2)
      {
         throwClassCastException();
      }
      else if(num ==3)
      {
         throwArrayIndexOutOfBoundsException();
      }
   }
   
}