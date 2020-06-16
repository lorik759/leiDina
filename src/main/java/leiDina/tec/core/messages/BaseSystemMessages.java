package main.java.leiDina.tec.core.messages;

/**
 * @author vitor.alves
 */
public enum BaseSystemMessages implements MessageCreator {

    OBJECT_IS_NOT_PARENT("object.is.not.parent", 2),

    OBJECT_DOESNT_EXTENDS_PARENT("object.doesnt.extends.parent", 4),

    FAILED_TO_RESOLVE_VALUE("failed.to.resolve.value", 0),

    OBJECT_NAME_ALREADY_IN_USE("object.name.already.in.use", 2),

    OBJECT_DEFINITION_DOESNT_EXIST("object.definition.doesnt.exist", 1),

    OBJECT_DEFINITION_OF_TYPE_DOESNT_EXIST("object.definition.of.type.doesnt.exist", 1),

    OBJECT_DEFINITION_ALREADY_EXISTS("object.definition.already.exists", 2),

    FAILED_TO_INSTANTIATE_OBJECT("failed.to.instantiate.object", 3),

    FAILED_TO_POPULATE_OBJECT("failed.to.populate.object", 3),

    CIRCULAR_OBJECT_DEPENDENCY("circular.object.dependency", 1),

    FAILED_TO_WIRE_OBJECT("failed.to.wire.object", 2);
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
