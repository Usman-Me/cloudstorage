package com.udacity.jwdnd.course1.cloudstorage.model;

/*
 * Note Getter / Setter Klasse
 * */
public class Note{
    private Integer noteId;
    private String noteTitle;
    private String noteDescription;
    private Integer userId;

    public Note(String noteTitle, String noteDescription) {
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public Integer getUserid() {
        return userId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public void setNoteTitle(String notetitle) {
        this.noteTitle = notetitle;
    }

    public void setNoteDescription(String notedescription) {
        this.noteDescription = notedescription;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}