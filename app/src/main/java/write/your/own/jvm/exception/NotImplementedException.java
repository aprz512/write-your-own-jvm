package write.your.own.jvm.exception;

public class NotImplementedException extends RuntimeException {
    public NotImplementedException(String msg) {
        super(msg);
    }

    public NotImplementedException() {
    }
}
