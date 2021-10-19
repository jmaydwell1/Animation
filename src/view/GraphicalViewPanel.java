package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import javax.swing.JPanel;
import model.IShape;
import model.ShapeType;


/**
 * A class for the panel used in the view.GraphicalViewFrame, which paints the shapes from the model
 * onto the screen for the user.
 */
public class GraphicalViewPanel extends JPanel implements IGraphicalView {
  private List<IShape> m;


  /**
   * Constructs the panel to add to the frame.
   * @param w the given width.
   * @param h the given height.
   * @param model the list of Shape objects needed for this panel.
   */
  public GraphicalViewPanel(int w, int h, List<IShape> model) {
    super(true);
    this.m = model;
    this.setLocation(0,0);
    this.setSize(w,h);
  }

  /**
   * The paintComponent method to actually draw the shapes onto the screen.
   * @param g the Graphics class (Java).
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D gd = (Graphics2D) g;
    for (IShape s : m) {
      gd.setColor(new Color(s.getR(), s.getG(), s.getB()));

      if (s.getType() == ShapeType.OVAL) {
        gd.fillOval( s.getLocation().getX(),  s.getLocation().getY(),
                s.getWidth(),  s.getHeight());
      }
      else if (s.getType() == ShapeType.RECTANGLE) {
        gd.fillRect(s.getLocation().getX(), s.getLocation().getY(),
                s.getWidth(), s.getHeight());
      }
    }
  }

  /**
   * Sets the model to the correct list of shapes, using getShapesAtTick.
   * @param model the list of Shape objects.
   */
  @Override
  public void setModel(List<IShape> model) {
    this.m = model;
  }


  /**
   * Utilizes the setModel to set the correct shapes to the frame. Then it repaints the frame.
   * Used in view.GraphicalViewFrame.
   * @param shapes the list of shapes for this panel on the frame.
   */
  public void currentFrame(List<IShape> shapes) {
    throw new UnsupportedOperationException();
  }



}
