package main.java.leiDina.tec.vinjection.exception;

import main.java.leiDina.tec.core.exception.BaseException;
import main.java.leiDina.tec.core.messages.MessageHolder;

/**
 * @author vitor.alves
 */
public class PropertyException extends BaseException {

    public PropertyException(MessageHolder messageHolder) {
        super(messageHolder);
    }

    public PropertyException(MessageHolder messageHolder, Throwable cause) {
        super(messageHolder, cause);
    }

    public PropertyException(Throwable cause) {
        super(cause);
    }
}
