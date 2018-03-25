/**
 * A fraction class that has many fucntions involving fractions
 *
 *@Alexander DeMello
 *@Program 1
 */

public class Fraction
{
   private int num;
   private int den;
   
   public Fraction()
   {
      this(0,1);
   }
   public Fraction(int num)
   {
      this(num,1);
   }
   public Fraction(int num, int den)
   {
      this.num= num;
      this.den = den;
      if(den == 0 || den < 0)
      {
         throw new IllegalArgumentException("Fraction has 0 or denominator less than 0");
      }

      reduce();
      
   }
   private void reduce()
   {
      int gcd = eucilidGCD(num, den);
      num /= gcd;
      den /= gcd;
   }
   private int eucilidGCD(int a, int b)
   {
      if(b == 0)
      {
         return a;
      }
      else
      {
         return eucilidGCD(b, a % b);
         //Concepts from the Greatest common denominator wikipedia page
      }
   }
   public int getNumerator()
   {
      return num;
   }
   public int getDenominator()
   {
      return den;
   }
   public double value()
   { 
      return ((double) num)/den;
   }
   public java.lang.String toString()
   {
      int tempNum = getNumerator();
      int tempDen = getDenominator();
      if(tempDen == 1)
      {
         return "" + tempNum;
      }
      else
      {
      return tempNum + "/" + tempDen;
      }
   }
   public boolean equals(Fraction f)
   {
      if((this.num == f.num) && (this.den == f.den))
      {
         return true;
      }
      else
      {
         return false;
      }
   }
   public Fraction add(Fraction f)
   {
    return new Fraction(num*f.den + f.num*den, den*f.den); 
   }
   public Fraction sub(Fraction f)
   {
     return new Fraction(num*f.den - f.num*den, den*f.den);
   }
   public Fraction mul(Fraction f)
   {
      return new Fraction(num*f.num, den*f.den);
   }
   public Fraction div(Fraction f)
   {
      return new Fraction(num*f.den, den*f.num);
   }
}