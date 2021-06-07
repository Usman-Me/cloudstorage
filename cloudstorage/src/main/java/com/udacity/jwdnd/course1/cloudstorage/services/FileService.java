package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class FileService {
    // Autowired wieder rausgenommen
    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }
    /*
     *  File abspeichern
     * */
    public void storeFile(MultipartFile file, Integer userId) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file.");
            }

            if (fileMapper.getFile(file.getOriginalFilename()) != null) {
                throw new RuntimeException(String.format("File \"%s\" already exist", file.getOriginalFilename()));
            }

            File newFile = new File(file.getOriginalFilename(), file.getContentType(), file.getSize()+"");
            newFile.setUserid(userId);
            try (InputStream fis = file.getInputStream()) {
                newFile.setFiledata(fis.readAllBytes());
                fileMapper.addFile(newFile);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    public void delete(Integer fileId) {
        fileMapper.deleteFile(fileId);
    }

    public File getFileByFilename(String fileName) {
        return fileMapper.getFile(fileName);
    }

    public List<File> getAllFilesByUserId(Integer userId) {
        return fileMapper.findAllByUserId(userId);
    }
}