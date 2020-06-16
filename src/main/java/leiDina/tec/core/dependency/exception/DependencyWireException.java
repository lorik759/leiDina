package main.java.leiDina.tec.core.dependency.exception;

import main.java.leiDina.tec.core.messages.MessageHolder;

/**
 * @author vitor.alves
 */
public class DependencyWireException extends DependencyException {

    public DependencyWireException(MessageHolder messageHolder) {
        super(messageHolder);
    }

    public DependencyWireException(MessageHolder messageHolder, Throwable cause) {
        super(messageHolder, cause);
    }

    public DependencyWireException(Throwable cause) {
        super(cause);
    }
}
