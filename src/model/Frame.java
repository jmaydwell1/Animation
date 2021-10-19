package model;

/**
 * A class to represent a frame in which an animation is displayed.
 */
public class Frame {
  private int x;
  private int y;
  private int width;
  private int height;

  /**
   * A method to construct a frame object initialized to a give x coordinate, y coordinate, width,
   * and height.
   * @param x the left most x coordinate of the frame
   * @param y the top most y coordinate of the frame
   * @param width the width of the frame
   * @param height the height of the frame
   */
  public Frame(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  /**
   * A method to return the height of the frame.
   * @return an integer representing frame height
   */
  public int getHeight() {
    return height;
  }

  /**
   * A method to return the width of the frame.
   * @return an integer representing frame width
   */
  public int getWidth() {
    return width;
  }

  /**
   * A method to return the left most x coordinate of the frame.
   * @return an integer representing left most x coordinate of the frame
   */
  public int getX() {
    return x;
  }

  /**
   * A method to set the left most x coordinate of the frame.
   * @param x an integer representing left most x coordinate of the frame
   */
  public void setX(int x) {
    this.x = x;
  }

  /**
   * A method to return the top most y coordinate of the frame.
   * @return an integer representing top most y coordinate of the frame
   */
  public int getY() {
    return y;
  }

  /**
   * A method to set the top most y coordinate of the frame.
   * @param y an integer representing left most x coordinate of the frame
   */
  public void setY(int y) {
    this.y = y;
  }

  /**
   * A method to set the height of the frame.
   * @param height an integer representing height of the frame
   */
  public void setHeight(int height) {
    this.height = height;
  }

  /**
   * A method to set the width of the frame.
   * @param width an integer representing width of the frame
   */
  public void setWidth(int width) {
    this.width = width;
  }
}
