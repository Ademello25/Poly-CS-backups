public class Animal
{
   private int legs;
   
   public Animal(int legs)
   {
      this.legs = legs;
   }
   
   public boolean equals(Object o)
   {
      if(o == null)
      {
         return false;
      }
      
      if(this.getClass() != o.getClass())
      {
         return false;
      }
      
      if(this.legs != ((Animal)o).legs)
      {
         return false;
      }
      
      return true;
   }
   
   public java.lang.String toString()
   {
      return ("I am an Animal object with " + legs + "legs");
   }
}