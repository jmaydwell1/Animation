package model;

/**
 * An abstract class that contains common methods shared by transformation objects.
 */
public abstract class AbstractTransformation implements ITransformation {
  protected int start;
  protected int end;
  protected TransformationType type;

  /**
   * A method to construct a transformation object initialized to a given start and end type, and
   * type of transformation.
   * @param start the integer start time of the transformation
   * @param end the integer end time of the transformation
   * @param type an enum representing type of transformation
   */
  public AbstractTransformation(int start, int end, TransformationType type) {
    this.start = start;
    this.end = end;
    this.type = type;
  }

  /**
   * A method to return the start time of the transformation.
   * @return an integer start time for the transformation
   */
  @Override
  public int getStartTime() {
    return this.start;
  }

  /**
   * A method to return the end time of the transformation.
   * @return an integer end time for the transformation
   */
  @Override
  public int getEndTime() {
    return this.end;
  }

  /**
   * A method to return the type of transformation.
   * @return an enum representing type of transformation
   */
  @Override
  public TransformationType getType() {
    return type;
  }
}
