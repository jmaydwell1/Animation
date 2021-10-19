package model;

/**
 * This class represents an oval. It offers all the operations mandated by the
 * Shape interface.
 */
public class Oval extends AbstractShape {

  /**
   * Construct an oval object using the given center and radius.
   * @param x x coordinate of the center of this oval
   * @param y y coordinate of the center of this oval
   * @param xradius the x radius of this oval
   * @param yradius the y radius of this oval
   * @param r red value for the color of a shape
   * @param g green value for the color of a shape
   * @param b blue value for the color of a shape
   */
  public Oval(int x, int y, int xradius, int yradius, int r, int g, int b) {
    super(new Point(x, y), yradius, xradius, r, g, b, ShapeType.OVAL);
  }

  /**
   * A method to construct an oval with default values 0, 0, 1, 1, 0, 0, and 0 for x coordinate,
   * y coordinate, height, width, r, g, and b values respectively.
   */
  public Oval() {
    super(new Point(0, 0), 1, 1, 0, 0, 0,
            ShapeType.OVAL);
  }

  /**
   * A method to represent an oval as a string.
   * @return a string representation of this oval
   */
  public String toString() {
    return String.format("Name: %s\nType: oval\nCenter: (%d, %d), X radius: %d, "
                    + "Y radius: %d\nColor: (%d, %d, %d)",
            super.id, this.location.getX(), this.location.getY(), super.width, super.height,
            super.r, super.g, super.b);
  }

  /**
   * A method to return a copy of a shape.
   * @return a copy of a shape object
   */
  @Override
  public IShape copy() {
    IShape ovalCopy = new Oval(location.getX(), location.getY(), super.height, super.width, super.r,
            super.g, super.b);
    ovalCopy.setID(super.id);
    return ovalCopy;
  }
}