package com.my_downloader;

import com.my_downloader.ComboList.ComboList;
import com.my_downloader.dao.MainUIDao;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
public class MainUI {

    private static String defaultFilePath = "/home/benura/Desktop/Pesuru-AL";
    private static Parent parent;
    private static Stage stage;
    private static Scene scene;
    @FXML
    public ComboBox downloadTime;
    @FXML
    public TextField downloadUrl;
    @FXML
    public DatePicker downloadDate;
    @FXML
    public CheckBox downloadNotify;
    @FXML
    public MenuItem totalDownloads;

    @FXML
    public void initialize() throws IOException {
        LocalDate today = LocalDate.now();
        downloadTime.setItems(FXCollections.observableArrayList(new ComboList("Peek","_peek").getItem(),new ComboList("Off-Peek","_offPeek").getItem()));
        downloadTime.setValue(new ComboList("Peek","_peek").getItem());
        downloadDate.setValue(today);
    }

    @FXML
    public void addItem(ActionEvent actionEvent) throws IOException,Exception {
        String url = downloadUrl.getText();
        Date date = Date.valueOf(downloadDate.getValue());
        String time = String.valueOf(downloadTime.getValue());
        boolean isNotify = downloadNotify.isSelected();
        System.out.println(url+" "+date+" "+time+" "+isNotify);
        boolean isSuccess = new MainUIDao().addScheduler(url,date,time,isNotify);
        if(isSuccess) {
            downloadTime.setValue(new ComboList("Peek","_peek").getItem());
            downloadUrl.setText(null);
            LocalDate today = LocalDate.now();
            downloadDate.setValue(today);
            downloadNotify.setSelected(false);
        }
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

    @FXML
    public void openTotalDownloads(ActionEvent actionEvent) throws Exception {
        parent = FXMLLoader.load(App.class.getResource("totalDownloads.fxml"));
        stage = new Stage();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Downloaded Items");
        stage.show();
    }

    @FXML
    public void viewDiskUsage(ActionEvent actionEvent) throws IOException {
        parent = FXMLLoader.load(App.class.getResource("storageStatUI.fxml"));
        stage = new Stage();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Storage Used");
        stage.show();
    }

}
