package school21.spring.service.repositories;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    JdbcTemplate jdbcTemplate;

    private static final String INIT_ONE = "CREATE SCHEMA IF NOT EXISTS public";
    private static final String INIT_TWO = "DROP TABLE IF EXISTS public.users";
    private static final String INIT_THREE = "CREATE TABLE IF NOT EXISTS public.users (identifier int, email varchar(20))";
    private static final String FIND_BY_ID = "SELECT * FROM public.users WHERE identifier=?";
    private static final String FIND_ALL = "SELECT * FROM public.users";
    private static final String SAVE = "INSERT INTO public.users (identifier, email) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE public.users SET email=? WHERE identifier=?";
    private static final String DELETE = "DELETE FROM public.users WHERE identifier=?";
    private static final String FIND_BY_EMAIL = "SELECT * FROM public.users WHERE email=?";

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void init() {
        jdbcTemplate.update(INIT_ONE);
        jdbcTemplate.update(INIT_TWO);
        jdbcTemplate.update(INIT_THREE);
    }

    @Override
    public User findById(Long id) {
        try {
            return (User)jdbcTemplate.queryForObject(FIND_BY_ID, new BeanPropertyRowMapper(User.class), id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper(User.class));
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update(SAVE, entity.getIdentifier(), entity.getEmail());
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update(UPDATE, entity.getEmail(), entity.getIdentifier());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE, id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            User user = (User)jdbcTemplate.queryForObject(FIND_BY_EMAIL, new BeanPropertyRowMapper(User.class), email);
            return Optional.ofNullable(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
