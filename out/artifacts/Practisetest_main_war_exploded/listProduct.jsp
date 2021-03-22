<%--
  Created by IntelliJ IDEA.
  User: Tuan
  Date: 3/22/2021
  Time: 8:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
            crossorigin="anonymous"></script>
    <title>LIST PRODUCT</title>
  </head>
  <body>
  <div class="row">
    <div class="col-1"></div>
    <div class="col-10">
      <div class="row">
        <div class="col-8">
          <caption align="center"> <span style="background: #edc096"> <strong>PRODUCT INFORMATION</strong> </span></caption>
          <table class="table table-hover" border="1" cellpadding="5" style="background: #edc096">
            <tr>
              <th>Product ID</th>
              <th>Product Name</th>
              <th>Product Price</th>
              <th>Product Quantity</th>
              <th>Product Color</th>
              <th>Product Category ID</th>
              <th>Action</th>
            </tr>
            <c:forEach var="product" items="${requestScope['productList']}">
              <tr>
                <td><c:out value="${product.getProductId()}"/></td>
                <td><c:out value="${product.getProductName()}"/></td>
                <td><c:out value="${product.getPrice()}"/></td>
                <td><c:out value="${product.getQuantity()}"/></td>
                <td><c:out value="${product.getColor()}"/></td>
                <td><c:out value="${product.getCategoryId()}"/></td>
                <td>
                  <a href="/products?action=update&productid=${product.getProductId()}">UPDATE</a>
                  <a href="/products?action=delete&productid=${product.getProductId()}">DELETE</a>
                </td>
              </tr>
            </c:forEach>'
          </table>
        </div>
        <div class="col-4">
          <div><h2><a class="btn btn-primary" href="/products?action=create">ADD NEW PRODUCT</a></h2></div>
          <br>
          <div><a class="btn btn-primary" href="/products?action=sort">SORT BY PRODUCT NAME</a></div>
          <br>
          <div>
            <form action="/products?action=search" method="post">
              <input placeholder="Search product name" type="text" name="productname" value="">
              <button class="btn btn-primary" type="submit">Search</button>
            </form>
          </div>
        </div>
      </div>
    </div>
    <div class="col-1"></div>
  </div>
  </body>
</html>
