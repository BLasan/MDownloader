package com.my_downloader.db_config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Config {

    public static Connection init_db() throws ClassNotFoundException {

        Class.forName("org.sqlite.JDBC");
        Connection connection = null;
        try
        {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:/home/benura/Desktop/My-Downloader/Downloader.db");
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
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e);
            }

            return connection;
        }

    }
}
