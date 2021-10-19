package model;

/**
 * An enum to represent types of transformations.
 */
public enum TransformationType {
  MOVE,
  CHANGECOLOR,
  RESIZE;

  /**
   * A method to represent an enum as a string.
   * @return a string representation of an enum that describes a transformation
   */
  @Override
  public String toString() {
    if (this == TransformationType.MOVE) {
      return "Move";
    }
    else if (this == TransformationType.CHANGECOLOR) {
      return "ChangeColor";
    }
    else {
      return "Resize";
    }
  }
}
