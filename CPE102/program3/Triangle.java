/**
 * Triangle methods.
 * 
 * @author Alexander DeMello
 * @version Program 3
 */
import java.lang.Math;
import java.awt.Color;
import java.awt.Point;

public class Triangle implements Shape
{
   private Point a;
   private Point b;
   private Point c; 
   private Color color;
   private boolean filled;

   public Triangle(Point a, Point b, Point c, Color color, boolean filled) 
   {
      this.a = a;
      this.b = b;
      this.c = c;
      this.color = color;
      this.filled = filled;
   }

   public Point getVertexA() 
   {
      return a;
   }

   public Point getVertexB() 
   {
      return b;
   }

   public Point getVertexC() 
   {
      return c;
   }

   public Color getColor()
   {
      return color;
   }

   public boolean getFilled() 
   {
      return filled;
   }

   public void setVertexA(Point a) 
   {
      this.a = a;
   }

   public void setVertexB(Point b) 
   {
      this.b = b;
   }

   public void setVertexC(Point c)
   {
      this.c = c;
   }

   public void setColor(Color color)
   {
      this.color = color;
   }

   public void setFilled(boolean filled) 
   {
      this.filled = filled;
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
      
      if(this.a.x != ((Triangle)o).a.x)
      {
         return false;
      }
      
      if(this.a.y != ((Triangle)o).a.y)
      {
         return false;
      }
      
      if(this.b.x != ((Triangle)o).b.x)
      {
         return false;
      }
      
      if(this.b.y != ((Triangle)o).b.y)
      {
         return false;
      }
      
      if(this.c.x != ((Triangle)o).c.x)
      {
         return false;
      }
      
      if(this.c.y != ((Triangle)o).c.y)
      {
         return false;
      }
      
      if(this.color.getRGB() != ((Triangle)o).color.getRGB())
      {
         return false;
      }
      
      if(this.filled != ((Triangle)o).filled)
      {
         return false;
      }
      
      return true;
   }
    
   public double getArea()
   {
      double sideA = a.distance(c);
      double sideB = a.distance(b);
      double sideC = b.distance(c);

      double s = ((sideA + sideB + sideC)/2);
      double Heron = (Math.sqrt(s*(s-sideA)*(s-sideB)*(s-sideC)));
      
      return Heron;
   }
   @Override
   public void move(Point delta)
   {
      a.x += delta.x;
      a.y += delta.y;
      
      b.x += delta.x;
      b.y += delta.y;
      
      c.x += delta.x;
      c.y += delta.y;
   }
   
   public Point getPosition()
   {
      return a;
   }
   
   public void setPosition(Point position)
   {
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
}