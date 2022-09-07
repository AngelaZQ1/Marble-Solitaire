package cs3500.marblesolitaire.model.hw04;

import java.util.Objects;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * This class represents a mock model for testing purposes. It ensures that
 * the correct arguments are being passed into its methods.
 */
public class MockModel implements MarbleSolitaireModel {
  private final StringBuilder log;

  /**
   * Creates a MockModel object with the given StringBuilder.
   *
   * @param log the StringBuilder to use to log values
   */
  public MockModel(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  /**
   * Tests that the move method is called with the correct arguments by appending the given
   * arguments to this mock's log.
   *
   * @param fromRow the fromRow to log
   * @param fromCol the fromCol to log
   * @param toRow   the toRow to log
   * @param toCol   the toCol to log
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) {
    this.log.append(String.format("fromRow = %d, fromCol = %d, toRow = %d, toCol = %d\n",
            fromRow, fromCol, toRow, toCol));
  }

  /**
   * Tests that the isGameOver method is called by appending "isGameOver" to this mock's log.
   *
   * @return false
   */
  @Override
  public boolean isGameOver() {
    this.log.append("isGameOver\n");
    return false;
  }

  /**
   * Tests that the getBoardSize method is called by appending "getBoardSize" to this mock's log.
   *
   * @return 0
   */
  @Override
  public int getBoardSize() {
    this.log.append("getBoardSize\n");
    return 0;
  }

  /**
   * Tests that this method is called with the correct arguments by appending the given row and
   * column to this mock's log.
   *
   * @param row the row value to append to the log
   * @param col the column value to append to the log
   * @return null
   */
  @Override
  public SlotState getSlotAt(int row, int col) {
    this.log.append(String.format("row = %d, col = %d\n", row, col));
    return null;
  }

  /**
   * Tests that this method is called by appending "getScore" to this mock's log.
   *
   * @return 0
   */
  @Override
  public int getScore() {
    this.log.append("getScore\n");
    return 0;
  }
}
