package controllers;

import models.entities.Product;
import models.services.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductController", urlPatterns = "/products")
public class ProductController extends HttpServlet {

    public ProductServiceImpl productService;

    public void init() {
        productService = new ProductServiceImpl();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    showNewForm(request, response);
                    break;
                case "update":
                    showUpdateForm(request, response);
                    break;
                case "showAll":
                    showAllProduct(request,response);
                    break;
                case "delete":
                    deleteProduct(request,response);
                    break;
                default:
                    listProduct(request, response);
                    break;

            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void showAllProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException,IOException,ServletException{
            List<Product> productList=productService.showAllProduct();
            request.setAttribute("productList",productList);
            RequestDispatcher dispatcher=request.getRequestDispatcher("/listProduct.jsp");
            dispatcher.forward(request,response);

    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("productid"));
        Product existingProduct = productService.selectProduct(id);
        request.setAttribute("product", existingProduct);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/update.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/create.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    insertProduct(request, response);
                    break;
                case "delete":
                    deleteProduct(request, response);
                    break;
                case "update":
                    updateProduct(request, response);
                    break;
                case "search":
                    searchProduct(request,response);
                    break;
                default:
                    listProduct(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("productid"));
        productService.deleteProduct(id);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/delete.jsp");
        dispatcher.forward(request, response);

        List<Product> productList = productService.showAllProduct();
        request.setAttribute("listProduct", productList);
        RequestDispatcher dispatcher1 = request.getRequestDispatcher("/listProduct.jsp");
        dispatcher1.forward(request, response);

    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("productid"));
        String name = request.getParameter("productname");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        int categoryid = Integer.parseInt(request.getParameter("categoryid"));
        Product product = new Product(id, name, price, quantity, color, categoryid);
        productService.updateProduct(product);
        response.sendRedirect("/products");
    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
//        int id = Integer.parseInt(request.getParameter("productid"));
        String name = request.getParameter("productname");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        int categoryid = Integer.parseInt(request.getParameter("categoryid"));
        Product product = new Product( name, price, quantity, color, categoryid);
        productService.insertProduct(product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/create.jsp");
        dispatcher.forward(request, response);
    }

    private void listProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Product> productList = productService.showAllProduct();
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("/listProduct.jsp").forward(request,response);
    }

    private void searchProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        String productname = request.getParameter("productname");
        List<Product> productList = productService.searchProduct(productname);
        request.setAttribute("searching", productList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/search.jsp");
        dispatcher.forward(request, response);

    }
}

