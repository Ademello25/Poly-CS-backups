/**
 * Rectangle methods.
 * 
 * @author Alexander DeMello
 * @version Program 3
 */
import java.awt.Color;
import java.awt.Point;

public class Rectangle implements Shape
{
   private int width;
   private int height;
   private Point position;
   private Color color;
   private boolean filled;
   
   public Rectangle(int width, int height, Point position, Color color, boolean filled)
   {
      this.width = width;
      this.height = height;
      this.position = position;
      this.color = color;
      this.filled = filled;
   }

   public Color getColor() 
   {
      return color;
   }

   public boolean getFilled() 
   {
      return filled;
   }

   public int getHeight()
   {
      return height;
   }

   public Point getPosition() 
   {
      return position;
   }

   public int getWidth() 
   {
      return width;
   }

   public void setColor(Color color) 
   {
      this.color = color;
   }

   public void setFilled(boolean filled) 
   {
      this.filled = filled;
   }

   public void setHeight(int height) 
   {
      this.height = height;
   }

   public void setPosition(Point position) 
   {
      this.position = position;
   }

   public void setWidth(int width) 
   {
      this.width = width;
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
      
      if(this.width != ((Rectangle)o).width)
      {
         return false;
      }
      
      if(this.height != ((Rectangle)o).height)
      {
         return false;
      }
      
      if(this.color.getRGB() != ((Rectangle)o).color.getRGB())
      {
         return false;
      }
      
      if(this.filled != ((Rectangle)o).filled)
      {
         return false;
      }
      
      if(this.position.x != ((Rectangle)o).position.x)
      {
         return false;
      }
      
      if(this.position.y != ((Rectangle)o).position.y)
      {
         return false;
      }
      
      return true;
   }
   
   public double getArea()
   {
      return width * height;
   }
   
   public void move(Point delta)
   {
      this.position.x += delta.x;
      this.position.y += delta.y;
   }
   
}