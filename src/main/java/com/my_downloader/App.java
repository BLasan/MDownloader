package com.my_downloader;

import com.my_downloader.dao.AppDao;
import com.my_downloader.dao.PathSelectorDao;
import com.my_downloader.db_config.DB_Config;
import com.my_downloader.model.DownloadDataList;
import com.my_downloader.model.PathObject;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static Stage primaryStage;
    public static Connection connection;
    public static List<DownloadDataList> dataList;
    public static HostServices hostServices;

    /**
     * Start FX app.
     * @param stage
     * @throws IOException
     */
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("mainUi"), 660, 520);
        String css = this.getClass().getResource("fxml.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        primaryStage = stage;
        stage.setTitle("MDownloader");
        stage.show();
        hostServices = getHostServices();
        try{
            DBCONFIG();
            readDB(true);
        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Initialized DB_CONFIG.
     * @throws Exception
     */
    public static void DBCONFIG() throws Exception {
        connection = DB_Config.init_db();
    }

    /**
     * Get the platform of FX UI.
     * @return primary stage.
     * @throws IOException
     */
    public static Stage getStage() throws  IOException {
        return primaryStage;
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Get Available data continuously.
     * @param isRunning
     * @throws Exception
     */
    public static void readDB(boolean isRunning) throws Exception {
        String time = getTime();
        System.out.println(time);
        dataList = new AppDao().returnDownloadData();
        PathObject path = new PathSelectorDao().getSelectedPath();
        for(int i=0;i<dataList.size();i++){
            if(time.equals(dataList.get(i).time))
                new Thread(new Download(dataList.get(i).url, path.directory, dataList.get(i).id, dataList.get(i).isNotify)).start();
            Thread.sleep(2000);
        }
    }

    public static String getTime() throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String timeString = dtf.format(now).substring(0,dtf.format(now).indexOf(':'));
       // System.out.println(timeString);
        String time;
        if(Integer.parseInt(timeString) <8) time = "Off-Peek";
        else time = "Peek";

        return time;
    }

    /**
     * Load FXML.
     * @param fxml
     * @return Parent node.
     * @throws IOException
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Main method.
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

}