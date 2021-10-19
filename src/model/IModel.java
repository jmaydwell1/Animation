package model;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


/**
 * An interface that represents the operations offered by animation objects.
 */
public interface IModel {

  void createFrame(int x, int y, int width, int height);

  /**
   * A method to add a shape object to an animation.
   * @param id the identifier for the shape
   * @param shape a shape object
   * @throws IllegalArgumentException when question is null, id is null, id is an empty string,
   *          id is not unique, appear or disappear time is less than 0, or shape disappears
   *          before it appears
   */
  void addShape(String id, IShape shape);

  /**
   * Will be implemented in a later stage of this project.
   * @return List of current shapes and their location at a given frame
   */
  List<IShape> getShapesAtTick(int tick);

  /**
   * A method to get a shape with a given identifier from the current animation.
   * @param id a string identifier for the desired shape
   * @return the shape with the given identifier
   * @throws IllegalArgumentException when the id is null or an empty string
   * @throws NoSuchElementException when the element with the given id cannot be found in the list
   */
  IShape getShape(String id);

  /**
   * A method to add a transformation to a shape with the specified id.
   * @param id a string identifier for a shape
   * @param t a transformation object to add to a shape
   */
  void addTransformation(String id, ITransformation t);

  /**
   * A method to return the animationsCopy hashmap.
   * @return the animationsCopy hashmap
   */
  Map<String, IShape> getAnimationsCopy();

  /**
   * A method to return the transformations hashmap.
   * @return the transformations hashmap
   */
  Map<String, List<ITransformation>> getTransformations();

  /**
   * A method to return the frame for an animation model.
   * @return the frame for the model
   */
  Frame getFrame();

  /**
   * A method to determine the last tick in an animation.
   * @return an integer representing last tick in an animation
   */
  int getFinalTick();

  /**
   * A method to return the linkedHashMap for animation.
   * @return a linkedHashMap for animation
   */
  Map<String, IShape> getAnimation();

  /**
   * A method to revert all shapes in an animation back to their original state.
   * @return a linkedHashMap with each shape reverted back to its original form
   */
  Map<String, IShape> resetAnimation();
}