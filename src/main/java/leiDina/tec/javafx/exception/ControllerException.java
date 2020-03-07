package main.java.leiDina.tec.javafx.exception;


import main.java.leiDina.tec.messages.MessageHolder;

/**
 * @author vitor.alves
 */
public class ControllerException extends BaseException {

    private static final long serialVersionUID = 3841085676318767168L;

    public ControllerException(MessageHolder messageHolder) {
        super(messageHolder);
    }

    public ControllerException(MessageHolder messageHolder, Throwable cause) {
        super(messageHolder, cause);
    }

    public ControllerException(Throwable cause) {
        super(cause);
    }
}
