package cs3500.marblesolitaire;

import java.io.InputStreamReader;
import java.util.Arrays;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;


/**
 * This class is used to run the MarbleSolitaire game. It takes in a series of arguments used
 * to configure the game.
 */
public class MarbleSolitaire {

  /**
   * This is the main method used to run the game.
   *
   * @param args arguments used to configure the game.
   */
  public static void main(String[] args) {

    int size = 0;
    int emptyRow = 0;
    int emptyCol = 0;

    String modelType = args[0];

    if (args[0].equals("english") || args[0].equals("european")) {
      size = 3;
      emptyRow = 4;
      emptyCol = 4;
    }
    else if (args[0].equals("triangle")) {
      size = 5;
      emptyRow = 1;
      emptyCol = 1;
    }

    if (Arrays.asList(args).contains("-size")) {
      size = Integer.parseInt(args[Arrays.asList(args).indexOf("-size") + 1]);
      emptyRow = (size + 2 * size) / 2;
      emptyCol = (size + 2 * size) / 2;
    }
    if (Arrays.asList(args).contains("-hole")) {
      emptyRow = Integer.parseInt(args[Arrays.asList(args).indexOf("-hole") + 1]);
      emptyCol = Integer.parseInt(args[Arrays.asList(args).indexOf("-hole") + 2]);
    }

    MarbleSolitaireModel model;
    MarbleSolitaireView view;
    switch (modelType) {
      case "english":
        model = new EnglishSolitaireModel(size, emptyRow - 1, emptyCol - 1);
        view = new MarbleSolitaireTextView(model);
        break;
      case "european":
        model = new EuropeanSolitaireModel(size, emptyRow - 1, emptyCol - 1);
        view = new MarbleSolitaireTextView(model);
        break;
      default:
        model = new TriangleSolitaireModel(size, emptyRow - 1, emptyCol - 1);
        view = new TriangleSolitaireTextView(model);
        break;
    }

    MarbleSolitaireControllerImpl game = new MarbleSolitaireControllerImpl(model, view,
            new InputStreamReader(System.in));
    game.playGame();
  }
}
