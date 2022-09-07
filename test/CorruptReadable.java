import java.io.IOException;
import java.nio.CharBuffer;

/**
 * This class represents a corrupt class that implements Readable.
 * Every method throws an IOException as this is used for testing IOExceptions.
 */
public class CorruptReadable implements Readable {
  @Override
  public int read(CharBuffer cb) throws IOException {
    throw new IOException();
  }
}

