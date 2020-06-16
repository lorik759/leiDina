package main.java.leiDina.tec.core.dependency.exception;


import main.java.leiDina.tec.core.messages.MessageHolder;

/**
 * @author vitor.alves
 */
public class DependencyDefinitionException extends DependencyException {

    public DependencyDefinitionException(MessageHolder messageHolder) {
        super(messageHolder);
    }

    public DependencyDefinitionException(MessageHolder messageHolder, Throwable cause) {
        super(messageHolder, cause);
    }

    public DependencyDefinitionException(Throwable cause) {
        super(cause);
    }
}
