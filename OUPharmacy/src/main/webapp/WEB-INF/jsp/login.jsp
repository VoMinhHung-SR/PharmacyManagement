<%-- 
    Document   : login
    Created on : Aug 1, 2022, 10:42:41 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/login-style.css"/>"/>
</head>


<div class="form-bg mt-5"  >
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-sm-8 col-lg-4  col-md-offset-4 m-auto">
                <div class="form-container mt-3 mb-3">
                    <div class="form-icon"><i class="bi bi-person-fill"></i></div>
                    <h3 class="title">Ðăng nhập</h3>
                    <c:url var="action" value="/login"/>
                    <form class="form-horizontal" action="${action}" method="post">
                        <c:if test="${param.error != null}">
                            <div class="alert alert-danger">
                                Tai khoan hoac mat khau khong khop!!!
                            </div>
                        </c:if>
                        <div class="form-group">
                            <label for="username">Tên tài khoản</label>
                            <input class="form-control" type="text" required
                                   id="username" name="username" placeholder="nhập tài khoản..">
                        </div>
                        <div class="form-group">
                            <label>Mật khẩu</label>
                            <input class="form-control" name="password" required
                                   type="password" placeholder="nhập mật khẩu..">
                        </div>
                        <button type="submit" class="btn btn-default">Ðăng nhập</button>

                        <div class="text-center mt-3">
                            <a href="<c:url value="/register"/>">
                                <i class="bi bi-person-plus-fill"></i> Ðăng ký
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


