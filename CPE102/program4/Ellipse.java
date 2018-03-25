import java.awt.Point;
import java.awt.Color;

public class Ellipse extends Shape
{
   private double majorAx;
   private double minorAx;
   private Point position;
   
   /**
    * The ellipse constructor.
    * @param majorAx
    * @param minorAx
    * @param position
    * @param color
    * @param filled 
    */
   public Ellipse(double majorAx, double minorAx, Point position, Color color, boolean filled)
   {
      super(filled, color);
      this.majorAx = majorAx;
      this.minorAx = minorAx;
      this.position = new Point(position);
   }
   
   /**
    * gets the semimajoraxis.
    * @return the semimajor axis.
    */
   public double getSemiMajorAxis()
   {
      return majorAx;
   }
   
   /**
    * sets the semimajoraxis.
    * 
    * @param majorAx 
    */
   public void setSemiMajorAxis(double majorAx)
   {
      this.majorAx = majorAx;
      if(this.majorAx < minorAx)
      {
         this.majorAx = this.minorAx;
         this.minorAx = majorAx;
      }
   }
   
   /**
    * gets the semi minor axis.
    * @return the semi minor axis.
    */
   public double getSemiMinorAxis()
   {
    
      return minorAx;
   }
   
   /**
    * sets the semi minor axis
    * @param minorAx 
    */
   public void setSemiMinorAxis(double minorAx)
   {
      this.minorAx = minorAx;
      if(this.minorAx > majorAx)
      {
         this.minorAx = this.majorAx;
         this.majorAx = minorAx;
      }
   }
   
   public boolean equals(Object o)
   {
      if(!super.equals(o))
      {
         return false;
      }
      if((this.getSemiMajorAxis()) != (((Ellipse)o).getSemiMajorAxis()))
      {
         return false;
      }
      if((this.getSemiMinorAxis()) != (((Ellipse)o).getSemiMinorAxis()))
      {
         return false;
      }
      if(this.position.x != ((Ellipse)o).position.x)
      {
         return false;
      }
      if(this.position.y != ((Ellipse)o).position.y)
      {
         return false;
      }
      return true;
   }
   
   public Point getPosition()
   {
      Point temp = new Point(this.position.x, this.position.y);
      return temp;
   }
   
   public void setPosition(Point newPos)
   {
      this.position = new Point(newPos);
   }
   
   public void move(Point delta)
   {
      Point tempd = new Point(delta);
      this.position.x += tempd.x;
      this.position.y += tempd.y;
   }
   
   public double getArea()
   {
      double temp1 = this.majorAx;
      double temp2 = this.minorAx;
      double result = ((Math.PI)*(temp1)*(temp2));
      return result;
   }
}