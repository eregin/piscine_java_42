package edu.school21.chat.repositories;

import edu.school21.chat.models.ChatRoom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private final DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) throws SQLException {

        Connection connection = dataSource.getConnection();

        Statement statement = connection.createStatement();

        String sqlRequest = "SELECT * FROM chat.messages WHERE id = " + id;
        ResultSet resultRequest = statement.executeQuery(sqlRequest);
        resultRequest.next();
        User user = new User(1L, "ereginia", "12345",null, null);
        ChatRoom chatRoom = new ChatRoom(1L, "chatroom", null);

        Optional<Message> optionalMessage = Optional.of(new Message((long) resultRequest.getInt(1),
                                            user, chatRoom, resultRequest.getString("message"),
                                            LocalDateTime.of(2000, 8, 5, 5,5)));
        connection.close();
        statement.close();

        return optionalMessage;
    }
}
