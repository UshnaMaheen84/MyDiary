package com.example.admin.mydiary.models;

/**
 * Created by Admin on 13-Jul-18.
 */

public class DiaryModel {

    String date, subject, entry;
    String id;

    public DiaryModel() {
    }

    public DiaryModel(String date, String subject, String entry, String id) {
        this.date = date;
        this.subject = subject;
        this.entry = entry;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
