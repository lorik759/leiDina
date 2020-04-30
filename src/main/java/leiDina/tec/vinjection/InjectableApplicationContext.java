package main.java.leiDina.tec.vinjection;

import main.java.leiDina.tec.core.context.ApplicationContext;

/**
 * A {@link ApplicationContext} that works as dependency container.
 *
 * @author vitor.alves
 */
public interface InjectableApplicationContext extends ApplicationContext {

    /**
     * Returns a Bean of the specified type.
     *
     * @param type the {@link Class} of the requested bean.
     * @param <T> the Type of the bean.
     * @return the bean of the specified class.
     */
    <T> T getBeanByType(Class<?> type);

    /**
     * Returns a bean with the specified id.
     *
     * @param id the id of the requested bean.
     * @param <T> the Type of the bean.
     * @return the bean with the specified id.
     */
    <T> T getBeanById(String id);

}
