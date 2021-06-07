package com.udacity.jwdnd.course1.cloudstorage.model;

/*
 * File Getter / Setter Klasse
 * */
public class File{
    private int fileId;
    private String fileName;
    private String fileSize;
    private String contentType;
    private byte[] fileData;
    private int userId;

    public File(String fileName, String contentType, String fileSize) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
    }


    public int getFileId() {
        return fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilesize() {
        return fileSize;
    }

    public String getContenttype() {
        return contentType;
    }

    public byte[] getFiledata() {
        return fileData;
    }

    public int getUserId() {
        return userId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setFiledata(byte[] fileData) {
        this.fileData = fileData;
    }

    public void setUserid(int userId) {
        this.userId = userId;
    }
}