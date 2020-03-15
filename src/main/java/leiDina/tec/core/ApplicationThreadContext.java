package main.java.leiDina.tec.core;

/**
 * @author vitor.alves
 */
public class ApplicationThreadContext {

    private static ThreadLocal<ApplicationContext> applicationThreadLocal = new ThreadLocal<>();

    public static void init(ApplicationContext applicationContext) {
        applicationThreadLocal.set(applicationContext);
    }

    public static ApplicationContext getApplicationContext() {
        ApplicationContext applicationContext = applicationThreadLocal.get();
        return applicationContext;
    }

}
