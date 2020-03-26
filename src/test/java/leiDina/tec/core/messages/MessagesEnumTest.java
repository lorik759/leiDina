package java.leiDina.tec.core.messages;


import main.java.leiDina.tec.core.messages.MessageCreator;
import main.java.leiDina.tec.core.messages.MessageDelegate;
import main.java.leiDina.tec.core.messages.MessageHolder;

/**
 * @author vitor.alves
 */
public enum MessagesEnumTest implements MessageCreator {
    ONE_PARAM_TEST("one.param", 1),
    NO_PARAM_TEST("no.param", 0);

    private MessageDelegate messageDelegate;

    private final String bundle = "system-messages-holder-test";

    private final String key;

    private final int numArgs;

    MessagesEnumTest(String key, int numArgs) {
        this.key = key;
        this.numArgs = numArgs;
        this.messageDelegate = MessageDelegate.getMessageDelegate();
    }

    @Override
    public MessageHolder create(Object... args) {
        return this.messageDelegate.createMessageHolder(this, args);
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
