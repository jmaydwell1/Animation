import model.ChangeColor;
import model.IModel;
import model.IShape;
import model.ITransformation;
import model.Model;
import model.Move;
import model.Oval;
import model.Point;
import model.Rectangle;
import model.Resize;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.NoSuchElementException;

import cs5004.animator.util.AnimationReader;
import view.SVGView;

import static org.junit.Assert.assertEquals;

/**
 * A testing class for all classes of the Model and Shape interfaces.
 */
public class AnimationTest {
  IShape o1;
  IShape r1;

  IModel model;

  @Before
  public void setUp() {
    model = new Model();

    o1 = new Oval(20, 30, 20, 15, 0, 0, 255);

    r1 = new Rectangle(5, 5, 20, 20, 0, 255, 255);

    model.addShape("o1", o1);
    model.addShape("r1", r1);
  }

  @Test
  public void testValidConstructor() {
    assertEquals("Shapes:\nName: o1\nType: oval\nCenter: (20, 30), X radius: 15, Y "
            + "radius: 20\nColor: (0, 0, 255)\n\nName: r1\nType: rectangle\nMin corner: (5, 5), "
            + "Width: 20, Height: 20\nColor: (0, 255, 255)\n", model.toString());
  }

  @Test
  public void testSortByAppearanceTime() {
    ITransformation t1 = new Move(8, 13, o1.getLocation(), new Point(9, 7), o1);
    ITransformation t2 = new Resize(9, 12, o1.getHeight(), o1.getWidth(), 2,
            7, o1);
    ITransformation t3 = new ChangeColor(10, 20, r1.getR(), r1.getG(), r1.getB(),
            255, 0, 0, r1);
    model.addTransformation("o1", t1);
    model.addTransformation("o1", t2);
    model.addTransformation("r1", t3);

    assertEquals("Shapes:\nName: o1\nType: oval\nCenter: (20, 30), X radius: 15, Y "
            + "radius: 20\nColor: (0, 0, 255)\n\nName: r1\nType: rectangle\nMin corner: (5, 5), "
            + "Width: 20, Height: 20\nColor: (0, 255, 255)\n\nShape o1 moves from (20, 30) to "
            + "(9, 7) from t = 8 to t = 13\nShape o1 scales from Width: 20, Height: 15 to Width: 7,"
            + " Height: 2 from t = 9 to t = 12\nShape r1 changes color from (0, 255, 255) to "
            + "(255, 0, 0) from t = 10 to t = 20", model.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSetNegWidth() throws IllegalArgumentException {
    r1.setWidth(-1);
    o1.setWidth(-4);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSetNegHeight() throws IllegalArgumentException {
    r1.setHeight(-1);
    o1.setHeight(-4);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSetInvalidRGB() throws IllegalArgumentException {
    r1.setR(-1);
    o1.setR(256);
    r1.setG(256);
    o1.setG(-100000);
    r1.setB(98348905);
    o1.setB(-2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddNullShape() throws IllegalArgumentException {
    model.addShape("nullShape", null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddNullID() throws IllegalArgumentException {
    model.addShape(null, o1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddEmptyID() throws IllegalArgumentException {
    model.addShape("", o1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddRepeatID() throws IllegalArgumentException {
    model.addShape("o1", o1);
    model.addShape("r1", r1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGetNullShape() throws IllegalArgumentException {
    model.getShape(null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testGetEmptyID() throws IllegalArgumentException {
    model.getShape("");
  }

  @Test (expected = NoSuchElementException.class)
  public void testGetNonExistentID() throws NoSuchElementException {
    model.getShape("circle5");
  }

  @Test (expected = IllegalArgumentException.class)
  public void setNegativeAppearance() throws IllegalArgumentException {
    o1.appears(-1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void setNegativeDisappearance() throws IllegalArgumentException {
    o1.disappears(-1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void setDisappearAfterAppear() throws IllegalArgumentException {
    o1.appears(10);
    o1.disappears(5);
  }

  @Test
  public void testGetLocation() {
    assertEquals("20, 30", o1.getLocation().toString());
    assertEquals("5, 5", r1.getLocation().toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullTransformation() {
    model.addTransformation("o1", null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testUniqueIDTransformation() {
    ITransformation t1 = new Move(2, 4, o1.getLocation(), new Point(90, 70), o1);
    model.addTransformation("o2", t1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNegativeGetShapesAtTick() throws IllegalArgumentException {
    model.getShapesAtTick(-1);
  }

  @Test
  public void testResetAnimation() {
    o1.getLocation().movePoint(40, 50);
    o1.setHeight(40);
    r1.setColor(133, 211, 13);
    r1.setWidth(30);
    model.resetAnimation();
    assertEquals("Shapes:\nName: o1\nType: oval\nCenter: (20, 30), X radius: 15, Y "
            + "radius: 20\nColor: (0, 0, 255)\n\nName: r1\nType: rectangle\nMin corner: (5, 5), "
            + "Width: 20, Height: 20\nColor: (0, 255, 255)\n", model.toString());
  }

  @Test
  public void testGetFinalTick() {
    ITransformation t1 = new Move(12, 24, o1.getLocation(), new Point(90, 70), o1);
    ITransformation t2 = new Resize(0, 33, o1.getHeight(), o1.getWidth(), 2,
            7, o1);
    ITransformation t3 = new ChangeColor(10, 20, r1.getR(), r1.getG(), r1.getB(),
            255, 0, 0, r1);
    ITransformation t4 = new Resize(8, 21, r1.getHeight(), r1.getWidth(), 7,
            9, r1);
    model.addTransformation("o1", t1);
    model.addTransformation("o1", t2);
    model.addTransformation("r1", t3);
    model.addTransformation("r1", t4);
    assertEquals(33, model.getFinalTick());
  }

  @Test
  public void testGetShapesAtTick() {
    ITransformation t1 = new Move(12, 24, o1.getLocation(), new Point(90, 70), o1);
    ITransformation t2 = new Resize(0, 33, o1.getHeight(), o1.getWidth(), 2,
            7, o1);
    ITransformation t3 = new ChangeColor(10, 20, r1.getR(), r1.getG(), r1.getB(),
            255, 0, 0, r1);
    ITransformation t4 = new Resize(8, 21, r1.getHeight(), r1.getWidth(), 7,
            9, r1);
    model.addTransformation("o1", t1);
    model.addTransformation("o1", t2);
    model.addTransformation("r1", t3);
    model.addTransformation("r1", t4);
    assertEquals("[Name: o1\nType: oval\nCenter: (37, 40), X radius: 14, Y radius: 9\n"
            + "Color: (0, 0, 255), Name: r1\nType: rectangle\nMin corner: (5, 5), Width: 14, "
            + "Height: 13\nColor: (127, 127, 127)]", model.getShapesAtTick(15).toString());
  }

  @Test
  public void testGetShapesAtTickBeforeTransformation() {
    ITransformation t1 = new Move(12, 24, o1.getLocation(), new Point(90, 70), o1);
    ITransformation t2 = new Resize(0, 33, o1.getHeight(), o1.getWidth(), 2,
            7, o1);
    ITransformation t3 = new ChangeColor(10, 20, r1.getR(), r1.getG(), r1.getB(),
            255, 0, 0, r1);
    ITransformation t4 = new Resize(8, 21, r1.getHeight(), r1.getWidth(), 7,
            9, r1);
    model.addTransformation("o1", t1);
    model.addTransformation("o1", t2);
    model.addTransformation("r1", t3);
    model.addTransformation("r1", t4);
    assertEquals("[Name: o1\nType: oval\nCenter: (20, 30), X radius: 18, Y radius: 13\n"
            + "Color: (0, 0, 255), Name: r1\nType: rectangle\nMin corner: (5, 5), Width: 20, "
            + "Height: 20\nColor: (0, 255, 255)]", model.getShapesAtTick(5).toString());
  }

  @Test
  public void testGetShapesAtTickAfterTransformation() {
    ITransformation t1 = new Move(12, 24, o1.getLocation(), new Point(90, 70), o1);
    ITransformation t2 = new Resize(0, 33, o1.getHeight(), o1.getWidth(), 2,
            7, o1);
    ITransformation t3 = new ChangeColor(10, 20, r1.getR(), r1.getG(), r1.getB(),
            255, 0, 0, r1);
    ITransformation t4 = new Resize(8, 21, r1.getHeight(), r1.getWidth(), 7,
            9, r1);
    model.addTransformation("o1", t1);
    model.addTransformation("o1", t2);
    model.addTransformation("r1", t3);
    model.addTransformation("r1", t4);
    assertEquals("[Name: o1\nType: oval\nCenter: (90, 70), X radius: 7, Y radius: 2\n"
            + "Color: (0, 0, 255), Name: r1\nType: rectangle\nMin corner: (5, 5), Width: 9, "
            + "Height: 7\nColor: (255, 0, 0)]", model.getShapesAtTick(50).toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testOverlappingStartTransformation() throws IllegalArgumentException {
    ITransformation t1 = new Resize(8, 37, o1.getHeight(), o1.getWidth(), 2,
            7, o1);
    ITransformation t2 = new Resize(0, 33, o1.getHeight(), o1.getWidth(), 2,
            7, o1);
    model.addTransformation("o1", t1);
    model.addTransformation("o1", t2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testOverlappingEndTransformation() throws IllegalArgumentException {
    ITransformation t1 = new Resize(8, 37, o1.getHeight(), o1.getWidth(), 2,
            7, o1);
    ITransformation t2 = new Resize(10, 33, o1.getHeight(), o1.getWidth(), 2,
            7, o1);
    model.addTransformation("o1", t1);
    model.addTransformation("o1", t2);
  }

  @Test
  public void testReader() throws IOException {
    model.createFrame(0, 0, 400, 400);
    ITransformation t1 = new Move(12, 24, o1.getLocation(), new Point(90, 70), o1);
    ITransformation t2 = new Resize(0, 33, o1.getHeight(), o1.getWidth(), 2,
            7, o1);
    ITransformation t3 = new ChangeColor(10, 20, r1.getR(), r1.getG(), r1.getB(),
            255, 0, 0, r1);
    ITransformation t4 = new Resize(8, 21, r1.getHeight(), r1.getWidth(), 7,
            9, r1);
    model.addTransformation("o1", t1);
    model.addTransformation("o1", t2);
    model.addTransformation("r1", t3);
    model.addTransformation("r1", t4);
    new SVGView(model, "sample.svg", 20);
    Path fileName = Path.of("sample.svg");
    String content = Files.readString(fileName, StandardCharsets.US_ASCII);
    assertEquals("<svg width=\"400\" height=\"400\" version=\"1.1\" xmlns=\"http://www.w3"
            + ".org/2000/svg\">\n<ellipse id=\"o1\" cx=\"20\" cy=\"30\" rx=\"15\" ry=\"20\" fill="
            + "\"rgb(0,0,255)\" visibility=\"visible\" >\n\t<animate attributeType=\"xml\" begin"
            + "=\"0.60s\" dur=\"0.60s\" attributeName=\"cx\" from=\"20\" to=\"90\" fill=\"freeze\""
            + " />\n\t<animate attributeType=\"xml\" begin=\"0.60s\" dur=\"0.60s\" attributeName="
            + "\"cy\" from=\"30\" to=\"70\" fill=\"freeze\" />\n\t<animate attributeType=\"xml\" "
            + "attributeName=\"rx\" from=\"20\" to=\"7\" begin=\"0.00s\" dur=\"1.65s\" fill=\""
            + "freeze\" />\n\t<animate attributeType=\"xml\" attributeName=\"ry\" from=\"15\" "
            + "to=\"2\" begin=\"0.00s\" dur=\"1.65s\" fill=\"freeze\" />\n</ellipse>\n<rect id=\""
            + "r1\" x=\"5\" y=\"5\" width=\"20\" height=\"20\" fill=\"rgb(0,255,255)\" visibility"
            + "=\"visible\" >\n\t<animate attributeType=\"xml\" attributeName=\"fill\" begin=\""
            + "0.50s\" dur=\"0.50s\" from=\"rgb(0,255,255)\" to=\"rgb(255,0,0)\" fill=\"freeze\" "
            + "/>\n\t<animate attributeType=\"xml\" attributeName=\"width\" from=\"20\" to=\"9\" "
            + "begin=\"0.40s\" dur=\"0.65s\" fill=\"freeze\" />\n\t<animate attributeType=\"xml\" "
            + "attributeName=\"height\" from=\"20\" to=\"7\" begin=\"0.40s\" dur=\"0.65s\" "
            + "fill=\"freeze\" />\n</rect>\n</svg>\n", content);
  }

  @Test
  public void testText() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("smalldemo.txt"));
    IModel myModel = AnimationReader.parseFile(reader, new Model.Builder());
    assertEquals("Shapes:\nName: R\nType: rectangle\nMin corner: (0, 130), Width: 50, "
                    + "Height: 100\nColor: (255, 0, 0)\n\nName: C\nType: oval\nCenter: (240, 0), X "
                    + "radius: 120, Y radius: 60\nColor: (0, 0, 255)\n\nShape R moves from (0, 130)"
                    + " to (100, 230) from t = 10 to t = 50\nShape R scales from Width: 50, Height:"
                    + " 100 to Width: 25, Height: 100 from t = 51 to t = 70\nShape R moves from "
                    + "(100, 230) to (0, 130) from t = 70 to t = 100\nShape C moves from (240, 0) "
                    + "to (240, 180) from t = 20 to t = 50\nShape C moves from (240, 180) to "
                    + "(240, 300) from t = 50 to t = 70\nShape C changes color from (0, 0, 255) "
                    + "to (0, 170, 85) from t = 50 to t = 70\nShape C changes color from "
                    + "(0, 170, 85) to (0, 255, 0) from t = 70 to t = 80",
            myModel.toString());
  }
}
