package com.example.admin.mydiary.models;

import java.util.Date;

/**
 * Created by Admin on 16-Jul-18.
 */

public class mNotes {

    private String id;
    private String note;
    private long messageTime;

    public mNotes(String string, String cursorString) {
    }

    public mNotes(String id, String note, long messageTime) {
        this.id = id;
        this.note = note;
        this.messageTime = messageTime;
    }

    public mNotes() {

    }

    public String getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }

}
