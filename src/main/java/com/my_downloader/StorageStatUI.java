package com.my_downloader;

import com.my_downloader.dao.StorageStatDao;
import com.my_downloader.model.PathObject;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

import java.io.IOException;

public class StorageStatUI {

    @FXML public PieChart storageStat;

    @FXML
    public void initialize() throws Exception {
        PathObject pathObject = new StorageStatDao().returnSpaceConsumption();
        PieChart.Data slice1 = new PieChart.Data("Free Space", pathObject.freeSpace);
        PieChart.Data slice2 = new PieChart.Data("Used Space"  , pathObject.usedSpace);
        storageStat.getData().add(slice1);
        storageStat.getData().add(slice2);

    }
}
