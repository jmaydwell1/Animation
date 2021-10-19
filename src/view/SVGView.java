package view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import model.ChangeColor;
import model.Frame;
import model.IModel;
import model.IShape;
import model.ITransformation;
import model.Move;
import model.Rectangle;
import model.Resize;
import model.ShapeType;
import model.TransformationType;

/**
 * A class to create an svg file for an animation model.
 */
public class SVGView implements ISVGView {
  IModel model;
  static double speed;

  /**
   * A class to construct an svg file containing an animation based off the given model.
   * @param model the model to be added to the svg file
   * @param fileName the name of the file to be added to
   * @param speed the speed at which the animation will run
   * @throws IOException when the file cannot be created
   * @throws IllegalArgumentException when speed is less than or equal to zero
   */
  public SVGView(IModel model, String fileName, double speed) throws IOException,
          IllegalArgumentException {
    if (speed <= 0) {
      throw new IllegalArgumentException("Cannot provide speed less than 0");
    }
    this.speed = speed;
    this.model = model;
    addToFile(createSVGFile(fileName));
  }

  /**
   * Creates the SVG using the file name.
   * @param fileName String object of the file name.
   * @return a File object.
   * @throws IOException if an error occurred and the SVG could not be created.
   */
  @Override
  public File createSVGFile(String fileName) throws IOException {
    try {
      File mySVG = new File(fileName);
      if (mySVG.createNewFile()) {
        System.out.println("New file created");
        return mySVG;
      }
      else {
        throw new IllegalArgumentException("File with given name exists");
      }
    } catch (IOException e) {
      throw new IllegalStateException("File not created");
    }
  }

  /**
   * Adds an svg string to the file given.
   * @param file the File object.
   */
  @Override
  public void addToFile(File file) {
    Map<String, List<ITransformation>> allShapes = model.getTransformations();
    Frame myFrame = model.getFrame();

    try {
      BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
      String frameInfo = String.format("<svg width=\"%d\" height=\"%d\" version=\"1.1\""
                      + " xmlns=\"http://www.w3.org/2000/svg\">\n", myFrame.getWidth(),
              myFrame.getHeight());
      String shapeInfo;
      out.write(frameInfo);
      for (Map.Entry<String, List<ITransformation>> e : allShapes.entrySet()) {
        IShape s = model.getShape(e.getKey());
        if (s.getType() == ShapeType.OVAL) {
          shapeInfo = String.format("<ellipse id=\"%s\" cx=\"%d\" cy=\"%d\" rx=\"%d\" ry=\"%d\""
                          + " fill=\"rgb(%d,%d,%d)\" visibility=\"visible\" >\n",
                  s.getID(), s.getLocation().getX(), s.getLocation().getY(), s.getWidth(),
                  s.getHeight(), s.getR(), s.getG(), s.getB());
        } else {
          shapeInfo = String.format("<rect id=\"%s\" x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" "
                          + "fill=\"rgb(%d,%d,%d)\" visibility=\"visible\" >\n", s.getID(),
                  s.getLocation().getX(), s.getLocation().getY(), s.getWidth(), s.getHeight(),
                  s.getR(), s.getG(), s.getB());
        }
        out.write(shapeInfo);
        for (ITransformation transformation : e.getValue()) {
          if (transformation.getType() == TransformationType.MOVE) {
            if (s.getType() == ShapeType.OVAL) {
              int difference = transformation.getEndTime() - transformation.getStartTime();
              String moveX = String.format("\t<animate attributeType=\"xml\" begin=\"%.2fs\" "
                              + "dur=\"%.2fs\" attributeName=\"cx\" from=\"%d\" to=\"%d\" "
                              + "fill=\"freeze\" />\n",
                      (transformation.getStartTime() / speed),
                      (difference / speed),
                      ((Move) transformation).getLocationStart().getX(),
                      ((Move) transformation).getLocationEnd().getX());
              out.write(moveX);
              String moveY = String.format("\t<animate attributeType=\"xml\" begin=\"%.2fs\" "
                              + "dur=\"%.2fs\" attributeName=\"cy\" from=\"%d\" to=\"%d\" "
                              + "fill=\"freeze\" />\n",
                      (transformation.getStartTime() / speed),
                      (difference / speed),
                      ((Move) transformation).getLocationStart().getY(),
                      ((Move) transformation).getLocationEnd().getY());
              out.write(moveY);
            }
            else {
              int difference = transformation.getEndTime() - transformation.getStartTime();
              String moveX = String.format("\t<animate attributeType=\"xml\" begin=\"%.2fs\" dur="
                              + "\"%.2fs\" attributeName=\"x\" from=\"%d\" to=\"%d\" fill=\""
                              + "freeze\" />\n",
                      (transformation.getStartTime() / speed),
                      (difference / speed),
                      ((Move) transformation).getLocationStart().getX(),
                      ((Move) transformation).getLocationEnd().getX());
              out.write(moveX);
              String moveY = String.format("\t<animate attributeType=\"xml\" begin=\"%.2fs\" dur="
                              + "\"%.2fs\" attributeName=\"y\" from=\"%d\" to=\"%d\" fill=\"freeze"
                              + "\" />\n",
                      (transformation.getStartTime() / speed),
                      (difference / speed),
                      ((Move) transformation).getLocationStart().getY(),
                      ((Move) transformation).getLocationEnd().getY());
              out.write(moveY);
            }
          }
          else if (transformation.getType() == TransformationType.CHANGECOLOR) {
            int difference = transformation.getEndTime() - transformation.getStartTime();
            String changeColor = String.format("\t<animate attributeType=\"xml\" attributeName"
                            + "=\"fill\" begin=\"%.2fs\" dur=\"%.2fs\" from=\"rgb(%d,%d,%d)\" to="
                            + "\"rgb(%d,%d,%d)\" fill=\"freeze\" />\n",
                    (transformation.getStartTime() / speed),
                    (difference / speed),
                    ((ChangeColor) transformation).getStartR(),
                    ((ChangeColor) transformation).getStartG(),
                    ((ChangeColor) transformation).getStartB(),
                    ((ChangeColor) transformation).getEndR(),
                    ((ChangeColor) transformation).getEndG(),
                    ((ChangeColor) transformation).getEndB());
            out.write(changeColor);
          }
          else {
            if (s.getType() == ShapeType.RECTANGLE) {
              int difference = transformation.getEndTime() - transformation.getStartTime();
              String changeWidth = String.format("\t<animate attributeType=\"xml\" attributeName"
                              + "=\"width\" from=\"%d\" to=\"%d\" begin=\"%.2fs\" dur=\"%.2fs\" "
                              + "fill=\"freeze\" />\n", ((Resize) transformation).getStartWidth(),
                      ((Resize) transformation).getEndWidth(),
                      (transformation.getStartTime() / speed),
                      (difference / speed));
              out.write(changeWidth);
              String changeHeight = String.format("\t<animate attributeType=\"xml\" attributeName"
                              + "=\"height\" from=\"%d\" to=\"%d\" begin=\"%.2fs\" dur=\"%.2fs\" "
                              + "fill=\"freeze\" />\n", ((Resize) transformation).getStartHeight(),
                      ((Resize) transformation).getEndHeight(),
                      (transformation.getStartTime() / speed),
                      (difference / speed));
              out.write(changeHeight);
            } else {
              int difference = transformation.getEndTime() - transformation.getStartTime();
              String changeRX = String.format("\t<animate attributeType=\"xml\" attributeName"
                              + "=\"rx\" from=\"%d\" to=\"%d\" begin=\"%.2fs\" dur=\"%.2fs\" "
                              + "fill=\"freeze\" />\n", ((Resize) transformation).getStartWidth(),
                      ((Resize) transformation).getEndWidth(),
                      (transformation.getStartTime() / speed),
                      (difference / speed));
              out.write(changeRX);
              String changeRY = String.format("\t<animate attributeType=\"xml\" attributeName"
                              + "=\"ry\" from=\"%d\" to=\"%d\" begin=\"%.2fs\" dur=\"%.2fs\" "
                              + "fill=\"freeze\" />\n", ((Resize) transformation).getStartHeight(),
                      ((Resize) transformation).getEndHeight(),
                      (transformation.getStartTime() / speed),
                      (difference / speed));
              out.write(changeRY);
            }
          }
        }
        if (s instanceof Rectangle) {
          String endRect = "</rect>\n";
          out.write(endRect);
        } else {
          String endEllipse = "</ellipse>\n";
          out.write(endEllipse);
        }
      }
      String endSVG = "</svg>\n";
      out.write(endSVG);
      out.close();
    } catch (IOException e) {
      throw new IllegalStateException("Could not open file");
    }
  }
}
