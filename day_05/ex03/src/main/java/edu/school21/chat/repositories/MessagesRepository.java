package edu.school21.chat.repositories;

import java.sql.SQLException;
import java.util.Optional;

import edu.school21.chat.exeptions.NotSavedSubEntityException;
import edu.school21.chat.models.Message;

public interface MessagesRepository {

    Optional<Message> findById(Long id) throws SQLException;

    boolean save(Message message) throws NotSavedSubEntityException, SQLException;

    boolean update(Message message) throws SQLException;
}
