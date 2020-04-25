package main.java.leiDina.tec.vinjection;

import main.java.leiDina.tec.core.context.ApplicationContext;

/**
 * @author vitor.alves
 */
public interface InjectableApplicationContext extends ApplicationContext {

    <T> T getBeanByType(Class<?> type);

    <T> T getBeanById(String id);

}
