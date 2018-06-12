package com.congphuong.reactivechatsse.entity;

import com.congphuong.reactivechatsse.request.ChatMessageModel;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Document(collection = "chatMessage")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatMessage {
    public static enum ChatType  {TEXT,TEXT_AND_PICTURE};

    @Id
    private String id;

    @DBRef
    private User user;

    @NotNull
    private String roomId;

    @NotNull
    private Date createAt = new Date();

    @NotNull
    private ChatType chatType;

    private String text;

    private String url;

    public ChatMessage() {}

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ChatMessage(@NotNull User user, @NotNull String roomId, @NotNull ChatType chatType, String text, String url) {
        this.user = user;
        this.roomId = roomId;
        this.chatType = chatType;
        this.text = text;
        this.url = url;
    }

    public ChatMessage(ChatMessageModel chatMessageModel){
        this.roomId = chatMessageModel.getRoomId();
        this.chatType = chatMessageModel.getChatType();
        this.text = chatMessageModel.getText();
        this.url = chatMessageModel.getUrl();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
