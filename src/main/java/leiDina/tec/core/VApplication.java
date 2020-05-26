package main.java.leiDina.tec.core;


import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironment;
import main.java.leiDina.tec.core.env.DefaultConfigurableApplicationEnvironment;

/**
 * @author vitor.alves
 */
public class VApplication {

    private ConfigurableApplicationEnvironment environment;

    public static ApplicationContext start() {
        return new VApplication().run();
    }

    public ApplicationContext run() {
        ConfigurableApplicationEnvironment environment = this.getOrCreateEnvironment();
        ApplicationContext applicationContext = this.creatContext();
        this.setupContext(applicationContext, environment);
        this.initializeContext(applicationContext);
        return applicationContext;
    }

    private void initializeContext(ApplicationContext applicationContext) {
        applicationContext.initialize();
    }

    private void setupContext(ApplicationContext applicationContext, ConfigurableApplicationEnvironment environment) {
        applicationContext.setConfigurableApplicationEnvironment(environment);
    }

    private ApplicationContext creatContext() {
        return new DefaultApplicationContext();
    }

    private ConfigurableApplicationEnvironment getOrCreateEnvironment() {
        if (this.environment == null) {
            this.environment = new DefaultConfigurableApplicationEnvironment();
        }
        return this.environment;
    }

    public void setEnvironment(ConfigurableApplicationEnvironment environment) {
        this.environment = environment;
    }
}
