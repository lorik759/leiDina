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

    <T> T getBean(Class<?> type) throws BeanException;

    boolean beanExists(String name);

    <T> Map<String, T> getBeansOfTypes(Class<T> type) throws BeanException;

    Class<?> getBeanType(String name) throws BeanException;

}
