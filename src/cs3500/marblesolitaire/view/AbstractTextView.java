package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This class represents the abstractions between TextView classes. It implements
 * MarbleSolitaireView.
 */
public abstract class AbstractTextView implements MarbleSolitaireView {
  protected MarbleSolitaireModelState modelState;
  protected Appendable destination;
  private static final MarbleSolitaireModelState.SlotState invalid =
          MarbleSolitaireModelState.SlotState.Invalid;
  private static final MarbleSolitaireModelState.SlotState marble =
          MarbleSolitaireModelState.SlotState.Marble;
  private static final MarbleSolitaireModelState.SlotState empty =
          MarbleSolitaireModelState.SlotState.Empty;


  /**
   * Constructs a text view object depending on the class that calls this constructor.
   *
   * @param modelState the model state to use in methods
   * @throws IllegalArgumentException if the given modelState is null
   */
  public AbstractTextView(MarbleSolitaireModelState modelState) throws IllegalArgumentException {
    if (modelState == null) {
      throw new IllegalArgumentException("The model cannot be null");
    }
    this.modelState = modelState;
    this.destination = System.out;
  }

  /**
   * Constructs a text view object depending on the class that calls this constructor.
   *
   * @param model       the model state object to use as this view's model
   * @param destination the destination for this view to use as an Appendable
   * @throws IllegalArgumentException if the provided model or destination is null
   */
  public AbstractTextView(MarbleSolitaireModelState model, Appendable destination)
          throws IllegalArgumentException {
    if (model == null || destination == null) {
      throw new IllegalArgumentException("The model and destination cannot be null");
    }
    this.modelState = model;
    this.destination = destination;
  }

  // creates the String representation for European and English marble solitaire boards
  // Triangle boards override this method
  @Override
  public String toString() {
    String result = "";
    int width = modelState.getBoardSize();
    for (int row = 0; row < width; row++) {
      boolean noMoreInvalidSpaces = false;
      for (int col = 0; col < width; col += 1) {

        if (modelState.getSlotAt(row, col) == invalid) { // if the current slot is invalid
          if (noMoreInvalidSpaces) {
            break;
          }
          result += "  "; // add 2 spaces
        } else if (modelState.getSlotAt(row, col) == marble) { // if the current is a marble
          result += "O"; // add a 0
          noMoreInvalidSpaces = true;
          if (col + 1 < modelState.getBoardSize()) { // if the next slot exists
            if (modelState.getSlotAt(row, col + 1) != invalid) { // and if its not invalid
              result += " "; // add a space
            }
          }
        } else if (modelState.getSlotAt(row, col) == empty) { // if the current is empty
          result += "_"; // add a _
          if (col + 1 < modelState.getBoardSize()) { // if the next slot exists
            if (modelState.getSlotAt(row, col + 1) != invalid) { // and if its not invalid
              result += " "; // add a space
            } else {
              break;
            }
          }
        }
      }
      // dont include a new line after the last row
      if (row != modelState.getBoardSize() - 1) {
        result += "\n";
      }
    }
    return result;
  }

  @Override
  public void renderBoard() throws IOException {
    this.destination.append(this.toString());
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.destination.append(message);
  }

}
