package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import model.IModel;
import model.IShape;

/**
 * A class for the frame of the editor view that displays the model to the user
 * in a visual way. To do so, it utilizes JFrame and implements its interface, view.IEditorView.
 */
public class EditorView extends JFrame implements IEditorView {
  private JButton playButton;
  private JButton pauseButton;
  private JButton restartButton;
  private JButton speedUp;
  private JButton speedDown;
  private JCheckBox loop;
  private GraphicalViewPanel animationPanel;

  /**
   * A method to construct the editor view frame that is initialized to the height and width of
   * a given animation frame.
   * @param w integer width of the frame
   * @param h integer height of the frame
   * @param model a model to be displayed in the frame
   */
  public EditorView(int w, int h, IModel model) {
    super("Shapes Painting");
    this.setBounds(0, 0, w, h + 100);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    // Animation Panel with the model and its shapes, transformations, etc
    animationPanel = new GraphicalViewPanel(w, h, model.getShapesAtTick(0));
    animationPanel.setPreferredSize(new Dimension(w,h));
    animationPanel.setVisible(true);


    // Scroll panel to place scroll pane into
    JPanel scrollPanel = new JPanel();
    scrollPanel.setLayout(new BorderLayout());

    // JScrollPane to actually enable scrolling when resizing
    JScrollPane jScrollPane = new JScrollPane(animationPanel);
    jScrollPane.setPreferredSize(new Dimension(w,h));
    jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    // add scroll pane to scroll panel
    scrollPanel.add(jScrollPane, BorderLayout.CENTER);

    // add scroll panel to the content pane (this) & make visible
    this.getContentPane().add(scrollPanel, BorderLayout.CENTER);
    this.setVisible(true);


    // bar panel for the play, pause & restart buttons
    JPanel barPanel = new JPanel();
    barPanel.setLayout(new GridLayout());
    barPanel.setSize(w,100);
    barPanel.setBorder(new LineBorder(Color.BLACK, 2));
    this.playButton = new JButton("▶");
    this.pauseButton = new JButton("Pause");
    this.restartButton = new JButton("Restart");
    this.speedUp = new JButton("Speed +");
    this.speedDown = new JButton("Speed -");
    this.loop = new JCheckBox("Loop", false);
    restartButton.setActionCommand("restart");

    playButton.setVisible(true);
    pauseButton.setVisible(true);
    restartButton.setVisible(true);
    speedUp.setVisible(true);
    speedDown.setVisible(true);
    loop.setVisible(true);

    barPanel.add(playButton);
    barPanel.add(pauseButton);
    barPanel.add(restartButton);
    barPanel.add(speedUp);
    barPanel.add(speedDown);
    barPanel.add(loop);

    this.add(barPanel, BorderLayout.SOUTH);
    this.setVisible(true);
    this.pack();
    this.revalidate();
  }

  /**
   * A method to generate the current frame in an animation.
   * @param shapes a list of shapes in a transformation
   */
  @Override
  public void currentFrame(List<IShape> shapes) {
    this.animationPanel.setModel(shapes);
    this.repaint();
  }

  /**
   * A method to get the pause button.
   * @return an instance of a pause button
   */
  @Override
  public JButton getPauseButton() {
    return pauseButton;
  }

  /**
   * A method to get the play button.
   * @return an instance of a play button
   */
  @Override
  public JButton getPlayButton() {
    return playButton;
  }

  /**
   * A method to get the restart button.
   * @return an instance of a restart button
   */
  @Override
  public JButton getRestartButton() {
    return restartButton;
  }

  /**
   * A method to get the speed up button.
   * @return an instance of a speed up button
   */
  @Override
  public JButton getSpeedUpButton() {
    return speedUp;
  }

  /**
   * A method to get the speed up button.
   * @return an instance of a speed up button
   */
  @Override
  public JButton getSpeedDownButton() {
    return speedDown;
  }

  /**
   * A method to get the loop toggle check box.
   * @return an instance of a loop toggle check box
   */
  @Override
  public JCheckBox getLoopToggle() {
    return loop;
  }
}


