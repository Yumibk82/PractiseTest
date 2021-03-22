package models.services;

import models.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements IProductService{

    private String jdbcURL = "jdbc:mysql://localhost:3306/practisetest";
    private String getJdbcUsername = "root";
    private String getJdbcPassword = "Tulinh0308@a";

    private static final String INSERT_PRODUCT_SQL = "INSERT INTO product (productname,price,quantity,color,categoryid) VALUES (?,?,?,?,?);";
    private static final String UPDATE_PRODUCT_SQL = "UPDATE product set productname=?,price=?,quantity=?,color=?,categoryid=? where productid=?;";
    private static final String DELETE_PRODUCT_SQL = "DELETE from product where productid=?";
    private static final String SELECT_PRODUCT_BY_ID="SELECT * from product where productid=?";
    private static final String SEARCH_ALL_PRODUCT_BY_NAME="SELECT * FROM product where productname like ?";


    public ProductServiceImpl(){

    }
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, getJdbcUsername, getJdbcPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
    public void getDB(Product product,PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1,product.getProductName());
        preparedStatement.setDouble(2,product.getPrice());
        preparedStatement.setInt(3,product.getQuantity());
        preparedStatement.setString(4,product.getColor());
        preparedStatement.setInt(5,product.getCategoryId());
    }

    @Override
    public void insertProduct(Product product) throws SQLException {
        System.out.println(INSERT_PRODUCT_SQL);
        try(
                Connection connection=getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement(INSERT_PRODUCT_SQL);){
                getDB(product, preparedStatement);
                preparedStatement.executeUpdate();
        }
    }
    @Override
    public boolean updateProduct(Product product) throws SQLException {
        boolean updatedProduct;
        try(
                Connection connection=getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement(UPDATE_PRODUCT_SQL);){
                getDB(product,preparedStatement);
                preparedStatement.setInt(6,product.getProductId());
                updatedProduct=preparedStatement.executeUpdate()>0;
        }
        return updatedProduct;
    }

    @Override
    public boolean deleteProduct(int productId) throws SQLException {
        boolean deletedProduct;
        try(
                Connection connection=getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement(DELETE_PRODUCT_SQL);){
                preparedStatement.setInt(1,productId);
                deletedProduct=preparedStatement.executeUpdate()>0;
        }
        return deletedProduct;
    }

    @Override
    public List<Product> showAllProduct() {
    List<Product> productList=new ArrayList<>();
        try(
                Connection connection=getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement("select * from product")){

            System.out.println(preparedStatement);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                Product product=new Product();
                int id=rs.getInt("productid");
                String name=rs.getString("productname");
                double price=rs.getDouble("price");
                int quantity=rs.getInt("quantity");
                String color=rs.getString("color");
                int categoryid=rs.getInt("categoryid");
                product.setProductId(id);
                product.setProductName(name);
                product.setPrice(price);
                product.setQuantity(quantity);
                product.setColor(color);
                product.setCategoryId(categoryid);
                productList.add(product);
            }
        }catch (SQLException e){
            printSQLException(e);
        }
        return productList;
    }

    @Override
    public Product selectProduct(int id) {

        try(
                Connection connection=getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement(SELECT_PRODUCT_BY_ID)){
            preparedStatement.setInt(1,id);
            System.out.println(preparedStatement);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                Product product=new Product();
                String name=rs.getString("productname");
                double price=rs.getDouble("price");
                int quantity=rs.getInt("quantity");
                String color=rs.getString("color");
                int categoryid=rs.getInt("categoryid");
                product.setProductId(id);
                product.setProductName(name);
                product.setPrice(price);
                product.setQuantity(quantity);
                product.setColor(color);
                product.setCategoryId(categoryid);
                return product;
            }
        }catch (SQLException e){
            printSQLException(e);
        }
        return null;
    }
    @Override
    public List<Product> searchProduct(String productname) throws SQLException {
        List<Product> productList=new ArrayList<>();
        try(
                Connection connection=getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement(SEARCH_ALL_PRODUCT_BY_NAME);){
           String productname1 = "%"+productname+"%";
            preparedStatement.setString(1,productname1);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                int id=rs.getInt("productid");
                String name=rs.getString("productname");
                double price=rs.getDouble("price");
                int quantity=rs.getInt("quantity");
                String color=rs.getString("color");
                int categoryid=rs.getInt("categoryid");
                Product product=new Product();
                product.setProductId(id);
                product.setProductName(name);
                product.setPrice(price);
                product.setQuantity(quantity);
                product.setColor(color);
                product.setCategoryId(categoryid);
                productList.add(product);
            }
        }
        return productList;
    }

    private void printSQLException(SQLException e) {
        for(Throwable ex:e){
            if(e instanceof SQLException){
                e.printStackTrace();
                System.err.println("SQLState: "+((SQLException)e).getSQLState());
                System.err.println("ErrorCode: "+((SQLException)e).getErrorCode());
                System.err.println("Message: "+e.getMessage());
                Throwable t=ex.getCause();
                while (t!=null){
                    System.out.println("Cause: "+t);
                    t=t.getCause();
                }
            }
        }
    }
}
