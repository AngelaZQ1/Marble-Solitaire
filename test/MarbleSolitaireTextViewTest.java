import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class is used to test the methods in the MarbleSolitaireTextView class.
 * It ensures that methods and constructors work as intended and that exceptions
 * are thrown when necessary.
 */
public class MarbleSolitaireTextViewTest {

  // test that the one arg constructor works
  @Test
  public void testOneArgConstructorValid() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model);
    assertEquals(7, model.getBoardSize());
    try {
      view.renderMessage("test");
    } catch (IOException e) {
      fail("Should not have thrown an IOEXception");
    }
  }

  // tests that the one arg constructor throws an IllegalArgumentException
  @Test(expected = IllegalArgumentException.class)
  public void testOneArgConstructorNullArgError() {
    new MarbleSolitaireTextView(null);
  }

  // tests that the two arg constructor works
  @Test
  public void testTwoArgConstructorValid() {
    Appendable destination = new StringBuilder();
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, destination);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", view.toString());
    assertEquals(7, model.getBoardSize());
    try {
      view.renderMessage("test");
    } catch (IOException e) {
      // do nothing
    }
    assertEquals("test", destination.toString());
  }

  // tests that the two arg constructor throws an IllegalArgumentException
  @Test
  public void testTwoArgConstructorNullArgError() {
    try {
      new MarbleSolitaireTextView(null, System.out);
      fail("constructor should have thrown an error from null model");
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      new MarbleSolitaireTextView(new EnglishSolitaireModel(), null);
      fail("constructor should have thrown an error from null appendable");
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try {
      new MarbleSolitaireTextView(null, null);
      fail("constructor should have thrown an error from null model and appendable");
    } catch (IllegalArgumentException e) {
      // do nothing
    }
  }

  // test the toString() method for english models
  @Test
  public void testToStringEnglishModel() {
    EnglishSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", view.toString());

    model.move(3, 1, 3, 3);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", view.toString());
  }

  @Test
  public void testToStringEuropeanModel() {
    MarbleSolitaireModel model = new EuropeanSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", view.toString());

    model.move(3, 1, 3, 3);
    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", view.toString());
  }

  // test that the English board successfully renders with a given appendable
  @Test
  public void testRenderBoardWithGivenAppendableEnglishBoard() {
    try {
      Appendable destination = new StringWriter();
      MarbleSolitaireTextView view = new MarbleSolitaireTextView(new EnglishSolitaireModel(),
              destination);
      view.renderBoard();
      assertEquals("    O O O\n" +
              "    O O O\n" +
              "O O O O O O O\n" +
              "O O O _ O O O\n" +
              "O O O O O O O\n" +
              "    O O O\n" +
              "    O O O", destination.toString());
    } catch (IOException e) {
      fail("An IOException should not have been thrown");
    }
  }

  // test that an IOException is thrown when rendering an English board
  @Test
  public void testRenderBoardCorruptAppendableEnglishBoard() {
    try {
      new MarbleSolitaireTextView(new EnglishSolitaireModel(), new CorruptAppendable())
              .renderBoard();
      fail("Should have thrown an IOException");
    } catch (IOException e) {
      // do nothing
    }
  }

  // test that the message successfully renders with an English board
  @Test
  public void testRenderMessageSuccess() {
    try {
      Appendable destination = new StringBuilder();
      MarbleSolitaireModel model = new EnglishSolitaireModel();
      MarbleSolitaireTextView view = new MarbleSolitaireTextView(model, destination);
      view.renderMessage("test message");
      assertEquals("test message", destination.toString());
      view.renderMessage("Score: " + model.getScore());
      assertEquals("test messageScore: 32", destination.toString());
    } catch (IOException e) {
      fail("An IOException should not have been thrown");
    }
  }

  // test that an IOException is thrown when rendering a message with an English board
  @Test
  public void testRenderMessageCorruptAppendable() {
    try {
      MarbleSolitaireTextView view = new MarbleSolitaireTextView(new EnglishSolitaireModel(),
              new CorruptAppendable());
      view.renderMessage("test");
      fail("Should have thrown an IOException");
    } catch (IOException e) {
      // do nothing
    }
  }

  // test that the European board successfully renders with a given appendable
  @Test
  public void testRenderBoardWithGivenAppendableEuropeanBoard() {
    try {
      Appendable destination = new StringWriter();
      MarbleSolitaireView view = new MarbleSolitaireTextView(new EuropeanSolitaireModel(),
              destination);
      view.renderBoard();
      assertEquals("    O O O\n" +
              "  O O O O O\n" +
              "O O O O O O O\n" +
              "O O O _ O O O\n" +
              "O O O O O O O\n" +
              "  O O O O O\n" +
              "    O O O", destination.toString());
    } catch (IOException e) {
      fail("An IOException should not have been thrown");
    }
  }

  // test that an IOException is thrown when rendering the European board
  @Test
  public void testRenderBoardCorruptAppendableEuropeanBoard() {
    try {
      new MarbleSolitaireTextView(new EuropeanSolitaireModel(), new CorruptAppendable())
              .renderBoard();
      fail("An IOException should have been thrown");
    } catch (IOException e) {
      // do nothing
    }
  }

  // test that the message successfully renders with a European board
  @Test
  public void testRenderMessageSuccessEuropeanBoard() {
    try {
      Appendable destination = new StringBuilder();
      MarbleSolitaireModel model = new EuropeanSolitaireModel();
      MarbleSolitaireView view = new MarbleSolitaireTextView(model, destination);
      view.renderMessage("test message");
      assertEquals("test message", destination.toString());
    } catch (IOException e) {
      fail("An IOException should not have been thrown");
    }
  }

  // test that an IOException is thrown when rendering a message with a European board
  @Test
  public void testRenderMessageCorruptAppendableEuropeanBoard() {
    try {
      new MarbleSolitaireTextView(new EuropeanSolitaireModel(), new CorruptAppendable())
              .renderMessage("test");
      fail("An IOException should have been thrown");
    } catch (IOException e) {
      // do nothing
    }
  }
}
