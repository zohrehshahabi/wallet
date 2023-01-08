package ir.mapsa.wallet.exceptions;

public class BaseException extends Exception {

    private final String message;

    public BaseException(String message) {
        super();
        this.message = message;
    }


//
//    public BaseException(String message, Throwable cause, String exceptionType) {
//        super(message, cause);
//        this.exceptionType = exceptionType;
//    }
//
//    public BaseException(Throwable cause, String exceptionType) {
//        super(cause);
//        this.exceptionType = exceptionType;
//    }
//
//    protected BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String exceptionType) {
//        super(message, cause, enableSuppression, writableStackTrace);
//        this.exceptionType = exceptionType;
//    }

    public String getMessage() {
        return message;
    }
}
