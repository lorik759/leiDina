package main.java.leiDina.tec.core.beans.exception;

import main.java.leiDina.tec.core.messages.MessageHolder;

/**
 * @author vitor.alves
 */
public class BeanWireException extends BeanException {

    public BeanWireException(MessageHolder messageHolder) {
        super(messageHolder);
    }

    public BeanWireException(MessageHolder messageHolder, Throwable cause) {
        super(messageHolder, cause);
    }

    public BeanWireException(Throwable cause) {
        super(cause);
    }
}
