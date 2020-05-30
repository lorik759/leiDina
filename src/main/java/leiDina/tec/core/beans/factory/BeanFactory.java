package main.java.leiDina.tec.core.beans.factory;

import java.util.Map;
import main.java.leiDina.tec.core.beans.exception.BeanException;

/**
 * The root interface for a bean factory. This interface works as an accesses point to the VTEC Bean Container. For a bootable bean factory look at
 * {@link BootableBeanFactory}.
 *
 * @author vitor.alves
 */
public interface BeanFactory {

    /**
     * Gets the bean with the uniquely specified name.
     *
     * @param beanName the name of the bean.
     * @param <T> the type of the bean.
     * @return a bean withe the specified name.
     * @throws BeanException a {@link BeanException}
     */
    <T> T getBean(String beanName) throws BeanException;

    /**
     * Gets the bean with the specified type.
     *
     * @param type the {@link Class} of the requested bean.
     * @param <T> the type of the bean.
     * @return a bean of the specified class.
     * @throws BeanException a {@link BeanException}.
     */
    <T> T getBean(Class<?> type) throws BeanException;

    /**
     * @param name the name of the bean.
     * @return true if a bean instance of the specified name exists. Otherwise, return false.
     */
    boolean beanExists(String name);

    /**
     * Gets all beans that either extend ou implements the specified class, separated by name.
     *
     * @param type the classe that the beans either extend ou implements.
     * @param <T> the type of the beans.
     * @return a map of name by bean.
     * @throws BeanException a {@link BeanException}.
     */
    <T> Map<String, T> getBeansOfTypes(Class<T> type) throws BeanException;

    /**
     * Return the class of the bean with the specified name.
     *
     * @param name the name of the bean.
     * @return the class of the specified name.
     * @throws BeanException a {@link BeanException}
     */
    Class<?> getBeanType(String name) throws BeanException;

}
