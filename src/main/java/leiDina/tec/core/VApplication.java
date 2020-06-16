package main.java.leiDina.tec.core;


import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironment;
import main.java.leiDina.tec.core.env.DefaultConfigurableApplicationEnvironment;

/**
 * A class that can bootstrap and launch a VApplication. A VApplication is a simple dependency injection framework that creates a dependency container
 * as an {@link ApplicationContext} for eager, singleton objects, based on a {@link ConfigurableApplicationEnvironment}. The application can be
 * bootstrap through the main method on application startup:
 *
 * <code>
 *     public static void main(String[] args) {
 *         ApplicationContext applicationContext = VApplication.start();
 *         ...
 *     }
 * </code>
 *
 * Or in a java fx application in the start method:
 *
 * <code>
 *     public void start(Stage primaryStage) throws Exception {
 *         ApplicationContext applicationContext = VApplication.start();
 *         ...
 *     }
 * </code>
 *
 * For more information in the default configuration of an VApplication, look at {@link DefaultConfigurableApplicationEnvironment}
 * and {@link DefaultApplicationContext}.
 *
 * @author vitor.alves
 */
public class VApplication {

    private ConfigurableApplicationEnvironment environment;

    /**
     * The default starting point for a VApplication. Through this method a VApplication is instantiated and run.
     *
     * @return a {@link ApplicationContext}.
     */
    public static ApplicationContext start() {
        return new VApplication().run();
    }

    /**
     * This method creates and initializes the {@link ApplicationContext}.
     *
     * @return a {@link ApplicationContext}.
     */
    public ApplicationContext run() {
        ConfigurableApplicationEnvironment environment = this.getOrCreateEnvironment();
        ApplicationContext applicationContext = this.creatContext();
        this.setupContext(applicationContext, environment);
        this.initializeContext(applicationContext);
        return applicationContext;
    }

    /**
     * This method initializes the {@link ApplicationContext} through the {@link ApplicationContext#initialize()} method.
     *
     * @param applicationContext the {@link ApplicationContext} that will be initialized.
     */
    private void initializeContext(ApplicationContext applicationContext) {
        applicationContext.initialize();
    }

    /**
     * Sets up the {@link ApplicationContext} with the {@link ConfigurableApplicationEnvironment}.
     *
     * @param applicationContext the {@link ApplicationContext} to be set tp.
     * @param environment the {@link ConfigurableApplicationEnvironment}.
     */
    private void setupContext(ApplicationContext applicationContext, ConfigurableApplicationEnvironment environment) {
        applicationContext.setConfigurableApplicationEnvironment(environment);
    }

    /**
     * Creates the {@link ApplicationContext}. By default, a {@link DefaultApplicationContext} is instantiated and used by the application.
     *
     * @return a {@link ApplicationContext}
     */
    private ApplicationContext creatContext() {
        return new DefaultApplicationContext();
    }

    /**
     * Gets the {@link ConfigurableApplicationEnvironment} set on this VApplication. If no {@link ConfigurableApplicationEnvironment} was set, than a
     * {@link DefaultConfigurableApplicationEnvironment} is instantiated and used.
     *
     * @return a {@link ConfigurableApplicationEnvironment}.
     */
    private ConfigurableApplicationEnvironment getOrCreateEnvironment() {
        if (this.environment == null) {
            this.environment = new DefaultConfigurableApplicationEnvironment();
        }
        return this.environment;
    }

    /**
     * Sets the {@link ConfigurableApplicationEnvironment}.
     *
     * @param environment a {@link ConfigurableApplicationEnvironment}.
     */
    public void setEnvironment(ConfigurableApplicationEnvironment environment) {
        this.environment = environment;
    }
}
