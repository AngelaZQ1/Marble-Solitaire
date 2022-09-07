import org.junit.Test;

import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.MockModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.MockView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class represents a way of testing the MarbleSolitaireControllerImpl class by using
 * a mock model and view. This class ensures that all constructors and methods work as
 * intended and throw exceptions where necessary.
 */
public class MarbleSolitaireControllerImplTest {
  private MarbleSolitaireModel model;
  private MarbleSolitaireView view;
  Readable input;
  MarbleSolitaireController controller;

  // to reduce code duplication
  private void init() {
    this.model = new EnglishSolitaireModel();
    this.view = new MarbleSolitaireTextView(model);
    this.input = new StringReader("4 2 4 4");
    this.controller = new MarbleSolitaireControllerImpl(model, view, input);
  }

  // test that the constructor works
  @Test
  public void testValidConstructor() {
    init();
    assertEquals(7, model.getBoardSize());
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", view.toString());
  }

  // test that the constructor throws an error when receiving null arguments
  @Test
  public void testConstructorNullArguments() {
    init();
    try { // only model is null throws error
      new MarbleSolitaireControllerImpl(null, view, input);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try { // only view is null throws error
      new MarbleSolitaireControllerImpl(model, null, input);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try { // only input is null throws error
      new MarbleSolitaireControllerImpl(model, view, null);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // do nothing
    }
    try { // all arguments are null throws error
      new MarbleSolitaireControllerImpl(null, null, null);
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // do nothing
    }
  }

  // test that the model's methods are called with the correct arguments
  @Test
  public void testPlayGameMockModelValid() {
    StringBuilder modelLog = new StringBuilder();
    MarbleSolitaireModel model = new MockModel(modelLog);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);
    Readable input = new StringReader("4 2 4 4 q");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
            input);
    controller.playGame();
    assertEquals("isGameOver\n" +
                    "getBoardSize\n" +
                    "getScore\n" +
                    "fromRow = 3, fromCol = 1, toRow = 3, toCol = 3\n" +
                    "isGameOver\n" +
                    "getBoardSize\n" +
                    "getScore\n" +
                    "getBoardSize\n" +
                    "getScore\n",
            modelLog.toString());
  }

  // test that the view's methods are called with the correct arguments
  @Test
  public void testPlayGameMockViewValid() {
    StringBuilder viewLog = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MockView(viewLog);
    Readable input = new StringReader("4 2 4 4 q");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
            input);
    controller.playGame();
    assertEquals("renderBoard" +
            "message = Score: 32" +
            "message = Enter the row to move from (starting at 1): " +
            "message = Enter the column to move from (starting at 1): " +
            "message = Enter the row to move to (starting at 1): " +
            "message = Enter the column to move to (starting at 1): " +
            "renderBoard" +
            "message = Score: 31" +
            "message = Enter the row to move from (starting at 1): " +
            "message = Game quit!\nState of game when quit:" +
            "renderBoard" +
            "message = Score: 31", viewLog.toString());
  }

  // test that the view shows the correct message when user gives an invalid input
  @Test
  public void testPlayGameMockViewInvalidInput() {
    StringBuilder viewLog = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MockView(viewLog);
    Readable input = new StringReader("4 2 w 4 4 q");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, input);
    controller.playGame();
    assertEquals("renderBoard" +
            "message = Score: 32" +
            "message = Enter the row to move from (starting at 1): " +
            "message = Enter the column to move from (starting at 1): " +
            "message = Enter the row to move to (starting at 1): " +
            "message = Invalid input: w\nEnter a valid integer: " +
            "message = Enter the column to move to (starting at 1): " +
            "renderBoard" +
            "message = Score: 31" +
            "message = Enter the row to move from (starting at 1): " +
            "message = Game quit!\nState of game when quit:" +
            "renderBoard" +
            "message = Score: 31", viewLog.toString());
  }

  // test that the view shows the correct message when user enters a negative number
  @Test
  public void testPlayGameMockViewNegativeInput() {
    StringBuilder viewLog = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MockView(viewLog);
    Readable input = new StringReader("2 4 -1 4 4 q");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, input);
    controller.playGame();
    assertEquals("renderBoard" +
            "message = Score: 32" +
            "message = Enter the row to move from (starting at 1): " +
            "message = Enter the column to move from (starting at 1): " +
            "message = Enter the row to move to (starting at 1): " +
            "message = Invalid input: -1\nEnter a valid integer: " +
            "message = Enter the column to move to (starting at 1): " +
            "renderBoard" +
            "message = Score: 31" +
            "message = Enter the row to move from (starting at 1): " +
            "message = Game quit!\nState of game when quit:" +
            "renderBoard" +
            "message = Score: 31", viewLog.toString());
  }

  // test that the view shows the correct message when user makes an invalid move
  @Test
  public void testPlayGameMockViewInvalidMove() {
    StringBuilder viewLog = new StringBuilder();
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MockView(viewLog);
    Readable input = new StringReader("4 2 10 4 q");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
            input);
    controller.playGame();
    assertEquals("renderBoard" +
            "message = Score: 32" +
            "message = Enter the row to move from (starting at 1): " +
            "message = Enter the column to move from (starting at 1): " +
            "message = Enter the row to move to (starting at 1): " +
            "message = Enter the column to move to (starting at 1): " +
            "message = Invalid move. Play again." +
            "renderBoard" +
            "message = Score: 32" +
            "message = Enter the row to move from (starting at 1): " +
            "message = Game quit!\nState of game when quit:" +
            "renderBoard" +
            "message = Score: 32", viewLog.toString());
  }

  // test that the game throws an IllegalStateException if it runs out of inputs
  @Test(expected = IllegalStateException.class)
  public void testPlayGameCantReadInput() {
    init();
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, input);
    controller.playGame();
  }

  // test that the game throws an IllegalStateException if the output could not be transmitted
  @Test(expected = IllegalStateException.class)
  public void testPlayGameCantTransmitOutput() {
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    Appendable output = new CorruptAppendable();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, output);
    Readable input = new StringReader("4 2 4 4");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, input);
    controller.playGame();
  }

  // test that the correct messages are transmitted when the game is over
  @Test
  public void testGameOverEndsGame() {
    StringBuilder viewLog = new StringBuilder();
    this.model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MockView(viewLog);
    this.input = new StringReader("2 4 4 4 q");
    this.controller = new MarbleSolitaireControllerImpl(model, view, input);

    this.model.move(3, 1, 3, 3);
    this.model.move(5, 2, 3, 2);
    this.model.move(4, 0, 4, 2);
    this.model.move(4, 3, 4, 1);
    this.model.move(4, 5, 4, 3);
    this.model.move(6, 4, 4, 4);
    this.model.move(3, 4, 5, 4);
    this.model.move(6, 2, 6, 4);
    this.model.move(6, 4, 4, 4);
    this.model.move(2, 2, 4, 2);
    this.model.move(0, 2, 2, 2);
    this.model.move(1, 4, 3, 4);
    this.model.move(3, 4, 5, 4);
    this.model.move(5, 4, 5, 2);
    this.model.move(5, 2, 3, 2);
    this.model.move(3, 2, 1, 2);
    this.model.move(2, 0, 4, 0);
    this.model.move(4, 0, 4, 2);
    this.model.move(4, 2, 4, 4);
    this.model.move(2, 6, 2, 4);
    this.model.move(2, 3, 2, 5);
    this.model.move(4, 6, 2, 6);
    this.model.move(2, 6, 2, 4);
    this.model.move(0, 4, 0, 2);
    this.model.move(0, 2, 2, 2);
    this.model.move(2, 1, 2, 3);
    this.model.move(2, 3, 2, 5);
    this.model.move(2, 5, 4, 5);
    this.model.move(4, 5, 4, 3);
    this.model.move(4, 3, 2, 3);
    assertEquals(2, model.getScore());
    // complete every move except last move
    this.controller.playGame(); // input takes care of last move
    assertEquals("renderBoard" +
            "message = Score: 2" +
            "message = Enter the row to move from (starting at 1): " +
            "message = Enter the column to move from (starting at 1): " +
            "message = Enter the row to move to (starting at 1): " +
            "message = Enter the column to move to (starting at 1): " +
            "message = Game over!" +
            "renderBoard" +
            "message = Score: 1", viewLog.toString());
  }
}
