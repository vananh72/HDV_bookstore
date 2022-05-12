<%-- 
    Document   : PaymentInfo
    Created on : Jan 1, 2022, 11:26:28 PM
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
                    <h2 class="text-center text-uppercase">Thông tin thanh toán</h2>
                </div>

                <div class="card-body">
                    <form action="payment" method="post">
                        <fieldset class="form-group">
                            <label>Chọn 1 trong 2 phương thức thanh toán sau:</label>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="method" id="flexRadioDefault1" checked value="Cash">
                                <label class="form-check-label" for="Cash">
                                    Thanh toán khi nhận hàng
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="method" id="flexRadioDefault2" value="Credit">
                                <label class="form-check-label" for="Credit">
                                    Thanh toán bằng thẻ tín dụng
                                </label>
                            </div>
                        </fieldset>

                        <label class="text-dark">Nếu chọn thanh toán bằng thẻ tín dụng, vui lòng nhập các thông tin sau để tiếp tục:</label>
                        <fieldset class="form-group">
                            <label>Số thẻ</label>
                            <input type="text" value="<c:out value='${credit.cardId}'/>" class="form-control" name="cardId" placeholder="Nhập số thẻ"> 
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Số tài khoản</label>
                            <input type="text" value="<c:out value='${credit.accountId}'/>" class="form-control" name="accountId" placeholder="Nhập số tài khoản"> 
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Ngân hàng</label>
                            <input type="text" value="<c:out value='${credit.bank}'/>" class="form-control" name="bank" placeholder="Nhập ngân hàng"> 
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Loại thẻ</label>
                            <input type="text" value="<c:out value='${credit.type}'/>" class="form-control" name="type" placeholder="Nhập loại thẻ"> 
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>Ngày hết hạn</label>
                            <input type="date" class="form-control" name="expDate"> 
                        </fieldset>
                        
                        <div class="text-body">
                            <c:if test="${reply == 'Failed'}">
                                <h4 style="color: red">Một hoặc nhiều hơn các trường chưa có thông tin. Hãy nhập lại!</h4>
                            </c:if>
                        </div>
                        
                        <div class="card-footer text-center">
                            <button type="submit" class="btn btn-success">Tiếp tục đến Địa chỉ giao hàng</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
