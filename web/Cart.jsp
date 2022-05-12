<%-- 
    Document   : Cart
    Created on : Dec 31, 2021, 6:21:01 PM
    Author     : pc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book store</title>
        <link rel="stylesheet" href="styles/bootstrap.css" type="text/css">
        <style>
            <jsp:include page="styles/css/bootstrap.css"></jsp:include>
            </style>
        </head>

        <body>
            <header>
                <nav class="navbar-expand-md navbar-dark" style="background-color: forestgreen">
                    <a href="<%=request.getContextPath()%>/homeCus" class="navbar-brand text-uppercase text-xl-start">Bookstore</a>
                <c:if test="${cookie.usernameCookie.value == null}">
                    <span class="navbar-brand text-center">Xin chào, <c:out value="${username}"></c:out></span>
                </c:if>
                <c:if test="${cookie.usernameCookie.value != null}">
                    <span class="navbar-brand text-center">Xin chào, <c:out value="${cookie.usernameCookie.value}"></c:out></span>
                </c:if>
                <a href="<%=request.getContextPath()%>/cart" class="navbar-brand text-center">Giỏ hàng</a>
            </nav>
        </header>
        <br>

        <div class="container">
            <h3 class="text-center text-uppercase">Giỏ hàng</h3>
            <br>

            <c:if test="${cartAvailable == 'No'}">
                <h3 class="text-center" style="color: red">Chưa có giỏ hàng</h3>
            </c:if>
            <c:if test="${cartAvailable == 'Yes'}">
                <div class="table-responsive-md">
                    <table class="table table-bordered table-striped table-hover">
                        <thead class="table-primary text-center text-capitalize">
                            <tr>
                                <th>Tựa đề</th>
                                <th>Giá / cuốn</th>
                                <th>Giảm giá</th>
                                <th>Số lượng</th>
                                <th>Thành tiền</th>
                                <th>Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="items" items="${cart.selectedItems}">
                                <tr>
                                    <td>
                                        <c:out value="${items.itemBook.book.title}"/>
                                    </td>
                                    <td>
                                        <c:out value="${items.itemBook.price}"/>
                                    </td>
                                    <td>
                                        <c:out value="${items.itemBook.discount}"/>
                                    </td>
                                    <td>
                                        <c:out value="${items.quantity}"/>
                                    </td>
                                    <td>
                                        <c:out value="${items.itemBook.price * items.quantity}"/>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="3">
                                    <h5 style="color: mediumblue" class="text-end">Tổng cộng</h5>
                                </td>
                                <td>
                                    <h5 style="color: green">
                                        <c:out value="${cart.totalAmount}"/>
                                    </h5>
                                </td>
                                <td>
                                    <h5 style="color: red">
                                        <c:out value="${cart.totalPrice}"/>
                                    </h5>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <div class="container text-center">
                    <a href="<%=request.getContextPath()%>/payment" class="btn btn-danger">Thanh toán và Đặt hàng</a>
                </div>
            </c:if>
        </div>
    </body>
</html>
