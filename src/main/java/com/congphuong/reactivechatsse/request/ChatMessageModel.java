package com.congphuong.reactivechatsse.request;

import com.congphuong.reactivechatsse.entity.ChatMessage;

import java.util.Date;

public class ChatMessageModel {

    private String roomId;

    private ChatMessage.ChatType chatType;

    private String text;

    private String url;

    public ChatMessageModel() {}

    public ChatMessageModel(String roomId, ChatMessage.ChatType chatType, String text, String url) {
        this.roomId = roomId;
        this.chatType = chatType;
        this.text = text;
        this.url = url;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public ChatMessage.ChatType getChatType() {
        return chatType;
    }

    public void setChatType(ChatMessage.ChatType chatType) {
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
