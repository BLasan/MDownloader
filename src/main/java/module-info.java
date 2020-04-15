module com.my_downloader {
    requires javafx.controls;
    requires javafx.fxml;
    opens com.my_downloader to javafx.fxml;
    exports com.my_downloader;
}