package main.java.leiDina.tec.javafx.exception;


import main.java.leiDina.tec.core.exception.BaseException;
import main.java.leiDina.tec.core.messages.MessageHolder;

/**
 * @author vitor.alves
 */
public class BuilderException extends BaseException {

    private static final long serialVersionUID = 4522812311766583592L;

    public BuilderException(MessageHolder messageHolder) {
        super(messageHolder);
    }

    public BuilderException(MessageHolder messageHolder, Throwable cause) {
        super(messageHolder, cause);
    }

    public BuilderException(Throwable cause) {
        super(cause);
    }
}
