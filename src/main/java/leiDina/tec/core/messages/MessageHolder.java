package main.java.leiDina.tec.core.messages;

import java.io.Serializable;

/**
 * A simple interface that holds a message that can be serializable.
 *
 * @author vitor.alves
 */
public interface MessageHolder extends Serializable {

    /**
     * @return returns a simple message
     */
    String getMessage();

}
