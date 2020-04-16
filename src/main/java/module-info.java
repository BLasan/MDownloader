module com.my_downloader {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens com.my_downloader to javafx.fxml;
    exports com.my_downloader;
}