package main.java.leiDina.tec.core.dependency.model;


import main.java.leiDina.tec.core.dependency.exception.DependencyException;
import main.java.leiDina.tec.core.dependency.factory.BootableDependencyContainer;

/**
 * The root interface of an object that is able to store and organize {@link ObjectDefinition}. All mapped objects are converted to a {@link
 * ObjectDefinition} and stored in this object, where a {@link BootableDependencyContainer} will read and use to
 * instantiate the objects. For the default implementation and use of this interface look at {@link DefaultObjectDefinitionHolder}.
 *
 * @author vitor.alves
 */
public interface ObjectDefinitionHolder {

    /**
     * Adds the {@link ObjectDefinition} to this holder.
     *
     * @param objectDefinition the {@link ObjectDefinition} to be added.
     * @throws DependencyException a {@link DependencyException}.
     */
    void addObjectDefinition(ObjectDefinition objectDefinition) throws DependencyException;

    /**
     * Removes the specified {@link ObjectDefinition} from this holder. Important to note, this dos not remove the instance of the object definition. It
     * only removes the definition so that an instance of the object definition cant be created.
     *
     * @param objectDefinition the {@link ObjectDefinition} to be removed.
     * @throws DependencyException a {@link DependencyException}.
     */
    void removeObjectDefinition(ObjectDefinition objectDefinition) throws DependencyException;

    /**
     * Checks to see if the specified {@link ObjectDefinition} is already register to this {@link ObjectDefinitionHolder}.
     *
     * @param objectDefinition a {@link ObjectDefinition}.
     * @return true if the specified {@link ObjectDefinition} already existes, and false otherwise.
     */
    boolean existsObjectDefinition(ObjectDefinition objectDefinition);

    /**
     * Checks to see if the specified name is already in use.
     *
     * @param objectName the object name.
     * @return true if the object name is in used, false otherwise.
     */
    boolean isObjectDefinitionNameInUse(String objectName);

    /**
     * Checks to see if the holder allows {@link ObjectDefinition} with the same name can be overwritten.
     *
     * @return true if a {@link ObjectDefinition} can be overwritten.
     */
    boolean isAllowOverwriteObjectDefinition();

    /**
     * Sets is this {@link ObjectDefinitionHolder} can overwrite {@link ObjectDefinition}.
     */
    void allowOverwriteObjectDefinition(boolean allowOverwrite);

    /**
     * Gets the {@link ObjectDefinition} for the specified name.
     *
     * @param name the name of the {@link ObjectDefinition}.
     * @return the {@link ObjectDefinition} with the specified name.
     * @throws DependencyException a {@link DependencyException}.
     */
    ObjectDefinition getObjectDefinitionForName(String name) throws DependencyException;

    /**
     * Gets the {@link ObjectDefinition} that contais same object class of the specified class.
     *
     * @param objectClass the object class that a {@link ObjectDefinition} contains.
     * @return the {@link ObjectDefinition} that contains the specified class.
     * @throws DependencyException a {@link DependencyException}.
     */
    ObjectDefinition getObjectDefinitionForType(Class<?> objectClass) throws DependencyException;

    /**
     * Gets the object name of the specified class.
     *
     * @param type the class of the searched object name.
     * @return a object name.
     */
    String getObjectName(Class<?> type);

}
