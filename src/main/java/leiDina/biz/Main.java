package main.java.leiDina.biz;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.leiDina.biz.scene.LeiDinamicaScenes;
import main.java.leiDina.tec.core.ApplicationContext;
import main.java.leiDina.tec.core.VApplication;
import main.java.leiDina.tec.javafx.VFXMLLoader;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationContext applicationContext = VApplication.start();
        VFXMLLoader vfxmlLoader = applicationContext.getBean(VFXMLLoader.class);
        Parent root = vfxmlLoader.load(LeiDinamicaScenes.MAIN.getScenes());
        primaryStage.setTitle("Leitura Dinamica");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
