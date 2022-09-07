import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This class tests the methods and constructors in the EnglishSolitaireModel class. This class
 * ensures that all methods and constructors work as intended and throw exceptions where necessary.
 */

public class EnglishSolitaireModelTest {
  private EnglishSolitaireModel modelArm3;
  private EnglishSolitaireModel modelArm5;
  MarbleSolitaireModelState.SlotState invalid;
  MarbleSolitaireModelState.SlotState marble;
  MarbleSolitaireModelState.SlotState empty;

  @Before
  public void init() {
    this.modelArm3 = new EnglishSolitaireModel();
    this.modelArm5 = new EnglishSolitaireModel(5);
    invalid = MarbleSolitaireModelState.SlotState.Invalid;
    marble = MarbleSolitaireModelState.SlotState.Marble;
    empty = MarbleSolitaireModelState.SlotState.Empty;
  }

  // For testing a board with an arm thickness of 3, doesnt test the empty slot
  @Test
  public void testArmThickness3() {
    assertEquals(invalid, this.modelArm3.getSlotAt(1, 1));
    assertEquals(invalid, this.modelArm3.getSlotAt(5, 1));
    assertEquals(invalid, this.modelArm3.getSlotAt(1, 5));
    assertEquals(invalid, this.modelArm3.getSlotAt(5, 5));
    assertEquals(marble, this.modelArm3.getSlotAt(0, 2));
    assertEquals(marble, this.modelArm3.getSlotAt(6, 4));
    assertEquals(marble, this.modelArm3.getSlotAt(4, 2));
    assertEquals(marble, this.modelArm3.getSlotAt(2, 4));
  }

  // For testing a board with an arm thickness of 5, doesnt test the empty slot
  @Test
  public void testArmThickness5() {
    assertEquals(invalid, this.modelArm5.getSlotAt(3, 3));
    assertEquals(invalid, this.modelArm5.getSlotAt(3, 9));
    assertEquals(invalid, this.modelArm5.getSlotAt(9, 3));
    assertEquals(invalid, this.modelArm5.getSlotAt(9, 9));
    assertEquals(marble, this.modelArm5.getSlotAt(0, 4));
    assertEquals(marble, this.modelArm5.getSlotAt(12, 8));
    assertEquals(marble, this.modelArm5.getSlotAt(4, 8));
    assertEquals(marble, this.modelArm5.getSlotAt(8, 4));
  }

  // testing the no argument constructor creates a board filled correctly
  @Test
  public void testConstructor1() {
    this.init();
    testArmThickness3();
    assertEquals(empty, this.modelArm3.getSlotAt(3, 3));
  }

  // testing the constructor taking a row and column creates a board filled correctly
  @Test
  public void testConstructor2() {
    this.modelArm3 = new EnglishSolitaireModel(3, 1);
    testArmThickness3();
    assertEquals(empty, modelArm3.getSlotAt(3, 1));
  }

  // testing the constructor taking a row and column throws an error if the empty slot
  // is invalid or outside the board's dimensions
  @Test
  public void testConstructor2Error() {
    try {
      new EnglishSolitaireModel(1, 1);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new EnglishSolitaireModel(5, 1);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new EnglishSolitaireModel(1, 5);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new EnglishSolitaireModel(5, 5);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new EnglishSolitaireModel(50, 50);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
  }

  // testing the constructor that takes an arm thickness creates a board filled correctly
  @Test
  public void testConstructor3() {
    this.modelArm3 = new EnglishSolitaireModel(3);
    testArmThickness3();
    assertEquals(empty, this.modelArm3.getSlotAt(3, 3));

    this.modelArm5 = new EnglishSolitaireModel(5);
    testArmThickness5();
    assertEquals(empty, this.modelArm5.getSlotAt(6, 6));
  }

  // testing the constructor that takes an arm thickness throws an exception when the arm
  // thickness is invalid
  @Test
  public void testConstructor3Error() {
    try {
      new EnglishSolitaireModel(-2);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new EnglishSolitaireModel(0);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new EnglishSolitaireModel(2);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
  }

  // testing the constructor that takes an arm thickness and empty slot position
  //  creates a board filled correctly
  @Test
  public void testConstructor4() {
    this.modelArm3 = new EnglishSolitaireModel(3, 4, 4);
    testArmThickness3();

    // to test that (3,3) is a marble, not empty
    assertEquals(marble, this.modelArm3.getSlotAt(3, 3));
    // to test that (4,4) is empty
    assertEquals(empty, this.modelArm3.getSlotAt(4, 4));

    this.modelArm5 = new EnglishSolitaireModel(5, 0, 5);
    testArmThickness5();
    assertEquals(marble, this.modelArm3.getSlotAt(3, 3));
    assertEquals(empty, this.modelArm5.getSlotAt(0, 5));
  }

  // test the 3 argument constructor throws an error
  @Test
  public void testConstructor4Error() {
    try {
      new EnglishSolitaireModel(-2, 3, 3);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new EnglishSolitaireModel(-1, 3, 3);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new EnglishSolitaireModel(0, 3, 3);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new EnglishSolitaireModel(2, 3, 3);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new EnglishSolitaireModel(3, 1, 1);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new EnglishSolitaireModel(3, 5, 1);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new EnglishSolitaireModel(3, 5, 5);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
  }

  // test that the move method works
  @Test
  public void testMove() {
    this.init();
    assertEquals(empty, modelArm3.getSlotAt(3, 3));
    modelArm3.move(5, 3, 3, 3);
    assertEquals(marble, modelArm3.getSlotAt(3, 3));
    assertEquals(empty, modelArm3.getSlotAt(4, 3));
    assertEquals(empty, modelArm3.getSlotAt(5, 3));
    modelArm3.move(4, 1, 4, 3);
    modelArm3.move(2, 2, 4, 2);
    modelArm3.move(2, 4, 2, 2);
  }

  // test that the move method throws an error
  @Test
  public void testMoveError() {
    EnglishSolitaireModel model = new EnglishSolitaireModel(2, 1);
    try { // invalid "from" position
      model.move(1, 1, 1, 3);
      fail("from position should be invalid but was valid");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try { // invalid "to" position
      model.move(2, 2, 4, 2);
      fail("to position should be invalid but was valid");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try { // no marble at from position
      model.move(3, 3, 1, 3);
      fail("should be marble at the from position but there was a marble");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try { // "from" and "to" positions are not 2 positions away horizontally
      model.move(3, 1, 3, 3);
      fail("the from and to positions should be two positions away horizontally but aren't");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try { // "from" and "to" positions are not 2 positions away vertically
      model.move(6, 3, 3, 3);
      fail("the from and to positions should be two positions away vertically but aren't");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try { // "from" and "to" positions cant be diagonal
      model.move(2, 2, 4, 4);
      fail("the from and to positions cannot be diagonal");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    model.move(4, 1, 2, 1);
    try { // no marble between the "to" and "from" slots
      model.move(3, 0, 3, 2);
      fail("should be no marble between the to and from slots but there is a marble");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
  }

  // test that the isGameOver method works
  @Test
  public void testIsGameOver() {
    this.init();
    assertFalse(this.modelArm3.isGameOver());
    this.modelArm3.move(3, 1, 3, 3);
    assertFalse(this.modelArm3.isGameOver());
    this.modelArm3.move(5, 2, 3, 2);
    this.modelArm3.move(4, 0, 4, 2);
    this.modelArm3.move(4, 3, 4, 1);
    assertFalse(this.modelArm3.isGameOver());
    this.modelArm3.move(4, 5, 4, 3);
    this.modelArm3.move(6, 4, 4, 4);
    this.modelArm3.move(3, 4, 5, 4);
    this.modelArm3.move(6, 2, 6, 4);
    assertFalse(this.modelArm3.isGameOver());
    this.modelArm3.move(6, 4, 4, 4);
    this.modelArm3.move(2, 2, 4, 2);
    this.modelArm3.move(0, 2, 2, 2);
    this.modelArm3.move(1, 4, 3, 4);
    this.modelArm3.move(3, 4, 5, 4);
    assertFalse(this.modelArm3.isGameOver());
    this.modelArm3.move(5, 4, 5, 2);
    this.modelArm3.move(5, 2, 3, 2);
    this.modelArm3.move(3, 2, 1, 2);
    this.modelArm3.move(2, 0, 4, 0);
    assertFalse(this.modelArm3.isGameOver());
    this.modelArm3.move(4, 0, 4, 2);
    this.modelArm3.move(4, 2, 4, 4);
    this.modelArm3.move(2, 6, 2, 4);
    this.modelArm3.move(2, 3, 2, 5);
    assertFalse(this.modelArm3.isGameOver());
    this.modelArm3.move(4, 6, 2, 6);
    this.modelArm3.move(2, 6, 2, 4);
    assertFalse(this.modelArm3.isGameOver());
    this.modelArm3.move(0, 4, 0, 2);
    this.modelArm3.move(0, 2, 2, 2);
    this.modelArm3.move(2, 1, 2, 3);
    this.modelArm3.move(2, 3, 2, 5);
    assertFalse(this.modelArm3.isGameOver());
    this.modelArm3.move(2, 5, 4, 5);
    this.modelArm3.move(4, 5, 4, 3);
    assertFalse(this.modelArm3.isGameOver());
    this.modelArm3.move(4, 3, 2, 3);
    this.modelArm3.move(1, 3, 3, 3);
    assertTrue(this.modelArm3.isGameOver());
    MarbleSolitaireView view = new MarbleSolitaireTextView(modelArm3);
    assertEquals("    _ _ _\n" +
            "    _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ _", view.toString());
  }

  // test getting the board size
  @Test
  public void testGetBoardSize() {
    this.init();
    EnglishSolitaireModel model2 = new EnglishSolitaireModel(5);
    assertEquals(7, modelArm3.getBoardSize());
    assertEquals(13, model2.getBoardSize());
  }

  // test getting the slot at a given position
  @Test
  public void testGetSlotAt() {
    init();
    testArmThickness3();
    assertEquals(empty, modelArm3.getSlotAt(3, 3));
  }

  // test that getting a slot at an invalid position throws an error
  @Test
  public void testGetSlotAtError() {
    this.init();
    try {
      modelArm3.getSlotAt(-1, -1);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      modelArm3.getSlotAt(-1, 0);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      modelArm3.getSlotAt(0, -1);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      modelArm3.getSlotAt(8, 7);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      modelArm3.getSlotAt(4, 8);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
  }

  // test that the getScore method works
  @Test
  public void testGetScore() {
    this.init();
    assertEquals(32, this.modelArm3.getScore());
    this.modelArm3.move(3, 1, 3, 3);
    assertEquals(31, this.modelArm3.getScore());
    this.modelArm3.move(5, 2, 3, 2);
    assertEquals(30, this.modelArm3.getScore());
  }
}
