package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This class represents operations that offer a way to view the current
 * Marble Solitaire game. Methods in this class do not change the state of the game.
 */
public class MarbleSolitaireTextView
        extends AbstractTextView
        implements MarbleSolitaireView {

  /**
   * Constructs a MarbleSolitaireTextView object with the given model.
   *
   * @param model the model object for this view to use
   * @throws IllegalArgumentException if the given model is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model)
          throws IllegalArgumentException {
    super(model);
  }

  /**
   * Constructs a MarbleSolitaireTextView object with the given model and destination.
   *
   * @param model       the model object for this view to use
   * @param destination the destination for this view to use as an Appendable
   * @throws IllegalArgumentException if the given model or destination is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model, Appendable destination)
          throws IllegalArgumentException {
    super(model, destination);
  }
}
