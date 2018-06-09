package com.congphuong.reactivechatsse.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Document(collection = "chatMessage")
public class ChatMessage {
    enum ChatType  {TEXT,TEXT_AND_PICTURE};

    @Id
    private String id;

    @NotNull
    private String username;

    @NotNull
    private String roomId;

    @NotNull
    private Date createAt = new Date();

    @NotNull
    private ChatType chatType;

    private String text;

    private String url;

    public ChatMessage() {}

    public ChatMessage(@NotNull String username, @NotNull String roomId, @NotNull ChatType chatType, String text, String url) {
        this.username = username;
        this.roomId = roomId;
        this.chatType = chatType;
        this.text = text;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public ChatType getChatType() {
        return chatType;
    }

    public void setChatType(ChatType chatType) {
        this.chatType = chatType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
