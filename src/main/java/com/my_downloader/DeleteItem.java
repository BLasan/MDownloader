package com.my_downloader;

import com.my_downloader.dao.DeleteItemsDao;
import com.my_downloader.dao.TotalDownloadsDao;
import com.my_downloader.model.DownloadDataList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

import java.io.IOException;
import java.util.List;

public class DeleteItem {

    @FXML public ListView listView;

    static class Cell extends ListCell<DownloadDataList> {
        HBox hBox = new HBox();
        Button deleteBtn = new Button("DELETE");
        Label label = new Label("");
        Pane pane = new Pane();

        public Cell() {
            super();
            hBox.getChildren().addAll(label,pane,deleteBtn);
            HBox.setHgrow(pane, Priority.ALWAYS);
            deleteBtn.setOnAction(e -> {
                try {
                    new DeleteItemsDao().updateDeleteItems(getItem().id);
                    getListView().getItems().remove(getItem());
                }catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
        }

        public void updateItem(DownloadDataList name, boolean empty) {
            System.out.println(name);
            super.updateItem(name,empty);
            setText(null);
            setGraphic(null);

            if(name!=null && !empty) {
                label.setText(name.url);
                setGraphic(hBox);
            }
        }
    }

    @FXML
    public void initialize() throws Exception {
        List<DownloadDataList> downloadDataLists = new TotalDownloadsDao().returnSchedule();
        ObservableList<DownloadDataList> observableList = FXCollections.observableArrayList(downloadDataLists);
        listView.setItems(observableList);
        listView.setCellFactory(param -> new Cell());
    }
}
