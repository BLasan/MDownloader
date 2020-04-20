package com.my_downloader.TableDataList;

import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;

public class TableDataList {
    private SimpleStringProperty id;
    public SimpleStringProperty url;
    public SimpleStringProperty date;
    public SimpleStringProperty time;
    public SimpleStringProperty progress;

    /**
     * Initialized Data List.
     * @param id
     * @param url
     * @param date
     * @param time
     * @param progress
     * @param isNotify
     */
    public TableDataList(int id, String url, Date date, String time, String progress, boolean isNotify) {
        this.id = new SimpleStringProperty(String.valueOf(id));
        this.url = new SimpleStringProperty(url);
        this.date = new SimpleStringProperty(String.valueOf(date));
        this.time = new SimpleStringProperty(time);
        this.progress = new SimpleStringProperty(progress);
    }
}
