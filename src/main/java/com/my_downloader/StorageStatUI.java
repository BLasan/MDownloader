package com.my_downloader;

import com.my_downloader.dao.StorageStatDao;
import com.my_downloader.model.PathObject;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import java.awt.*;
import java.text.DecimalFormat;

public class StorageStatUI {

    @FXML public PieChart storageStat;
    @FXML private Label freeSpace;
    @FXML private Label usedSpace;
    @FXML private Label totalSpace;

    @FXML
    public void initialize() throws Exception {
        PathObject pathObject = new StorageStatDao().returnSpaceConsumption();
        PieChart.Data slice1 = new PieChart.Data("Free Space", pathObject.freeSpace);
        PieChart.Data slice2 = new PieChart.Data("Used Space"  , pathObject.usedSpace);
        storageStat.getData().add(slice1);
        storageStat.getData().add(slice2);
        DecimalFormat df = new DecimalFormat("#.#");
        System.out.println(df.format((Long.valueOf(pathObject.size)/(1024*1024*1024))));
        String freeSpaceString =  df.format((Long.valueOf(pathObject.freeSpace)/(1024*1024*1024)))+"GB";
        String usedSpaceString =  df.format((Long.valueOf(pathObject.usedSpace)/(1024*1024*1024)))+"GB";
        String totalSpaceString = df.format((Long.valueOf(pathObject.size)/(1024*1024*1024)))+"GB";
        freeSpace.setText(freeSpaceString);
        usedSpace.setText(usedSpaceString);
        totalSpace.setText(totalSpaceString);
    }
}
