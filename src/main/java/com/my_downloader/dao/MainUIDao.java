package com.my_downloader.dao;

import com.my_downloader.model.MainUIDB;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MainUIDao {

    /**
     * Add scheduler to DB.
     * @param url
     * @param date
     * @param time
     * @param isNotify
     * @return true / false.
     * @throws IOException
     * @throws Exception
     */
    public boolean addScheduler(String url, Date date, String time, boolean isNotify) throws IOException,Exception {
        return new MainUIDB().addSchedule(url, date, time, isNotify);
    }

    /**
     * Update progress of the downloads.
     * @param id
     * @param error
     * @param progress
     * @return true / false.
     * @throws Exception
     */
    public boolean updateProgress(int id, String error, String progress) throws Exception {
        return new MainUIDB().updateProgress(id, error, progress);
    }
}
