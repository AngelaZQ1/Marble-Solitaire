import java.io.IOException;

/**
 * This class represents a corrupt class that implements Appendable.
 * Every method throws an IOException as this is used for testing IOExceptions.
 */
public class CorruptAppendable implements Appendable {

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException();
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException();
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException();
  }
}