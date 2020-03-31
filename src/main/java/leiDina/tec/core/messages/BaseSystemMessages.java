package main.java.leiDina.tec.core.messages;

/**
 * @author vitor.alves
 */
public enum BaseSystemMessages implements MessageCreator {

    OBJECT_NOT_ENTITY("object.not.entity", 1),

    UNABLE_TO_SAVE_ENTITY("unable.to.save.entity", 1),

    NO_GETTER_METHOD("no.getter.method", 2),

    ENTITY_NOT_FOUND("entity.not.found", 2),

    TOO_MANY_ENTITY_FOUND("to.many.entity.found", 2),

    ENTITY_ALREADY_EXISTS("entity.already.exists", 2),

    UNABLE_TO_REMOVE_ENTITY("unable.to.remove.entity", 1);

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
