package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper

/*
 * NoteMapper Interface für Dateneintrag
 * */
public interface NoteMapper{

    @Insert("INSERT INTO Notes (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty ="noteId")
    int addNote(Note note);

    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    List<Note> findAllbyUserId(Integer userId);

    @Select("SELECT * FROM NOTES WHERE noteid= #{noteId}")
    Note findById(Integer noteId);

    @Update("UPDATE NOTES SET notetitle=#{noteTitle}, notedescription=#{noteDescription} WHERE noteid=#{noteId}")
    @Options(useGeneratedKeys = true, keyProperty ="noteId")
    int updateNote(Note note);

    @Delete("DELETE FROM NOTES WHERE noteid= #{noteId}")
    void deleteNote(int noteId);
}