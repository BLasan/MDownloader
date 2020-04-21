package com.my_downloader.model;

import com.my_downloader.App;

import java.sql.*;

public class StorageStatDB {

    public static Connection connection = App.connection;

    public PathObject returnSpaceConsumption() throws Exception {
        String sql = "SELECT * FROM download_path";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return new PathObject(rs.getString("path"),rs.getLong("size"),rs.getInt("id"),rs.getLong("freeSpace"),rs.getLong("usedSpace"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new PathObject("Invalid",-1,-1,-1,-1);
        }
    }
}
