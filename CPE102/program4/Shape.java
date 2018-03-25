import java.awt.Color;
import java.awt.Point;

public abstract class Shape implements java.lang.Comparable<Shape>
{
   private boolean filled;
   private Color color;
   
   /**
    * The basic constructor for all Shapes.
    * 
    * @param filled Whether or not the shape is filled or wireframe.
    * @param color The color of the shape.
    */
   public Shape(boolean filled, Color color)
   {
      this.filled = filled;
      this.color = color;
   }
   
   /**
    * Returns the area of the shape as a double.
    * 
    * @return The area of the shape.
    */
   public abstract double getArea();
   
   /**
    * returns a deep copy of the color of the shape.
    * @return The color of the shape
    */
   public Color getColor()
   {
      int temp1 = this.color.getRGB();
      Color temp2 = new Color(temp1);
      return temp2;
   }
   
   /**
    * Sets the color of the shape to the input value.
    * @param color The new color.
    */
   public void setColor(Color color)
   {
      int temp1 = color.getRGB();
      this.color = new Color(temp1);
   }
   
   /**
    * Gets whether or not the shape is filled or not.
    * @return the fill state of the shape.
    */
   public boolean getFilled()
   {
      return this.filled;
   }
   
   /**
    * Sets the fill state of the Shape.
    * @param filled The new state of the shape.
    */
   public void setFilled(boolean filled)
   {
      this.filled = filled;
   }
   
   /**
    * Obtains the position of the shape.
    * @return the position of thee shape.
    */
   public abstract Point getPosition();
   
   /**
    * Sets the position of the shape.
    * @param position 
    */
   public abstract void setPosition(Point position);
   
   /**
    * moves the shape
    * @param delta 
    */
   public abstract void move(Point delta);
   
   /**
    * Compares the shape's class & area.
    * @param A
    * @return -# for less than, 0 for equals and +# for greater than.
    */
   public int compareTo(Shape A)
   {
      String class1 = this.getClass().getName();
      String class2 = A.getClass().getName();
      int res = class1.compareTo(class2);
      if(this == A)
      {
         return 0;
      }
      
      if(res < 0)
      {
         return -55;
      }
      else if(res > 0)
      {
         return 55;
      }
      else
      {
         double result1 = this.getArea();
         double result2 = ((Shape)A).getArea();
         if((result1 - result2) < (-.000001))
         {
            return -55;
         }
         else if((result1 - result2) > .0000001)
         {
            return 55;
         }
         else
         {
            return 0;
         }
      }
      
   }
   
   /**
    * The overridden equals method for the shape class.
    * @param o
    * @return true if equals false if not.
    */
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
      if(this.color.getRGB() != ((Shape)o).color.getRGB())
      {
         return false;
      }
      if(this.filled != ((Shape)o).filled)
      {
         return false;
      }
      return true;
   }
 
}