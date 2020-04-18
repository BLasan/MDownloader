package com.my_downloader.dao;

import com.my_downloader.model.AppDB;
import com.my_downloader.model.DownloadDataList;

import java.util.List;

public class AppDao {

    public List<DownloadDataList> returnDownloadData() throws Exception {
        return new AppDB().returnDownloadData();
    }
}
