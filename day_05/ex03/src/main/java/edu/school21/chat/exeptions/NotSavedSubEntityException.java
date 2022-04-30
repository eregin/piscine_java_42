package edu.school21.chat.exeptions;

public class NotSavedSubEntityException extends RuntimeException {
    public NotSavedSubEntityException() {
        System.out.println("Can't save message");
    }
}
