public class Person extends Animal
{
   private String name;
   
   public Person(String name, int legs)
   {
      super(legs);
      this.name = name;
   }
   
   public boolean equals(Object o)
   {
      if(!super.equals(o))
      {
         return false;
      }
      
      return name.equals(((Person)o).name);
   }
   
   public java.lang.String toString()
   {
      String temp = super.toString();
      return (temp + " and a Person object whose name is "+name);
   }
}
