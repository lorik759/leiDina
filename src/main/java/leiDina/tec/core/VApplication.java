package main.java.leiDina.tec.core;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;
import main.java.leiDina.tec.core.env.ConfigurableApplicationProvider;
import main.java.leiDina.tec.core.env.ConfigurableApplicationProviderImpl;
import main.java.leiDina.tec.core.model.ApplicationDefinitions;

/**
 * A class that can bootstrap and launch a VApplication. The VApplication works as an additional structure for a simple fx application, in which a
 * VApplication adds a full integration with javafx controllers and models, for a full MVC structure.
 *
 * @author vitor.alves
 */
public class VApplication {

    private static final Logger logger = Logger.getLogger(VApplication.class.getName());

    private ApplicationDefinitions applicationDefinitions;

    private ConfigurableApplicationProvider environmentProvider;

    public VApplication(Class<?> primeryClasses) {
        this(new Class[]{primeryClasses});
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

    public static ApplicationContext run(Class<?> primeryClasses) {
        return new VApplication(primeryClasses).run();
    }

    public ApplicationContext run() {
        logger.info("Starting VApplication");
        ApplicationContext applicationContext = createApplicationContext();
        ConfigurableApplicationProvider applicationEnvironmentProvider = getOrCreateEnvironmentProvider();
        applicationContext.setEnvironmentProvider(applicationEnvironmentProvider);
        applicationContext.init();
        ApplicationThreadContext.init(applicationContext);
        return applicationContext;
    }

    protected ConfigurableApplicationProvider getOrCreateEnvironmentProvider() {
        if (this.environmentProvider == null) {
            this.environmentProvider = new ConfigurableApplicationProviderImpl();
        }
        return this.environmentProvider;
    }

    protected ApplicationContext createApplicationContext() {
        return new ApplicationContextImpl(applicationDefinitions);
    }

    public void setEnvironmentProvider(ConfigurableApplicationProvider environmentProvider) {
        this.environmentProvider = environmentProvider;
    }
}
