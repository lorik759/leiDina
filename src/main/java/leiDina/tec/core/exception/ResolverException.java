package main.java.leiDina.tec.core.exception;

import main.java.leiDina.tec.core.messages.MessageHolder;

/**
 * @author vitor.alves
 */
public class ResolverException extends BaseException {

    public ResolverException(MessageHolder messageHolder) {
        super(messageHolder);
    }

    public ResolverException(MessageHolder messageHolder, Throwable cause) {
        super(messageHolder, cause);
    }

    public ResolverException(Throwable cause) {
        super(cause);
    }
}
