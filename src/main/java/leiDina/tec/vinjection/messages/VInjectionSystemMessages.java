package main.java.leiDina.tec.vinjection.messages;

import main.java.leiDina.tec.core.messages.MessageCreator;
import main.java.leiDina.tec.core.messages.MessageDelegate;
import main.java.leiDina.tec.core.messages.MessageHolder;

/**
 * @author vitor.alves
 */
public enum VInjectionSystemMessages implements MessageCreator {
    UNABLE_TO_RESOLVE_PROPERTY("unable.to.resolve.property", 2),

    UNABLE_TO_PARSE_XML("unable.to.parse.xml", 1);

    private MessageDelegate delagate;

    private final String bundle = "vinjection-system-messages-holder";

    private final String key;

    private final int numArgs;

    VInjectionSystemMessages(String key, int numArgs) {
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
