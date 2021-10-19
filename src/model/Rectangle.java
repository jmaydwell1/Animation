package model;

/**
 * This class represents a rectangle. It offers all the operations mandated by the
 * Shape interface.
 */
public class Rectangle extends AbstractShape {

  /**
   * Construct a rectangle object using the given lower left corner coordinate and radius.
   * @param x the x coordinate of the lower left corner
   * @param y the y coordinate of the lower left corner
   * @param width the width of this rectangle
   * @param height the height of this rectangle
   * @param r red value for the color of a shape
   * @param g green value for the color of a shape
   * @param b blue value for the color of a shape
   */
  public Rectangle(int x, int y, int width, int height, int r, int g, int b) {
    super(new Point(x, y), height, width, r, g, b, ShapeType.RECTANGLE);
  }

  /**
   * A method to construct a rectangle with default values 0, 0, 1, 1, 0, 0, and 0 for x coordinate,
   * y coordinate, height, width, r, g, and b values respectively.
   */
  public Rectangle() {
    super(new Point(0, 0), 1, 1, 0, 0, 0, ShapeType.RECTANGLE);
  }

  /**
   * A method to return a copy of a shape.
   * @return a copy of a shape object
   */
  @Override
  public IShape copy() {
    IShape rectangleCopy = new Rectangle(super.location.getX(), super.location.getY(), super.width,
            super.height, super.r, super.g, super.b);
    rectangleCopy.setID(super.id);
    return rectangleCopy;
  }

  /**
   * A method to represent a rectangle as a string.
   * @return a string representation of this rectangle
   */
  public String toString() {
    return String.format("Name: %s\nType: rectangle\nMin corner: (%d, %d), Width: %d, "
                    + "Height: %d\nColor: (%d, %d, %d)",
            super.id, this.location.getX(), this.location.getY(), super.width, super.height,
            super.r, super.g, super.b);
  }
}