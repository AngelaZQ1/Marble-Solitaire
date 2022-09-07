import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This class is used to test the EuropeanSolitaireModel class. It ensures that all constructors
 * and methods work as intended and throw exceptions where necessary.
 */
public class EuropeanSolitaireModelTest {
  private EuropeanSolitaireModel width3;
  private EuropeanSolitaireModel width5;
  private MarbleSolitaireModelState.SlotState invalid;
  private MarbleSolitaireModelState.SlotState marble;
  private MarbleSolitaireModelState.SlotState empty;

  // to initialize common variables
  @Before
  public void init() {
    this.width3 = new EuropeanSolitaireModel();
    this.width5 = new EuropeanSolitaireModel(5);
    invalid = MarbleSolitaireModelState.SlotState.Invalid;
    marble = MarbleSolitaireModelState.SlotState.Marble;
    empty = MarbleSolitaireModelState.SlotState.Empty;
  }

  // For testing a board with a width of 3, doesnt test the empty slot
  @Test
  public void testWidth3() {
    assertEquals(invalid, this.width3.getSlotAt(0, 0));
    assertEquals(invalid, this.width3.getSlotAt(0, 1));
    assertEquals(invalid, this.width3.getSlotAt(1, 0));
    assertEquals(invalid, this.width3.getSlotAt(0, 5));
    assertEquals(invalid, this.width3.getSlotAt(0, 6));
    assertEquals(invalid, this.width3.getSlotAt(1, 6));
    assertEquals(invalid, this.width3.getSlotAt(5, 0));
    assertEquals(invalid, this.width3.getSlotAt(6, 0));
    assertEquals(invalid, this.width3.getSlotAt(6, 1));
    assertEquals(invalid, this.width3.getSlotAt(5, 6));
    assertEquals(invalid, this.width3.getSlotAt(6, 5));
    assertEquals(invalid, this.width3.getSlotAt(6, 6));
    assertEquals(marble, this.width3.getSlotAt(1, 1));
    assertEquals(marble, this.width3.getSlotAt(0, 2));
    assertEquals(marble, this.width3.getSlotAt(0, 4));
    assertEquals(marble, this.width3.getSlotAt(2, 0));
    assertEquals(marble, this.width3.getSlotAt(2, 1));
    assertEquals(marble, this.width3.getSlotAt(5, 1));
    assertEquals(marble, this.width3.getSlotAt(5, 5));
    assertEquals(marble, this.width3.getSlotAt(6, 4));

  }

  // For testing a board with a width of 5, doesnt test the empty slot
  @Test
  public void testWidth5() {
    assertEquals(invalid, this.width5.getSlotAt(0, 0));
    assertEquals(invalid, this.width5.getSlotAt(0, 3));
    assertEquals(invalid, this.width5.getSlotAt(3, 0));
    assertEquals(invalid, this.width5.getSlotAt(2, 1));
    assertEquals(invalid, this.width5.getSlotAt(0, 10));
    assertEquals(invalid, this.width5.getSlotAt(2, 11));
    assertEquals(invalid, this.width5.getSlotAt(3, 12));
    assertEquals(invalid, this.width5.getSlotAt(9, 0));
    assertEquals(invalid, this.width5.getSlotAt(10, 1));
    assertEquals(invalid, this.width5.getSlotAt(11, 1));
    assertEquals(invalid, this.width5.getSlotAt(12, 9));
    assertEquals(invalid, this.width5.getSlotAt(12, 10));
    assertEquals(invalid, this.width5.getSlotAt(10, 11));
    assertEquals(invalid, this.width5.getSlotAt(10, 12));
    assertEquals(invalid, this.width5.getSlotAt(12, 12));
    assertEquals(marble, this.width5.getSlotAt(0, 4));
    assertEquals(marble, this.width5.getSlotAt(0, 6));
    assertEquals(marble, this.width5.getSlotAt(0, 8));
    assertEquals(marble, this.width5.getSlotAt(1, 3));
    assertEquals(marble, this.width5.getSlotAt(1, 9));
    assertEquals(marble, this.width5.getSlotAt(2, 2));
    assertEquals(marble, this.width5.getSlotAt(2, 10));
    assertEquals(marble, this.width5.getSlotAt(3, 1));
    assertEquals(marble, this.width5.getSlotAt(3, 11));
    assertEquals(marble, this.width5.getSlotAt(4, 0));
    assertEquals(marble, this.width5.getSlotAt(4, 12));
    assertEquals(marble, this.width5.getSlotAt(5, 0));
    assertEquals(marble, this.width5.getSlotAt(5, 12));
    assertEquals(marble, this.width5.getSlotAt(4, 4));
    assertEquals(marble, this.width5.getSlotAt(6, 10));
    assertEquals(marble, this.width5.getSlotAt(8, 0));
    assertEquals(marble, this.width5.getSlotAt(8, 12));
    assertEquals(marble, this.width5.getSlotAt(9, 1));
    assertEquals(marble, this.width5.getSlotAt(9, 10));
    assertEquals(marble, this.width5.getSlotAt(10, 2));
    assertEquals(marble, this.width5.getSlotAt(10, 10));
    assertEquals(marble, this.width5.getSlotAt(11, 3));
    assertEquals(marble, this.width5.getSlotAt(11, 9));
    assertEquals(marble, this.width5.getSlotAt(12, 4));
    assertEquals(marble, this.width5.getSlotAt(12, 8));
  }

  // testing the no argument constructor creates a board filled correctly
  @Test
  public void testConstructor1() {
    this.init();
    testWidth3();
    assertEquals(empty, this.width3.getSlotAt(3, 3));
  }

  // testing the constructor taking a row and column creates a board filled correctly
  @Test
  public void testConstructor2() {
    this.width3 = new EuropeanSolitaireModel(3, 1);
    testWidth3();
    assertEquals(empty, width3.getSlotAt(3, 1));
  }

  // testing the constructor taking a row and column throws an error if the empty slot
  // is invalid or outside the board's dimensions
  @Test
  public void testConstructor2InvalidEmptySlot() {
    try {
      new EuropeanSolitaireModel(0, 0);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new EuropeanSolitaireModel(1, 0);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new EuropeanSolitaireModel(0, 1);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new EuropeanSolitaireModel(0, 5);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new EuropeanSolitaireModel(5, 0);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new EuropeanSolitaireModel(6, 6);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2EmptySlotOutsideBoardDimensions() {
    new EuropeanSolitaireModel(50, 50);
  }

  // testing the constructor that takes a width creates a board filled correctly
  @Test
  public void testConstructor3() {
    this.width3 = new EuropeanSolitaireModel(3);
    testWidth3();
    assertEquals(empty, this.width3.getSlotAt(3, 3));

    this.width5 = new EuropeanSolitaireModel(5);
    testWidth5();
    assertEquals(empty, this.width5.getSlotAt(6, 6));
  }

  // testing the constructor that takes a width throws an exception when the width is invalid
  @Test
  public void testConstructor3Error() {
    try {
      new EuropeanSolitaireModel(-2);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new EuropeanSolitaireModel(0);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new EuropeanSolitaireModel(2);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
  }

  // testing the constructor that takes a width and empty slot position creates
  // a board filled correctly
  @Test
  public void testConstructor4() {
    this.width3 = new EuropeanSolitaireModel(3, 4, 4);
    testWidth3();

    // to test that (3,3) is a marble, not empty
    assertEquals(marble, this.width3.getSlotAt(3, 3));
    // to test that (4,4) is empty
    assertEquals(empty, this.width3.getSlotAt(4, 4));

    this.width5 = new EuropeanSolitaireModel(5, 1, 5);
    testWidth5();
    assertEquals(marble, this.width5.getSlotAt(3, 3));
    assertEquals(empty, this.width5.getSlotAt(1, 5));
  }

  // test the 4 argument constructor throws an error
  @Test
  public void testConstructor4ErrorInvalidWidth() {
    try {
      new EuropeanSolitaireModel(-2, 3, 3);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new EuropeanSolitaireModel(-1, 3, 3);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new EuropeanSolitaireModel(0, 3, 3);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new EuropeanSolitaireModel(2, 3, 3);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
  }

  @Test
  public void testConstructor4ErrorInvalidEmptySlot() {
    try {
      new EuropeanSolitaireModel(3, 0, 0);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new EuropeanSolitaireModel(3, 10, 10);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new EuropeanSolitaireModel(3, 6, 6);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
  }

  // test that the move method works when moving to a valid slot 2 slots up
  @Test 
  public void testMoveUp() {
    this.init();
    assertEquals(empty, width3.getSlotAt(3, 3));
    width3.move(5, 3, 3, 3);
    assertEquals(marble, width3.getSlotAt(3, 3));
    assertEquals(empty, width3.getSlotAt(4, 3));
    assertEquals(empty, width3.getSlotAt(5, 3));
  }

  // test that the move method works when moving to a valid slot 2 slots right
  @Test 
  public void testMoveRight() {
    this.init();
    assertEquals(empty, width3.getSlotAt(3, 3));
    width3.move(3, 1, 3, 3);
    assertEquals(marble, width3.getSlotAt(3, 3));
    assertEquals(empty, width3.getSlotAt(3, 2));
    assertEquals(empty, width3.getSlotAt(3, 1));
  }

  // test that the move method works when moving to a valid slot 2 slots down
  @Test 
  public void testMoveDown() {
    this.init();
    assertEquals(empty, width3.getSlotAt(3, 3));
    width3.move(1, 3, 3, 3);
    assertEquals(marble, width3.getSlotAt(3, 3));
    assertEquals(empty, width3.getSlotAt(1, 3));
    assertEquals(empty, width3.getSlotAt(2, 3));
  }

  // test that the move method works when moving to a valid slot 2 slots left
  @Test 
  public void testMoveLeft() {
    this.init();
    assertEquals(empty, width3.getSlotAt(3, 3));
    width3.move(3, 5, 3, 3);
    assertEquals(marble, width3.getSlotAt(3, 3));
    assertEquals(empty, width3.getSlotAt(3, 4));
    assertEquals(empty, width3.getSlotAt(3, 5));
  }

  // test that the move method throws an error
  @Test
  public void testMoveError() {
    MarbleSolitaireModel model = new EuropeanSolitaireModel(0, 2);
    try { // invalid "from" position
      model.move(0, 0, 0, 2);
      fail("from position should be invalid but was valid");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try { // invalid "to" position
      model.move(0, 3, 0, 5);
      fail("to position should be invalid but was valid");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try { // no marble at from position
      model.move(0, 2, 2, 2);
      fail("should be marble at the from position but there was a marble");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    model.move(2, 2, 0, 2);
    try { // "from" and "to" positions are not 2 positions away horizontally
      model.move(2, 5, 2, 2);
      fail("the from and to positions should be two positions away horizontally but aren't");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try { // "from" and "to" positions are not 2 positions away vertically
      model.move(5, 2, 2, 2);
      fail("the from and to positions should be two positions away vertically but aren't");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try { // "from" and "to" positions cant be diagonal
      model.move(4, 4, 2, 2);
      fail("the from and to positions cannot be diagonal");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try { // no marble between the "to" and "from" slots
      model.move(3, 2, 1, 2);
      fail("should be no marble between the to and from slots but there is a marble");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
  }

  // test that the isGameOver method works
  @Test
  public void testIsGameOver() {
    this.init();
    assertFalse(this.width3.isGameOver());
    this.width3.move(3, 1, 3, 3);
    this.width3.move(1, 1, 3, 1);
    this.width3.move(2, 3, 2, 1);
    this.width3.move(2, 0, 2, 2);
    assertFalse(this.width3.isGameOver());
    this.width3.move(2, 5, 2, 3);
    this.width3.move(2, 3, 2, 1);
    this.width3.move(0, 4, 2, 4);
    this.width3.move(4, 0, 2, 0);
    this.width3.move(2, 0, 2, 2);
    this.width3.move(4, 1, 2, 1);
    this.width3.move(2, 1, 2, 3);
    assertFalse(this.width3.isGameOver());
    this.width3.move(2, 3, 2, 5);
    this.width3.move(0, 2, 2, 2);
    this.width3.move(2, 6, 2, 4);
    assertFalse(this.width3.isGameOver());
    this.width3.move(4, 3, 4, 1);
    this.width3.move(5, 1, 3, 1);
    this.width3.move(0, 3, 2, 3);
    this.width3.move(2, 3, 2, 5);
    this.width3.move(4, 6, 2, 6);
    this.width3.move(2, 6, 2, 4);
    this.width3.move(4, 5, 2, 5);
    assertFalse(this.width3.isGameOver());
    this.width3.move(2, 5, 2, 3);
    this.width3.move(2, 3, 4, 3);
    this.width3.move(4, 3, 4, 5);
    this.width3.move(6, 2, 4, 2);
    assertFalse(this.width3.isGameOver());
    this.width3.move(6, 4, 4, 4);
    this.width3.move(5, 5, 3, 5);
    this.width3.move(6, 3, 4, 3);
    this.width3.move(4, 4, 2, 4);
    this.width3.move(4, 2, 4, 4);
    assertTrue(this.width3.isGameOver());
  }

  // test getting the board size
  @Test 
  public void testGetBoardSize() {
    this.init();
    EuropeanSolitaireModel model2 = new EuropeanSolitaireModel(5);
    assertEquals(7, width3.getBoardSize());
    assertEquals(13, model2.getBoardSize());
  }

  // test getting the slot at a given position
  @Test 
  public void testGetSlotAt() {
    init();
    testWidth5();
    assertEquals(empty, width3.getSlotAt(3, 3));
  }

  // test that getting a slot at an invalid position throws an error
  @Test 
  public void testGetSlotAtInvalidPosition() {
    this.init();
    try {
      width3.getSlotAt(-1, -1);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      width3.getSlotAt(-1, 0);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      width3.getSlotAt(0, -1);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      width3.getSlotAt(8, 7);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      width3.getSlotAt(4, 8);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
  }

  // test that the getScore method works
  @Test 
  public void testGetScore() {
    this.init();
    assertEquals(36, this.width3.getScore());
    this.width3.move(3, 1, 3, 3);
    assertEquals(35, this.width3.getScore());
    this.width3.move(5, 2, 3, 2);
    assertEquals(34, this.width3.getScore());
  }
}
