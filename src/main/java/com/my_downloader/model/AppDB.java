package com.my_downloader.model;

import com.my_downloader.App;

import java.io.LineNumberInputStream;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AppDB {
    public static Connection connection = App.connection;
    public static List<DownloadDataList> dataList = new ArrayList();

    public List returnDownloadData() throws Exception {
        java.util.Date today = new java.util.Date();
        try {
            java.sql.Date sqlDate = new java.sql.Date(today.getTime());
            java.sql.Date currentDate = Date.valueOf(sqlDate.toString());
            //System.out.println(currentDate.getTime());
            String sql = "SELECT * from scheduler where date=" + currentDate.getTime() + " and progress='Not Started'";

            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                // loop through the result set
                while (rs.next()) {
                    dataList.add(new DownloadDataList(rs.getInt("id"),rs.getString("url"),rs.getDate("date"),rs.getString("time"),rs.getString("progress"),rs.getBoolean("isNotify")));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }
}
