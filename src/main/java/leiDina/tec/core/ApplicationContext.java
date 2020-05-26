package main.java.leiDina.tec.core;


import main.java.leiDina.tec.core.beans.exception.BeanException;
import main.java.leiDina.tec.core.beans.factory.BeanFactory;
import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironment;

/**
 * @author vitor.alves
 */
public interface ApplicationContext extends BeanFactory {

    void setConfigurableApplicationEnvironment(ConfigurableApplicationEnvironment configurableApplicationEnvironment);

    void initialize() throws BeanException;

}
