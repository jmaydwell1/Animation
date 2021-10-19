package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;

import cs5004.animator.util.AnimationBuilder;

/**
 * A class to represent an animation consisting of various shapes.
 */
public class Model implements IModel {
  private Map<String, IShape> animation;
  private Map<String, IShape> animationsCopy;
  private Map<String, List<ITransformation>> transformations;
  private Frame frame;

  /**
   * A method to construct a model that is initialized to an empty hashmap to store shapes, another
   * hashmap to store copies of the shape, a hashmap to store transformations performed on a shape,
   * and a null frame.
   */
  public Model() {
    this.animation = new LinkedHashMap<>();
    this.animationsCopy = new LinkedHashMap<>();
    this.transformations = new LinkedHashMap<>();
    this.frame = null;
  }

  /**
   * A method to create a frame for a model to represent an animation.
   * @param x the left most x coordinate for the frame
   * @param y the top most y coordinate for the frame
   * @param width the width of the frame
   * @param height the height of the frame
   */
  @Override
  public void createFrame(int x, int y, int width, int height) {
    this.frame = new Frame(x, y, width, height);
  }

  /**
   * A method to add a shape object to an animation.
   * @param id the identifier for the shape
   * @param shape a shape object
   * @throws IllegalArgumentException when question is null, id is null, id is an empty string,
   *          id is not unique, appear or disappear time is less than 0, or shape disappears
   *          before it appears
   */
  public void addShape(String id, IShape shape)
          throws IllegalArgumentException {
    if (shape == null) {
      throw new IllegalArgumentException("The question cannot be null!");
    }
    else if (id == null) {
      throw new IllegalArgumentException("The identifier cannot be null!");
    }
    else if (id.equals("")) {
      throw new IllegalArgumentException("Invalid ID - is empty");
    }
    else if (!isUnique(id)) {
      throw new IllegalArgumentException("ID must be unique!");
    }
    else {
      shape.setID(id);
      shape.appears(0);
      shape.disappears(0);
      animation.put(id, shape.copy());
      animationsCopy.put(id, shape.copy());
      transformations.put(id, new ArrayList<>());
    }
  }

  /**
   * A method to add a transformation to a shape with the specified id.
   * @param id a string identifier for a shape
   * @param t a transformation object to add to a shape
   */
  @Override
  public void addTransformation(String id, ITransformation t) {
    if (t == null) {
      throw new IllegalArgumentException("Transformation cannot be null");
    }
    if (isUnique(id)) {
      throw new IllegalArgumentException("No shape exists with given id");
    }
    for (Map.Entry<String, IShape> e : animationsCopy.entrySet()) {
      if (e.getKey() == id) {
        List<ITransformation> shapeTransformations = transformations.get(e.getKey());
        for (ITransformation trans : shapeTransformations) {
          if (trans.getType() == t.getType()) {
            if ((t.getStartTime() > trans.getStartTime() && t.getStartTime() < trans.getEndTime())
                    || (t.getEndTime() > trans.getStartTime()
                    && t.getEndTime() < trans.getEndTime())) {
              throw new IllegalArgumentException("Two transformations of the same type can't occur"
                      + " at the same time");
            }
          }
        }
      }
    }
    transformations.get(id).add(t);
  }

  /**
   * A method to get a shape with a given identifier from the current animation.
   * @param id a string identifier for the desired shape
   * @return the shape with the given identifier
   * @throws IllegalArgumentException when the id is null or an empty string
   * @throws NoSuchElementException when the element with the given id cannot be found in the list
   */
  @Override
  public IShape getShape(String id) throws IllegalArgumentException, NoSuchElementException {
    if (id == null) {
      throw new IllegalArgumentException("The identifier cannot be null!");
    }
    else if (id.equals("")) {
      throw new IllegalArgumentException("Invalid ID - is empty");
    }
    else if (animationsCopy.containsKey(id)) {
      return animationsCopy.get(id);
    }
    throw new NoSuchElementException("No such shape found under given ID.");
  }

  /**
   * A class to build an animation model based off of information received from AnimationReader.
   */
  public static final class Builder implements AnimationBuilder<IModel> {
    private IModel model = new Model();

    /**
     * Constructs a final document.
     *
     * @return the newly constructed document
     */
    @Override
    public IModel build() {
      return model;
    }

    /**
     * Specify the bounding box to be used for the animation.
     *
     * @param x      The leftmost x value
     * @param y      The topmost y value
     * @param width  The width of the bounding box
     * @param height The height of the bounding box
     * @return This {@link AnimationBuilder}
     */
    @Override
    public AnimationBuilder<IModel> setBounds(int x, int y, int width, int height) {
      model.createFrame(x, y, width, height);
      return this;
    }

    /**
     * Adds a new shape to the growing document.
     *
     * @param name The unique name of the shape to be added. No shape with this name should already
     *             exist.
     * @param type The type of shape (e.g. "ellipse", "rectangle") to be added. The set of supported
     *             shapes is unspecified, but should include "ellipse" and "rectangle" as a
     *             minimum.
     * @return This {@link AnimationBuilder}
     */
    @Override
    public AnimationBuilder<IModel> declareShape(String name, String type) {
      if (type.toLowerCase().equals("rectangle")) {
        model.addShape(name, new Rectangle());
      }
      else {
        model.addShape(name, new Oval());
      }
      return this;
    }

    /**
     * Adds a transformation to the growing document.
     *
     * @param name The name of the shape (added with {@link AnimationBuilder#declareShape})
     * @param t1   The start time of this transformation
     * @param x1   The initial x-position of the shape
     * @param y1   The initial y-position of the shape
     * @param w1   The initial width of the shape
     * @param h1   The initial height of the shape
     * @param r1   The initial red color-value of the shape
     * @param g1   The initial green color-value of the shape
     * @param b1   The initial blue color-value of the shape
     * @param t2   The end time of this transformation
     * @param x2   The final x-position of the shape
     * @param y2   The final y-position of the shape
     * @param w2   The final width of the shape
     * @param h2   The final height of the shape
     * @param r2   The final red color-value of the shape
     * @param g2   The final green color-value of the shape
     * @param b2   The final blue color-value of the shape
     * @return This {@link AnimationBuilder}
     */
    @Override
    public AnimationBuilder<IModel> addMotion(String name, int t1, int x1, int y1, int w1, int h1,
                                              int r1, int g1, int b1, int t2, int x2, int y2,
                                              int w2, int h2, int r2, int g2, int b2) {
      IShape s = model.getShape(name);
      int offsetX = model.getFrame().getX();
      int offsetY = model.getFrame().getY();
      if (model.getShape(name).getLocation().getX() == 0
              && model.getShape(name).getLocation().getX() == 0
              && model.getShape(name).getHeight() == 1
              && model.getShape(name).getWidth() == 1 && model.getShape(name).getR() == 0
              && model.getShape(name).getB() == 0 && model.getShape(name).getG() == 0) {
        model.getAnimationsCopy().get(name).getLocation().movePoint(x1 - offsetX,
                y1 - offsetY);
        model.getAnimationsCopy().get(name).setColor(r1, g1, b1);
        model.getAnimationsCopy().get(name).setHeight(h1);
        model.getAnimationsCopy().get(name).setWidth(w1);
        model.getAnimation().get(name).getLocation().movePoint(x1 - offsetX,
                y1 - offsetY);
        model.getAnimation().get(name).setColor(r1, g1, b1);
        model.getAnimation().get(name).setHeight(h1);
        model.getAnimation().get(name).setWidth(w1);
      }
      if (x1 != x2 || y1 != y2) {
        model.addTransformation(name, new Move(t1, t2, new Point(x1 - offsetX, y1 - offsetY),
                new Point(x2 - offsetX, y2 - offsetY), s.copy()));
      }
      if (r1 != r2 || g1 != g2 || b1 != b2) {
        model.addTransformation(name, new ChangeColor(t1, t2, r1, g1, b1, r2, g2, b2, s.copy()));
      }
      if (h1 != h2 || w1 != w2) {
        model.addTransformation(name, new Resize(t1, t2, h1, w1, h2, w2, s.copy()));
      }
      return this;
    }
  }

  /**
   * Simple method to determine whether an ID string is unique or if it already exists.
   * @param id the String id.
   * @return an int value.
   */
  private boolean isUnique(String id) {
    return !animation.containsKey(id);
  }

  /**
   * A method to revert all shapes in an animation back to their original state.
   * @return a linkedHashMap with each shape reverted back to its original form
   */
  public int getFinalTick() {
    Map<String, List<ITransformation>> sorted = new LinkedHashMap<>();
    List<Integer> newList = new ArrayList();
    List<Integer> listOfEnds = new ArrayList();
    for (Map.Entry<String, List<ITransformation>> e : transformations.entrySet()) {
      listOfEnds = e.getValue().stream().map(ITransformation::getEndTime)
              .collect(Collectors.toList());
      newList.addAll(listOfEnds);
    }
    int i;
    int max = newList.get(0);
    for (i = 1; i < newList.size(); i++) {
      if (newList.get(i) > max) {
        max = newList.get(i);
      }
    }
    return max;
  }

  /**
   * A method to represent the current animation as a string.
   * @return a string representation of this animation
   */
  @Override
  public String toString() {
    StringBuilder animationString = new StringBuilder("Shapes:");
    boolean first = true;
    for (Map.Entry<String, IShape> e: animationsCopy.entrySet()) {
      if (!first) {
        animationString.append("\n\n").append(e.getValue().toString());
      } else {
        animationString.append("\n").append(e.getValue().toString());
        first = false;
      }
    }
    animationString.append("\n");
    for (Map.Entry<String, List<ITransformation>> a : transformations.entrySet()) {
      for (ITransformation transformation : a.getValue()) {
        animationString.append("\n").append(transformation.toString());
      }
    }
    return animationString.toString();
  }

  /**
   * A method to get current shapes in an animation in their time appropriate state at a specific
   * 'tick' in time.
   * @param tick a desired time for which to retrieve the current shapes in the animation.
   * @return a list of shapes in their time appropriate state
   */
  @Override
  public List<IShape> getShapesAtTick(int tick) {
    if (tick < 0) {
      throw new IllegalArgumentException("Tick cannot be negative");
    }
    if (tick == 0) {
      resetAnimation();
    }
    ArrayList<IShape> shapesAtTick = new ArrayList<>();

    for (Map.Entry<String, IShape> e : animationsCopy.entrySet()) {
      List<ITransformation> listAtTick = transformations.get(e.getKey());
      for (ITransformation t : listAtTick) {
        t.getTransformationsAtTick(e.getValue(), tick);
      }
      shapesAtTick.add(e.getValue());
    }
    if (tick == getFinalTick()) {
      resetAnimation();
    }
    return shapesAtTick;
  }

  /**
   * A method to return the animationsCopy hashmap.
   * @return a hashmap with shape id as the key and a shape object as the value
   */
  @Override
  public Map<String, IShape> getAnimationsCopy() {
    return this.animationsCopy;
  }

  /**
   * A method to revert all shapes in an animation back to their original state.
   * @return a linkedHashMap with each shape reverted back to its original form
   */
  @Override
  public Map<String, IShape> resetAnimation() {
    Map<String, IShape> copy = new LinkedHashMap<>();
    for (Map.Entry<String, IShape> e : animationsCopy.entrySet()) {
      String id = e.getKey();
      IShape shape = animation.get(id);
      if (shape != null && shape.getLocation() != null) {
        e.getValue().getLocation().movePoint(shape.getLocation().getX(),
                shape.getLocation().getY());
        e.getValue().setWidth(shape.getWidth());
        e.getValue().setHeight(shape.getHeight());
        e.getValue().setColor(shape.getR(), shape.getG(), shape.getB());
      }
    }
    return copy;
  }

  /**
   * A method to return the linkedHashMap for animation.
   * @return a linkedHashMap for animation
   */
  @Override
  public Map<String, IShape> getAnimation() {
    return this.animation;
  }

  /**
   * A method to return the transformations hashmap.
   * @return a hashmap with shape id as the key and a list of transformations for that shape as the
   *          value
   */
  @Override
  public Map<String, List<ITransformation>> getTransformations() {
    return this.transformations;
  }

  /**
   * A method to return the frame for the model which will specify dimensions for the animation
   * frame.
   * @return a frame object that contains coordinates for the animation frame.
   */
  public Frame getFrame() {
    return this.frame;
  }
}
