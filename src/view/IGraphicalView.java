package view;

import java.awt.Graphics;
import java.util.List;
import model.IShape;

/**
 * An interface for both classes of the graphical view - view.GraphicalViewFrame and
 * view.GraphicalViewPanel. The methods that are used in one class are instructed to throw an
 * UnsupportedOperationException.
 */
public interface IGraphicalView {


  /**
   * Utilizes the setModel to set the correct shapes to the frame. Then it repaints the frame.
   * @param shapes the list of shapes for this panel on the frame.
   */
  void currentFrame(List<IShape> shapes);

  /**
   * The paintComponent method to actually draw the shapes onto the screen.
   * Used in view.GraphicalViewPanel.
   * @param g the Graphics class (Java).
   */
  void paintComponent(Graphics g);

  /**
   * Sets the model to the correct list of shapes, using getShapesAtTick.
   * Used in view.GraphicalViewPanel.
   * @param model the list of Shape objects.
   */
  void setModel(List<IShape> model);
}
