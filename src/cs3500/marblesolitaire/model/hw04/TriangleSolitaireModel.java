package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * This class represents a TriangleSolitaireModel. It represents a Marble Solitaire model that
 * is triangular shaped.
 */
public class TriangleSolitaireModel
        extends AbstractSolitaireModel
        implements MarbleSolitaireModel {

  /**
   * Constructs a TriangleSolitaireModel object with a base length of 5 and the empty slot
   * at (0,0).
   */
  public TriangleSolitaireModel() {
    super(5, 0, 0);
  }

  /**
   * Constructs a TriangleSolitaireModel object with the given base length and the empty slot
   * at (0,0).
   *
   * @param baseLength the given base length
   * @throws IllegalArgumentException if the given base length is less than 1
   */
  public TriangleSolitaireModel(int baseLength) throws IllegalArgumentException {
    super(baseLength, 0, 0);
  }

  /**
   * Constructs a TriangleSolitaireModel object with a base length of 5 and the empty slot position.
   *
   * @param emptyRow the row for the empty slot
   * @param emptyCol the column for the empty slot
   * @throws IllegalArgumentException if the given empty slot position is invalid
   */
  public TriangleSolitaireModel(int emptyRow, int emptyCol) throws IllegalArgumentException {
    super(5, emptyRow, emptyCol);
  }

  /**
   * Constructs a TriangleSolitaireModel object with the given base length and the empty slot
   * position.
   *
   * @param baseLength the given base length
   * @param emptyRow   the row of the empty slot
   * @param emptyCol   the column of the empty slot
   * @throws IllegalArgumentException if the given base length is negative or if the given empty
   *                                  slot position is invalid
   */
  public TriangleSolitaireModel(int baseLength, int emptyRow, int emptyCol)
          throws IllegalArgumentException {
    super(baseLength, emptyRow, emptyCol);
  }

  @Override
  protected void initBoard(int baseLength, int emptyRow, int emptyCol)
          throws IllegalArgumentException {
    if (baseLength <= 0 || emptyCol > emptyRow || emptyCol < 0 || emptyRow < 0
            || emptyCol >= baseLength || emptyRow >= baseLength) {
      throw new IllegalArgumentException("The base length cannot be negative and the " +
              "empty slot cannot be at an invalid position.");
    }
    this.board = new ArrayList<>(baseLength);

    for (int r = 0; r < baseLength; r++) {
      ArrayList<SlotState> row = new ArrayList<SlotState>(baseLength);
      int numValidLeft = r + 1;
      for (int c = 0; c < baseLength; c++) {
        if (numValidLeft > 0) {
          row.add(SlotState.Marble);
          numValidLeft--;
        } else {
          row.add(SlotState.Invalid);
        }
      }
      board.add(row);
    }

    this.board.get(emptyRow).set(emptyCol, SlotState.Empty);
  }

  // determine if the marble at the given slot can make any valid moves
  protected boolean canMove(int row, int col) {
    if (row < 0 || row >= super.getBoardSize() || col < 0 || col >= super.getBoardSize()) {
      throw new IndexOutOfBoundsException("the marble is not on the board");
    }

    // if there exists a slot same row 2 cols left
    if (col - 2 >= 0) { // if its empty AND if the in between is a marble
      if (super.getSlotAt(row, col - 2) == SlotState.Empty
              && super.getSlotAt(row, col - 1) == SlotState.Marble) {
        return true;
      }
    }
    // if there exists a slot 2 rows up 2 cols left
    if (row - 2 >= 0 && col - 2 >= 0) { // if its empty AND if the in between is a marble
      if (super.getSlotAt(row - 2, col - 2) == SlotState.Empty
              && super.getSlotAt(row - 2, col - 1) == SlotState.Marble) {
        return true;
      }
    }

    // if there exists a slot 2 rows up same col
    if (row - 2 >= 0) { // if its empty AND if the in between is a marble
      if (super.getSlotAt(row - 2, col) == SlotState.Empty
              && super.getSlotAt(row - 1, col) == SlotState.Marble) {
        return true;
      }
    }

    // if there exists a slot same row 2 cols right
    if (col + 2 < super.getBoardSize()) { // if its empty AND if the in between is a marble
      if (super.getSlotAt(row, col + 2) == SlotState.Empty
              && super.getSlotAt(row, col + 1) == SlotState.Marble) {
        return true;
      }
    }

    // if there exists a slot 2 rows down 2 cols right
    if (row + 2 < super.getBoardSize() && col + 2 < super.getBoardSize()) {
      // if its empty AND if the in between is a marble
      if (super.getSlotAt(row + 2, col + 2) == SlotState.Empty
              && super.getSlotAt(row + 1, col + 1) == SlotState.Marble) {
        return true;
      }
    }

    // if there exists a slot 2 rows down same col
    if (row + 2 < super.getBoardSize()) {
      // if its empty AND if the in between is a marble
      return super.getSlotAt(row + 2, col) == SlotState.Empty
              && super.getSlotAt(row + 1, col) == SlotState.Marble;
    }
    return false;
  }

  @Override
  protected boolean canMoveFromTo(int fromRow, int fromCol, int toRow, int toCol) {
    return super.canMoveFromTo(fromRow, fromCol, toRow, toCol)
            || (Math.abs(toCol - fromCol) == 2) && (Math.abs(toRow - fromRow) == 2);
    // both row and col have a difference of 2
  }

}
