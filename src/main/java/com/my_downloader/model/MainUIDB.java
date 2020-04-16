package com.my_downloader.model;

import com.my_downloader.App;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainUIDB {
    public Connection connection = App.connection;
    public static List list = new ArrayList();

    public static void addSchedule(String url, Date date, String time, boolean isNotify) throws Exception {

    }

    public static ArrayList getScheduleData(String url) throws Exception {
        return (ArrayList) list;
    }
}
