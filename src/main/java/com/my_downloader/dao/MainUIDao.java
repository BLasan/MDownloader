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

    public boolean addScheduler(String url, Date date, String time, boolean isNotify) throws IOException,Exception {
        return new MainUIDB().addSchedule(url, date, time, isNotify);
    }

}
