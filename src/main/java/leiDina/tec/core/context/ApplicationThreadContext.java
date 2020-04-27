package main.java.leiDina.tec.core.context;

import main.java.leiDina.tec.core.model.SystemServiceKey;
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
     * Gets the service of the specified {@link SystemServiceKey} from the {@link ApplicationContext} of the thread.
     *
     * @param key the {@link SystemServiceKey} of the service.
     * @return a {@link SystemService}.
     */
    public static SystemService getService(SystemServiceKey key) {
        ApplicationContext applicationContext = getApplicationContext();
        return applicationContext.getService(key);
    }
}
