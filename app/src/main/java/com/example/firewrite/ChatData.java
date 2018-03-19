package com.example.firewrite;

import com.google.firebase.database.Exclude;

import java.util.HashMap;

/**
 * Created by ishaan on 21/1/18.
 */

public class ChatData {

    private String id;
    private String authorUid;
    private String message;
    private String imgUrl;
    private long createdTimestamp;
    private Object serverTimestamp;
    private HashMap<String, Long> deliveryReceipts = new HashMap<>();
    private HashMap<String, Long> readReceipts = new HashMap<>();

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ChatData) {
            ChatData objectToCompare = (ChatData) obj;
            if (this.id.equalsIgnoreCase(objectToCompare.getId())) {
                return true;
            }
            return false;
        }
        return super.equals(obj);
    }

    public String getAuthorUid() {
        return authorUid;
    }

    public void setAuthorUid(String authorUid) {
        this.authorUid = authorUid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(long createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public Object getServerTimestamp() {
        return serverTimestamp;
    }

    public void setServerTimestamp(Object serverTimestamp) {
        this.serverTimestamp = serverTimestamp;
    }

    @Exclude
    public Long getServerTimestampInLong() {
        return (Long) this.serverTimestamp;
    }

    public HashMap<String, Long> getDeliveryReceipts() {
        return deliveryReceipts;
    }

    public void setDeliveryReceipts(HashMap<String, Long> deliveryReceipts) {
        this.deliveryReceipts = deliveryReceipts;
    }

    public HashMap<String, Long> getReadReceipts() {
        return readReceipts;
    }

    public void setReadReceipts(HashMap<String, Long> readReceipts) {
        this.readReceipts = readReceipts;
    }
}
