package main.java.leiDina.tec.javafx.messages;

import main.java.leiDina.tec.core.messages.MessageCreator;
import main.java.leiDina.tec.core.messages.MessageDelegate;
import main.java.leiDina.tec.core.messages.MessageHolder;

/**
 * @author vitor.alves
 */
public enum FXSystemMessages implements MessageCreator {

    INSTANTIATING_CONTROLLER_EXCEPTION("instantiating.controller.exception", 1),

    CREATE_CONTROLLER_BUILDER_EXCEPTION("create.controller.builder", 1);

    private MessageDelegate delagate;

    private final String bundle = "fx-system-messages-holder";

    private final String key;

    private final int numArgs;

    FXSystemMessages(String key, int numArgs) {
        this.key = key;
        this.numArgs = numArgs;
        this.delagate = MessageDelegate.getMessageDelegate();
    }
    @Override
    public MessageHolder create(Object... args) {
        return delagate.createMessageHolder(this, args);
    }

    @Override
    public int getNumArgs() {
        return this.numArgs;
    }

    @Override
    public String getBundle() {
        return this.bundle;
    }

    @Override
    public String getKey() {
        return this.key;
    }
}
