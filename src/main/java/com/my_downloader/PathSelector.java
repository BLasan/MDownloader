package com.my_downloader;

import com.my_downloader.ComboList.ComboList;
import com.my_downloader.dao.PathSelectorDao;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class PathSelector {

    private static File pathSelected;
    @FXML
    public Label filePath;
    public String path;
    @FXML
    public void initialize() throws Exception {
        path = new PathSelectorDao().getSelectedPath().directory;
        if(!path.equals("Invalid"))
        filePath.setText(path);
        else
            throw new Exception();
    }

    @FXML
    private void selectPath(ActionEvent actionEvent) throws Exception {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(App.getStage());
        pathSelected = selectedDirectory;
        String pathString = pathSelected.toString();
        long freeSpace = new File(pathString).getFreeSpace();
        long fileSize = new File(pathString).getTotalSpace();
        long usedSpace = new File(pathString).getUsableSpace();
        System.out.println(freeSpace);
        System.out.println(usedSpace);
        System.out.println(fileSize);
        new PathSelectorDao().addSelectedPath(pathString, fileSize, freeSpace, usedSpace);
        filePath.setText(pathString);
    }

    @FXML
    private void clickOk(ActionEvent actionEvent) throws IOException {
        String path = pathSelected.toString();
        MainUI.closeStage(path);
    }

    @FXML
    private void cancelStage(ActionEvent actionEvent) throws IOException {
        MainUI.closeStage(path);
    }
}
