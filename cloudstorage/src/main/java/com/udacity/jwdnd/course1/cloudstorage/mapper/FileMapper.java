package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
/*
 * FileMapper Interface für Dateneintrag
 * */
public interface FileMapper{

        @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
        @Options(useGeneratedKeys = true, keyProperty ="fileId")
        int addFile(File file);

        @Select("SELECT * FROM FILES WHERE userid=#{userId}")
        List<File> findAllByUserId(Integer userId);

        @Select("SELECT * FROM FILES WHERE filename= #{fileName}")
        File getFile(String fileName);

        @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
        void deleteFile(int fileName);
        }
