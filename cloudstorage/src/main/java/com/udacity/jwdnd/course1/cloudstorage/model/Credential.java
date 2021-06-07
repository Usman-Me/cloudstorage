package com.udacity.jwdnd.course1.cloudstorage.model;

/*
 * Credential Getter / Setter Klasse
 * */
public class Credential{
    private int credentialid;
    private String url;
    private String username;
    private String password;
    private String key;
    private int userId;

    public Credential( String url, String username, String key, String password){
        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;

    }

    public int getCredentialId() {
        return credentialid;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getKey() {
        return key;
    }

    public int getUserid() {
        return userId;
    }

    public void setCredentialId(int credentialId) {
        this.credentialid = credentialId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setUserid(int userId) {
        this.userId = userId;
    }
}