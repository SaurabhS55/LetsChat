package com.example.letschat.models;

public class MessageModel {
    String message,uid;
    Long timestamp;

    public MessageModel(String message, String uid, Long timestamp) {
        this.message = message;
        this.uid = uid;
        this.timestamp = timestamp;
    }

    public MessageModel(String message, String uid) {
        this.message = message;
        this.uid = uid;
    }

    public MessageModel() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
