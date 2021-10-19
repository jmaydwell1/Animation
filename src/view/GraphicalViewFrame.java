package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import model.IModel;
import model.IShape;

/**
 * A class for the frame of the graphical view that displays the model to the user
 * in a visual way. To do so, it utilizes JFrame and implements its interface, view.IGraphicalView.
 */
public class GraphicalViewFrame extends JFrame implements IGraphicalView {
  private GraphicalViewPanel animationPanel;


  /**
   * Constructor the frame, which adds the necessary panels and sets the width and height.
   * @param w the given width of the frame.
   * @param h the given height of the frame.
   * @param model the model with all information to display.
   */
  public GraphicalViewFrame(int w, int h, IModel model) {
    super("Shapes Painting");
    this.setSize(w, h);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    setLayout(new BorderLayout());

    animationPanel = new GraphicalViewPanel(w, h, model.getShapesAtTick(0));
    animationPanel.setPreferredSize(new Dimension(w,h)); // change this somehow
    animationPanel.setBorder(new EmptyBorder(4, 4, 4, 4));
    animationPanel.setVisible(true);

    JScrollPane jScrollPane = new JScrollPane(animationPanel);
    jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    this.add(jScrollPane);
    this.setVisible(true);
  }

  /**
   * Utilizes the setModel to set the correct shapes to the frame. Then it repaints the frame.
   * @param shapes the list of shapes for this panel on the frame.
   */
  @Override
  public void currentFrame(List<IShape> shapes) {
    this.animationPanel.setModel(shapes);
    this.repaint();
  }

  /**
   * The paintComponent method to actually draw the shapes onto the screen.
   * Used in view.GraphicalViewPanel.
   * @param g the Graphics class (Java).
   */
  @Override
  public void paintComponent(Graphics g) {
    throw new UnsupportedOperationException();
  }

  /**
   * Sets the model to the correct list of shapes, using getShapesAtTick.
   * Used in view.GraphicalViewPanel.
   * @param model the list of Shape objects.
   */
  @Override
  public void setModel(List<IShape> model) {
    throw new UnsupportedOperationException();
  }
}