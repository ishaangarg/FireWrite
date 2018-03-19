package com.example.firewrite;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ishaan on 10/3/18.
 */

public class NotifData {

    @SerializedName("messageId")
    private String messageId;
    @SerializedName("authorId")
    private String authorId;
    @SerializedName("authorName")
    private String authorName;
    @SerializedName("authorThumbnail")
    private String authorDpUrl;
    @SerializedName("groupThumbnail")
    private String groupDpUrl;
    @SerializedName("groupId")
    private String groupId;
    @SerializedName("groupTitle")
    private String groupName;
    @SerializedName("message")
    private String messageBody;
    @SerializedName("notification_channel")
    private String notifChannel;
    @SerializedName("timestamp")
    private String timestamp;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorDpUrl() {
        return authorDpUrl;
    }

    public void setAuthorDpUrl(String authorDpUrl) {
        this.authorDpUrl = authorDpUrl;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getNotifChannel() {
        return notifChannel;
    }

    public void setNotifChannel(String notifChannel) {
        this.notifChannel = notifChannel;
    }

    public String getGroupDpUrl() {
        return groupDpUrl;
    }

    public void setGroupDpUrl(String groupDpUrl) {
        this.groupDpUrl = groupDpUrl;
    }

    public long getTimestamp() {
        return Long.parseLong(timestamp);
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
