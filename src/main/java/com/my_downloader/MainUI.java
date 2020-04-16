package com.my_downloader;

import com.my_downloader.ComboList.ComboList;
import com.my_downloader.dao.MainUIDao;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    public ComboBox downloadTime;

    @FXML
    public void initialize() throws IOException {
        downloadTime.setItems(FXCollections.observableArrayList(new ComboList("Peek","_peek").getItem(),new ComboList("Off-Peek","_offPeek").getItem()));
        downloadTime.setValue(new ComboList("Peek","_peek").getItem());
    }

    @FXML
    public void addItem(ActionEvent actionEvent) throws IOException {

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
