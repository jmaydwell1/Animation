package model;

/**
 * A class to represent a resize transformation for a shape in an animation.
 */
public class Resize extends AbstractTransformation {
  int startHeight;
  int startWidth;
  int endHeight;
  int endWidth;
  IShape s;

  /**
   * A method to construct a resize transformation initialized to a given start time, end time,
   * start height, start width, end height, end width, and shape on which the transformation
   * is to be performed.
   * @param start an integer start time of the transformation
   * @param end an integer end time of the transformation
   * @param startHeight an integer start height for the shape
   * @param startWidth an integer start width for the shape
   * @param endHeight an integer end height for the shape
   * @param endWidth an integer end width for the shape
   * @param shape shape object on which the transformation is to be performed
   */
  public Resize(int start, int end, int startHeight, int startWidth, int endHeight,
                int endWidth, IShape shape) {
    super(start, end, TransformationType.RESIZE);
    this.startHeight = startHeight;
    this.startWidth = startWidth;
    this.endHeight = endHeight;
    this.endWidth = endWidth;
    this.s = shape;
  }

  /**
   * A method to return the start height for the transformation.
   * @return integer start height for the transformation
   */
  public int getStartHeight() {
    return this.startHeight;
  }

  /**
   * A method to return the end height for the transformation.
   * @return integer end height for the transformation
   */
  public int getEndHeight() {
    return endHeight;
  }

  /**
   * A method to return the start width for the transformation.
   * @return integer start width for the transformation
   */
  public int getStartWidth() {
    return startWidth;
  }

  /**
   * A method to return the end width for the transformation.
   * @return integer end width for the transformation
   */
  public int getEndWidth() {
    return this.endWidth;
  }

  /**
   * A method to get the current state of the shape depending on where it is a specified point
   * in time elapsed and where the shape is in this transformation.
   * @param s the shape the transformation is performed on
   * @param timeElapsed the time elapsed in the animation
   * @return a shape with the attributes corresponding to a specific point in time along a
   *          transformation
   */
  @Override
  public IShape getTransformationsAtTick(IShape s, double timeElapsed) {
    if (timeElapsed < start) {
      return s;
    }
    else if (timeElapsed > end) {
      s.setHeight(endHeight);
      s.setWidth(endWidth);
      return s;
    }
    else {
      int newWidth = (int) (startWidth * ((end - timeElapsed) / (end - start))
              + endWidth * ((timeElapsed - start) / (end - start)));
      int newHeight = (int) (startHeight * ((end - timeElapsed) / (end - start))
              + endHeight * ((timeElapsed - start) / (end - start)));

      s.setWidth(newWidth);
      s.setHeight(newHeight);
      return s;
    }
  }

  /**
   * A method to represent a resize transformation as a string.
   * @return a string representation of a resize transformation
   */
  @Override
  public String toString() {
    return String.format("Shape %s scales from Width: %d, Height: %d to Width: %d, Height: "
                    + "%d from t = %d to t = %d", s.getID(), startWidth, startHeight, endWidth,
            endHeight, start, end);
  }
}
