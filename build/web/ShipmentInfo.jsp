<%-- 
    Document   : ShipmentInfo
    Created on : Jan 1, 2022, 11:04:40 PM
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

        <div class="container col-md-5">
            <div class="card">
                <div class="card-header text-center">
                    <h2 class="text-center text-uppercase">Thông tin giao hàng</h2>
                </div>

                <div class="card-body">
                    <form action="shipment" method="post">
                        <fieldset class="form-group">
                            <label>Địa chỉ nhận hàng</label>
                            <input type="text" value="<c:out value="Số nhà ${customer.address.houseNo}, đường ${customer.address.street}, quận ${customer.address.district}, TP ${customer.address.city}"/>" class="form-control" name="shippingAddress" required oninvalid="this.setCustomValidity('Không được bỏ trống trường này')" oninput="this.setCustomValidity('')"> 
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Chọn phương thức giao hàng và chi phí</label>
                            <select name="method" class="form-select form-select" aria-label=".form-select-sm example">
                                <option selected value="1">Bưu điện Việt Nam (VNPost) - 50000 VNĐ</option>
                                <option value="2">Viettel Post - 60000 VNĐ</option>
                                <option value="3">DHL - 55000 VNĐ</option>
                                <option value="4">Giaohangnhanh - 65000 VNĐ</option>
                            </select>
                        </fieldset>
                        
                        <input type="hidden" name="paymentId" value="${paymentId}"/>
                        
                        <div class="card-footer text-center">
                            <button type="submit" class="btn btn-danger">Đặt hàng</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
