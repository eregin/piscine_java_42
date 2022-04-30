package edu.school21.chat.models;

import java.util.ArrayList;
import java.util.Objects;

public class ChatRoom {

    private Integer roomId;

    private String chatName;

    private User owner;

    private ArrayList<Message> chat;

    public ChatRoom(Integer roomId, String chatName, User owner) {
        this.roomId = roomId;
        this.chatName = chatName;
        this.owner = owner;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, chatName, owner, chat);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        ChatRoom room = (ChatRoom) obj;
        return  room.roomId.equals(roomId) &&
                room.chatName.equals(chatName) &&
                room.owner.equals(owner) &&
                room.chat.equals(chat);
    }

    @Override
    public String toString() {
        return "Chatroom - " + chatName +
                ", author " + owner +
                ", messages " + chat;
    }
}
