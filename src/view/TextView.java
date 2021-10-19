package view;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import model.IModel;

/**
 * A class to offer a textual representation of an animation.
 */
public class TextView implements ITextView {

  /**
   * A method to construct a view.TextView, and either create a file with a given file name, or
   * print animation description to the console if no file name is provided.
   * @param model the model to be represented as a text view
   * @param filename the desired name for the file to which the text view will be written
   * @throws IOException when the file cannot be created
   */
  public TextView(IModel model, String filename) throws IOException {
    if (filename.equals("")) {
      System.out.println(model.toString());
    }
    else {
      fillTextFile(createFile(filename), model);
    }
  }

  /**
   * A method to create a file with the given name.
   * @param filename the name to be assigned to the file
   * @return a file with the given name
   */
  public File createFile(String filename) {
    try {
      File mySVG = new File(filename);
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
   * A method to add a textual description of an animation to a given file.
   * @param file a file to which the description is written
   * @param model the model that is to be added in text format to the file
   */
  public void fillTextFile(File file, IModel model) {
    try {
      BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
      String modelString = model.toString();
      out.write(modelString);
    } catch (IOException e) {
      throw new IllegalStateException("Could not open file");
    }
  }
}
