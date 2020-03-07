package main.java.leiDina.tec.javafx.exception;


import main.java.leiDina.tec.messages.MessageHolder;

/**
 * @author vitor.alves
 */
public abstract class BaseException extends RuntimeException {

    private static final long serialVersionUID = 6714382345466664741L;

    public BaseException(MessageHolder messageHolder) {
        super(messageHolder.getMessage());
    }

    public BaseException(MessageHolder messageHolder, Throwable cause) {
        super(messageHolder.getMessage(), cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }
}
