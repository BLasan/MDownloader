package com.my_downloader.model;

import com.my_downloader.App;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TotalDownloadsDB {

    public static Connection connection = App.connection;
    public List<DownloadDataList> downloadDataList = new ArrayList();

    public List<DownloadDataList> returnSchedule() throws Exception {
        String sql = "SELECT * from scheduler where progress!="+"'Not Started'";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             while (rs.next()) {
                 Date date=new Date(rs.getLong("date"));
                 SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
                 String dateText = df2.format(date);
                // System.out.println(java.sql.Date.valueOf(dateText));
                 DownloadDataList downloadDataListObj=new DownloadDataList(rs.getInt("id"),rs.getString("url"),java.sql.Date.valueOf(dateText),rs.getString("time"),rs.getString("progress"),rs.getBoolean("isNotify"));
                 //System.out.println(downloadDataList1.date);
                 downloadDataList.add(downloadDataListObj);
             }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(downloadDataList.get(0));
        return downloadDataList;
    }
}
