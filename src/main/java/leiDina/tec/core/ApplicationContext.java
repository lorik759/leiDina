package main.java.leiDina.tec.core;


import main.java.leiDina.tec.core.env.ConfigurableApplicationProvider;
import main.java.leiDina.tec.core.model.SystemServiceKey;
import main.java.leiDina.tec.core.service.SystemService;

/**
 * A root interface of an application context. This interface contains all base {@link SystemService} of an {@link VApplication}.
 *
 * @author vitor.alves
 */
public interface ApplicationContext {

    /**
     * A base method to initialize the application context with a reference to the starter class.
     */
    void init();

    /**
     * Set the {@link ConfigurableApplicationProvider} of the application. This method is to be used only on application startup.
     *
     * @param environmentProvider the {@link ConfigurableApplicationProvider} to be used by the application.
     */
    void setEnvironmentProvider(ConfigurableApplicationProvider environmentProvider);

    /**
     * @param key a {@link SystemServiceKey} that represents a {@link SystemService}.
     * @return the {@link SystemService} of the specified {@link SystemServiceKey}.
     */
    SystemService getService(SystemServiceKey key);
}
