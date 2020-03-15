package main.java.leiDina.tec.core.messages;

/**
 * @author vitor.alves
 */
public enum BaseSystemMessages implements MessageCreator {

    ;

    private MessageDelegate delagate;

    private final String bundle = "system-messages-holder";

    private final String key;

    private final int numArgs;

    BaseSystemMessages(String key, int numArgs) {
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
