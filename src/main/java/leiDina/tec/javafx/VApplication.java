package main.java.leiDina.tec.javafx;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author vitor.alves
 */
public abstract class VApplication extends Application {

    private ApplicationContext applicationContext;

    @Override
    public void init() throws Exception {
        super.init();
        this.applicationContext = getApplicationContext();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationThreadContext.init(this.applicationContext);
        this.startStage(primaryStage);
    }

    protected abstract void startStage(Stage primaryStage) throws Exception;

    protected ApplicationContext getApplicationContext() {
        if (this.applicationContext == null) {
            this.applicationContext = new ApplicationContextImpl();
            this.applicationContext.init(this.getStarterClass());
        }
        return this.applicationContext;
    }

    protected abstract Class<?> getStarterClass();
}
