package main.java.leiDina.tec.core;

import main.java.leiDina.tec.core.model.SystemKey;
import main.java.leiDina.tec.core.service.SystemService;

/**
 * The context holder of the application for the thread.
 *
 * @author vitor.alves
 */
public class ApplicationThreadContext {

    private static ThreadLocal<ApplicationContext> applicationThreadLocal = new ThreadLocal<>();

    /**
     * Initialize the thread context.
     *
     * @param applicationContext the {@link ApplicationContext} of the thread.
     */
    public static void init(ApplicationContext applicationContext) {
        applicationThreadLocal.set(applicationContext);
    }

    /**
     * @return the {@link ApplicationContext} of the thread.
     */
    public static ApplicationContext getApplicationContext() {
        ApplicationContext applicationContext = applicationThreadLocal.get();
        return applicationContext;
    }

    /**
     * Gets the service of the specified {@link SystemKey} from the {@link ApplicationContext} of the thread.
     *
     * @param key the {@link SystemKey} of the service.
     * @return a {@link SystemService}.
     */
    public static SystemService getService(SystemKey key) {
        ApplicationContext applicationContext = getApplicationContext();
        return applicationContext.getService(key);
    }
}
