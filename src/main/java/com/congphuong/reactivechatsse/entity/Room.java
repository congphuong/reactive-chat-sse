package com.congphuong.reactivechatsse.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "room")
public class Room {
    @Id
    private String id;

    @NotNull
    private String roomName;

    public Room() {

    }

    public Room(@NotNull String roomName) {
        this.roomName = roomName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
