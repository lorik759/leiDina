package main.java.leiDina.tec.javafx.exception;


import main.java.leiDina.tec.core.exception.BaseException;
import main.java.leiDina.tec.core.messages.MessageHolder;

/**
 * @author vitor.alves
 */
public class VFXException extends BaseException {

    private static final long serialVersionUID = 6853471773656296804L;

    public VFXException(MessageHolder messageHolder) {
        super(messageHolder);
    }

    public VFXException(MessageHolder messageHolder, Throwable cause) {
        super(messageHolder, cause);
    }

    public VFXException(Throwable cause) {
        super(cause);
    }
}
