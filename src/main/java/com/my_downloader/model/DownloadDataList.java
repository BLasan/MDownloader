package com.my_downloader.model;

import java.sql.Date;

public class DownloadDataList {
    public int id;
    public String url;
    public Date date;
    public String time;
    public String progress;
    public boolean isNotify;

    /**
     * Constructor.
     * @param id
     * @param url
     * @param date
     * @param time
     * @param progress
     * @param isNotify
     */
    public DownloadDataList(int id, String url, Date date, String time, String progress, boolean isNotify) {
        this.url = url;
        this.date = date;
        this.time = time;
        this.progress = progress;
        this.isNotify = isNotify;
        this.id = id;
    }
}
