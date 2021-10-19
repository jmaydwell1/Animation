package view;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import model.IShape;

/**
 * An interface containing all method signatures shared by classes that implement IEditorView.
 */
public interface IEditorView {
  /**
   * A method to generate the current frame in an animation.
   * @param shapes a list of shapes in a transformation
   */
  void currentFrame(List<IShape> shapes);

  /**
   * A method to get the pause button.
   * @return an instance of a pause button
   */
  JButton getPauseButton();

  /**
   * A method to get the play button.
   * @return an instance of a play button
   */
  JButton getPlayButton();

  /**
   * A method to get the restart button.
   * @return an instance of a restart button
   */
  JButton getRestartButton();

  /**
   * A method to get the speed up button.
   * @return an instance of a speed up button
   */
  JButton getSpeedUpButton();

  /**
   * A method to get the speed up button.
   * @return an instance of a speed up button
   */
  JButton getSpeedDownButton();

  /**
   * A method to get the loop toggle check box.
   * @return an instance of a loop toggle check box
   */
  JCheckBox getLoopToggle();
}
