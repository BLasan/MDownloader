package com.my_downloader.db_config;

import java.sql.*;

public class DB_Config {

    public static Connection init_db() throws ClassNotFoundException {

        Class.forName("org.sqlite.JDBC");
        Connection connection = null;
        try
        {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:/home/benura/Desktop/My-Downloader/Downloader.db");
            createTable(connection);
            //Statement statement = connection.createStatement();
            //statement.setQueryTimeout(30);
//            ResultSet rs = statement.executeQuery("select * from scheduler");
//            while(rs.next())
//            {
//                // read the result set
//                System.out.println("name = " + rs.getString("name"));
//                System.out.println("id = " + rs.getInt("id"));
//            }
        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println("ERROR" + e.getMessage());
        }
        finally
        {
            return connection;
        }

    }

    public static void createTable(Connection connection) throws Exception {
        String createSchedulerTable = "CREATE TABLE IF NOT EXISTS scheduler(id INTEGER not null primary key autoincrement,date BIGINT not null,time varchar(10) not null,url varchar(50) not null,progress varchar(20) not null default 'Not Started',isNotify char(1) not null default 'N',isRemoved char(1) not null default 'N',isDeleted char(1) not null default 'N')";
        String createDownloadPathTable = "CREATE TABLE IF NOT EXISTS download_path(id int primary key not null,path varchar(30) not null,size BIGINT not null,freeSpace BIGINT not null,usedSpace BIGINT not null)";
        String insertToPath = "INSERT INTO download_path(id,path,size,freeSpace,usedSpace) values(1,'~/Desktop',0,0,0)";
        Statement statement = connection.createStatement();
        statement.execute(createDownloadPathTable);
        statement.execute(insertToPath);
        statement.execute(createSchedulerTable);
    }
}
