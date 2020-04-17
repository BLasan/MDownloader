package com.my_downloader.model;

import com.my_downloader.App;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainUIDB {
    public static Connection connection = App.connection;
    public static List list = new ArrayList();

    public boolean addSchedule(String url, Date date, String time, boolean isNotify) throws Exception {
        char isNotifyCharacter;
        if(isNotify) isNotifyCharacter = 'Y';
        else isNotifyCharacter = 'N';
        String sql = "INSERT INTO scheduler(date,time,url,progress,isNotify) values(?,?,?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDate(1, date);
            pstmt.setString(2, time);
            pstmt.setString(3,url);
            pstmt.setString(4,"Not Started");
            pstmt.setString(5,String.valueOf(isNotifyCharacter));
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static ArrayList getScheduleData(String url) throws Exception {
        return (ArrayList) list;
    }
}
