package cs3500.marblesolitaire.model.hw04;

import java.util.List;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;


/**
 * This class represents abstractions between SolitaireModel classes. It implements
 * MarbleSolitaireModel.
 */
public abstract class AbstractSolitaireModel implements MarbleSolitaireModel {
  protected List<List<SlotState>> board;

  /**
   * Constructs a subclass of this class with the given size (arm thickness for an English board,
   * side length for a European board, and base width for a triangle board) and positions for
   * the empty slot.
   *
   * @param size     the size of the board
   * @param emptyRow the row value of the empty slot
   * @param emptyCol the column value of the empty slot
   */
  protected AbstractSolitaireModel(int size, int emptyRow, int emptyCol) {
    initBoard(size, emptyRow, emptyCol);
  }

  /**
   * An abstract method used by this abstract class's constructor that initializes the board
   * based on the class in which the method is called using the given size and empty position.
   *
   * @param size     the size of the board to create
   * @param emptyRow the row value of the empty slot
   * @param emptyCol the column value of the empty slot
   */
  protected abstract void initBoard(int size, int emptyRow, int emptyCol);

  /**
   * Move a single marble from a given position to another given position.
   * A move is valid only if the from and to positions are valid. Specific
   * implementations may place additional constraints on the validity of a move.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException if any of the conditions are invalid:
   *                                  the "from" or "to" positions are invalid
   *                                  there is no marble at the "from" position
   *                                  the "to" position is not empty
   *                                  the "to" and "from" positions are not exactly two
   *                                  positions away horizontally or vertically (for English
   *                                  and European boards) and are not exactly two rows away
   *                                  and exactly two columns away (for the triangle board only)
   *                                  there is no marble in the slot between the "to"
   *                                  and "from" positions
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (fromRow < 0 || fromRow >= this.board.size() || fromCol < 0 || fromCol >= this.board.size()
            || toRow < 0 || toRow >= this.board.size() || toCol < 0 || toCol >= this.board.size()
            || getSlotAt(fromRow, fromCol) != SlotState.Marble
            || getSlotAt(toRow, toCol) != SlotState.Empty
            || !this.canMoveFromTo(fromRow, fromCol, toRow, toCol)
            || getSlotAt((fromRow + toRow) / 2, (fromCol + toCol) / 2)
            != SlotState.Marble) {
      throw new IllegalArgumentException("Invalid values for the 'from' or 'to' position.");
    }
    this.board.get(fromRow).set(fromCol, SlotState.Empty);
    this.board.get(toRow).set(toCol, SlotState.Marble);
    this.board.get((fromRow + toRow) / 2).set((fromCol + toCol) / 2, SlotState.Empty);
  }

  // determine if the given from position can move to the given to position
  protected boolean canMoveFromTo(int fromRow, int fromCol, int toRow, int toCol) {
    return (Math.abs(toCol - fromCol) == 2 && toRow == fromRow)
            || (toCol == fromCol && Math.abs(toRow - fromRow) == 2);
  }

  @Override
  public int getBoardSize() {
    return this.board.size();
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || row >= this.board.size() || col < 0 || col >= this.board.size()) {
      throw new IllegalArgumentException("The given position" + row + "," + col
              + "is outside the board's dimensions");
    }
    return this.board.get(row).get(col);
  }

  @Override
  public int getScore() {
    int numMarbles = 0;
    for (List<SlotState> row : this.board) {
      for (SlotState slot : row) {
        if (slot == SlotState.Marble) {
          numMarbles += 1;
        }
      }
    }
    return numMarbles;
  }

  @Override
  public boolean isGameOver() {
    // return false if any marble on the board can make a move
    for (int row = 0; row < this.board.size(); row += 1) {
      for (int col = 0; col < this.board.get(row).size(); col += 1) {
        if (board.get(row).get(col).equals(SlotState.Marble)) {
          if (this.canMove(row, col)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  // Determines if the marble at the given slot can make any valid moves
  protected boolean canMove(int row, int col) {
    if (row < 0 || row >= this.board.size() || col < 0 || col >= this.board.size()) {
      throw new IndexOutOfBoundsException("the marble is not on the board");
    }
    if (row - 2 >= 0) { // if there exists a slot 2 squares up of this marble
      if (this.board.get(row - 1).get(col) == SlotState.Marble
              && this.board.get(row - 2).get(col) == SlotState.Empty) {
        return true; // return true if the slot 2 squares up is empty and
        // the slot 1 square up is a marble
      }
    }
    if (col + 2 < this.getBoardSize()) { // if there exists a slot 2
      // squares to the right of this marble
      if (this.board.get(row).get(col + 1) == SlotState.Marble
              && this.board.get(row).get(col + 2) == SlotState.Empty) {
        return true; // return true if the slot 2 squares to the right is empty and
        // the slot 1 square to the right is a marble
      }
    }
    if (row + 2 < this.getBoardSize()) { // if there exists a slot 2 squares below this marble
      if (this.board.get(row + 1).get(col) == SlotState.Marble
              && this.board.get(row + 2).get(col) == SlotState.Empty) {
        return true; // return true if the slot 2 squares down is empty and
        // the slot 1 square down is a marble
      }
    }
    if (col - 2 >= 0) { // if there exists a slot 2 squares below this marble
      // the slot 1 square down is a marble
      return this.board.get(row).get(col - 1) == SlotState.Marble
              && this.board.get(row).get(col - 2) == SlotState.Empty;
      // return true if the slot 2 squares down is empty and
    }
    return false;
  }
}
