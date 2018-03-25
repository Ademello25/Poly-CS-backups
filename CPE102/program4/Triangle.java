
/**
 * Triangle methods.
 * 
 * @author Alexander DeMello
 * @version Program 3
 */
import java.lang.Math;
import java.awt.Color;
import java.awt.Point;

public class Triangle extends ConvexPolygon
{

   /**
    * The constuctor for a Triangle.
    * @param a
    * @param b
    * @param c
    * @param color
    * @param filled 
    */
   public Triangle(Point a, Point b, Point c, Color color, boolean filled) 
   {
      super(new Point[] {a, b, c}, color, filled);
   }
   
   /**
    * Gets vertex "A".
    * @return vert A.
    */
   public Point getVertexA() 
   {
      Point temp = new Point(super.getVertex(0));
      return temp;
   }

   /**
    * gets vertex B.
    * @return vert B.
    */
   public Point getVertexB() 
   {
      Point temp = new Point(super.getVertex(1));
      return temp;
   }

   /**
    * gets vertex C.
    * @return Vert c.
    */
   public Point getVertexC() 
   {
      Point temp = new Point(super.getVertex(2));
      return temp;
   }

   /**
    * sets vertex A.
    * @param a new vert a.
    */
   public void setVertexA(Point a) 
   {
      super.setVertex(0, new Point(a));
   }

   /**
    * sets vertex B.
    * @param b new vert b.
    */
   public void setVertexB(Point b) 
   {
      super.setVertex(1, new Point(b));
   }

   /**
    * sets vertex C.
    * @param c new vert c.
    */
   public void setVertexC(Point c)
   {
      super.setVertex(2, new Point(c));
   }
   
   public double getArea()
   {
      double sideA = super.getVertex(0).distance(super.getVertex(2));
      double sideB = super.getVertex(0).distance(super.getVertex(1));
      double sideC = super.getVertex(1).distance(super.getVertex(2));

      double s = ((sideA + sideB + sideC)/2);
      double Heron = (Math.sqrt(s*(s-sideA)*(s-sideB)*(s-sideC)));
      
      return Heron;
   }
}