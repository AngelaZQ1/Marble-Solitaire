package cs3500.marblesolitaire.controller;

/**
 * This interface represents the operations offered by a controller for this game.
 */
public interface MarbleSolitaireController {

  /**
   * Play a new game of Marble Solitaire.
   *
   * @throws IllegalStateException if the controller is unable to successfully read input
   *                               transmit output.
   */
  void playGame() throws IllegalStateException;
}
