<%-- 
    Document   : HomePage
    Created on : Dec 31, 2021, 1:06:21 AM
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
                    <a href="<%=request.getContextPath()%>/home" class="navbar-brand text-uppercase text-xl-start">Bookstore</a>
                <a href="<%=request.getContextPath()%>/login" class="navbar-brand text-center">Đăng nhập</a>
                <a href="<%=request.getContextPath()%>/register" class="navbar-brand text-center">Đăng ký</a>
            </nav>
        </header>
        <br>

        <div class="container">
            <h3 class="text-center text-uppercase">Chào mừng bạn đến với cửa hàng sách!</h3>
            <br>

            <div class="table-responsive-md">
                <table class="table table-bordered table-striped table-hover">
                    <thead class="table-primary text-center text-capitalize">
                        <tr>
                            <th>Ảnh</th>
                            <th>ISBN</th>
                            <th>Tựa đề</th>
                            <th>Tác giả</th>
                            <th>Tóm lược</th>
                            <th>Thể loại</th>
                            <th>Ấn bản thứ</th>
                            <th>Ngày xuất bản</th>
                            <th>Nhà xuất bản</th>
                            <th>Số trang</th>
                            <th>Ngôn ngữ</th>
                            <th>Kích thước</th>
                            <th>Trọng lượng</th>
                            <th>Giá</th>
                            <th>Giảm giá</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="itemBook" items="${listItemBooks}">
                            <tr>
                                <td>
                                    <c:forEach items="${itemBook.images}" var="image">
                                        <img src="${image.src}" style="width:175px;height:250px;">
                                    </c:forEach>
                                </td>
                                <td>
                                    <c:out value="${itemBook.book.isbn}"/>
                                </td>
                                <td>
                                    <c:out value="${itemBook.book.title}"/>
                                </td>
                                <td>
                                    <c:forEach items="${itemBook.book.author}" var="auth">
                                        <c:out value="${auth.name}"/>
                                    </c:forEach>
                                </td>
                                <td>
                                    <c:out value="${itemBook.book.summary}"/>
                                </td>
                                <td>
                                    <c:out value="${itemBook.book.category.type}"/>
                                </td>
                                <td>
                                    <c:out value="${itemBook.book.edition} ed."/>
                                </td>
                                <td>
                                    <c:out value="${itemBook.book.publicationDate}"/>
                                </td>
                                <td>
                                    <c:out value="${itemBook.book.publisher.name}"/>
                                </td>
                                <td>
                                    <c:out value="${itemBook.book.numOfPages} trang"/>
                                </td>
                                <td>
                                    <c:out value="${itemBook.book.language}"/>
                                </td>
                                <td>
                                    <c:out value="${itemBook.book.dimensions}"/>
                                </td>
                                <td>
                                    <c:out value="${itemBook.book.weight} gam"/>
                                </td>
                                <td>
                                    <c:out value="${itemBook.price} VNĐ"/>
                                </td>
                                <td>
                                    <c:out value="${itemBook.discount}"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>