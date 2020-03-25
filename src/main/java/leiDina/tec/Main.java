package main.java.leiDina.tec;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.leiDina.tec.core.VApplication;
import main.java.leiDina.tec.javafx.VFXMLLoader;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        VApplication.run(Main.class);
        VFXMLLoader vfxmlLoader = new VFXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = vfxmlLoader.load();
        primaryStage.setTitle("Leitura Dinamica");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
