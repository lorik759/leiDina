package main.java.leiDina.tec.persister.messages;

import main.java.leiDina.tec.core.messages.MessageCreator;
import main.java.leiDina.tec.core.messages.MessageDelegate;
import main.java.leiDina.tec.core.messages.MessageHolder;

/**
 * @author vitor.alves
 */
public enum PersisterSystemMessages implements MessageCreator {

    OBJECT_NOT_ENTITY("object.not.entity", 1),

    UNABLE_TO_SAVE_ENTITY("unable.to.save.entity", 1),

    NO_GETTER_METHOD("no.getter.method", 2),

    ENTITY_NOT_FOUND("entity.not.found", 2),

    TOO_MANY_ENTITY_FOUND("to.many.entity.found", 2),

    ENTITY_ALREADY_EXISTS("entity.already.exists", 2),

    UNABLE_TO_REMOVE_ENTITY("unable.to.remove.entity", 1),

    FAILED_TO_CREATE_DAD("failed.to.create.dao", 1);

    private MessageDelegate delagate;

    private final String bundle = "persister-system-messages-holder";

    private final String key;

    private final int numArgs;

    PersisterSystemMessages(String key, int numArgs) {
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
