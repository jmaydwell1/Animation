package model;

/**
 * An interface that represents the operations offered by transformation objects.
 */
public interface ITransformation {

  /**
   * A method to return the start time of a transformation.
   * @return integer start time of a transformation
   */
  int getStartTime();

  /**
   * A method to return the end time of a transformation.
   * @return integer end time of a transformation
   */
  int getEndTime();

  /**
   * A method to get the current state of the shape depending on where it is a specified point
   * in time elapsed and where the shape is in this transformation.
   * @param s the shape the transformation is performed on
   * @param timeElapsed the time elapsed in the animation
   * @return a shape with the attributes corresponding to a specific point in time along a
   *          transformation
   */
  IShape getTransformationsAtTick(IShape s, double timeElapsed);

  /**
   * A method to return the type of transformation.
   * @return an enum representing type of transformation
   */
  TransformationType getType();
}
