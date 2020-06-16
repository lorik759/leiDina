package main.java.leiDina.tec.core;


import main.java.leiDina.tec.core.dependency.exception.DependencyException;
import main.java.leiDina.tec.core.dependency.factory.DependencyContainer;
import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironment;

/**
 * The root interface of a VApplication. Its through this interface that the application has acesse to all VApplication data, including acesse to the
 * VTEC dependency container. For a better understand of the default use of this interface, look at {@link DefaultApplicationContext}.
 *
 * @author vitor.alves
 */
public interface ApplicationContext extends DependencyContainer {

    /**
     * Sets the {@link ConfigurableApplicationEnvironment} for this context.
     *
     * @param configurableApplicationEnvironment a {@link ConfigurableApplicationEnvironment}.
     */
    void setConfigurableApplicationEnvironment(ConfigurableApplicationEnvironment configurableApplicationEnvironment);

    /**
     * Initializes the context.
     *
     * @throws DependencyException a {@link DependencyException}.
     */
    void initialize() throws DependencyException;

}
