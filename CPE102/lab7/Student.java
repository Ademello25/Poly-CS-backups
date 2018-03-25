public class Student extends Person
{
   private double GPA;
   
   public Student(double GPA, String name, int legs)
   {
      super(name, legs);
      this.GPA = GPA;
   }
   
   public boolean equals(Object o)
   {
      if(!super.equals(o))
      {
         return false;
      }
      
      return (GPA == (((Student)o).GPA));
   }
   
   public java.lang.String toString()
   {
      String temp = super.toString();
      return (temp + " and a Student Object with a " + GPA + " gpa");
   }
}