package main.java.leiDina.tec.core.dependency.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import main.java.leiDina.tec.core.dependency.exception.DependencyDefinitionException;
import main.java.leiDina.tec.core.dependency.exception.DependencyException;
import main.java.leiDina.tec.core.messages.BaseSystemMessages;
import main.java.leiDina.tec.core.utils.StringUtils;

/**
 * This is the default implementation af the {@link ObjectDefinitionHolder} interface. This class contains and organizes the object definitions of the
 * context.
 *
 * @author vitor.alves
 */
public class DefaultObjectDefinitionHolder implements ObjectDefinitionHolder {

    private final Map<String, ObjectDefinition> objectDefinitionByName = new HashMap<>();

    private final Map<Class<?>, String> objectDefinitionNameByType = new HashMap<>();

    private final Set<String> objectDefinitionNames = new HashSet<>();

    private final Set<ObjectDefinition> allObjectDefinitions = new HashSet<>();

    private boolean allowOverwrite = false;

    /**
     * Adds the {@link ObjectDefinition} to this context, validating: 1) If it already doesn't exist. 2) If the object name is already in use. If this
     * {@link ObjectDefinitionHolder} is set to overwrite, it will remove the old {@link ObjectDefinition}, and add the new {@link ObjectDefinition}.
     * Otherwise, it will throw a {@link DependencyDefinitionException}.
     *
     * @param objectDefinition the {@link ObjectDefinition} to be added.
     */
    @Override
    public void addObjectDefinition(ObjectDefinition objectDefinition) throws DependencyException {
        this.validateObjectDefinition(objectDefinition);
        if (this.allowOverwrite) {
            this.doRemoveObjectDefinition(objectDefinition);
        }
        this.doAddObjectDefinition(objectDefinition);
    }

    /**
     * Validates the {@link ObjectDefinition} and checking if: 1) If it already doesn't exist. 2) If the object name is already in use. If any of this
     * criterias are meet, and this {@link ObjectDefinitionHolder is not set to overwrite, than an {@link DependencyDefinitionException } is thrown.
     *
     * @param objectDefinition the {@link ObjectDefinition } to Validate.
     */
    private void validateObjectDefinition(ObjectDefinition objectDefinition) {
        String name = objectDefinition.getName();
        if (this.existsObjectDefinition(objectDefinition) && this.isNotAllowOverwriteObjectDefinition()) {
            throw new DependencyDefinitionException(BaseSystemMessages.OBJECT_DEFINITION_ALREADY_EXISTS.create(name, objectDefinition.getObjectClass()));
        }
        if (this.isObjectDefinitionNameInUse(name) && this.isNotAllowOverwriteObjectDefinition()) {
            throw new DependencyDefinitionException(BaseSystemMessages.OBJECT_NAME_ALREADY_IN_USE.create(name, objectDefinition.getLocationName()));
        }
    }

    private boolean isNotAllowOverwriteObjectDefinition() {
        return !this.isAllowOverwriteObjectDefinition();
    }

    private void doAddObjectDefinition(ObjectDefinition objectDefinition) {
        this.allObjectDefinitions.add(objectDefinition);
        String name = objectDefinition.getName();
        this.objectDefinitionNames.add(name);
        this.objectDefinitionNameByType.put(objectDefinition.getObjectClass(), name);
        this.objectDefinitionByName.put(name, objectDefinition);
    }

    /**
     * Checks to see if the specified {@link ObjectDefinition} exists. If it dose, than it is removed from this Holder. Otherwise, an {@link DependencyDefinitionException}} is thrown.
     *
     * @param objectDefinition the {@link ObjectDefinition} to be removed.
     * @throws DependencyException a {@link DependencyDefinitionException} if the specified {@link ObjectDefinition} dose not existe.
     */
    @Override
    public void removeObjectDefinition(ObjectDefinition objectDefinition) throws DependencyException {
        if (!this.existsObjectDefinition(objectDefinition)) {
            throw new DependencyDefinitionException(BaseSystemMessages.OBJECT_DEFINITION_DOESNT_EXIST.create(objectDefinition.getName()));
        }
        this.doRemoveObjectDefinition(objectDefinition);
    }

    private void doRemoveObjectDefinition(ObjectDefinition objectDefinition) {
        String name = objectDefinition.getName();
        ObjectDefinition objectDefinitionToRemove = this.objectDefinitionByName.get(name);
        this.allObjectDefinitions.remove(objectDefinitionToRemove);
        this.objectDefinitionNames.remove(name);
        this.objectDefinitionByName.remove(name);
        this.objectDefinitionNameByType.remove(objectDefinitionToRemove.getObjectClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsObjectDefinition(ObjectDefinition objectDefinition) {
        return this.allObjectDefinitions.contains(objectDefinition);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isObjectDefinitionNameInUse(String objectName) {
        return this.objectDefinitionNames.contains(objectName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAllowOverwriteObjectDefinition() {
        return this.allowOverwrite;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void allowOverwriteObjectDefinition(boolean allowOverwrite) {
        this.allowOverwrite = allowOverwrite;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectDefinition getObjectDefinitionForName(String name) throws DependencyException {
        ObjectDefinition objectDefinition = this.objectDefinitionByName.get(name);
        if (objectDefinition == null) {
            throw new DependencyDefinitionException(BaseSystemMessages.OBJECT_DEFINITION_DOESNT_EXIST.create(name));
        }
        return objectDefinition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectDefinition getObjectDefinitionForType(Class<?> objectClass) throws DependencyException {
        String name = this.objectDefinitionNameByType.get(objectClass);
        if (StringUtils.isEmpty(name)) {
            throw new DependencyDefinitionException(BaseSystemMessages.OBJECT_DEFINITION_OF_TYPE_DOESNT_EXIST.create(objectClass));
        }
        return this.getObjectDefinitionForName(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getObjectName(Class<?> type) {
        return this.objectDefinitionNameByType.get(type);
    }

    protected Set<String> getAllObjectNames() {
        return this.objectDefinitionNames;
    }
}
