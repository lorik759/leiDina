package main.java.leiDina.tec.core.dependency.exception;


import main.java.leiDina.tec.core.messages.MessageHolder;

/**
 * @author vitor.alves
 */
public class DependencyCreationException extends DependencyException {

    public DependencyCreationException(MessageHolder messageHolder) {
        super(messageHolder);
    }

    public DependencyCreationException(MessageHolder messageHolder, Throwable cause) {
        super(messageHolder, cause);
    }

    public DependencyCreationException(Throwable cause) {
        super(cause);
    }
}
