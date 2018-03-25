/**
 * 
 * @author Alexander DeMello
 * @version Lab QUiz 2
 */

public class MyPoint2D extends java.awt.geom.Point2D implements java.lang.Comparable<MyPoint2D>
{
   private double x;
   private double y;
   
   public MyPoint2D()
   {
      this.x = 0.0;
      this.y = 0.0;
   }
   
   public MyPoint2D(double x, double y)
   {
      this.x = x;
      this.y = y;
   }
   
   public double getX()
   {
      return x;
   }
   
   public double getY()
   {
      return y;
   }
   
   public void setLocation(double x, double y)
   {
      this.x = x;
      this.y = y;
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
      
      if(this.x != ((MyPoint2D)o).x)
      {
         return false;
      }
      
      if(this.y != ((MyPoint2D)o).y)
      {
         return false;
      }
      return true;
   }
   
   public java.lang.String toString()
   {
      return ("(" +this.x +", " + this.y +")");
   }
   
   public int compareTo(MyPoint2D p)
   {
      if(this.x < p.x)
      {
         return -55;
      }
      else if(this.x > p.x)
      {
         return 55;
      }
      else
      {
         if(this.y < p.y)
         {
            return -55;
         }
         else if(this.y > p.y)
         {
            return 55;
         }
         else
         {
            return 0;
         }
      }
   }
   
   
}