package main.java.leiDina.tec.core.beans.exception;


import main.java.leiDina.tec.core.messages.MessageHolder;

/**
 * @author vitor.alves
 */
public class BeanCreationException extends BeanException {

    public BeanCreationException(MessageHolder messageHolder) {
        super(messageHolder);
    }

    public BeanCreationException(MessageHolder messageHolder, Throwable cause) {
        super(messageHolder, cause);
    }

    public BeanCreationException(Throwable cause) {
        super(cause);
    }
}
