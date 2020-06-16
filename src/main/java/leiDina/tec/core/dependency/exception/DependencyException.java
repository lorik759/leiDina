package main.java.leiDina.tec.core.dependency.exception;


import main.java.leiDina.tec.core.dependency.factory.DependencyContainer;
import main.java.leiDina.tec.core.exception.BaseException;
import main.java.leiDina.tec.core.messages.MessageHolder;

/**
 * The base exception for all bean exception. This represents an error when dealing with a {@link DependencyContainer}.
 *
 * @author vitor.alves
 */
public abstract class DependencyException extends BaseException {

    public DependencyException(MessageHolder messageHolder) {
        super(messageHolder);
    }

    public DependencyException(MessageHolder messageHolder, Throwable cause) {
        super(messageHolder, cause);
    }

    public DependencyException(Throwable cause) {
        super(cause);
    }
}
