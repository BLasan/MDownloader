package com.my_downloader.TableDataList;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

public class HyperLinkCell implements  Callback<TableColumn<TableDataList, Hyperlink>, TableCell<TableDataList, Hyperlink>> {

    public static List<TableDataList> tableDataList = new ArrayList<>();
    public static int count = 0;
    @Override
    public TableCell<TableDataList, Hyperlink> call(TableColumn<TableDataList, Hyperlink> arg) {
        TableCell<TableDataList, Hyperlink> cell = new TableCell<TableDataList, Hyperlink>() {
            @Override
            protected void updateItem(Hyperlink item, boolean empty) {
                try {
                       //setGraphic(tableDataList.get(0).url);
                }catch (Exception exception) {
                   exception.printStackTrace();
                }
            }
        };
        return cell;
    }

    public void getHyperlink(ArrayList<TableDataList> data) throws Exception {
        tableDataList = data;
    }
}

