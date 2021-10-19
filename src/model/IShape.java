package model;

/**
 * An interface that represents the operations offered by shape objects.
 */
public interface IShape {

  /**
   * A method to return the current color of a Shape Object.
   * @return the current color of the shape
   */
  String getColor();

  /**
   * A method to determine the lower left corner or the center of a shape.
   * @return the x, y coordinate of the point
   */
  Point getLocation();

  /**
   * A method to set the time an object appears in an animation.
   * @param appearance a unit of time indicating when the shape appears
   * @throws IllegalArgumentException when the time of appearance is negative
   */
  void appears(int appearance);

  /**
   * A method to return the time when the current shape appears in the animation.
   * @return the time the shape appears
   */
  int getAppearance();

  /**
   * A method to return the time when teh current shape disappears in the animation.
   * @return the time the shape disappears
   */
  int getDisappearance();

  /**
   * A method to set the time an object disappears in an animation.
   * @param disappearance a unit of time indicating when the shape disappears
   * @throws IllegalArgumentException when the disappearance time is negative or before the
   *          appearance
   */
  void disappears(int disappearance);

  /**
   * A method to set the color of a shape.
   * @param r red value for the color of a shape
   * @param g green value for the color of a shape
   * @param b blue value for the color of a shape
   */
  void setColor(int r, int g, int b);

  /**
   * A method to get the id of the current shape.
   * @return a string representing the id of the shape
   */
  String getID();

  /**
   * A method to return a copy of a shape.
   * @return a copy of a shape object
   */
  IShape copy();

  /**
   * A method to set the id of a shape.
   * @param id the new String id
   */
  void setID(String id);

  /**
   * A method to set the width of a shape.
   * @param newWidth the new width of the shape.
   */
  void setWidth(int newWidth);

  /**
   * A method to set the height of a shape.
   * @param newHeight the new height of the shape
   */
  void setHeight(int newHeight);

  /**
   * A method to return the width of the shape.
   * @return the integer width of the shape
   */
  int getWidth();

  /**
   * A method to return the height of the shape.
   * @return the integer height of the shape
   */
  int getHeight();

  /**
   * A method to get the R component of the RGB color of the shape.
   * @return the integer R value of the shape
   */
  int getR();

  /**
   * A method to get the G component of the RGB color of the shape.
   * @return the integer G value of the shape
   */
  int getG();

  /**
   * A method to get the B component of the RGB color of the shape.
   * @return the integer B value of the shape
   */
  int getB();

  /**
   * A method to set the R component of the RGB color of the shape.
   * @param newR the integer R value of the new color of the shape
   */
  void setR(int newR);

  /**
   * A method to set the G component of the RGB color of the shape.
   * @param newG the integer G value of the new color of the shape
   */
  void setG(int newG);

  /**
   * A method to set the B component of the RGB color of the shape.
   * @param newB the integer B value of the new color of the shape
   */
  void setB(int newB);

  /**
   * A method to return the type of the current shape.
   * @return an enum representing type of shape.
   */
  ShapeType getType();

  /**
   * A method to set the type of the current shape.
   * @param type an enum representing type of shape
   */
  void setType(ShapeType type);
}

