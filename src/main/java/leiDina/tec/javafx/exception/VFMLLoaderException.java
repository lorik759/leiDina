package main.java.leiDina.tec.javafx.exception;


import main.java.leiDina.tec.core.exception.BaseException;
import main.java.leiDina.tec.core.messages.MessageHolder;

/**
 * @author vitor.alves
 */
public class VFMLLoaderException extends BaseException {

    private static final long serialVersionUID = 6853471773656296804L;

    public VFMLLoaderException(MessageHolder messageHolder) {
        super(messageHolder);
    }

    public VFMLLoaderException(MessageHolder messageHolder, Throwable cause) {
        super(messageHolder, cause);
    }

    public VFMLLoaderException(Throwable cause) {
        super(cause);
    }
}
