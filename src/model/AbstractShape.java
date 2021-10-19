package model;

/**
 * An abstract class that contains common methods shared by shape objects.
 */
public abstract class AbstractShape implements IShape {
  protected Point location;
  protected int height;
  protected int width;
  protected int r;
  protected int g;
  protected int b;
  protected String id;
  protected int appearance;
  protected int disappearance;
  protected ShapeType type;
  protected int duration;


  /**
   * A method to construct an abstract shape object, initializing the shape to a given location in
   * the form of a point, a given color, an empty id, an appearance at t = 0, and disappearance
   * at t = 0.
   * @param location the coordinates of the Shape's location
   * @param height the height of a shape
   * @param width the width of a shape
   * @param r red value for the color of a shape
   * @param g green value for the color of a shape
   * @param b blue value for the color of a shape
   * @param type the type of shape
   */
  public AbstractShape(Point location, int height, int width, int r, int g, int b,
                       ShapeType type) {
    if (r < 0 || g < 0 || b < 0 || r > 255 || g > 255 || b > 255) {
      throw new IllegalArgumentException("Value outside of RGB range");
    }
    this.location = location;
    this.height = height;
    this.width = width;
    this.r = r;
    this.g = g;
    this.b = b;
    this.id = "";
    this.appearance = 0;
    this.disappearance = 0;
    this.duration = 0;
    this.type = type;
  }

  /**
   * A method to set the time an object appears in an animation.
   * @param appearance a unit of time indicating when the shape appears
   * @throws IllegalArgumentException when the time of appearance is negative
   */
  @Override
  public void appears(int appearance) throws IllegalArgumentException {
    if (appearance < 0) {
      throw new IllegalArgumentException("Time of appearance cannot be negative!");
    }
    else {
      this.appearance = appearance;
    }
  }

  /**
   * A method to return the time when the current shape appears in the animation.
   * @return the time the shape appears
   */
  @Override
  public int getAppearance() {
    return appearance;
  }

  /**
   * A method to set the time an object disappears in an animation.
   * @param disappearance a unit of time indicating when the shape disappears
   * @throws IllegalArgumentException when the disappearance time is negative or before the
   *          appearance
   */
  @Override
  public void disappears(int disappearance) throws IllegalArgumentException {
    if (disappearance < appearance || disappearance < 0) {
      throw new IllegalArgumentException("Time of disappearance cannot be "
              + "before appearance!");
    }
    else {
      this.disappearance = disappearance;
    }
  }

  /**
   * A method to return the time when teh current shape disappears in the animation.
   * @return the time the shape disappears
   */
  @Override
  public int getDisappearance() {
    return disappearance;
  }

  /**
   * A method to return the current color of a Shape Object.
   * @return the current color of the shape
   */
  @Override
  public String getColor() {
    return String.format("(%d, %d, %d)", this.r, this.g, this.b);
  }

  /**
   * A method to determine the lower left corner or the center of a shape.
   * @return the x, y coordinate of the point
   */
  @Override
  public Point getLocation() {
    return this.location;
  }


  /**
   * A method to set the color of a shape.
   * @param r red value for the color of a shape
   * @param g green value for the color of a shape
   * @param b blue value for the color of a shape
   */
  @Override
  public void setColor(int r, int g, int b) throws IllegalArgumentException {
    if (r < 0 || g < 0 || b < 0 || r > 255 || g > 255 || b > 255) {
      throw new IllegalArgumentException("Value outside of RGB range");
    }
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * A method to get the id of the current shape.
   * @return a string representing the id of the shape
   */
  @Override
  public String getID() {
    return this.id;
  }

  /**
   * A method to set the id of a shape.
   * @param id the new String id
   */
  public void setID(String id) {
    this.id = id;
  }

  /**
   * A method to set the height of a shape.
   * @param newHeight the new height of the shape
   * @throws IllegalArgumentException when newHeight is negative
   */
  public void setHeight(int newHeight) throws IllegalArgumentException {
    if (newHeight < 0) {
      throw new IllegalArgumentException("Height cannot be negative");
    }
    this.height = newHeight;
  }

  /**
   * A method to set the width of a shape.
   * @param newWidth the new width of the shape.
   * @throws IllegalArgumentException when newWidth is negative
   */
  public void setWidth(int newWidth) throws IllegalArgumentException {
    if (newWidth < 0) {
      throw new IllegalArgumentException("Width cannot be negative");
    }
    this.width = newWidth;
  }

  /**
   * A method to return the height of the shape.
   * @return the integer height of the shape
   */
  @Override
  public int getHeight() {
    return height;
  }

  /**
   * A method to return the width of the shape.
   * @return the integer width of the shape
   */
  @Override
  public int getWidth() {
    return width;
  }

  /**
   * A method to get the R component of the RGB color of the shape.
   * @return the integer R value of the shape
   */
  @Override
  public int getR() {
    return this.r;
  }

  /**
   * A method to get the G component of the RGB color of the shape.
   * @return the integer G value of the shape
   */
  @Override
  public int getG() {
    return this.g;
  }

  /**
   * A method to get the B component of the RGB color of the shape.
   * @return the integer B value of the shape
   */
  @Override
  public int getB() {
    return this.b;
  }

  /**
   * A method to set the R component of the RGB color of the shape.
   * @param newR the integer R value of the new color of the shape
   * @throws IllegalArgumentException when value provided outside RGB range
   */
  @Override
  public void setR(int newR) throws IllegalArgumentException {
    if (newR < 0 || newR > 255) {
      throw new IllegalArgumentException("Value outside of RGB range");
    }
    this.r = newR;
  }

  /**
   * A method to set the G component of the RGB color of the shape.
   * @param newG the integer G value of the new color of the shape
   * @throws IllegalArgumentException when value provided outside RGB range
   */
  @Override
  public void setG(int newG) throws IllegalArgumentException {
    if (newG < 0 || newG > 255) {
      throw new IllegalArgumentException("Value outside of RGB range");
    }
    this.g = newG;
  }

  /**
   * A method to set the B component of the RGB color of the shape.
   * @param newB the integer B value of the new color of the shape
   * @throws IllegalArgumentException when value provided outside RGB range
   */
  @Override
  public void setB(int newB) throws IllegalArgumentException {
    if (newB < 0 || newB > 255) {
      throw new IllegalArgumentException("Value outside of RGB range");
    }
    this.b = newB;
  }

  /**
   * A method to return the type of the current shape.
   * @return an enum representing type of shape.
   */
  @Override
  public ShapeType getType() {
    return type;
  }

  /**
   * A method to set the type of the current shape.
   * @param type an enum representing type of shape
   */
  @Override
  public void setType(ShapeType type) {
    this.type = type;
  }
}
