/**
 * Convex Polygon methods.
 * 
 * @author Alexander DeMello
 * @version Program 3
 */

import java.awt.Point;
import java.awt.Color;

public class ConvexPolygon extends Shape
{
   private Point[] vertices;
   
   /**
    * the convex polygon constructor.
    * @param vertices
    * @param color
    * @param filled 
    */
   public ConvexPolygon(Point[] vertices, Color color, boolean filled)
   {
      super(filled,color);
      Point[] temp = new Point[vertices.length];
      for(int i=0; i<vertices.length; i++)
      {
         temp[i] = new Point(vertices[i]);
      }
      this.vertices = temp;
   }
   
   /**
    * gets a vertex of the convex polygon.
    * @param index
    * @return the requested shape.
    */
   public Point getVertex(int index)
   {
      return new Point(vertices[index]);
   }
   
   /**
    * sets the vertex of the convex polygon at an index.
    * @param index
    * @param vertex 
    */
   public void setVertex(int index, Point vertex)
   {
      this.vertices[index] = new Point(vertex);
   }
   
   @Override
   public boolean equals(Object o)
   {
      if(!super.equals(o))
      {
         return false;
      }
      
      Point[] arr = ((ConvexPolygon)o).vertices;
      
      for(int i = 0; i < vertices.length; i++)
      {
         if(this.vertices[i].x != arr[i].x)
         {
            return false;
         }
         
         if(this.vertices[i].y != arr[i].y)
         {
            return false;
         }
      }
      
            
      return true;
   }
   
   public double getArea()
   {
      double sum1 =0;
      double sum2 = 0;
      int count = 0;
      
      for(int i = 0; i < (vertices.length -1); i++)
      {
         sum1 += ((vertices[i].x)*(vertices[i+1].y));
         count++;
      }
      
      for(int o = 0; o < (vertices.length -1);o++)
      {
         sum2 += ((vertices[o].y)*(vertices[o+1].x));
      }
      
      sum1 += ((vertices[vertices.length -1].x)*(vertices[0].y));
      sum2 += ((vertices[vertices.length -1].y)*(vertices[0].x));
      
      return ((.5)*(sum1 - sum2));
   }

   
   public Point getPosition()
   {
      Point temp = new Point(vertices[0]);
      return temp;
   }
   
   public void setPosition(Point position)
   {
      Point a = new Point(vertices[0]);
      int disX = Math.abs(a.x - position.x);
      int disY = Math.abs(a.y - position.y);
      if(a.x > position.x)
      {
         disX = (disX)*(-1);
      }
      if(a.y > position.y)
      {
         disY = (disY)*(-1);
      }
      
      this.move(new Point(disX, disY));
   }
   
   public void move(Point delta)
   {
      Point tempd = new Point(delta);
      for(int i = 0; i < vertices.length; i++)
      {
         vertices[i].x += tempd.x;
         vertices[i].y += tempd.y;
      }
   }
}