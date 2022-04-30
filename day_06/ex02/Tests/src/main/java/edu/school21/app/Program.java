package edu.school21.app;

import edu.school21.models.Product;
import edu.school21.repositories.ProductsRepository;
import edu.school21.repositories.ProductsRepositoryJdbcImpl;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;

public class Program {

    public static void main(String[] args) throws SQLException {
        DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();

        ProductsRepository repository = new ProductsRepositoryJdbcImpl(dataSource);

        if (repository.findById(3L).isPresent())
            System.out.println(repository.findById(3L).get());

        repository.save(new Product(null, "popole", 666L));
        System.out.println(repository.findById(6L).get());

        repository.update(new Product(3L, "lopen", 67777L));
        System.out.println(repository.findById(3L).get());
    }
}
