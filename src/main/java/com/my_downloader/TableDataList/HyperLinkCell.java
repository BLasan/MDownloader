package com.my_downloader.TableDataList;
import com.my_downloader.App;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import javafx.application.HostServices.*;

import java.net.URI;

public class HyperLinkCell implements  Callback<TableColumn<TableDataList, Hyperlink>, TableCell<TableDataList, Hyperlink>> {

    public Hyperlink hyperlink;
    @Override
    public TableCell<TableDataList, Hyperlink> call(TableColumn<TableDataList, Hyperlink> arg) {
        TableCell<TableDataList, Hyperlink> cell = new TableCell<TableDataList, Hyperlink>() {
        };
        return cell;
    }

    public void setHyperlinkAction() {

    }

}

