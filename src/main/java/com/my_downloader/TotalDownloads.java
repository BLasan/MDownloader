package com.my_downloader;

import com.my_downloader.TableDataList.HyperLinkCell;
import com.my_downloader.TableDataList.TableDataList;
import com.my_downloader.dao.TotalDownloadsDao;
import com.my_downloader.model.DownloadDataList;
import javafx.application.HostServices;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.HyperlinkSkin;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class TotalDownloads {

    public List<DownloadDataList> downloadDataList;
    public List<TableDataList> tableDataList = new ArrayList();
    public HostServices hostServices = App.hostServices;
    private static Parent parent;
    @FXML public AnchorPane totalDownloadUI;
    @FXML public TableView<TableDataList> tableView;
    @FXML private TableColumn<TableDataList, Hyperlink> downloadUrl;
    @FXML private TableColumn<TableDataList, String> downloadDate;
    @FXML private TableColumn<TableDataList, String> downloadProgress;
    @FXML private Label totalDownloadsCount;

    @FXML
    public void initialize() throws Exception {
        downloadDataList = new TotalDownloadsDao().returnSchedule();
        ListIterator<DownloadDataList> listListIterator = downloadDataList.listIterator();
        while (listListIterator.hasNext()) {
            DownloadDataList downloadDataList = listListIterator.next();
            int id = downloadDataList.id;
            Date date = downloadDataList.date;
            String time = downloadDataList.time;
            String progress = downloadDataList.progress;
            String url = downloadDataList.url;
            boolean isNotify = downloadDataList.isNotify;
            tableDataList.add(new TableDataList(id,url,date,time,progress,isNotify));
        }

        final ObservableList<TableDataList> data = FXCollections.observableArrayList(tableDataList);

        downloadUrl.setCellValueFactory(urlData -> {urlData.getValue().url.get().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //System.out.println(urlData.getValue().url.get().getText());
                hostServices.showDocument(urlData.getValue().url.get().getText());
            }
        });
        return urlData.getValue().url;
        });
        downloadDate.setCellValueFactory(dateData -> dateData.getValue().date);
        downloadProgress.setCellValueFactory(progress -> progress.getValue().progress);
        tableView.getItems().setAll(data);

        int downloadCount = new TotalDownloadsDao().returnDownloadCount();
        if(downloadCount != -1)
        totalDownloadsCount.setText(String.valueOf(downloadCount));
        else
            throw new Exception();
    }

    @FXML
    public void redirectToMainUI(ActionEvent actionEvent) throws IOException {
        parent = FXMLLoader.load(App.class.getResource("mainUi.fxml"));
        totalDownloadUI.getChildren().setAll(parent);
    }
}
