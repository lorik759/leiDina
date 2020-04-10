package main.java.leiDina.tec.persister.exception;

import main.java.leiDina.tec.core.exception.BaseException;
import main.java.leiDina.tec.core.messages.MessageHolder;

/**
 * @author vitor.alves
 */
public class PersistenceException extends BaseException {

    public PersistenceException(MessageHolder messageHolder) {
        super(messageHolder);
    }

    public PersistenceException(MessageHolder messageHolder, Throwable cause) {
        super(messageHolder, cause);
    }

    public PersistenceException(Throwable cause) {
        super(cause);
    }
}
