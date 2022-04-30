package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

    private final DataSource dataSource;

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll() {
        final String query = "SELECT * FROM products";
        List<Product> ret = new LinkedList<>();
        ResultSet rs = null;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            rs = statement.executeQuery();
            while (rs.next()) {
                ret.add(new Product(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getLong("price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (ret);
    }

    @Override
    public Optional<Product> findById(Long id) {
        Optional<Product> optionalProduct = null;
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

            String request = "SELECT * FROM products WHERE id = " + id;
            ResultSet resultRequest = statement.executeQuery(request);
            resultRequest.next();
            Long productId = resultRequest.getLong("id");
            String name = resultRequest.getString("name");
            Long price = resultRequest.getLong("price");
            optionalProduct = Optional.of(new Product(productId, name, price));
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return optionalProduct;
    }

    @Override
    public void save(Product product) {
        String request = "INSERT INTO products (id, name, price) " +
                "VALUES (" +
                product.getId() + ", " +
                "'" +product.getName() + "', " +
                product.getPrice() + ");";
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            statement.execute();
            ResultSet key = statement.getGeneratedKeys();
            key.next();
            product.setId(key.getLong(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product product) {
        String sqlRequest = "UPDATE products SET " +
                "id = ?, " +
                "name = ?, " +
                "price = ? " +
                "WHERE id = ?;";
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlRequest);
            statement.setLong(1, product.getId());
            statement.setString(2, product.getName());
            statement.setLong(3, product.getPrice());
            statement.setLong(4, product.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM products WHERE id=?";

        try {
            if (findById(id).isPresent()) {
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setLong(1, id);
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
