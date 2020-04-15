package com.my_downloader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static Stage primaryStage;

    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("mainUi"), 660, 520);
        String css = this.getClass().getResource("fxml.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        primaryStage = stage;
        stage.setTitle("MDownloader");
        stage.show();
    }

    public static Button getButtonElement(String id) throws IOException {
        Button btn = (Button) scene.lookup(id);
        return  btn;
    }

    public static TextField getTextField(String id) throws IOException {
        TextField textField = (TextField) scene.lookup(id);
        return textField;
    }

    public static Stage getStage() throws  IOException {
        return primaryStage;
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}