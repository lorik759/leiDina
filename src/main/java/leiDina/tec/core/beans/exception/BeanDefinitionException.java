package main.java.leiDina.tec.core.beans.exception;


import main.java.leiDina.tec.core.messages.MessageHolder;

/**
 * @author vitor.alves
 */
public class BeanDefinitionException extends BeanException {

    public BeanDefinitionException(MessageHolder messageHolder) {
        super(messageHolder);
    }

    public BeanDefinitionException(MessageHolder messageHolder, Throwable cause) {
        super(messageHolder, cause);
    }

    public BeanDefinitionException(Throwable cause) {
        super(cause);
    }
}
