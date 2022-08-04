<%-- 
    Document   : registry
    Created on : Aug 1, 2022, 10:49:13 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<c:if test="${errMgs != null}">
    <div class="text-align text-danger">${errMsg}</div>
</c:if>

<c:url var="action" value="/register"/>

<form:form method="post" action="${action}" 
           enctype="multipart/form-data" modelAttribute="user">

    <form:errors path="*" element="div" cssClass="alert alert-danger" />

    <section class="h-100 bg-dark">
        <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col">
                    <div class="card card-registration my-4">
                        <div class="row g-0">
                            <div class="col-xl-12">
                                <div class="card-body p-md-5 text-black">
                                    <h3 class="mb-5 text-uppercase">Đăng kí người dùng</h3>
                                    <div class="row">
                                        <div class="col-md-6 mb-4">
                                            <div class="form-outline">
                                                <label class="form-label" for="firstName">Họ</label>
                                                <form:input type="text" path="firstName" id="firstName" class="form-control form-control-lg" />
                                            </div>
                                        </div>
                                        <div class="col-md-6 mb-4">
                                            <div class="form-outline">
                                                <label class="form-label" for="lastName">Tên</label>
                                                <form:input type="text" path="lastName" id="lastName"  class="form-control form-control-lg" />
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6 mb-4">
                                            <div class="form-outline">
                                                <label class="form-label" for="username">Tên tài khoản</label>
                                                <form:input type="text" path="username" id="username" class="form-control form-control-lg" />

                                            </div>
                                        </div>
                                        <div class="col-md-6 mb-4">
                                            <div class="form-outline">
                                                <label class="form-label" for="password">Mật khẩu</label>
                                                <form:input type="password" path="password" id="password" class="form-control form-control-lg" />
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6 mb-4">
                                            <div class="form-outline">
                                                <label class="form-label" for="dateOfBirth">Ngày sinh</label>
                                                <form:input type="date" path="dateOfBirth" id="dateOfBirth" class="form-control form-control-lg" />
                                            </div>
                                        </div>
                                        <div class="col-md-6 mb-4">
                                            <div class="form-outline mb-4">
                                                <label class="form-label" for="confirmPassword">Xác nhận mật khẩu</label>
                                                <form:input type="password" path="confirmPassword" id="confirmPassword" class="form-control form-control-lg" />
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-5 mb-4">
                                            <div class="form-outline">
                                                <label class="form-label" for="email">Email</label>
                                                <form:input type="email" id="email" path="email" class="form-control form-control-lg" />   
                                            </div>

                                        </div>
                                        <div class="col-md-5 mb-4">
                                            <div class="form-outline">
                                                <label class="form-label" for="phoneNumber">Số điện thoại</label>
                                                <form:input type="text" path="phoneNumber" id="phoneNumber" class="form-control form-control-lg" />
                                            </div>
                                        </div>
                                        <div class="col-md-2 mb-4">
                                            <label class="form-label" for="gender">Giới tính</label>
                                            <form:select id="gender" path="gender" class="form-control">
                                                <option value="0">Nam</option>
                                                <option value="1">Nữ</option>
                                                <option value="2">Khác</option>
                                            </form:select>
                                        </div>
                                    </div>

                                    <div class="form-outline mb-4">
                                        <label class="form-label" for="address">Địa chỉ</label>
                                        <form:input type="text" path="address" id="address" class="form-control form-control-lg" />
                                    </div>

                                    <div class="form-outline mb-4">
                                        <label class="form-label" for="avatar">Ảnh đại diện</label>
                                        <form:input type="file" path="file" id="avatar" class="form-control form-control-lg" />
                                    </div>


                                    <div class="d-flex justify-content-end pt-3">
                                        <button type="button" class="btn btn-light btn-lg">Reset all</button>
                                        <button type="submit" class="btn btn-warning btn-lg ms-2">Submit form</button>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</form:form>
