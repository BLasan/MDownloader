package com.my_downloader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.IOException;

public class PathSelector {

    private static File pathSelected;

    @FXML
    private void selectPath(ActionEvent actionEvent) throws IOException {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(App.getStage());
        pathSelected = selectedDirectory;
    }

    @FXML
    private void clickOk(ActionEvent actionEvent) throws IOException {
        String path = pathSelected.toString();
        MainUI.closeStage(path);
    }

    @FXML
    private void cancelStage(ActionEvent actionEvent) throws IOException {
        MainUI.closeStage("/home/benura/Desktop/Pesuru-AL");
    }
}
