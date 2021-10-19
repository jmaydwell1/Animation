package controller;

import cs5004.animator.util.AnimationReader;

import java.util.Arrays;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import model.IModel;
import model.Model;
import view.EditorView;
import view.GraphicalViewFrame;
import view.IEditorView;
import view.SVGView;
import view.TextView;



/**
 * This class creates the easy animator creating shapes and movements of the shapes.
 */
public class EasyAnimator {

  /**
   * Driver method.
   * @param args provided arguments for the driver function
   */
  private static String inputString = "";
  private static String outputString = "";
  private static String viewType = "";
  private static String playSpeed = "1";
  private static double playSpeedDouble = Double.parseDouble(playSpeed);
  private static long playSpeedLong = Long.parseLong(playSpeed);
  private static final String[] acceptableViewTypes = {"text", "visual", "svg", "playback"};
  private static final List<String> acceptableViewTypesArray = Arrays.asList(acceptableViewTypes);
  private static boolean looping;

  /**
   * A method to construct a main driver function for the animation.
   * @param args arguments provided to driver
   * @throws FileNotFoundException if the given file does not exist
   * @throws IOException when a file cannot be accessed
   */
  public static void main(String [] args) throws FileNotFoundException, IOException {
    int count = 0;

    List<String> argsArray = Arrays.asList(args);

    if (args.length < 4) {
      JOptionPane.showMessageDialog(null,
              "Sorry, not enough arguments specified.");
    }
    else {
      for (String s: argsArray) {
        if (s.equals("-in")) {
          int inputIndex = argsArray.indexOf(s);
          try {
            inputString = argsArray.get(inputIndex + 1);
          } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null,
                    "No supplied argument to input parameter!");
          }
          if (inputString.equals("") ) {
            throw new IllegalArgumentException("Cannot have empty string");
          }
        }

        if (s.equals("-out")) {
          int outIndex = argsArray.indexOf(s);

          try {
            outputString = argsArray.get(outIndex + 1);
          } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null,
                    "No supplied argument to output parameter!");
          }
        }

        if (s.equals("-view")) {
          int viewIndex = argsArray.indexOf(s);
          try {
            viewType = argsArray.get(viewIndex + 1);
          } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null,
                    "No supplied argument to view parameter!");
          }
          if (!acceptableViewTypesArray.contains(viewType)) {
            JOptionPane.showMessageDialog(null,
                    "Your view parameter is not one of the allowed options. Try svg, text,"
                            + "or visual.");
            throw new IllegalArgumentException("View type does not conform.");
          }
        }

        if (s.equals("-speed")) {
          int speedIndex = argsArray.indexOf(s);
          try {
            playSpeed = argsArray.get(speedIndex + 1);
          } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null,
                    "No supplied argument to speed parameter!");
          }
        }
      }
      BufferedReader reader = new BufferedReader(new FileReader(inputString));
      IModel yourModel = AnimationReader.parseFile(reader, new Model.Builder());

      if (viewType.equals("text")) {
        new TextView(yourModel, outputString);
      }
      else if (viewType.equals("svg")) {
        new SVGView(yourModel, outputString, playSpeedDouble);
        return;
      }
      else if (viewType.equals("visual")) {
        GraphicalViewFrame gvf = new GraphicalViewFrame(yourModel.getFrame().getWidth(),
                yourModel.getFrame().getHeight(), yourModel);
        gvf.currentFrame(yourModel.getShapesAtTick(0));
        while (count > -1) {
          gvf.currentFrame(yourModel.getShapesAtTick(count));
          count++;

          try {
            Thread.sleep(1000 / playSpeedLong);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        return;
      }
      else if (viewType.equals("playback")) {
        IEditorView view = new EditorView(yourModel.getFrame().getWidth(),
                yourModel.getFrame().getHeight(), yourModel);

        new Controller(view, yourModel, playSpeed);
      }
    }
  }
}



