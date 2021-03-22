package models.services;

import models.entities.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductService {
    public void insertProduct(Product product)throws SQLException;
    public boolean updateProduct(Product product)throws SQLException;
    public boolean deleteProduct(int productId)throws SQLException;

    List<Product> showAllProduct();
    public List<Product> searchProduct(String productname) throws SQLException;

    Product selectProduct(int id);
}
