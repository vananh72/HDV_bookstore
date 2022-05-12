<%-- 
    Document   : Order
    Created on : Dec 31, 2021, 6:21:12 PM
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
            <h3 class="text-center text-uppercase">Đơn hàng</h3>
            <br>

            <%--  <c:if test="${orderAvailable == 'Yes'}"> --%>
                <h3 class="text-center" style="color: red">Chúc mừng bạn đã đặt hàng thành công!</h3>
                <h3 class="text-center" style="color: #0b5ed7">Thông tin chi tiết về đơn hàng của khách hàng <c:out value="${order.customer.fullName.lastName} ${order.customer.fullName.midName} ${order.customer.fullName.firstName}"></c:out></h3>
                <div class="table-responsive-md">
                    <table class="table table-bordered table-striped table-hover">
                        <thead class="table-primary text-center text-capitalize">
                            <tr>
                                <th>Tựa đề</th>
                                <th>Giá / cuốn</th>
                                <th>Giảm giá</th>
                                <th>Số lượng</th>
                                <th>Thành tiền</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="items" items="${order.cart.selectedItems}">
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
                                    <h5 style="color: mediumblue" class="text-end">Tổng số sản phẩm và tổng giá</h5>
                                </td>
                                <td>
                                    <h5 style="color: green">
                                        <c:out value="${order.cart.totalAmount}"/>
                                    </h5>
                                </td>
                                <td>
                                    <h5 style="color: crimson">
                                        <c:out value="${order.cart.totalPrice}"/>
                                    </h5>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3">
                                    <h5 style="color: mediumblue" class="text-end">Địa chỉ nhận hàng</h5>
                                </td>
                                <td colspan="2">
                                    <h5>
                                        <c:out value="${order.shipment.shippingAddress}"/>
                                    </h5>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3">
                                    <h5 style="color: mediumblue" class="text-end">Phương thức giao hàng và chi phí</h5>
                                </td>
                                <td>
                                    <h5>
                                        <c:out value="${order.shipment.method}"/>
                                    </h5>
                                </td>
                                <td>
                                    <h5 style="color: crimson">
                                        <c:out value="${order.shipment.cost}"/>
                                    </h5>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3">
                                    <h5 style="color: mediumblue" class="text-end">Phương thức thanh toán</h5>
                                </td>
                                <td colspan="2">
                                    <c:if test="${order.payment.method == 'Cash'}">
                                        <h5>Thanh toán khi nhận hàng</h5>
                                    </c:if>
                                    <c:if test="${order.payment.method == 'Credit'}">
                                        <h5>Thanh toán bằng thẻ tín dụng</h5>
                                        <h5>
                                            <c:out value="Số tài khoản: ${credit.accountId} - Số thẻ: ${credit.cardId} - Ngân hàng: ${credit.bank}"/>
                                        </h5>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3">
                                    <h5 style="color: red" class="text-end">Tổng số tiền phải trả</h5>
                                </td>
                                <td colspan="2">
                                    <h5 style="color: red">
                                        <c:out value="${order.totalPrice}"/>
                                    </h5>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <div class="container text-center">
                    <a href="<%=request.getContextPath()%>/homeCus" class="btn btn-success">Về trang chủ</a>
                </div>
                    <%-- </c:if> --%>
        </div>
    </body>
</html>
