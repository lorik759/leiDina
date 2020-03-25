package main.java.leiDina.tec.core.exception;


import main.java.leiDina.tec.core.messages.MessageHolder;

/**
 * A base {@link RuntimeException} that accepts a {@link MessageHolder} as parameter.
 *
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
