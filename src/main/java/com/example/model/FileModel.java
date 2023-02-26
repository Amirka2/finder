package com.example.model;

public class FileModel {
    private String name;
    private boolean isDirectory;
    private long length;
    private long lastModified;

    public FileModel(String name, boolean isDirectory, long length, long lastModified) {
        this.name = name;
        this.isDirectory = isDirectory;
        this.length = length;
        this.lastModified = lastModified;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    public long getLength() {
        return length;
    }

    public long getLastModified() {
        return lastModified;
    }
}