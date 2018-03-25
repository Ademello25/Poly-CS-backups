/**
 * Convex Polygon methods.
 * 
 * @author Alexander DeMello
 * @version Program 3
 */

import java.awt.Point;
import java.awt.Color;

public class ConvexPolygon implements Shape
{
   private Point[] vertices;
   private Color color;
   private boolean filled;
   
   public ConvexPolygon(Point[] vertices, Color color, boolean filled)
   {
      this.vertices = vertices;
      this.color = color;
      this.filled = filled;
   }
   
   public Point getVertex(int index)
   {
      return vertices[index];
   }
   
   public void setVertex(int index, Point vertex)
   {
      this.vertices[index] = vertex;
   }
   
   @Override
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
      
      if(this.color.getRGB() != ((ConvexPolygon)o).color.getRGB())
      {
         return false;
      }
      
      if(this.filled != ((ConvexPolygon)o).filled)
      {
         return false;
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

   public Color getColor() 
   {
      return color;
   }

   public void setColor(Color color) 
   {
      this.color = color;
   }

   public boolean getFilled() 
   {
      return filled;
   }

   public void setFilled(boolean filled) 
   {
      this.filled = filled;
   }
   
   public Point getPosition()
   {
      return vertices[0];
   }
   
   public void setPosition(Point position)
   {
      Point a = vertices[0];
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
      for(int i = 0; i < vertices.length; i++)
      {
         vertices[i].x += delta.x;
         vertices[i].y += delta.y;
      }
   }
}