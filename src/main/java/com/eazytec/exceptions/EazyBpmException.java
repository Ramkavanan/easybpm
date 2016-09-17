package 
/**
 * Runtime exception that is the superclass of all exceptions.
 * 
 * @author ramachandran
 */
public class CIPException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public CIPException(String message, Throwable cause) {
    super(message, cause);
  }

  public CIPException(String message) {
    super(message);
  }
}
