package main.java.leiDina.tec.core.beans.model;


import main.java.leiDina.tec.core.beans.exception.BeanException;

/**
 * The root interface of an object that is able to store and organize {@link BeanDefinition}. All mapped beans are converted to a {@link
 * BeanDefinition} and stored in this object, where a {@link main.java.leiDina.tec.core.beans.factory.BootableBeanFactory} will read and use to
 * instantiate the beans. For the default implementation and use of this interface look at {@link DefaultBeanDefinitionHolder}.
 *
 * @author vitor.alves
 */
public interface BeanDefinitionHolder {

    /**
     * Adds the {@link BeanDefinition} to this holder.
     *
     * @param beanDefinition the {@link BeanDefinition} to be added.
     * @throws BeanException a {@link BeanException}.
     */
    void addBeanDefinition(BeanDefinition beanDefinition) throws BeanException;

    /**
     * Removes the specified {@link BeanDefinition} from this holder. Important to note, this dos not remove the instance of the bean definition. It
     * only removes the definition so that an instance of the bean definition cant be created.
     *
     * @param beanDefinition the {@link BeanDefinition} to be removed.
     * @throws BeanException a {@link BeanException}.
     */
    void removeBeanDefinition(BeanDefinition beanDefinition) throws BeanException;

    /**
     * Checks to see if the specified {@link BeanDefinition} is already register to this {@link BeanDefinitionHolder}.
     *
     * @param beanDefinition a {@link BeanDefinition}.
     * @return true if the specified {@link BeanDefinition} already existes, and false otherwise.
     */
    boolean existsBeanDefinition(BeanDefinition beanDefinition);

    /**
     * Checks to see if the specified name is already in use.
     *
     * @param beanName the bean name.
     * @return true if the bean name is in used, false otherwise.
     */
    boolean isBeanDefinitionNameInUse(String beanName);

    /**
     * Checks to see if the holder allows {@link BeanDefinition} with the same name can be overwritten.
     *
     * @return true if a {@link BeanDefinition} can be overwritten.
     */
    boolean isAllowOverwriteBeanDefinition();

    /**
     * Sets is this {@link BeanDefinitionHolder} can overwrite {@link BeanDefinition}.
     */
    void allowOverwriteBeanDefinition(boolean allowOverwrite);

    /**
     * Gets the {@link BeanDefinition} for the specified name.
     *
     * @param name the name of the {@link BeanDefinition}.
     * @return the {@link BeanDefinition} with the specified name.
     * @throws BeanException a {@link BeanException}.
     */
    BeanDefinition getBeanDefinitionForName(String name) throws BeanException;

    /**
     * Gets the {@link BeanDefinition} that contais same bean class of the specified class.
     *
     * @param beanClass the bean class that a {@link BeanDefinition} contains.
     * @return the {@link BeanDefinition} that contains the specified class.
     * @throws BeanException a {@link BeanException}.
     */
    BeanDefinition getBeanDefinitionForType(Class<?> beanClass) throws BeanException;

    /**
     * Gets the bean name of the specified class.
     *
     * @param type the class of the searched bean name.
     * @return a bean name.
     */
    String getBeanName(Class<?> type);

}
