package com.my_downloader.model;

public class PathObject {
    public int id;
    public String directory;
    public long size;
    public long freeSpace;
    public long usedSpace;

    public PathObject(String directory, long size, int id, long freeSpace, long usedSpace) {
        this.id = id;
        this.directory = directory;
        this.size = size;
        this.freeSpace = freeSpace;
        this.usedSpace = usedSpace;
    }
}
