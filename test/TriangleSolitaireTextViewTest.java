import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class is used to test the methods in the TriangleSolitaireTextView class.
 * It ensures that all constructors and methods work as intended and throw exceptions
 * where necessary.
 */
public class TriangleSolitaireTextViewTest {

  @Test
  public void testOneArgConstructorNoError() {
    MarbleSolitaireModel model = new TriangleSolitaireModel();
    MarbleSolitaireView view = new TriangleSolitaireTextView(model);
    assertEquals(5, model.getBoardSize());
    try {
      view.renderMessage("test");
    } catch (IOException e) {
      fail("Should not have thrown an IOEXception");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOneArgConstructorThrowsIllegalArgumentException() {
    new TriangleSolitaireTextView(null);
  }

  @Test
  public void testTwoArgConstructorNoError() {
    Appendable destination = new StringBuilder();
    MarbleSolitaireModel model = new TriangleSolitaireModel();
    TriangleSolitaireTextView view = new TriangleSolitaireTextView(model, destination);
    assertEquals("    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O", view.toString());
    assertEquals(5, model.getBoardSize());
    try {
      view.renderMessage("test");
    } catch (IOException e) {
      // do nothing
    }
    assertEquals("test", destination.toString());
  }

  @Test
  public void testTwoArgConstructorThrowsIllegalArgumentException() {
    try {
      new TriangleSolitaireTextView(null, System.out);
      fail("constructor should have thrown an error from null model");
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      new TriangleSolitaireTextView(new TriangleSolitaireModel(), null);
      fail("constructor should have thrown an error from null appendable");
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      new TriangleSolitaireTextView(null, null);
      fail("constructor should have thrown an error from null model and appendable");
    } catch (IllegalArgumentException e) {
      // do nothing
    }
  }

  @Test
  public void testToString() {
    MarbleSolitaireModel model = new TriangleSolitaireModel();
    MarbleSolitaireView view = new TriangleSolitaireTextView(model);
    assertEquals("    _\n"
            + "   O O\n"
            + "  O O O\n"
            + " O O O O\n"
            + "O O O O O", view.toString());

    model.move(2, 0, 0, 0);
    assertEquals("    O\n"
            + "   _ O\n"
            + "  _ O O\n"
            + " O O O O\n"
            + "O O O O O", view.toString());
  }

  // test that the board successfully renders with a given appendable
  @Test
  public void testRenderBoardWithGivenAppendable() {
    try {
      Appendable destination = new StringWriter();
      MarbleSolitaireView view = new TriangleSolitaireTextView(new TriangleSolitaireModel(),
              destination);
      view.renderBoard();
      assertEquals("    _\n"
              + "   O O\n"
              + "  O O O\n"
              + " O O O O\n"
              + "O O O O O", destination.toString());
    } catch (IOException e) {
      fail("An IOException should not have been thrown");
    }
  }

  // test that an IOException is thrown when rendering the board
  @Test
  public void testRenderBoardCorruptAppendable() {
    try {
      new TriangleSolitaireTextView(new TriangleSolitaireModel(), new CorruptAppendable())
              .renderBoard();
      fail("An IOException should have been thrown");
    } catch (IOException e) {
      // do nothing
    }
  }

  // test that the message successfully renders
  @Test
  public void testRenderMessageSuccess() {
    try {
      Appendable destination = new StringBuilder();
      MarbleSolitaireModel model = new TriangleSolitaireModel();
      MarbleSolitaireView view = new TriangleSolitaireTextView(model, destination);
      view.renderMessage("test message");
      assertEquals("test message", destination.toString());
    } catch (IOException e) {
      fail("An IOException should not have been thrown");
    }
  }

  // test that an IOException is thrown when rendering a message
  @Test
  public void testRenderMessageCorruptAppendable() {
    try {
      new TriangleSolitaireTextView(new TriangleSolitaireModel(), new CorruptAppendable())
              .renderMessage("test");
      fail("An IOException should have been thrown");
    } catch (IOException e) {
      // do nothing
    }
  }

}
