package com.my_downloader.dao;

import com.my_downloader.model.DownloadDataList;
import com.my_downloader.model.TotalDownloadsDB;

import java.util.List;

public class TotalDownloadsDao {

    /**
     * Get Completed Data.
     * @return List.
     * @throws Exception
     */
    public List<DownloadDataList> returnSchedule() throws Exception {
        return new TotalDownloadsDB().returnSchedule();
    }

    public int returnDownloadCount() throws Exception {
        return new TotalDownloadsDB().returnDownloadCount();
    }
}
