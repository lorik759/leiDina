package main.java.leiDina.tec.core.dependency.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import main.java.leiDina.tec.core.dependency.exception.DependencyCreationException;
import main.java.leiDina.tec.core.dependency.exception.DependencyException;
import main.java.leiDina.tec.core.dependency.model.BaseObjectDefinition;
import main.java.leiDina.tec.core.dependency.model.ObjectDefinition;
import main.java.leiDina.tec.core.dependency.model.DefaultObjectDefinitionHolder;
import main.java.leiDina.tec.core.dependency.model.PropertyValue;
import main.java.leiDina.tec.core.dependency.service.DependencyWire;
import main.java.leiDina.tec.core.dependency.service.ObjectPopulateService;
import main.java.leiDina.tec.core.dependency.service.DependencyWireImp;
import main.java.leiDina.tec.core.dependency.service.ObjectInstantiationService;
import main.java.leiDina.tec.core.dependency.service.PropertyValueResolver;
import main.java.leiDina.tec.core.messages.BaseSystemMessages;

/**
 * The default implementation of the {@link BootableDependencyContainer}. This dependency container creates eagerly singleton instantiations for all {@link
 * ObjectDefinition} register to this object.
 *
 * @author vitor.alves
 */
public class DefaultDependencyContainer extends DefaultObjectDefinitionHolder implements BootableDependencyContainer {

    private static final String DEPENDENCY_WIRE = "dependencyWire";

    private final Map<String, Object> objectsByName = new HashMap<>();

    private final Map<Class<?>, Map<String, ?>> objectsByType = new HashMap<>();

    private final List<String> objectNamesBeingResolved = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T getObject(String objectName) throws DependencyException {
        return (T) getOrCreateObject(objectName);
    }

    /**
     * gets or creates a object instance for the specified object name.
     *
     * @param objectName the name of the required object.
     * @return the instance of the object.
     */
    private Object getOrCreateObject(String objectName) {
        Object object = this.getCachedObject(objectName);
        if (object == null) {
            object = this.getNewObjectInstance(objectName);
            this.addObjectToCache(objectName, object);
        }
        return object;
    }

    /**
     * Adds the object instance to this factory cache.
     *
     * @param objectName the name of the object.
     * @param object the instance of the object.
     */
    private void addObjectToCache(String objectName, Object object) {
        this.objectsByName.put(objectName, object);
    }

    /**
     * Creates the instance of the specified object name, based on a {@link ObjectDefinition} with the same name. To create the instance, it must first
     * check if the object is already being resolved, if not the name of the object is added to the list of objects being resolved, and an instance is
     * created for it.
     *
     * @param objectName the name of the object.
     * @return the instance of the object with the specified name.
     */
    private Object getNewObjectInstance(String objectName) throws DependencyException {
        if (this.isObjectAlreadyBeingResolved(objectName)) {
            throw new DependencyCreationException(BaseSystemMessages.CIRCULAR_OBJECT_DEPENDENCY.create(objectName));
        } else {
            return this.createInstanceFor(objectName);
        }
    }

    private Object createInstanceFor(String objectName) {
        this.addObjectToBeingResolved(objectName);
        Object objecInstance = this.doCreateInstanceFor(objectName);
        this.removeObjectNameFromBeingResolved(objectName);
        return objecInstance;
    }

    private void addObjectToBeingResolved(String objectName) {
        this.objectNamesBeingResolved.add(objectName);
    }

    private Object doCreateInstanceFor(String objectName) {
        ObjectDefinition objectDefinition = this.getObjectDefinitionForName(objectName);
        Object objecInstance = this.createNewObjectInstance(objectDefinition);
        this.populateObject(objectDefinition, objecInstance);
        return objecInstance;
    }

    private void removeObjectNameFromBeingResolved(String objectName) {
        this.objectNamesBeingResolved.remove(objectName);
    }

    private boolean isObjectAlreadyBeingResolved(String objectName) {
        return this.objectNamesBeingResolved.contains(objectName);
    }

    /**
     * Populates the object instance with its dependencies mapped in the {@link ObjectDefinition}.
     *
     * @param objectDefinition the {@link ObjectDefinition} with all the dependencies of the object instance.
     * @param objecInstance the instance of the mapped object in the specified {@link ObjectDefinition}.
     */
    private void populateObject(ObjectDefinition objectDefinition, Object objecInstance) {
        this.doPopulateObject(objectDefinition, objecInstance, this.getResolvedPropertyValues(objectDefinition));
    }

    private void doPopulateObject(ObjectDefinition objectDefinition, Object objectInstance, List<PropertyValue> resolvedPropertyValues)
        throws DependencyCreationException {
        try {
            new ObjectPopulateService(resolvedPropertyValues, objectDefinition).populate(objectInstance);
        } catch (Exception e) {
            throw new DependencyCreationException(BaseSystemMessages.FAILED_TO_POPULATE_OBJECT
                .create(objectDefinition.getName(), objectDefinition.getObjectClass(), objectDefinition.getLocationName()), e);
        }
    }

    /**
     * Gets the resolved values of the {@link PropertyValue} from the {@link ObjectDefinition}.
     *
     * @param objectDefinition the {@link ObjectDefinition} with the unresolved {@link PropertyValue}.
     * @return a list of the resolved {@link PropertyValue} from within the {@link ObjectDefinition}.
     */
    private List<PropertyValue> getResolvedPropertyValues(ObjectDefinition objectDefinition) {
        final List<PropertyValue> resolvedPropertyValues = new ArrayList<>();
        objectDefinition.getPropertyValues().forEach(propertyValue -> resolvedPropertyValues.add(this.getResolvedValue(propertyValue)));
        return resolvedPropertyValues;
    }

    private PropertyValue getResolvedValue(PropertyValue propertyValue) {
        return new PropertyValueResolver(this, propertyValue).resolve();
    }

    private Object createNewObjectInstance(ObjectDefinition objectDefinition) throws DependencyCreationException {
        try {
            return new ObjectInstantiationService(objectDefinition).newInstance();
        } catch (ReflectiveOperationException e) {
            throw new DependencyCreationException(BaseSystemMessages.FAILED_TO_INSTANTIATE_OBJECT
                .create(objectDefinition.getName(), objectDefinition.getObjectClass(), objectDefinition.getLocationName()));
        }
    }

    private <T> T getCachedObject(String objectName) {
        return (T) this.objectsByName.get(objectName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T getObject(Class<?> type) {
        return this.getObject(this.getObjectName(type));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean objectExists(String name) {
        return objectsByName.get(name) != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> Map<String, T> getObjectsOfTypes(Class<T> type) {
        Map<String, T> candidateObjects = this.getCachedObjects(type);
        if (candidateObjects == null) {
            candidateObjects = this.findObjectFor(type);
            this.addMapToCache(type, candidateObjects);
        }
        return candidateObjects;
    }

    /**
     * finds all objects of the specified type and creates a map the object insentience by the object name.
     *
     * @param type the type of the specified object.
     * @return a map of the object instance by object name.
     */
    private <T> Map<String, T> findObjectFor(Class<T> type) {
        final Map<String, T> candidateObjects = new HashMap<>();
        this.findCandidatesFor(type).forEach(candidateName -> candidateObjects.put(candidateName, this.getObject(candidateName)));
        return candidateObjects;
    }

    private void addMapToCache(Class<?> type, Map<String, ?> objecs) {
        this.objectsByType.put(type, objecs);
    }

    /**
     * Finds all objects names that are of the specified type.
     *
     * @param type the type of the searched objects.
     * @return a list of all objects names that are of the specified type.
     */
    private <T> List<String> findCandidatesFor(Class<T> type) {
        List<String> possibleObjectNamesCandidates = new ArrayList<>();
        for (String objectName : this.getAllObjectNames()) {
            ObjectDefinition objectDefinition = this.getObjectDefinitionForName(objectName);
            if (objectDefinition.isOfType(type)) {
                possibleObjectNamesCandidates.add(objectName);
            }
        }
        return possibleObjectNamesCandidates;
    }

    private <T> Map<String, T> getCachedObjects(Class<T> type) {
        return (Map<String, T>) this.objectsByType.get(type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> getObjectType(String name) throws DependencyException {
        return this.getObjectDefinitionForName(name).getObjectClass();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void instantiateSingletons() {
        this.instantiateFactoryObjects();
        this.instantiateMappedObjects();
    }

    /**
     * Instantiate all objects that where mapped by the {@link ObjectDefinition} and registered to this dependency container.
     */
    private void instantiateMappedObjects() {
        for (String objectName : this.getAllObjectNames()) {
            if (!this.objectExists(objectName)) {
                this.getObject(objectName);
            }
        }
    }

    /**
     * Instantiates factory objects. This are objects that are provided by default, by the dependency container.
     */
    private void instantiateFactoryObjects() {
        this.createObjectWire();
    }

    private void createObjectWire() {
        this.addObjectDefinition(new BaseObjectDefinition(DEPENDENCY_WIRE, DependencyWire.class));
        this.addObjectToCache(DEPENDENCY_WIRE, new DependencyWireImp(this));
    }
}
