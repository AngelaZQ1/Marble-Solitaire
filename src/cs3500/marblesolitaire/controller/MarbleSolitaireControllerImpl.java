package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * This class represents an implementation of the MarbleSolitaireController class.
 * It handles controlling the state of the game and responds to user input.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private final MarbleSolitaireModel model;
  private final MarbleSolitaireView view;
  private final Readable inputSource;

  /**
   * Constructs a MarbleSolitaireControllerImpl object with the given model, view, and input
   * source.
   *
   * @param model       the model to use
   * @param view        the view to use
   * @param inputSource the source from which to read input
   * @throws IllegalArgumentException if any of the three provided arguments are null
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
                                       Readable inputSource) throws IllegalArgumentException {
    if (model == null || view == null || inputSource == null) {
      throw new IllegalArgumentException("The given model, view, and input source cannot" +
              "be null");
    }
    this.model = model;
    this.view = view;
    this.inputSource = inputSource;
  }

  @Override
  public void playGame() throws IllegalStateException {
    Scanner scanner = new Scanner(this.inputSource);
    while (!model.isGameOver()) {
      this.renderBoard();
      this.renderMessage("Score: " + this.model.getScore());

      int fromRow;
      int fromCol;
      int toRow;
      int toCol;

      try {
        this.renderMessage("Enter the row to move from (starting at 1): ");
        fromRow = this.takeInput(scanner);
        this.renderMessage("Enter the column to move from (starting at 1): ");
        fromCol = this.takeInput(scanner);
        this.renderMessage("Enter the row to move to (starting at 1): ");
        toRow = this.takeInput(scanner);
        this.renderMessage("Enter the column to move to (starting at 1): ");
        toCol = this.takeInput(scanner);
      } catch (NoSuchElementException e) {
        throw new IllegalStateException("No more inputs"); // there are no more inputs
      } catch (RuntimeException e) {
        return; // user quit game
      }

      try { // try to move after receiving inputs
        model.move(fromRow - 1, fromCol - 1, toRow - 1, toCol - 1);
      } catch (IllegalArgumentException e) {
        this.renderMessage("Invalid move. Play again.");
      }

    } // end of while game is not over
    this.renderMessage("Game over!");
    this.renderBoard();
    this.renderMessage("Score: " + this.model.getScore());
  }

  // helper method to take in valid user inputs
  // throws a RuntimeException if the user quits the game, which is caught in playGame()
  private Integer takeInput(Scanner scanner) throws RuntimeException {
    while (true) {
      String input = scanner.next();
      if (input.equalsIgnoreCase("q")) { // gameOver if press Q
        this.renderMessage("Game quit!\nState of game when quit:");
        this.renderBoard();
        this.renderMessage("Score: " + this.model.getScore());
        throw new RuntimeException("User quit game");
      } else {
        try { // get an input from user
          Integer integerInput = Integer.parseInt(input);
          if (integerInput < 0) { // if negative, ask for another input
            this.renderMessage(String.format("Invalid input: %s\nEnter a valid integer: ", input));
          } else { // else return the valid input
            return Integer.parseInt(input);
          }
        } catch (NumberFormatException e) { // didnt receive valid input
          this.renderMessage(String.format("Invalid input: %s\nEnter a valid integer: ", input));
        }
      }
    }
  }

  // helper method to render a given message
  private void renderMessage(String message) {
    try {
      view.renderMessage(message);
      view.renderMessage("\n");
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  // helper method to render the board
  private void renderBoard() {
    try {
      view.renderBoard();
      view.renderMessage("\n");
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }
}

