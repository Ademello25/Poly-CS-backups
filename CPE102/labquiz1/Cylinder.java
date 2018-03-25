/**
 * 
 * @author Alexander DeMello
 * @version Lab Quiz 1
 */

public class Cylinder
{
   double rad =0, height =0;
   public Cylinder()
   {
      this(1,1);
   }
   public Cylinder(double rad, double height)
   {
      this.rad = rad;
      this.height = height;
   }
   public double getHeight()
   {
      return height;
   }

   public double getRadius()
   {
      return rad;
   }
   public double getSurfaceArea()
   {
      double tempRad = getRadius();
      double tempHeight = getHeight();
      double result;
      result = (2*(Math.PI)*(tempRad*tempRad)) + (2*(Math.PI)*(tempRad)*(tempHeight));
      return result;
   }
   public double Volume()
   {
      double tempRad = getRadius();
      double tempHeight = getHeight();
      double volume;
      volume = (Math.PI)*(tempRad*tempRad)*(tempHeight);
      return volume;
   }
   public boolean equals(Cylinder c)
   {
      if((this.rad == c.rad) && (this.height == c.height))
      {
         return true;
      }
      else
      {
         return false;
      }
   }
   public java.lang.String toString()
   {
      return ("The Cylinder class is mutable");
   }
}