package main.java.leiDina.tec;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.leiDina.tec.core.VApplication;
import main.java.leiDina.tec.core.annotation.VApplicationProfile;

@VApplicationProfile
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        VApplication.run(Main.class);
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
