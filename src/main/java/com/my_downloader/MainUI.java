package com.my_downloader;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MainUI {

    private static String defaultFilePath = "/home/benura/Desktop/Pesuru-AL";
    private static Parent parent;
    private static Stage stage;
    private static Scene scene;


    @FXML
    private void onClick(ActionEvent actionEvent) throws IOException {
     System.out.print("Hello" + ((Button)actionEvent.getSource()).getId());
     Button btn = App.getButtonElement("#searchBtn");
    }

    @FXML
    private void startDownload(ActionEvent actionEvent) throws  IOException {
        TextField textField = App.getTextField("#downloadLink");
        System.out.println(textField.getText());
        new Thread(new Download(textField.getText(),defaultFilePath,textField)).start();
//        new Download(textField.getText().toString(), defaultFilePath).run();
    }

    public void checkAvailability() throws IOException {

    }

    @FXML
    public void openFileSelectorUI(ActionEvent actionEvent) throws IOException {
        parent = FXMLLoader.load(App.class.getResource("pathSetUI.fxml"));
        stage = new Stage();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void openVideoDescriptionUI(ActionEvent actionEvent) throws IOException {

    }

    @FXML
    public void openViewHistoryUI(ActionEvent actionEvent) throws IOException {

    }

    @FXML
    public void openDownloadStatUI(ActionEvent actionEvent) throws IOException {

    }

    @FXML
    public void openSchedulerUI(ActionEvent actionEvent) throws IOException {

    }

    @FXML
    public void openViewStorageStat(ActionEvent actionEvent) throws IOException {

    }

    @FXML

    public static void closeStage(String path) throws IOException {
        defaultFilePath = path;
        System.out.print(defaultFilePath);
        stage.close();
    }

    public static TextField getTextField(String id) throws IOException {
        TextField textField = (TextField) scene.lookup(id);
        return textField;
    }



}
