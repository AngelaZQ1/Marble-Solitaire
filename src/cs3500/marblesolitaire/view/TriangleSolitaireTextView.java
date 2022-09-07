package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This class represents operations that offer a way to view the current
 * Marble Solitaire game. Methods in this class do not change the state of the game.
 */
public class TriangleSolitaireTextView
        extends AbstractTextView
        implements MarbleSolitaireView {

  /**
   * Constructs a TriangleSolitaireTextView object with the given model.
   *
   * @param model the model to use
   * @throws IllegalArgumentException if the given model is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model)
          throws IllegalArgumentException {
    super(model);
  }

  /**
   * Constructs a TriangleSolitaireTextView with the given model and destination.
   *
   * @param model       the model to use
   * @param destination the destination for this view to use as an Appendable
   * @throws IllegalArgumentException if the given model or destination is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model, Appendable destination)
          throws IllegalArgumentException {
    super(model, destination);
  }

  @Override
  public String toString() {
    String result = "";
    int width = modelState.getBoardSize();
    int spaces;
    for (int row = 0; row < width; row++) {

      spaces = width - row - 1; // the number of spaces to put before each row
      while (spaces > 0) {
        result += " ";
        spaces--;
      }
      for (int col = 0; col <= row; col++) {

        if (modelState.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Invalid) {
          result += " ";
        } else if (modelState.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Empty) {
          result += "_";
        } else if (modelState.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Marble) {
          result += "O";
        }
        if (col != row) { // if its not the last slot put a space
          result += " ";
        }
      }

      // dont include a new line after the last row
      if (row != width - 1) {
        result += "\n";
      }
    }
    return result;
  }

}
