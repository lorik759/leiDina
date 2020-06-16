package main.java.leiDina.tec.core.dependency.service;


import main.java.leiDina.tec.core.dependency.model.ObjectDefinition;
import main.java.leiDina.tec.core.utils.ReflectionUtils;

/**
 * Instantiates a new instance of the {@link ObjectDefinition}.
 *
 * @author vitor.alves
 */
//TODO: Add dependency of the instantiation throw specified constructor.
public class ObjectInstantiationService {

    private final ObjectDefinition objectDefinition;

    public ObjectInstantiationService(ObjectDefinition objectDefinition) {
        this.objectDefinition = objectDefinition;
    }

    /**
     * Creates new instance oh the {@link ObjectDefinition#getObjectClass()}.
     *
     * @return a instance of {@link ObjectDefinition#getObjectClass()}.
     * @throws ReflectiveOperationException a {@link ReflectiveOperationException}.
     */
    public Object newInstance() throws ReflectiveOperationException {
        return ReflectionUtils.newInstance(objectDefinition.getObjectClass());
    }
}
