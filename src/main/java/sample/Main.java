package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader root = new FXMLLoader(getClass().getResource("sample.fxml"));
        Controller c = new Controller();
        root.setController(c);
        primaryStage.setScene(new Scene(root.load(), 450, 450));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
