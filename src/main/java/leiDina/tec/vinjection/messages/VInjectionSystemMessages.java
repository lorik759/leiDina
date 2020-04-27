package main.java.leiDina.tec.vinjection.messages;

import main.java.leiDina.tec.core.messages.MessageCreator;
import main.java.leiDina.tec.core.messages.MessageDelegate;
import main.java.leiDina.tec.core.messages.MessageHolder;

/**
 * @author vitor.alves
 */
public enum VInjectionSystemMessages implements MessageCreator {
    UNABLE_TO_RESOLVE_PROPERTY("unable.to.resolve.property", 2),

    UNABLE_TO_PARSE_XML("unable.to.parse.xml", 1),

    FAILED_TO_RESOLVE_KEY_OF_TYPE("failed.to.resolve.key.of.type", 2),

    REFERENCE_TO_BEAN_IS_NULL("ref.to.bean.is.null", 0),

    FAILED_TO_CREATE_BEAN_INSTANCE("failed.to.create.bean.instance", 1),

    NO_SETTER_METHOD_FOR_PROPERTY("no.setter.method.for.property", 2),

    FAILED_TO_WRITE_VALUE_TO_PROPERTY("failed.to.write.value.to.property", 3),

    CIRCULAR_DEPENDENCY("circular.dependency", 2),

    BEAN_NOT_FOUND("bean.not.found", 1);

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
