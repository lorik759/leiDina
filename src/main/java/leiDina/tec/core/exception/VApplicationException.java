package main.java.leiDina.tec.core.exception;

import main.java.leiDina.tec.core.messages.MessageHolder;

/**
 * @author vitor.alves
 */
public class VApplicationException extends BaseException {

    public VApplicationException(MessageHolder messageHolder) {
        super(messageHolder);
    }

    public VApplicationException(MessageHolder messageHolder, Throwable cause) {
        super(messageHolder, cause);
    }

    public VApplicationException(Throwable cause) {
        super(cause);
    }
}
