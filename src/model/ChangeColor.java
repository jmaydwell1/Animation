package model;

/**
 * A class to represent a color change transformation for a shape in an animation.
 */
public class ChangeColor extends AbstractTransformation {
  int startR;
  int startG;
  int startB;
  int endR;
  int endG;
  int endB;
  IShape s;

  /**
   * A method to construct a color change transformation initialized to a given start time, end
   * time, start R value, start G value, start B value, end R value, end G value, end B value,
   * and shape to perform the transformation on.
   * @param start integer start time of the transformation
   * @param end integer end time of the transformation
   * @param startR integer start R value of the transformation
   * @param startG integer start G value of the transformation
   * @param startB integer start B value of the transformation
   * @param endR integer end R value of the transformation
   * @param endG integer end G value of the transformation
   * @param endB integer end B value of the transformation
   * @param shape shape object on which the transformation is performed
   */
  public ChangeColor(int start, int end, int startR, int startG, int startB, int endR, int endG,
                     int endB, IShape shape) {
    super(start, end, TransformationType.CHANGECOLOR);
    this.startR = startR;
    this.startG = startG;
    this.startB = startB;
    this.endR = endR;
    this.endG = endG;
    this.endB = endB;
    this.s = shape.copy();
  }

  /**
   * A method to return the start R value of the transformation.
   * @return integer start R value of the transformation
   */
  public int getStartR() {
    return startR;
  }

  /**
   * A method to return the start G value of the transformation.
   * @return integer start G value of the transformation
   */
  public int getStartG() {
    return startG;
  }

  /**
   * A method to return the start B value of the transformation.
   * @return integer start B value of the transformation
   */
  public int getStartB() {
    return startB;
  }

  /**
   * A method to return the end R value of the transformation.
   * @return integer end R value of the transformation
   */
  public int getEndR() {
    return endR;
  }

  /**
   * A method to return the end G value of the transformation.
   * @return integer end G value of the transformation
   */
  public int getEndG() {
    return endG;
  }

  /**
   * A method to return the end B value of the transformation.
   * @return integer end B value of the transformation
   */
  public int getEndB() {
    return endB;
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
      s.setColor(endR, endG, endB);
      return s;
    }
    else {
      int newR = (int) (startR * ((end - timeElapsed) / (end - start))
              + endR * ((timeElapsed - start) / (end - start)));
      int newG = (int) (startG * ((end - timeElapsed) / (end - start))
              + endG * ((timeElapsed - start) / (end - start)));
      int newB = (int) (startB * ((end - timeElapsed) / (end - start))
              + endB * ((timeElapsed - start) / (end - start)));

      s.setColor(newR, newG, newB);
      return s;
    }
  }

  /**
   * A method to represent a color change transformation as a string.
   * @return a string representation of a color transformation
   */
  @Override
  public String toString() {
    return String.format("Shape %s changes color from (%d, %d, %d) to (%d, %d, %d)"
                    + " from t = %d to t = %d", s.getID(), startR, startG, startB, endR, endG,
            endB, start, end);
  }
}
