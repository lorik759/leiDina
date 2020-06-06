package main.java.leiDina.tec.core;


import main.java.leiDina.tec.core.beans.exception.BeanException;
import main.java.leiDina.tec.core.beans.factory.BeanFactory;
import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironment;

/**
 * The root interface of a VApplication. Its through this interface that the application has acesse to all VApplication data, including acesse to the
 * VTEC dependency container. For a better understand of the default use of this interface, look at {@link DefaultApplicationContext}.
 *
 * @author vitor.alves
 */
public interface ApplicationContext extends BeanFactory {

    /**
     * Sets the {@link ConfigurableApplicationEnvironment} for this context.
     *
     * @param configurableApplicationEnvironment a {@link ConfigurableApplicationEnvironment}.
     */
    void setConfigurableApplicationEnvironment(ConfigurableApplicationEnvironment configurableApplicationEnvironment);

    /**
     * Initializes the context.
     *
     * @throws BeanException a {@link BeanException}.
     */
    void initialize() throws BeanException;

}
