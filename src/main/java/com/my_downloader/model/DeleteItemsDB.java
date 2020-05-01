package com.my_downloader.model;

import com.my_downloader.App;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteItemsDB {

    public static Connection connection = App.connection;

    public void updateDeleteStatus(int id) throws Exception {

        String sql = "UPDATE scheduler SET isDeleted='Y' WHERE id=?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
