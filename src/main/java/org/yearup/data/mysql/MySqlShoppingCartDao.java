package org.yearup.data.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao {

    private ProductDao productDao;

    @Autowired
    public MySqlShoppingCartDao(DataSource dataSource, ProductDao productDao) {
        super(dataSource);
        this.productDao = productDao;

    }

    @Override
    public ShoppingCart getByUserId(int userId) {
        ShoppingCart shoppingCart = new ShoppingCart();

        String query = "Select product_id, quantity From shopping_cart Where user_id = ?";

        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        )
        {
            preparedStatement.setInt(1, userId);
            try(ResultSet row = preparedStatement.executeQuery())
            {
                while (row.next()){
                    int productId = row.getInt("product_id");
                    int quantity = row.getInt("quantity");

                    //Gets the product with the matching product id
                    Product product = productDao.getById(productId);

                    //Turn product into a shoppingCartItem so that it can be added to cart
                    ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
                    shoppingCartItem.setProduct(product);
                    shoppingCartItem.setQuantity(quantity);

                    shoppingCart.add(shoppingCartItem);


                }
                return shoppingCart;
            }
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }


    }

    @Override
    public ShoppingCart addProduct(int userId, int productId) {

        String sql = "INSERT INTO shopping_cart(user_id, product_id, quantity) VALUES(?, ?, 1)";

        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        )
        {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, productId);

            preparedStatement.executeUpdate();

            return getByUserId(userId);


        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int productId) {

    }

    @Override
    public void delete() {

    }
}
