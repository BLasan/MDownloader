package com.my_downloader.model;

import com.my_downloader.App;

import java.sql.*;

public class PathSelectorDB {

    public static Connection connection = App.connection;

    /**
     * Add Selected Path.
     * @param directory
     * @param fileSize
     * @param freeSpace
     * @param usedSpace
     * @throws Exception
     */
    public void addSelectedPath(String directory, long fileSize, long freeSpace, long usedSpace) throws Exception {
        String sql = "UPDATE download_path SET path=?,size=?,freeSpace=?,usedSpace=? WHERE id=?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, directory);
            pstmt.setLong(2, fileSize);
            pstmt.setLong(3,freeSpace);
            pstmt.setLong(4,usedSpace);
            pstmt.setInt(5,1);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Get Selected Path.
     * @return Path Object.
     * @throws Exception
     */
    public PathObject getSelectedPath() throws Exception {
        String sql = "SELECT * from download_path where id=1";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return new PathObject(rs.getString("path"),rs.getLong("size"),rs.getInt("id"),rs.getLong("freeSpace"),rs.getLong("usedSpace"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new PathObject("Invalid",0,0,0,0);
        }
    }
}
