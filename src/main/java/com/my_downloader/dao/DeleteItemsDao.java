package com.my_downloader.dao;

import com.my_downloader.model.DeleteItemsDB;

public class DeleteItemsDao {

    public void updateDeleteItems(int id) throws Exception {
        new DeleteItemsDB().updateDeleteStatus(id);
    }
}
