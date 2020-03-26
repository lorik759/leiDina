package main.java.leiDina.tec.core.exception;

import main.java.leiDina.tec.core.messages.MessageHolder;

/**
 * @author vitor.alves
 */
public class EntityNotFoundException extends BaseException {

    public EntityNotFoundException(MessageHolder messageHolder) {
        super(messageHolder);
    }

    public EntityNotFoundException(MessageHolder messageHolder, Throwable cause) {
        super(messageHolder, cause);
    }

    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }
}
