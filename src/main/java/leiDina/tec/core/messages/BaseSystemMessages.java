package main.java.leiDina.tec.core.messages;

/**
 * @author vitor.alves
 */
public enum BaseSystemMessages implements MessageCreator {

    BEAN_IS_NOT_PARENT("bean.is.not.parent", 2),

    BEAN_DOESNT_EXTENDS_PARENT("bean.doesnt.extends.parent", 4),

    FAILED_TO_RESOLVE_VALUE("failed.to.resolve.value", 0),

    BEAN_NAME_ALREADY_IN_USE("bean.name.already.in.use", 2),

    BEAN_DEFINITION_DOESNT_EXIST("bean.definition.doesnt.exist", 1),

    BEAN_DEFINITION_OF_TYPE_DOESNT_EXIST("bean.definition.of.type.doesnt.exist", 1),

    BEAN_DEFINITION_ALREADY_EXISTS("bean.definition.already.exists", 2),

    FAILED_TO_INSTANTIATE_BEAN("failed.to.instantiate.bean", 3),

    FAILED_TO_POPULATE_BEAN("failed.to.populate.bean", 3),

    CIRCULAR_BEAN_DEPENDENCY("circular.bean.dependency", 1),

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
