package com.my_downloader.dao;

import com.my_downloader.model.AppDB;
import com.my_downloader.model.DownloadDataList;

import java.util.List;

public class AppDao {

    /**
     * Get download data.
     * @return Download List.
     * @throws Exception
     */
    public List<DownloadDataList> returnDownloadData() throws Exception {
        return new AppDB().returnDownloadData();
    }
}
