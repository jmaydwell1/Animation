package model;

/**
 * A class to represent a move transformation for a shape in an animation.
 */
public class Move extends AbstractTransformation {
  Point locationStart;
  Point locationEnd;
  IShape s;

  /**
   * A method to construct a Move transformation initialized to a given start time, end time, start
   * location represented as a Point object, end location represented os a Point object, and shape
   * to perform the transformation on.
   * @param start integer start time for transformation
   * @param end integer end time for transformation
   * @param locationStart Point start location for transformation
   * @param locationEnd Point end location for transformation
   * @param shape shape to perform the transformation on
   */
  public Move(int start, int end, Point locationStart, Point locationEnd, IShape shape) {
    super(start, end, TransformationType.MOVE);
    this.locationStart = locationStart;
    this.locationEnd = locationEnd;
    this.s = shape;
  }

  /**
   * A method to return a point object specifying start location for a shape.
   * @return a point object representing a shape's start position
   */
  public Point getLocationStart() {
    return this.locationStart;
  }

  /**
   * A method to return a point object specifying end location for a shape.
   * @return a point object representing a shape's end position
   */
  public Point getLocationEnd() {
    return this.locationEnd;
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
      s.getLocation().movePoint(locationEnd.getX(), locationEnd.getY());
      return s;
    }
    else {
      int newX = (int) (locationStart.getX() * ((end - timeElapsed) / (end - start))
              + locationEnd.getX() * ((timeElapsed - start) / (end - start)));
      int newY = (int) (locationStart.getY() * ((end - timeElapsed) / (end - start))
              + locationEnd.getY() * ((timeElapsed - start) / (end - start)));

      s.getLocation().movePoint(newX, newY);
      return s;
    }
  }

  /**
   * A method to represent a move transformation as a string.
   * @return a string representation of a move transformation
   */
  @Override
  public String toString() {
    return String.format("Shape %s moves from (%d, %d) to (%d, %d) from t = %d to t = %d",
            s.getID(), locationStart.getX(), locationStart.getY(), locationEnd.getX(),
            locationEnd.getY(), start, end);
  }
}
