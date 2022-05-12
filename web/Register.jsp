<%-- 
    Document   : Register
    Created on : Dec 31, 2021, 1:48:20 AM
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
                <nav class="navbar-expand-lg navbar-dark" style="background-color: forestgreen">
                    <a href="<%=request.getContextPath()%>/home" class="navbar-brand text-uppercase text-xl-start">Bookstore</a>
                <a href="<%=request.getContextPath()%>/login" class="navbar-brand text-center">Đăng nhập</a>
                <a href="<%=request.getContextPath()%>/register" class="navbar-brand text-center">Đăng ký</a>
            </nav>
        </header>
        <br>

        <div class="container col-md-5">
            <div class="card">
                <div class="card-header text-center">
                    <h2 class="text-center text-uppercase">Đăng ký</h2>
                </div>

                <div class="card-body">
                    <form action="register" method="post">
                        <fieldset class="form-group">
                            <label>Tên đăng nhập</label>
                            <input type="text" value="<c:out value='${customer.account.username}'/>" class="form-control" name="username" required placeholder="Nhập tên đăng nhập" oninvalid="this.setCustomValidity('Không được bỏ trống trường này')" oninput="this.setCustomValidity('')"> 
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Mật khẩu</label>
                            <input type="password" value="<c:out value='${customer.account.password}'/>" class="form-control" name="password" required placeholder="Nhập mật khẩu" oninvalid="this.setCustomValidity('Không được bỏ trống trường này')" oninput="this.setCustomValidity('')"> 
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Họ</label>
                            <input type="text" value="<c:out value='${customer.fullName.lastName}'/>" class="form-control" name="lastName" required placeholder="Nhập họ" oninvalid="this.setCustomValidity('Không được bỏ trống trường này')" oninput="this.setCustomValidity('')"> 
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Tên đệm</label>
                            <input type="text" value="<c:out value='${customer.fullName.midName}'/>" class="form-control" name="midName" required placeholder="Nhập tên đệm" oninvalid="this.setCustomValidity('Không được bỏ trống trường này')" oninput="this.setCustomValidity('')"> 
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Tên</label>
                            <input type="text" value="<c:out value='${customer.fullName.firstName}'/>" class="form-control" name="firstName" required placeholder="Nhập tên" oninvalid="this.setCustomValidity('Không được bỏ trống trường này')" oninput="this.setCustomValidity('')"> 
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Số điện thoại</label>
                            <input type="text" value="<c:out value='${customer.tel}'/>" class="form-control" name="tel" required placeholder="Nhập số điện thoại" oninvalid="this.setCustomValidity('Không được bỏ trống trường này')" oninput="this.setCustomValidity('')"> 
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Ngày sinh</label>
                            <input type="date" class="form-control" name="doB" required oninvalid="this.setCustomValidity('Không được bỏ trống trường này')" oninput="this.setCustomValidity('')"> 
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Giới tính</label>
                            <input type="text" value="<c:out value='${customer.sex}'/>" class="form-control" name="sex" required placeholder="Nhập giới tính" oninvalid="this.setCustomValidity('Không được bỏ trống trường này')" oninput="this.setCustomValidity('')"> 
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Số nhà</label>
                            <input type="text" value="<c:out value='${customer.address.houseNo}'/>" class="form-control" name="houseNo" required placeholder="Nhập số nhà" oninvalid="this.setCustomValidity('Không được bỏ trống trường này')" oninput="this.setCustomValidity('')">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Đường/ Phường</label>
                            <input type="text" value="<c:out value='${customer.address.street}'/>" class="form-control" name="street" required placeholder="Nhập đường/ phường" oninvalid="this.setCustomValidity('Không được bỏ trống trường này')" oninput="this.setCustomValidity('')">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Quận</label>
                            <input type="text" value="<c:out value='${customer.address.district}'/>" class="form-control" name="district" required placeholder="Nhập quận" oninvalid="this.setCustomValidity('Không được bỏ trống trường này')" oninput="this.setCustomValidity('')"> 
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Thành phố</label>
                            <input type="text" value="<c:out value='${customer.address.city}'/>" class="form-control" name="city" required placeholder="Nhập thành phố" oninvalid="this.setCustomValidity('Không được bỏ trống trường này')" oninput="this.setCustomValidity('')"> 
                        </fieldset>

                        <div class="text-body">
                            <c:if test="${reply == 'Success'}">
                                <h4 style="color: mediumblue">Đăng ký thành công!</h4>
                            </c:if>
                            <c:if test="${reply == 'Failed'}">
                                <h4 style="color: red">Đăng ký không thành công! Hãy thử lại</h4>
                            </c:if>
                        </div>
                        
                        <div class="card-footer text-center">
                            <button type="submit" class="btn btn-success">Đăng ký</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
