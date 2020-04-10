package main.java.leiDina.tec.persister.exception;

import main.java.leiDina.tec.core.exception.BaseException;
import main.java.leiDina.tec.core.messages.MessageHolder;

/**
 * @author vitor.alves
 */
public class EntityCreationException extends BaseException {

    public EntityCreationException(MessageHolder messageHolder) {
        super(messageHolder);
    }

    public EntityCreationException(MessageHolder messageHolder, Throwable cause) {
        super(messageHolder, cause);
    }

    public EntityCreationException(Throwable cause) {
        super(cause);
    }
}
