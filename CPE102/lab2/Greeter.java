public class Greeter
{
   private String outName;
   public Greeter(String inName)
   {
      outName = inName;
   }
   public String greet()
   {
     return ("Hello"+" "+outName);
   }
}