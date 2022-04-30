package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductsRepositoryJdbcImplTest {

    ProductsRepository productsRepository;
    EmbeddedDatabase dataSource;

    final List<Product> EXPECTED_FIND_ALL = Arrays.asList(
            new Product(0L, "apple", 55L),
            new Product(1L, "pen", 22L),
            new Product(2L, "penapple", 2255L),
            new Product(3L, "applepen", 5522L),
            new Product(4L, "penapplepen", 225522L),
            new Product(5L, "penpenappleapplepen", 222255552L)
    );
    final Product EXPECTED_FIND_BY_ID = new Product(3L, "applepen", 5522L);
    final Product EXPECTED_UPDATE = new Product(1L, "Youpi", 111111L);
    final Product EXPECTED_SAVE = new Product(6L, "Hithere", 666666L);

    @BeforeEach
    void init(){
        dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        productsRepository = new ProductsRepositoryJdbcImpl(dataSource);
    }

    @Test
    void testFindAll() throws Exception {
        assertEquals(EXPECTED_FIND_ALL, productsRepository.findAll());
    }

    @Test
    void testFindById() throws Exception {
        assertEquals(EXPECTED_FIND_BY_ID, productsRepository.findById(3L).get());
    }

    @Test
    void  testSave() throws Exception {
        productsRepository.save(new Product(6L, "Hithere", 666666L));
        assertEquals(productsRepository.findById(6L).get(), EXPECTED_SAVE);
    }

    @Test
    void testUpdate() throws Exception {
        productsRepository.update(new Product(1L, "Youpi", 111111L));
        assertEquals(EXPECTED_UPDATE, productsRepository.findById(1L).get());
    }

    @Test
    void testDelete() throws Exception {
        Long expectSize = productsRepository.findAll().size() - 1L;
        productsRepository.delete(2L);
        assertEquals(expectSize, productsRepository.findAll().size());
    }

//    @Test
//    void testDeleteWithEx() throws Exception {
//        productsRepository.delete(2L);
//        assertThrows(RuntimeException.class, () -> productsRepository.findById(2L).get());
//    }

    @AfterEach
    void close() {dataSource.shutdown();}
}
