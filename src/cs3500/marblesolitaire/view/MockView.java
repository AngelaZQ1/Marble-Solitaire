package cs3500.marblesolitaire.view;

import java.util.Objects;

/**
 * This class represents a mock view for testing purposes. It ensures that
 * the correct arguments are being passed into its methods.
 */
public class MockView implements MarbleSolitaireView {
  private final StringBuilder log;

  /**
   * Creates a MockView object with the given StringBuilder.
   *
   * @param log the StringBuilder to use to log values
   */
  public MockView(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  /**
   * Tests that this method is called by appending "renderBoard" to this mock's log.
   */
  @Override
  public void renderBoard() {
    this.log.append("renderBoard");
  }

  /**
   * Tests that this method is called with the correct argument by appending the given message
   * to this mock's log.
   *
   * @param message the message to append to the log
   */
  @Override
  public void renderMessage(String message) {
    if (message.equals("\n")) {
      return;
    }
    this.log.append(String.format("message = %s", message));
  }
}
