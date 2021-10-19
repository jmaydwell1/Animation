package view;

import java.io.File;
import java.io.IOException;

/**
 * An interface for SVG objects.
 */
public interface ISVGView {

  /**
   * Creates the SVG using the file name.
   * @param fileName String object of the file name.
   * @return a File object.
   * @throws IOException if an error occurred and the SVG could not be created.
   */
  File createSVGFile(String fileName) throws IOException;

  /**
   * Adds an svg string to the file given.
   * @param file the File object.
   */
  void addToFile(File file);

}
