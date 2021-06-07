package com.udacity.jwdnd.course1.cloudstorage.model;
/*
 * NoteForm Getter / Setter Klasse
 * */
public class NoteForm{

    private String title;
    private String description;
    private Integer NoteId;
    private Integer userId;

    public String getNoteTitle() {
        return title;
    }

    public void setNoteTitle(String title) {
        this.title = title;
    }

    public String getNoteDescription() {
        return description;
    }

    public void setNoteDescription(String description) {
        this.description = description;
    }

    public Integer getNoteId() {
        return NoteId;
    }

    public void setNoteId(Integer id) {
        this.NoteId = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}