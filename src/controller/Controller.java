package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import model.IModel;
import view.EditorView;
import view.IEditorView;

/**
 * A controller class that takes in a given view and model and coordinates response in the model
 * and the view based on user interaction.
 */
public class Controller {
  private final IEditorView view;
  private final IModel model;
  private final Timer timer;
  private int tick = 0;
  private int[] predefinedSpeeds = {50, 75, 100, 150, 200, 300, 400, 600, 800, 1200, 1600, 2400,
      3200, 4800, 6400};
  private int currentSpeed = 7;
  boolean loopToggle;

  /**
   * A method to construct a controller, initialized to a given view, model, and user specified
   * play speed.
   * @param view a view of type IView to be displayed to the user
   * @param model a model of type IModel from which the animation is generated
   * @param playSpeed user specified play speed for the animation
   */
  public Controller(IEditorView view, IModel model, String playSpeed) {
    int speed = Integer.parseInt(playSpeed);
    int i;
    for (i = 0; i < predefinedSpeeds.length; i++) {
      predefinedSpeeds[i] = predefinedSpeeds[i] / speed;
    }
    this.view = view;
    this.model = model;
    this.loopToggle = false;
    this.timer = new Timer(predefinedSpeeds[currentSpeed], new ActionListener() {
      /**
       * Invoked when an action occurs.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        if (loopToggle && tick > model.getFinalTick()) {
          tick = 0;
        }
        view.currentFrame(model.getShapesAtTick(tick));
        tick++;
      }
    });
    this.timer.start();
    this.setActionListeners();
  }

  /**
   * A method to set up action listeners for an animation.
   */
  public void setActionListeners() {
    EditorView edview = (EditorView) this.view;
    /**
     * A class to listen and respond to user clicks on the pause button.
     */
    class PauseButtonListener implements ActionListener {
      Timer timer;

      /**
       * A method to listen for user clicks on the pause button.
       * @param timer a timer for an animation
       */
      public PauseButtonListener(Timer timer) {
        this.timer = timer;
      }

      /**
       * Invoked when an action occurs.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        timer.stop();
      }
    }

    edview.getPauseButton().addActionListener(new PauseButtonListener(timer));

    /**
     * A class to listen and respond to user clicks on the play button.
     */
    class PlayButtonListener implements ActionListener {
      final Timer timer;

      /**
       * A method to listen for mouse clicks on the play button from the user.
       * @param timer a timer for an animation
       */
      public PlayButtonListener(Timer timer) {
        this.timer = timer;
      }

      /**
       * Invoked when an action occurs.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        timer.start();
      }
    }

    edview.getPlayButton().addActionListener(new PlayButtonListener(timer));

    /**
     * A class to listen and respond to user clicks on the speed up button.
     */
    class SpeedUp implements ActionListener {
      Timer timer;

      /**
       * A method to listen for clicks on the speed up button.
       * @param timer a timer for an animation
       */
      public SpeedUp(Timer timer) {
        this.timer = timer;
      }

      /**
       * Invoked when an action occurs.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        if (currentSpeed == 0) {
          return;
        }
        timer.setDelay(predefinedSpeeds[currentSpeed - 1]);
        currentSpeed--;
      }
    }

    edview.getSpeedUpButton().addActionListener(new SpeedUp(timer));

    /**
     * A class to listen and respond to user clicks on the speed down button.
     */
    class SpeedDown implements ActionListener {
      Timer timer;

      /**
       * A method to listen for clicks on the speed down button.
       * @param timer a timer for an animation
       */
      public SpeedDown(Timer timer) {
        this.timer = timer;
      }

      /**
       * Invoked when an action occurs.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        if (currentSpeed == predefinedSpeeds.length - 1) {
          return;
        }
        timer.setDelay(predefinedSpeeds[currentSpeed + 1]);
        currentSpeed++;
      }
    }

    edview.getSpeedDownButton().addActionListener(new SpeedDown(timer));

    /**
     * A class to listen and respond to whether a toggle box is checked on or off.
     */
    class LoopButtonListener implements ActionListener {
      Timer timer;

      /**
       * A method to listen to whether the loop box is toggled on or off.
       * @param timer a timer for an animation
       */
      public LoopButtonListener(Timer timer) {
        this.timer = timer;
      }

      /**
       * Invoked when an action occurs.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        loopToggle = !loopToggle;
      }
    }

    edview.getLoopToggle().addActionListener(new LoopButtonListener(timer));

    class RestartButtonListener implements ActionListener {
      Timer timer;

      /**
       * A class to listen for user clicks on the restart button.
       * @param timer a timer for an animation
       */
      public RestartButtonListener(Timer timer) {
        this.timer = timer;
      }

      /**
       * Invoked when an action occurs.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        tick = 0;
        timer.restart();
        view.currentFrame(model.getShapesAtTick(model.getFinalTick()));
      }
    }

    edview.getRestartButton().addActionListener(new RestartButtonListener(timer));
  }
}
