/**
 * Shape interface
 * 
 * @author Alexander DeMello
 * @version Program 3
 */
public interface Shape
{
   /**
    * Calculates and returns the area of the shape.
    * 
    * @return the area of the shape. 
    */
   public double getArea();
   
   /**
    * Returns the color of the shape.
    * 
    * @return the color of the shape.
    */
   public java.awt.Color getColor();
   
   /**
    * Changes the color of the object.
    * 
    * @param color The new color.
    */
   public void setColor(java.awt.Color color);
   
   /**
    * Returns true if the shape is filled with color, false if it is a wire frame.
    * @return whether the object is filled or not.
    */
   public boolean getFilled();
   
   /**
    * Sets the filled state of the shape to specified value.
    * @param filled What the user chooses to do with this fill status.
    */
   public void setFilled(boolean filled);
   
   /**
    * Returns the current position of the shape.
    * @return the position of the shape.
    */
   public java.awt.Point getPosition();
   
   /**
    * Sets the position of the shape.
    * @param position What the user chooses are the new position.
    */
   public void setPosition(java.awt.Point position);
   
   /**
    * moves the object by the specified amount.
    * @param delta the change in position that the user has chosen.
    */
   public void move(java.awt.Point delta);
}