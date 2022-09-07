package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;
import java.util.List;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * This class represents a EuropeanSolitaireModel. It provides the implementations necessary
 * to play a MarbleSolitaire game with a European board.
 */
public class EuropeanSolitaireModel
        extends AbstractSolitaireModel
        implements MarbleSolitaireModel {

  /**
   * Constructs a EuropeanSolitaireModel object with a side length of 3 and the empty slot
   * at (3,3).
   */
  public EuropeanSolitaireModel() {
    super(3, 3, 3);
  }

  /**
   * Constructs a EuropeanSolitaireModel with the given side length and the empty slot at (3,3).
   *
   * @param sideLength the given side length
   */
  public EuropeanSolitaireModel(int sideLength) {
    super(sideLength, (sideLength + 2 * (sideLength - 1)) / 2,
            (sideLength + 2 * (sideLength - 1)) / 2);
  }

  /**
   * Constructs a EuropeanSolitaireModel with the given empty slot position and a side length
   * of 3.
   *
   * @param emptyRow the row value of the empty slot
   * @param emptyCol the column value of the empty slot
   */
  public EuropeanSolitaireModel(int emptyRow, int emptyCol) {
    super(3, emptyRow, emptyCol);
  }

  /**
   * Constructs a EuropeanSolitaireModel with the given side length and empty slot position.
   *
   * @param sideLength the given side length
   * @param emptyRow   the row value of the empty slot
   * @param emptyCol   the column value of the empty slot
   */
  public EuropeanSolitaireModel(int sideLength, int emptyRow, int emptyCol) {
    super(sideLength, emptyRow, emptyCol);
  }

  @Override
  protected void initBoard(int sideLength, int emptyRow, int emptyCol)
          throws IllegalArgumentException {

    if (sideLength % 2 == 0 || sideLength < 3) {
      throw new IllegalArgumentException("The side length should be a positive, odd integer "
              + "greater than or equal to 3");
    }

    int width = sideLength + 2 * (sideLength - 1);
    this.board = new ArrayList<List<SlotState>>(width);

    for (int r = 0; r < width; r += 1) {
      ArrayList<SlotState> row = new ArrayList<SlotState>(width);
      int numInvalidLeft;
      int numValidLeft;
      if (r <= sideLength - 2) { // if in the "top third"
        numInvalidLeft = sideLength - 1 - r; // number of invalid slots on each side
        numValidLeft = sideLength + r * 2; // number of valid slots in the middle
        for (int c = 0; c < width; c += 1) {
          if (numInvalidLeft > 0) { // set invalids on left side
            row.add(SlotState.Invalid);
            numInvalidLeft--;
          } else if (numValidLeft > 0) { // set marbles in middle
            row.add(SlotState.Marble);
            numValidLeft--;
          } else { // set invalids on right side
            row.add(SlotState.Invalid);
          }
        }
      }

      if (r >= sideLength - 1 && r <= width - sideLength) { // if in the "middle third"
        for (int c = 0; c < width; c += 1) { // every slot is marble
          row.add(SlotState.Marble);
        }
      }

      if (r >= width - sideLength + 1) { // if in the "bottom third"
        numInvalidLeft = sideLength - (width - r); // number of invalid slots on each side
        numValidLeft = (width - r - 1) * 2 + sideLength; // number of valid slots in the middle
        for (int c = 0; c < width; c += 1) {
          if (numInvalidLeft > 0) { // set invalids on left side
            row.add(SlotState.Invalid);
            numInvalidLeft--;
          } else if (numValidLeft > 0) { // set marbles in middle
            row.add(SlotState.Marble);
            numValidLeft--;
          } else { // set invalids on right side
            row.add(SlotState.Invalid);
          }
        }
      }
      this.board.add(row);
    } // end of initializing board
    if (emptyRow < 0 || emptyRow >= this.board.size()
            || emptyCol < 0 || emptyCol >= this.board.size()
            || this.board.get(emptyRow).get(emptyCol) == SlotState.Invalid) {
      throw new IllegalArgumentException("Invalid position for empty slot");
    }

    this.board.get(emptyRow).set(emptyCol, SlotState.Empty);
  }


}
