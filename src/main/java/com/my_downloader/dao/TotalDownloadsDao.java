package com.my_downloader.dao;

import com.my_downloader.model.DownloadDataList;
import com.my_downloader.model.TotalDownloadsDB;

import java.util.List;

public class TotalDownloadsDao {

    public List<DownloadDataList> returnSchedule() throws Exception {
        return new TotalDownloadsDB().returnSchedule();
    }
}