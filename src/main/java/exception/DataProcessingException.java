package exception;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String msg, Throwable e) {
        super(msg, e);
    }
}
