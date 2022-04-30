package school21.spring.service.repositories;

import com.zaxxer.hikari.HikariDataSource;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private static final String INIT_ONE = "CREATE SCHEMA IF NOT EXISTS public";
    private static final String INIT_TWO = "DROP TABLE IF EXISTS public.users";
    private static final String INIT_THREE = "CREATE TABLE IF NOT EXISTS public.users (identifier int, email varchar(20))";
    private static final String FIND_BY_ID = "SELECT * FROM public.users WHERE identifier=?";
    private static final String FIND_ALL = "SELECT * FROM public.users";
    private static final String SAVE = "INSERT INTO public.users (identifier, email) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE public.users SET email=? WHERE identifier=?";
    private static final String DELETE = "DELETE FROM public.users WHERE identifier=?";
    private static final String FIND_BY_EMAIL = "SELECT * FROM public.users WHERE email=";

    DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void init() {
        try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(INIT_ONE)) {
            statement.execute();
        }  catch (SQLException ex) {
            System.err.println("Can't create schema");
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INIT_TWO)) {
            statement.execute();
        }  catch (SQLException ex) {
            System.err.println("Can't drop table");
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INIT_THREE)) {
            statement.execute();
        }  catch (SQLException ex) {
            System.err.println("Can't create table");
        }
    }

    @Override
    public User findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)){

            preparedStatement.setLong(1, id);
            ResultSet resultRequest = preparedStatement.executeQuery();
            if (resultRequest.next()) {
                String email = resultRequest.getString("email");
                return new User(id, email);
            }
        }
        catch (SQLException ex) {
            System.err.printf("Try to find by id = %d failed", id);
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong(1);
                String email = resultSet.getString(2);
                users.add(new User(id, email));
            }
        } catch (SQLException e) {
            System.err.println("Try to find all is failed");
        }
        return users;
    }

    @Override
    public void save(User entity) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE)) {
            preparedStatement.setLong(1, entity.getIdentifier());
            preparedStatement.setString(2, entity.getEmail());
            if (preparedStatement.executeUpdate() != 1) {
                throw new IllegalStateException("Updated rows not equal 1");
            }
        } catch (Exception e) {
            System.err.println("Failed to save user id =" + entity.getIdentifier());
        }
    }

    @Override
    public void update(User entity) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, entity.getEmail());
            statement.setLong(2, entity.getIdentifier());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to update user id = " + entity.getIdentifier());
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to execute delete query");
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EMAIL + "'" + email + "'")) {

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User(resultSet.getLong(1),
                        resultSet.getString(2));
                return Optional .of(user);
            }
        } catch (SQLException e) {
            System.err.println("Failed to execute delete query: " + e);
        }
        return Optional.empty();
    }
}
