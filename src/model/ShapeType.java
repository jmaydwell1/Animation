package model;

/**
 * An enum to represent types of shapes.
 */
public enum ShapeType {
  RECTANGLE,
  OVAL,
  DEFAULTRECT,
  DEFAULTOVAL;

  /**
   * A method to represent an enum as a string.
   * @return a string representation of an enum describing type of shape
   */
  @Override
  public String toString() {
    if (this == ShapeType.OVAL) {
      return "oval";
    }
    else if (this == ShapeType.DEFAULTRECT) {
      return "default rectangle";
    }
    else if (this == ShapeType.DEFAULTOVAL) {
      return "default oval";
    }
    else {
      return "rectangle";
    }
  }
}
