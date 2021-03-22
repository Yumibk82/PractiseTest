<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Tuan
  Date: 3/22/2021
  Time: 3:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SEARCH PRODUCT</title>
</head>
<body>
<div>
    <div align="center">
        <caption> <h2>Search Result by part name</h2> </caption>
        <%--                <c:set var="search" value="${requestScope.searching}"/>--%>
        <%--                <c:if test="${(not empty search)}">--%>
        <table border="1"cellpadding="5">

            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Color</th>
                <th>Category ID</th>
            </tr>
            <c:forEach var="product" items="${requestScope['searching']}">
                <tr>
                    <td> <c:out value="${product.getProductId()}"/> </td>
                    <td> <c:out value="${product.getProductName()}"/> </td>
                    <td> <c:out value="${product.getPrice()}"/> </td>
                    <td> <c:out value="${product.getQuantity()}"/> </td>
                    <td> <c:out value="${product.getColor()}"/> </td>
                    <td> <c:out value="${product.getCategoryId()}"/> </td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <br>
        <br>
    </div>
    <div id="pager" class="pager" align="center">
        <form>
            <input type="button"value="<<"class="first"/>
            <input type="button"value="<"class="prev"/>
            <input type="text"class="pagedisplay"/>
            <input type="button"value=">" class="next"/>
            <input type="button"value=">>"class="last">
            <select class="pagesize">
                <option value="2">2</option>
                <option selected="selected" value="10">10</option>
                <option value="20">20</option>
                <option value="30">30</option>
                <option value="40">40</option>
                <option value="50">50</option>
                <option value="100">100</option>
            </select>
        </form>
        <td colspan="2"align="center">
            <a href="/products?action=showAll">BACK</a>
        </td>
    </div>
</div>
</body>
</html>
