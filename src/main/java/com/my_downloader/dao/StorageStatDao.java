package com.my_downloader.dao;

import com.my_downloader.PathSelector;
import com.my_downloader.model.PathObject;
import com.my_downloader.model.StorageStatDB;

public class StorageStatDao {

    public PathObject returnSpaceConsumption() throws Exception {
        return new StorageStatDB().returnSpaceConsumption();
    }
}
