/**
 * Rectangle methods.
 * 
 * @author Alexander DeMello
 * @version Program 3
 */
import java.awt.Color;
import java.awt.Point;
import java.lang.UnsupportedOperationException;

public class Rectangle extends ConvexPolygon
{
   /**
    * the Rectangle constructor.
    * @param w
    * @param h
    * @param p
    * @param color
    * @param filled 
    */
   public Rectangle(int w, int h, Point p, Color color, boolean filled)
   {
      super(new Point[] {new Point(p),new Point(p.x+w,p.y),new Point(p.x+w,p.y+h), 
         new Point(p.x, p.y+h)},new Color(color.getRGB()),filled);
   }

   /**
   * gets the height of the rectangle.
   * @return the height of the rectangle.
   */
   public int getHeight()
   {
      int result = (int)Math.abs(super.getVertex(0).distance(super.getVertex(3)));
      
      return result;
   }

   /**
    * gets the width of the rectangle.
    * @return the width of the rectangle.
    */
   public int getWidth() 
   {
      int result = (int)Math.abs(super.getVertex(1).distance(super.getVertex(0))); 
      
      return result;
   }

   /**
    * sets the height of the rectangle.
    * @param height 
    */
   public void setHeight(int height) 
   {
      int hi = this.getHeight();
      int diff = Math.abs(hi - height);

      Point newH = new Point(super.getVertex(3).x, super.getVertex(3).y + diff);
      Point newH2 = new Point(super.getVertex(2).x, super.getVertex(2).y + diff);
      super.setVertex(3, newH);
      super.setVertex(2,newH2);
   }

   /**
    * sets the width of the rectangle.
    * @param width 
    */
   public void setWidth(int width) 
   {
      int wi = this.getWidth();
      int diff = Math.abs(wi - width);
      
      Point newW = new Point(super.getVertex(1).x + diff, super.getVertex(1).y);
      Point newW2 = new Point(super.getVertex(2).x + diff, super.getVertex(2).y);
      super.setVertex(1, newW);
      super.setVertex(2, newW2);
   }
  
   /**
    * sets the a vertex at the index in the array of points.
    * @param index
    * @param vertex 
    */
   public void setVertex(int index, Point vertex)
   {
      throw new UnsupportedOperationException();
   }
   
}