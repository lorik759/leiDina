package main.java.leiDina.tec.core.beans.exception;


import main.java.leiDina.tec.core.exception.BaseException;
import main.java.leiDina.tec.core.messages.MessageHolder;

/**
 * A base exception for all bean exception. This represents an error when dealing with a bean.
 *
 * @author vitor.alves
 */
public abstract class BeanException extends BaseException {

    public BeanException(MessageHolder messageHolder) {
        super(messageHolder);
    }

    public BeanException(MessageHolder messageHolder, Throwable cause) {
        super(messageHolder, cause);
    }

    public BeanException(Throwable cause) {
        super(cause);
    }
}
