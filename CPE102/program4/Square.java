import java.awt.Point;
import java.awt.Color;

public class Square extends Rectangle
{
   /**
    * The square constructor.
    * @param side
    * @param position
    * @param color
    * @param filled 
    */
   public Square(int side, Point position, Color color, boolean filled)
   {
      super(side, side, position, color, filled);
   }
   
   /**
    * Gets the length and the width of the square.
    * @return the length of any given side.
    */
   public int getSize()
   {
      int temp = super.getHeight();
      return temp;
   }
   
   /**
    * sets the length of the square.
    * @param newlen 
    */
   public void setSize(int newlen)
   {
      super.setHeight(newlen);
      super.setWidth(newlen);
   }
   
   /**
    * sets the height of the square.
    * @param height 
    */
   public void setHeight(int height)
   {
      this.setSize(height);
   }
   
   /**
    * sets the width of the square.
    * @param width 
    */
   public void setWidth(int width)
   {
      this.setSize(width);
   }
}