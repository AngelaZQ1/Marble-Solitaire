package cs3500.marblesolitaire.model.hw02;

import java.util.ArrayList;

import cs3500.marblesolitaire.model.hw04.AbstractSolitaireModel;

/**
 * This class represents a model that implements MarbleSolitaireModel. It handles operations
 * on the game board and provides ways to monitor the state of the board without changing it.
 */
public class EnglishSolitaireModel
        extends AbstractSolitaireModel
        implements MarbleSolitaireModel {

  /**
   * Initializes the board with Marbles according to the given arm thickness and the position
   * of the empty slot.
   *
   * @param armThickness the arm thickness of the board
   * @param emptyRow     the row value of the empty slot
   * @param emptyCol     the column value of the empty slot
   * @throws IllegalArgumentException if the arm thickness if not a positive, odd integer
   *                                  greater than or equal to 3 or if the given position
   *                                  for the empty slot is invalid or outside the board's
   *                                  dimensions
   */
  @Override
  protected void initBoard(int armThickness, int emptyRow, int emptyCol)
          throws IllegalArgumentException {
    if (armThickness % 2 == 0 || armThickness < 3) {
      throw new IllegalArgumentException("Arm thickness should be a positive, odd integer "
              + "greater than or equal to 3");
    }
    int width = armThickness + 2 * (armThickness - 1);
    this.board = new ArrayList<>(width);
    // fill board with marbles
    for (int r = 0; r < width; r += 1) {
      ArrayList<SlotState> row = new ArrayList<SlotState>(width);
      for (int c = 0; c < width; c += 1) {
        row.add(SlotState.Marble);
      }
      this.board.add(row);
    }
    // change corners to invalid
    for (int r = 0; r < width; r += 1) {
      for (int c = 0; c < width; c += 1) {
        if ((r <= armThickness - 2 && c <= armThickness - 2) // top left
                || (r <= armThickness - 2 && c >= width - (armThickness - 1)) // top right
                || (r >= width - (armThickness - 1) && c <= armThickness - 2) // bottom left
                // bottom right
                || (r >= width - (armThickness - 1) && c >= width - (armThickness - 1))) {
          this.board.get(r).set(c, SlotState.Invalid);
        }
      }
    }
    if (emptyRow < 0 || emptyRow >= this.board.size()
            || emptyCol < 0
            || emptyCol >= this.board.size()
            || this.board.get(emptyRow).get(emptyCol) == SlotState.Invalid) {
      throw new IllegalArgumentException("Invalid position for empty slot");
    }
    this.board.get(emptyRow).set(emptyCol, SlotState.Empty);
  }

  /**
   * Constructs a game board with an arm thickness of 3 and the empty cell at row 3, column 3.
   */
  public EnglishSolitaireModel() {
    super(3, 3, 3);
  }

  /**
   * Constructs a game board with an arm thickness of 3 and the empty cell at the given row
   * and column values.
   *
   * @param sRow the row value of the empty cell
   * @param sCol the column value of the empty cell
   * @throws IllegalArgumentException if the given position for the empty slot is invalid or
   *                                  outside the board's dimensions
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(3, sRow, sCol);
  }

  /**
   * Constructs a game board with the given arm thickness and the empty cell at the center of the
   * board.
   *
   * @param armThickness the arm thickness of the board
   * @throws IllegalArgumentException if the given arm thickness is not a positive, odd integer
   *                                  greater than or equal to 3
   */
  public EnglishSolitaireModel(int armThickness) throws IllegalArgumentException {
    super(armThickness, (armThickness + 2 * (armThickness - 1)) / 2,
            (armThickness + 2 * (armThickness - 1)) / 2);
  }

  /**
   * Constructs a game board with the given arm thickness and the empty cell at the given row
   * and column values.
   *
   * @param armThickness the arm thickness of the board
   * @param sRow         the row value of the empty cell
   * @param sCol         the column value of the empty cell
   * @throws IllegalArgumentException if the arm thickness is not a positive, odd integer
   *                                  greater than or equal to 3 or if the given position
   *                                  for the empty slot is invalid
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    super(armThickness, sRow, sCol);
  }


}
