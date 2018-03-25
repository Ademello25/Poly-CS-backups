/**
 * Canvas Class
 * 
 * @author Alexander DeMello
 * @version Program 3
 */

import java.awt.Point;
import java.awt.Color;
import java.util.ArrayList;

public class Canvas
{
   private ArrayList<Shape> arr;
   
   /**
    * The canvas default constructor.
    */
   public Canvas()
   {
      arr = new ArrayList<Shape>(); 
   }
   
   /**
    * adds a shape to the canvas.
    * @param shape 
    */
   public void add(Shape shape)
   {
      arr.add(shape);
   }
   
   /**
    * removes a shape from the canvas.
    * @param index
    * @return the shape removed.
    */
   public Shape remove(int index)
   {
      return arr.remove(index);
   }
   
   /**
    * gets a shape at an index.
    * @param index
    * @return the shape.
    */
   public Shape get(int index)
   {
      return arr.get(index);
   }
   
   /**
    * returns the number of elements in the canvas.
    * @return the int of relevant elements.
    */
   public int size()
   {
      return arr.size();
   }
   
   /**
    * puts all of the circles in the Canvas into an arraylist.
    * @return the arraylist of circles.
    */
   public java.util.ArrayList<Circle> getCircles()
   {
      java.util.ArrayList<Circle> result = new ArrayList<Circle>();
      
      for( int i = 0; i < arr.size(); i++)
      { 
         if((arr.get(i)) instanceof Circle)
         {
            Circle d = (Circle)arr.get(i);
            result.add(d);
         }
      }
      
      return result;
   }
   
   /**
    * puts all of the Retangles in the canvas into an arraylist.
    * @return the arraylist of rectangles.
    */
   public java.util.ArrayList<Rectangle> getRectangles()
   {
      java.util.ArrayList<Rectangle> result = new ArrayList<Rectangle>();
      
      for( int i = 0; i < arr.size(); i++)
      {
         Shape s = arr.get(i);
         if((s.getClass() == Rectangle.class))
         {
            Rectangle z = (Rectangle)s;
            result.add(z);
         }
      }
      
      return result;
   }
   
   /**
    * puts all of the Triangles into an arraylist.
    * @return the arraylist of triangles.
    */
   public java.util.ArrayList<Triangle> getTriangles()
   {
      java.util.ArrayList<Triangle> result = new ArrayList<Triangle>();
      
      for( int i = 0; i < arr.size(); i++)
      { 
         if((arr.get(i)) instanceof Triangle)
         {
            Triangle e = (Triangle)arr.get(i);
            result.add(e);
         }
      }
      
      return result;
   }
   
   /**
    * puts all convex polygons into an arraylist.
    * @return the arraylist of convex polygons.
    */
   public java.util.ArrayList<ConvexPolygon> getConvexPolygons()
   {
      ArrayList<ConvexPolygon> result = new ArrayList<ConvexPolygon>();
      
      for(int i = 0; i <arr.size(); i++)
      {                  
         if((arr.get(i)).getClass() ==ConvexPolygon.class)
         {
            ConvexPolygon e = (ConvexPolygon)arr.get(i);
            result.add(e);
         }
      }
      return result;
   }
   
   /**
    * puts all shapes, sorted by color into an arraylist.
    * @param color
    * @return the arraylist of shapes sorted by color.
    */
   public java.util.ArrayList<Shape> getShapesByColor(Color color)
   {
      ArrayList<Shape> result = new ArrayList<Shape>();
      
      for(int i = 0; i < arr.size(); i++)
      {
         if(arr.get(i).getColor().getRGB() == color.getRGB())
         {
            Shape e = (Shape)arr.get(i);
            result.add(e);
         }
      }
      return result;
   }
   
   /**
    * returns the areas of all the shapes.
    * @return the sum of all the areas of the shapes.
    */
   public double getAreaOfAllShapes()
   {
      double sum =0;
      for(int i = 0; i < arr.size(); i++)
      {
         sum += arr.get(i).getArea();
      }
      return sum;
   }
}
