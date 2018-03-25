/**
 * Circle methods.
 * 
 * @author Alexander DeMello
 * @version Program 3
 */
import java.awt.Color;
import java.awt.Point;

public class Circle implements Shape
{
   private double radius;
   private java.awt.Point position;
   private java.awt.Color color;
   private boolean filled;
           
   public Circle(double radius, java.awt.Point position, java.awt.Color color, boolean filled)
   {
      this.radius = radius;
      this.position = position;
      this.color = color;
      this.filled = filled;
   }
   
   public double getRadius()
   {
      return radius;
   }

   public void setRadius(double radius)
   {
      this.radius = radius;
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
      
      if(this.radius != ((Circle)o).radius)
      {
         return false;
      }
      
      if(this.color.getRGB() != ((Circle)o).color.getRGB())
      {
         return false;
      }
      
      if(this.filled != ((Circle)o).filled)
      {
         return false;
      }
      
      if(this.position.x != ((Circle)o).position.x)
      {
         return false;
      }
      
      if(this.position.y != ((Circle)o).position.y)
      {
         return false;
      }
      
      return true;
   }
   
   public double getArea()
   {  
      return (Math.PI)*(this.radius*this.radius);
   }
   
   public java.awt.Color getColor()
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
      return position;
   }

   public void setPosition(Point position) 
   {
      this.position = position;
   }
   
   public void move(java.awt.Point delta)
   {
      this.position.x += delta.x;
      this.position.y += delta.y;
   } 
}
