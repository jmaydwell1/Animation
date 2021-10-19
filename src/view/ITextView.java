package view;

import java.io.File;
import model.IModel;

/**
 * An interface for the class of the text view - view.TextView.
 */
public interface ITextView {

  /**
   * A method to create a file with the given name.
   * @param filename the name to be assigned to the file
   * @return a file with the given name
   */
  File createFile(String filename);

  /**
   * A method to add a textual description of an animation to a given file.
   * @param file a file to which the description is written
   * @param model the model that is to be added in text format to the file
   */
  void fillTextFile(File file, IModel model);

}
