import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This class is used to test the TriangleSolitaireModel class. It ensures that all methods and
 * constructors work as intended.
 */
public class TriangleSolitaireModelTest {
  private TriangleSolitaireModel base5;
  private TriangleSolitaireModel base7;
  MarbleSolitaireModelState.SlotState invalid;
  MarbleSolitaireModelState.SlotState marble;
  MarbleSolitaireModelState.SlotState empty;

  @Before
  public void init() {
    this.base5 = new TriangleSolitaireModel();
    this.base7 = new TriangleSolitaireModel(7);
    invalid = MarbleSolitaireModelState.SlotState.Invalid;
    marble = MarbleSolitaireModelState.SlotState.Marble;
    empty = MarbleSolitaireModelState.SlotState.Empty;
  }

  @Test
  public void testBase5() {
    assertEquals(invalid, this.base5.getSlotAt(0, 1));
    assertEquals(invalid, this.base5.getSlotAt(0, 4));
    assertEquals(invalid, this.base5.getSlotAt(1, 2));
    assertEquals(invalid, this.base5.getSlotAt(1, 4));
    assertEquals(invalid, this.base5.getSlotAt(2, 3));
    assertEquals(invalid, this.base5.getSlotAt(2, 4));
    assertEquals(invalid, this.base5.getSlotAt(3, 4));
    assertEquals(marble, this.base5.getSlotAt(4, 0));
    assertEquals(marble, this.base5.getSlotAt(4, 4));
    assertEquals(marble, this.base5.getSlotAt(2, 2));
  }

  @Test
  public void testBase7() {
    assertEquals(invalid, this.base7.getSlotAt(0, 1));
    assertEquals(invalid, this.base7.getSlotAt(0, 6));
    assertEquals(invalid, this.base7.getSlotAt(0, 3));
    assertEquals(invalid, this.base7.getSlotAt(1, 2));
    assertEquals(invalid, this.base7.getSlotAt(1, 6));
    assertEquals(invalid, this.base7.getSlotAt(1, 4));
    assertEquals(invalid, this.base7.getSlotAt(2, 3));
    assertEquals(invalid, this.base7.getSlotAt(2, 6));
    assertEquals(invalid, this.base7.getSlotAt(3, 4));
    assertEquals(invalid, this.base7.getSlotAt(3, 6));
    assertEquals(invalid, this.base7.getSlotAt(4, 5));
    assertEquals(invalid, this.base7.getSlotAt(4, 6));
    assertEquals(invalid, this.base7.getSlotAt(5, 6));
    assertEquals(marble, this.base7.getSlotAt(1, 0));
    assertEquals(marble, this.base7.getSlotAt(1, 1));
    assertEquals(marble, this.base7.getSlotAt(2, 0));
    assertEquals(marble, this.base7.getSlotAt(2, 2));
    assertEquals(marble, this.base7.getSlotAt(3, 0));
    assertEquals(marble, this.base7.getSlotAt(3, 3));
    assertEquals(marble, this.base7.getSlotAt(4, 0));
    assertEquals(marble, this.base7.getSlotAt(4, 2));
    assertEquals(marble, this.base7.getSlotAt(4, 4));
    assertEquals(marble, this.base7.getSlotAt(5, 5));
    assertEquals(marble, this.base7.getSlotAt(6, 6));
  }

  @Test
  public void testDefaultConstructorNoError() {
    this.init();
    testBase5();
    assertEquals(empty, this.base5.getSlotAt(0, 0));
  }

  @Test
  public void testOneArgConstructorNoError() {
    this.base5 = new TriangleSolitaireModel(5);
    testBase5();
    assertEquals(empty, base5.getSlotAt(0, 0));
    this.base7 = new TriangleSolitaireModel(7);
    testBase7();
    assertEquals(empty, base7.getSlotAt(0, 0));
  }

  @Test
  public void testOneArgConstructorThrowsIllegalArgumentException() {
    try {
      new TriangleSolitaireModel(-2);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new TriangleSolitaireModel(0);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
  }

  @Test
  public void testTwoArgConstructorNoError() {
    this.base5 = new TriangleSolitaireModel(3, 1);
    testBase5();
    assertEquals(empty, base5.getSlotAt(3, 1));
  }

  @Test
  public void testTwoArgConstructorThrowsIllegalArgumentException() {
    try {
      new TriangleSolitaireModel(0, 1);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new TriangleSolitaireModel(1, 2);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new TriangleSolitaireModel(0, -1);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new TriangleSolitaireModel(5, 6);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new TriangleSolitaireModel(6, 0);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new TriangleSolitaireModel(6, 6);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
  }

  @Test
  public void testThreeArgConstructorNoError() {
    this.base5 = new TriangleSolitaireModel(5, 4, 3);
    testBase5();

    // to test that (0, 0) is a marble, not empty
    assertEquals(marble, this.base5.getSlotAt(0, 0));
    // to test that (4,3) is empty
    assertEquals(empty, this.base5.getSlotAt(4, 3));

    this.base7 = new TriangleSolitaireModel(7, 3, 2);
    testBase7();
    assertEquals(marble, this.base7.getSlotAt(0, 0));
    assertEquals(empty, this.base7.getSlotAt(3, 2));
  }

  @Test
  public void testThreeArgConstructorInvalidBaseLengthThrowsIllegalArgumentException() {
    try {
      new TriangleSolitaireModel(-2, 3, 3);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new TriangleSolitaireModel(-1, 3, 3);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new TriangleSolitaireModel(0, 3, 3);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
  }

  @Test
  public void testThreeArgConstructorInvalidEmptySlotThrowsIllegalArgumentException() {
    try {
      new TriangleSolitaireModel(5, 0, 1);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new TriangleSolitaireModel(5, 10, 10);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      new TriangleSolitaireModel(5, 6, 6);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
  }

  // test that you can make a valid move from two columns to the left in the same row
  @Test
  public void testMoveFromLeft() {
    MarbleSolitaireModel model = new TriangleSolitaireModel(3, 2);
    assertEquals(marble, model.getSlotAt(3, 0));
    assertEquals(marble, model.getSlotAt(3, 1));
    assertEquals(empty, model.getSlotAt(3, 2));

    model.move(3, 0, 3, 2);
    assertEquals(empty, model.getSlotAt(3, 0));
    assertEquals(empty, model.getSlotAt(3, 1));
    assertEquals(marble, model.getSlotAt(3, 2));
  }

  // test that you can make a valid move from two rows up and two columns from the left
  @Test
  public void testMoveFromTopLeft() {
    MarbleSolitaireModel model = new TriangleSolitaireModel(2, 2);
    assertEquals(marble, model.getSlotAt(0, 0));
    assertEquals(marble, model.getSlotAt(1, 1));
    assertEquals(empty, model.getSlotAt(2, 2));

    model.move(0, 0, 2, 2);
    assertEquals(empty, model.getSlotAt(0, 0));
    assertEquals(empty, model.getSlotAt(1, 1));
    assertEquals(marble, model.getSlotAt(2, 2));
  }

  // test that you can make a valid move from two rows up in the same column
  @Test
  public void testMoveFromTopRight() {
    MarbleSolitaireModel model = new TriangleSolitaireModel(2, 0);
    assertEquals(marble, model.getSlotAt(0, 0));
    assertEquals(marble, model.getSlotAt(1, 0));
    assertEquals(empty, model.getSlotAt(2, 0));

    model.move(0, 0, 2, 0);
    assertEquals(empty, model.getSlotAt(0, 0));
    assertEquals(empty, model.getSlotAt(1, 0));
    assertEquals(marble, model.getSlotAt(2, 0));
  }

  // test that you can make a valid move from two columns to the right in the same row
  @Test
  public void testMoveFromRight() {
    MarbleSolitaireModel model = new TriangleSolitaireModel(4, 0);
    assertEquals(empty, model.getSlotAt(4, 0));
    assertEquals(marble, model.getSlotAt(4, 1));
    assertEquals(marble, model.getSlotAt(4, 2));

    model.move(4, 2, 4, 0);
    assertEquals(marble, model.getSlotAt(4, 0));
    assertEquals(empty, model.getSlotAt(4, 1));
    assertEquals(empty, model.getSlotAt(4, 2));

  }

  // test that you can make a valid move from two rows down and two columns to the right
  @Test
  public void testMoveFromBottomRight() {
    MarbleSolitaireModel model = new TriangleSolitaireModel(0, 0);
    assertEquals(empty, model.getSlotAt(0, 0));
    assertEquals(marble, model.getSlotAt(1, 1));
    assertEquals(marble, model.getSlotAt(2, 2));

    model.move(2, 2, 0, 0);
    assertEquals(marble, model.getSlotAt(0, 0));
    assertEquals(empty, model.getSlotAt(1, 1));
    assertEquals(empty, model.getSlotAt(2, 2));
  }

  // test that you can make a valid move from two rows down and in the same column
  @Test
  public void testMoveFromBottomLeft() {
    MarbleSolitaireModel model = new TriangleSolitaireModel(0, 0);
    assertEquals(empty, model.getSlotAt(0, 0));
    assertEquals(marble, model.getSlotAt(1, 0));
    assertEquals(marble, model.getSlotAt(2, 0));

    model.move(2, 0, 0, 0);
    assertEquals(marble, model.getSlotAt(0, 0));
    assertEquals(empty, model.getSlotAt(1, 0));
    assertEquals(empty, model.getSlotAt(2, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidFromThrowsError() {
    init();
    base5.move(5, 6, 5, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidToThrowsError() {
    init();
    base5.move(5, 5, 5, 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveNoMarbleAtFromThrowsError() {
    init();
    base5.move(0, 0, 2, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveToNotEmptyThrowsError() {
    init();
    base5.move(3, 0, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveJumpOverEmptyThrowsError() {
    MarbleSolitaireModel model = new TriangleSolitaireModel(2, 1);
    model.move(2, 0, 2, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromMoreThan2ApartThrowsError() {
    MarbleSolitaireModel model = new TriangleSolitaireModel(3, 3);
    model.move(3, 0, 3, 3);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveDiagonallyThrowsError() {
    MarbleSolitaireModel model = new TriangleSolitaireModel(1, 0);
    base5.move(2, 2, 1, 0);
  }

  @Test
  public void testGetBoardSize() {
    this.init();
    assertEquals(5, base5.getBoardSize());
    assertEquals(7, base7.getBoardSize());
  }

  // test that the isGameOver method works
  @Test
  public void testIsGameOver() {
    this.init();
    assertFalse(this.base5.isGameOver());
    this.base5.move(2, 0, 0, 0);
    this.base5.move(4, 0, 2, 0);
    assertFalse(this.base5.isGameOver());
    this.base5.move(4, 2, 4, 0);
    this.base5.move(4, 4, 4, 2);
    this.base5.move(2, 2, 4, 4);
    this.base5.move(2, 0, 2, 2);
    assertFalse(this.base5.isGameOver());
    this.base5.move(4, 2, 2, 0);
    this.base5.move(1, 1, 3, 3);
    this.base5.move(3, 3, 3, 1);
    this.base5.move(2, 0, 4, 2);
    assertTrue(this.base5.isGameOver());
  }

  @Test
  public void testGetSlotAt() {
    init();
    testBase5();
    assertEquals(empty, base5.getSlotAt(0, 0));
  }

  // test that getting a slot at an invalid position throws an error
  @Test
  public void testGetSlotAtInvalidPosition() {
    this.init();
    try {
      base5.getSlotAt(-1, -1);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      base5.getSlotAt(-1, 0);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      base5.getSlotAt(0, -1);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      base5.getSlotAt(4, 5);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
    try {
      base5.getSlotAt(5, 0);
      fail("IllegalArgumentException was not thrown");
    } catch (IllegalArgumentException e) {
      // dont do anything
    }
  }

  // test that the getScore method works
  @Test
  public void testGetScore() {
    this.init();
    assertEquals(14, this.base5.getScore());
    this.base5.move(2, 0, 0, 0);
    assertEquals(13, this.base5.getScore());
    this.base5.move(4, 0, 2, 0);
    assertEquals(12, this.base5.getScore());
  }


}
