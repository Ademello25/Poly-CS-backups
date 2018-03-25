/**
 * Circle methods.
 * 
 * @author Alexander DeMello
 * @version Program 3
 */
import java.awt.Color;
import java.awt.Point;

public class Circle extends Ellipse
{
   /**
    * the circle constructor.
    * @param radius
    * @param position
    * @param color
    * @param filled 
    */
   public Circle(double radius, Point position, Color color, boolean filled)
   {
      super(radius, radius, new Point(position), color, filled);
   }
   
   /**
    * returns the radius of the circle.
    * @return the radius of the circle.
    */
   public double getRadius()
   {
      return super.getSemiMinorAxis();
   }

   /**
    * sets the radius to the input value.
    * @param radius the new radius of the circle.
    */
   public void setRadius(double radius)
   {
      super.setSemiMajorAxis(radius);
      super.setSemiMinorAxis(radius);
      super.setSemiMajorAxis(radius);
      
   }
   
   /**
    * sets the semiminor axis, but also retains the circular shape.
    * @param minorAx 
    */
   public void setSemiMinorAxis(double minorAx)
   {
      super.setSemiMajorAxis(minorAx);
      super.setSemiMinorAxis(minorAx);
      super.setSemiMajorAxis(minorAx);
   }
   
   /**
    * sets the semimajor axis, but also retains the circular shape.
    * @param majorAx 
    */
   public void setSemiMajorAxis(double majorAx)
   {
      super.setSemiMajorAxis(majorAx);
      super.setSemiMinorAxis(majorAx);
      super.setSemiMajorAxis(majorAx);
   }
}
