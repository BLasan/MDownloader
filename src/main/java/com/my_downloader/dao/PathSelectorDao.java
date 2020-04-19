package com.my_downloader.dao;

import com.my_downloader.model.PathObject;
import com.my_downloader.model.PathSelectorDB;

public class PathSelectorDao {

    /**
     * Add selected path.
     * @param directory
     * @param fileSize
     * @param freeSpace
     * @param usedSpace
     * @throws Exception
     */
    public void addSelectedPath(String directory, long fileSize, long freeSpace, long usedSpace) throws Exception {
        new PathSelectorDB().addSelectedPath(directory, fileSize, freeSpace, usedSpace);
    }

    /**
     * Return path details.
     * @return Path Object.
     * @throws Exception
     */
    public PathObject getSelectedPath() throws Exception {
        return new PathSelectorDB().getSelectedPath();
    }
}
