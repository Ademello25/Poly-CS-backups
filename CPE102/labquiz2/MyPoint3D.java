

public class MyPoint3D implements Comparable<MyPoint3D>
{
   private MyPoint2D xy;
   private double z;
   
   public MyPoint3D()
   {
      xy = new MyPoint2D();
      this.z = 0.0;
   }
   
   public MyPoint3D(double x, double y, double z)
   {
      xy = new MyPoint2D(x,y);
      this.z = z;
   }
   
   public double getX()
   {
      return xy.getX();
   }
   
   public double getY()
   {
      return xy.getY();
   }
   
   public double getZ()
   {
      return z;
   }
   
   public void setLocation(double x, double y, double z)
   {
      xy.setLocation(x,y);
      this.z = z;
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
      
      if(this.xy.getX() != ((MyPoint3D)o).xy.getX())
      {
         return false;
      }
      
      if(this.xy.getY() != ((MyPoint3D)o).xy.getY())
      {
         return false;
      }
      
      if(this.z != ((MyPoint3D)o).z)
      {
         return false;
      }
      
      return true;
   }
   
   public java.lang.String toString()
   {
      return ("(" +this.xy.getX() +", " + this.xy.getY() +", " +this.z+")");
   }
   
   public int compareTo(MyPoint3D p)
   {
      if(this.xy.getX() < p.xy.getX())
      {
         return -55;
      }
      else if(this.xy.getX() > p.xy.getX())
      {
         return 55;
      }
      else
      {
         if(this.xy.getY() < p.xy.getY())
         {
            return -55;
         }
         else if(this.xy.getY() > p.xy.getY())
         {
            return 55;
         }
         else
         {
            if(this.z < p.z)
            {
               return -55;
            }
            else if(this.z > p.z)
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
   
   
           
   
   
}
