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
        //System.out.println(date.getTime());
        String sql = "INSERT INTO scheduler(date,time,url,progress,isNotify,isRemoved) values(?,?,?,?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, date.getTime());
            pstmt.setString(2, time);
            pstmt.setString(3,url);
            pstmt.setString(4,"Not Started");
            pstmt.setString(5,String.valueOf(isNotifyCharacter));
            pstmt.setString(6,String.valueOf('N'));
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

    public boolean updateProgress(int id, String error, String progress) throws Exception {
        String sql = "UPDATE scheduler SET progress=?,error=? WHERE id=?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, progress);
            pstmt.setString(2, error);
            pstmt.setInt(3, id);
            // update
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
