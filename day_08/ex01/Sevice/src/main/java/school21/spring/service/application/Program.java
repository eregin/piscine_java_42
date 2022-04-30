package school21.spring.service.application;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;

import java.util.ArrayList;
import java.util.List;

public class Program {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        UsersRepository usersRepositoryJdbc = context.getBean("usersRepositoryJdbc", UsersRepository.class);
        DriverManagerDataSource ds = context.getBean("DriverManagerDataSource", DriverManagerDataSource.class);

        usersRepositoryJdbc.save(new User(1L, "pupkin@mail"));
        usersRepositoryJdbc.save(new User(2L, "sora@mail"));
        usersRepositoryJdbc.save(new User(3L, "goga@mail"));
        usersRepositoryJdbc.save(new User(4L, "lopa@mail"));
        usersRepositoryJdbc.save(new User(5L, "mike@mail"));
        System.out.println(usersRepositoryJdbc.findById(3L));
        System.out.println(usersRepositoryJdbc.findByEmail("lopa@mail").get());
        usersRepositoryJdbc.update(new User(2L, "PUPKIN@mail"));
        usersRepositoryJdbc.delete(1L);
        List<User> ulist = usersRepositoryJdbc.findAll();
        for (User u: ulist) {
            System.out.println(u);
        }
        System.out.println("----------------------------------------");
        UsersRepository jdbcTemplate = context.getBean("usersRepositoryJdbcTemplate", UsersRepositoryJdbcTemplateImpl.class);
        jdbcTemplate.save(new User(1L, "pupkin@mail"));
        jdbcTemplate.save(new User(2L, "sora@mail"));
        jdbcTemplate.save(new User(3L, "goga@mail"));
        jdbcTemplate.save(new User(4L, "lopa@mail"));
        jdbcTemplate.save(new User(5L, "mike@mail"));
        System.out.println(jdbcTemplate.findById(3L));
        System.out.println(jdbcTemplate.findByEmail("lopa@mail").get());
        jdbcTemplate.update(new  User(2L, "Yopek@mail"));
        jdbcTemplate.delete(1L);
        ulist = jdbcTemplate.findAll();
        for (User u: ulist) {
            System.out.println(u);
        }
        context.close();
    }
}
