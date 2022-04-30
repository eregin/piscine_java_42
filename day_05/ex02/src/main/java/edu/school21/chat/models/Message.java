package edu.school21.chat.models;

import java.time.LocalDate;
import java.util.Objects;

public class Message {

    private Long messageId;

    private User author;

    private ChatRoom whereSpelled;

    private String text;

    private LocalDate time;

    public Message(Long messageId, User author, ChatRoom whereSpelled, String text, LocalDate time) {
        this.messageId = messageId;
        this.author = author;
        this.whereSpelled = whereSpelled;
        this.text = text;
        this.time = time;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public ChatRoom getWhereSpelled() {
        return whereSpelled;
    }

    public void setWhereSpelled(ChatRoom whereSpelled) {
        this.whereSpelled = whereSpelled;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getTime() {
        return time;
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, author, whereSpelled, text, time);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        Message message = (Message) obj;
        return  message.messageId.equals(messageId) &&
                message.author.equals(author) &&
                message.whereSpelled.equals(whereSpelled) &&
                message.text.equals(text) &&
                message.time.equals(time);
    }

    @Override
    public String toString() {
        return "Message: {" +
                "id = " + messageId +
                ", author " + author +
                ", in chatroom " + whereSpelled +
                ", spelled message '" + text + "'" +
                ", at " + time + " }";
    }
}
