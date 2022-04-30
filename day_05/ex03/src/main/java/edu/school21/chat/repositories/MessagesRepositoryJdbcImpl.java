package edu.school21.chat.repositories;

import edu.school21.chat.exeptions.NotSavedSubEntityException;
import edu.school21.chat.models.ChatRoom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
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
                                            LocalDateTime.of(2000, 9, 4, 20, 15)));
        connection.close();
        statement.close();

        return optionalMessage;
    }

    @Override
    public boolean save(Message message) throws NotSavedSubEntityException, SQLException {
        String sqlRequest = "INSERT INTO chat.messages (author, room_id, message, time) VALUES (" +
                message.getAuthor().getUserId() + ", " +
                message.getWhereSpelled().getRoomId() + ", " +
                "'" + message.getText() + "', " +
                "'" + message.getTime() + "');";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlRequest, Statement.RETURN_GENERATED_KEYS)) {

            statement.execute();
            ResultSet key = statement.getGeneratedKeys();
            key.next();
            message.setMessageId(key.getLong(1));
        } catch (SQLException ex) {
            throw new NotSavedSubEntityException();
        }
        return true;
    }

    @Override
    public boolean update(Message message) throws SQLException {
        String sqlRequest = "UPDATE chat.messages SET " +
                            "author = ?, " +
                            "room_id = ?, " +
                            "message = ?, " +
                            "time = ? " +
                            "WHERE id = ?;";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlRequest)){

            statement.setLong(1, message.getAuthor().getUserId());
            statement.setLong(2, message.getWhereSpelled().getRoomId());
            statement.setString(3, message.getText());
            statement.setTimestamp(4, Timestamp.valueOf(message.getTime()));
            statement.setLong(5,message.getMessageId());

            statement.execute();
        }
        return true;
    }
}
