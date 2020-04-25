package main.java.leiDina.tec.core;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;
import main.java.leiDina.tec.core.context.ApplicationContext;
import main.java.leiDina.tec.core.context.PropertyApplicationContext;
import main.java.leiDina.tec.core.context.ApplicationThreadContext;
import main.java.leiDina.tec.core.env.ConfigurableApplicationProvider;
import main.java.leiDina.tec.core.env.PropertyConfigurableApplicationProvider;
import main.java.leiDina.tec.core.model.ApplicationDefinitions;
import main.java.leiDina.tec.vinjection.XmlInjectableApplicationContext;
import main.java.leiDina.tec.vinjection.env.XmlConfigurableApplicationProvider;

/**
 * A class that can bootstrap and launch a VApplication. The VApplication works as service loader, in which it creates and initializes a {@link
 * ApplicationContext}, with the {@link ConfigurableApplicationProvider} set in the VApplication.
 *
 * @author vitor.alves
 */
public class VApplication {

    private static final Logger logger = Logger.getLogger(VApplication.class.getName());

    private ApplicationDefinitions applicationDefinitions;

    private ConfigurableApplicationProvider environmentProvider;

    public VApplication(Class<?> primeryClasse) {
        this(new Class[]{primeryClasse});
    }

    public VApplication(Class<?>... primeryClasses) {
        Set<Class<?>> primeryClassesSet = new LinkedHashSet<>(Arrays.asList(primeryClasses));
        Class<?> mainApplicationClass = deduceMainClass();
        this.applicationDefinitions = new ApplicationDefinitions(logger, mainApplicationClass, primeryClassesSet);
    }

    private Class<?> deduceMainClass() {
        try {
            StackTraceElement[] stackTraceElements = new RuntimeException().getStackTrace();
            for (StackTraceElement stackTraceElement : stackTraceElements) {
                // using with a FX application, the starting method is "start"
                if ("main".equals(stackTraceElement.getMethodName()) || "start".equals(stackTraceElement.getMethodName())) {
                    return Class.forName(stackTraceElement.getClassName());
                }
            }
        } catch (ClassNotFoundException e) {
            // do nothing
        }
        return null;
    }

    /**
     * An auxiliary method for the start up of an VApplication, in which no instantiation of the VApplication will de required beforehand.
     *
     * @param primeryClasses the main class of the application.
     * @return a {@link ApplicationContext}.
     */
    public static ApplicationContext run(Class<?> primeryClasses) {
        return new VApplication(primeryClasses).run();
    }

    /**
     * The base starting method of an VApplication, in which the {@link ApplicationContext} is created and initialized.
     *
     * @return a {@link ApplicationContext}.
     */
    public ApplicationContext run() {
        logger.info("Starting VApplication");
        ApplicationContext applicationContext = createApplicationContext();
        applicationContext.setApplicationDefinitions(this.getAplicationDefinitions());
        applicationContext.init();
        ApplicationThreadContext.init(applicationContext);
        return applicationContext;
    }

    /**
     * If a {@link ConfigurableApplicationProvider} is not set, than a default implantation will be used.
     *
     * @return a {@link ConfigurableApplicationProvider}.
     */
    protected ConfigurableApplicationProvider getOrCreateEnvironmentProvider() {
        if (this.environmentProvider == null) {
            this.environmentProvider = new XmlConfigurableApplicationProvider();
        }
        return this.environmentProvider;
    }

    private ApplicationDefinitions getAplicationDefinitions() {
        ConfigurableApplicationProvider provider = this.getOrCreateEnvironmentProvider();
        this.applicationDefinitions.setConfigurableApplicationProvider(provider);
        return this.applicationDefinitions;
    }

    /**
     * @return creates a {@link ApplicationContext}.
     */
    protected ApplicationContext createApplicationContext() {
        return new XmlInjectableApplicationContext();
    }

    /**
     * Set a specific {@link ConfigurableApplicationProvider} fot the initialization of the {@link ApplicationContext}
     *
     * @param environmentProvider a {@link ConfigurableApplicationProvider}.
     */
    public void setEnvironmentProvider(ConfigurableApplicationProvider environmentProvider) {
        this.environmentProvider = environmentProvider;
    }
}
