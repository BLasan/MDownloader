package com.my_downloader;

import com.my_downloader.TableDataList.TableDataList;
import com.my_downloader.dao.TotalDownloadsDao;
import com.my_downloader.model.DownloadDataList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class TotalDownloads {

    public List<DownloadDataList> downloadDataList;
    public List<TableDataList> tableDataList = new ArrayList();
    @FXML public TableView<TableDataList> tableView;
    @FXML private TableColumn<TableDataList, String> downloadUrl;
    @FXML private TableColumn<TableDataList, String> downloadDate;
    @FXML private TableColumn<TableDataList, String> downloadProgress;

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

        downloadUrl.setCellValueFactory(urlData -> urlData.getValue().url);
        downloadDate.setCellValueFactory(dateData -> dateData.getValue().date);
        downloadProgress.setCellValueFactory(progress -> progress.getValue().progress);
        tableView.getItems().setAll(data);
    }
}
