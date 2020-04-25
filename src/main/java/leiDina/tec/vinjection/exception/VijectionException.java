package main.java.leiDina.tec.vinjection.exception;

import main.java.leiDina.tec.core.exception.BaseException;
import main.java.leiDina.tec.core.messages.MessageHolder;

/**
 * @author vitor.alves
 */
public class VijectionException extends BaseException {

    public VijectionException(MessageHolder messageHolder) {
        super(messageHolder);
    }

    public VijectionException(MessageHolder messageHolder, Throwable cause) {
        super(messageHolder, cause);
    }

    public VijectionException(Throwable cause) {
        super(cause);
    }
}
