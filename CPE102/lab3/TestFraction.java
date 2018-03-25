public class TestFraction
{
   public static void main(String[] args)
   {
      testConstructor1();
      testConstructor2();
      testConstructor3();
      testGetNumerator();
      testGetDenominator();
      testValue();
      testToString();
      testEquals();
      testAdd();
      testSub();
      testMul();
      testDiv();
   }
   public static void testConstructor1()
   {
      Fraction f = new Fraction();
      if(f.getNumerator() != 0)
      {
         System.out.println("Expected numerator 0, found " + f.getNumerator());
      }
      else if(f.getDenominator() != 1)
      {
         System.out.println("Expected denominator 1, found " + f.getDenominator());
      }
   }
   public static void testConstructor2()
   {
      Fraction f = new Fraction(5);
      if(f.getNumerator() != 5)
      {
         System.out.println("Expected numerator 5, found " + f.getNumerator());
      }
      else if(f.getDenominator() != 1)
      {
         System.out.println("Expected denominator 1, found " + f.getDenominator());
      }
   }
   public static void testConstructor3()
   {
      Fraction f = new Fraction(8, 2);
      if(f.getNumerator() != 4)
      {
         System.out.println("Expected numerator 4, found " + f.getNumerator());
      }
      else if(f.getDenominator() != 1)
      {
         System.out.println("Expected denominator 1, found " + f.getDenominator());
      }       
   }
   public static void testGetNumerator()
   {
      Fraction f = new Fraction(10,2);
      if(f.getNumerator() != 5)
      {
         System.out.println("Expected numerator 5, found " +f.getNumerator());
      }
   }
   public static void testGetDenominator()
   {
      Fraction f = new Fraction(10,2);
      if(f.getDenominator() != 1)
      {
         System.out.println("Expected denominator 1, found " +f.getDenominator());
      }
   }
   public static void testValue()
   {
      int num, denom;
      double result, expect;
      Fraction f = new Fraction(5,7);
      num = f.getNumerator();
      denom = f.getDenominator();
      result = num/denom;
      expect = .714285;
      if(!approxEQ(result, expect) )
      {
         System.out.println("Expected .714285, found " + result);
      }  
   }
   public static void testToString()
   {
      Fraction f = new Fraction(5,7);
      Fraction g = new Fraction(10,9);
      if(!g.toString().equals("10/9"))
      {
         System.out.println("Expected 10/9 , found " + g.toString());
      }
      if(!f.toString().equals("5/7"))
      {
         System.out.println("Expected 5/7 , found " + g.toString());
      }
      
   }
   public static void testEquals()
   {
      Fraction f = new Fraction(5,7);
      Fraction g = new Fraction(5,7);
      Fraction e = new Fraction(3,7);
      
      if(!f.equals(g))
      {
         System.out.println("Expected True, returned False");
      }
      else if(f.equals(e))
      {
         System.out.println("Expected False, returned True");
      }
   }
   public static void testAdd()
   {
      Fraction r;
      Fraction f = new Fraction(5,7);
      Fraction g = new Fraction(10,9);
      r = f.add(g);
      if(r.getNumerator() != 115)
      {
         System.out.println("Expected numerator 115, found " + r.getNumerator());
      }
      if(r.getDenominator() != 63)
      {
         System.out.println("Expected denominator 63, found " + r.getDenominator());
      }
   }
   public static void testSub()
   {
      Fraction r;
      Fraction f = new Fraction(5,7);
      Fraction g = new Fraction(10,9);
      r = g.sub(f);
      if(r.getNumerator() != 25);
      {
         System.out.println("Expected numerator 25, found " + r.getNumerator());
      }
      if(r.getDenominator() != 63)
      {
         System.out.println("Expected denominator 63, found " + r.getDenominator());
      }
   }
      public static void testMul()
   {
      Fraction r;
      Fraction f = new Fraction(5,7);
      Fraction g = new Fraction(10,9);
      r = g.mul(f);
      if(r.getNumerator() != 50);
      {
         System.out.println("Expected numerator 50, found " + r.getNumerator());
      }
      if(r.getDenominator() != 63)
      {
         System.out.println("Expected denominator 63, found " + r.getDenominator());
      }
   }
   public static void testDiv()
   {
      Fraction r;
      Fraction f = new Fraction(5,7);
      Fraction g = new Fraction(10,9);
      r = g.div(f);
      if(r.getNumerator() != 14);
      {
         System.out.println("Expected numerator 14, found " + r.getNumerator());
      }
      if(r.getDenominator() != 9)
      {
         System.out.println("Expected denominator 9, found " + r.getDenominator());
      }
   }
   private static boolean approxEQ(double double1, double double2)
   {
      if(Math.abs(double1-double2) < 0.0001)
         {
            return true;
         }
         else
         {
            return false;
         }
   }
}