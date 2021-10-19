package model;

/**
 * A class to represent a point that serves as the center or lower left corner of a shape.
 */
public class Point {
  private int x;
  private int y;

  /**
   * A method to construct a point object.
   * @param x the x coordinate of the lower left corner or center of a shape
   * @param y the y coordinate of the lower left corner or center of a shape
   * @throws IllegalArgumentException when x and/or y < 0
   */
  public Point(int x, int y) throws IllegalArgumentException {
    this.x = x;
    this.y = y;
  }

  /**
   * A method to get the x coordinate of the point.
   * @return the x coordinate of the point
   */
  public int getX() {
    return x;
  }

  /**
   * A method to get the y coordinate of the point.
   * @return the y coordinate of the point
   */
  public int getY() {
    return y;
  }

  /**
   * A method to set the point to a new x and y coordinate.
   * @param newX the new x coordinate
   * @param newY the new y coordinate
   */
  public void movePoint(int newX, int newY) {
    this.x = newX;
    this.y = newY;
  }

  /**
   * A method to set the x coordinate to a specified value.
   * @param newX the new x coordinate
   */
  public void setX(int newX) {
    this.x = newX;
  }

  /**
   * A method to set the x coordinate to a specified value.
   * @param newY the new x coordinate
   */
  public void setY(int newY) {
    this.x = newY;
  }

  /**
   * A method to represent a point as a string.
   * @return a string representation of a point
   */
  @Override
  public String toString() {
    return this.x + ", " + this.y;
  }
}