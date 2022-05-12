<%-- 
    Document   : Login
    Created on : Dec 31, 2021, 2:06:58 AM
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
                    <h2 class="text-center text-uppercase">Đăng nhập</h2>
                </div>

                <div class="card-body">
                    <form action="login" method="post">
                        <fieldset class="form-group">
                            <label>Tên đăng nhập</label>
                            <input type="text" class="form-control" name="username" required placeholder="Nhập tên đăng nhập" oninvalid="this.setCustomValidity('Không được bỏ trống trường này')" oninput="this.setCustomValidity('')"> 
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>Mật khẩu</label>
                            <input type="password" class="form-control" name="password" required placeholder="Nhập mật khẩu" oninvalid="this.setCustomValidity('Không được bỏ trống trường này')" oninput="this.setCustomValidity('')"> 
                        </fieldset>

                        <div class="text-body">
                            <c:if test="${reply == 'Failed'}">
                                <h4 style="color: red">Sai tên đăng nhập hoặc mật khẩu! Hãy thử lại!</h4>
                            </c:if>
                        </div>
                        
                        <div class="card-footer text-center">
                            <button type="submit" class="btn btn-success">Đăng nhập</button>
                        </div>

                        <%-- Đóng form --%>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

