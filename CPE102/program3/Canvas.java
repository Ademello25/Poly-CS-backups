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
   private ArrayList<Shape> arr = new ArrayList<Shape>();
   
   public Canvas()
   {
   }
   
   public void add(Shape shape)
   {
      arr.add(shape);
   }
   
   public Shape remove(int index)
   {
      return arr.remove(index);
   }
   
   public Shape get(int index)
   {
      return arr.get(index);
   }
   
   public int size()
   {
      return arr.size();
   }
   
   public java.util.ArrayList<Circle> getCircles()
   {
      java.util.ArrayList<Circle> result = new ArrayList();
      Point p = new Point();
      Color color = Color.black;
      boolean filled = true;
      Circle c = new Circle(5, p, color, filled);
      
      for( int i = 0; i < arr.size(); i++)
      { 
         if((arr.get(i)).getClass() == c.getClass())
         {
            Circle d = (Circle)arr.get(i);
            result.add(d);
         }
      }
      
      return result;
   }
   
   public java.util.ArrayList<Rectangle> getRectangles()
   {
      java.util.ArrayList<Rectangle> result = new ArrayList();
      Point p = new Point();
      Color color = Color.black;
      boolean filled = true;
      Rectangle c = new Rectangle(5, 5, p, color, filled);
      
      for( int i = 0; i < arr.size(); i++)
      { 
         if((arr.get(i)).getClass() == c.getClass())
         {
            Rectangle d = (Rectangle)arr.get(i);
            result.add(d);
         }
      }
      
      return result;
   }
   
   public java.util.ArrayList<Triangle> getTriangles()
   {
      java.util.ArrayList<Triangle> result = new ArrayList();
      Point a = new Point(5,1);
      Point b = new Point(2,1);
      Point c = new Point();
             
      Color color = Color.black;
      boolean filled = true;
      Triangle d = new Triangle(a, b, c, color, filled);
      
      for( int i = 0; i < arr.size(); i++)
      { 
         if((arr.get(i)).getClass() == d.getClass())
         {
            Triangle e = (Triangle)arr.get(i);
            result.add(e);
         }
      }
      
      return result;
   }
   
   public java.util.ArrayList<ConvexPolygon> getConvexPolygons()
   {
      ArrayList<ConvexPolygon> result = new ArrayList();
      Point a = new Point(5,1);
      Point b = new Point(2,1);
      Point c = new Point();
      Point[] vertices = new Point[10];
      vertices[0]=a;
      vertices[1]=b;
      vertices[2]=c;
      Color color = Color.black;
      boolean filled = true;
      ConvexPolygon d = new ConvexPolygon(vertices, color, filled);
      
      for(int i = 0; i <arr.size(); i++)
      {
         if((arr.get(i)).getClass() == d.getClass())
         {
            ConvexPolygon e = (ConvexPolygon)arr.get(i);
            result.add(e);
         }
      }
      return result;
   }
   
   public ArrayList<Shape> getShapesByColor(Color color)
   {
      ArrayList<Shape> result = new ArrayList();
      
      for(int i = 0; i <arr.size(); i++)
      {
         if(arr.get(i).getColor() == color)
         {
            Shape e = (Shape)arr.get(i);
            result.add(e);
         }
      }
      return result;
   }
   
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
