<%-- 
    Document   : user-role
    Created on : Aug 23, 2022, 7:09:32 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="container-fluid">
    <!-- ============================================================== -->
    <!-- Start Page Content -->
    <!-- ============================================================== -->
    <div class="row">
        <div class="col-sm-12">
            <div class="white-box">
                <h3 class="box-title">Bảng người dùng</h3>
                <p class="text-muted">User<code>.table</code></p>
                <div class="row">
                    <div class="col-md-8 mb-4" style="margin-left: auto">
                        <c:url var="filter" value="/admin/edit-user-role">
                            <c:param name="kw" value="${kw}" />
                        </c:url>
                        <form id="form-filter" action="${filter}">
                            <input name="page" id="page" hidden/>
                            <div class="row justify-content-end mt-2">
                                <div class="col-md-6 col-sm-12">
                                    <div class="form-group">
                                        <input class="form-control" type="text" 
                                               placeholder="Nhap ten nguoi dung..." 
                                               name="kw"
                                               aria-label="Search">
                                    </div>
                                </div>
                                <div class="col-md-3 col-sm-12">
                                    <button class="form-control ml-1 btn-info btn" type="submit">
                                        <i class=" fa fa-search" aria-hidden="true"></i> Tìm kiếm
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="table-responsive">
                    <table class="table text-nowrap">
                        <thead>
                            <tr>
                                <th class="border-top-0">#</th>
                                <th class="border-top-0">Ảnh</th>
                                <th class="border-top-0">Tài khoản</th>
                                <th class="border-top-0">Họ</th>
                                <th class="border-top-0">Tên</th>
                                <th class="border-top-0">Giới tính</th>
                                <th class="border-top-0">SÐT</th>
                                <th class="border-top-0">Email</th>
                                <th class="border-top-0">Chức vụ</th>
                                <th class="border-top-0">Chức năng</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${users.size() == null}">
                            <h1 class="text-center text-danger">
                                Hien tai chua co user phu hop. 
                                Vui long quay lai sau!!!
                            </h1>
                        </c:if>
                        <c:forEach var="u" items="${users}">
                            <tr>
                                <td>${u.id}</td>
                                <td>
                                    <span class="w-100">
                                        <img width="50" height="50" class="img-fluid" src="<c:url value="${u.avatar}"/>" 
                                             alt="${u.username}"/>
                                    </span>

                                </td>
                                <td>${u.username}</td>
                                <td>${u.firstName}</td>
                                <td>${u.lastName}</td>
                                <td>
                                    <c:if test="${u.gender == 0}">
                                        Nam
                                    </c:if>
                                    <c:if test="${u.gender==1}">
                                        Nữ
                                    </c:if>
                                    <c:if test="${u.gender==2}">
                                        Khác
                                    </c:if>

                                </td>
                                <td>${u.phoneNumber}</td>
                                <td>${u.email}</td>
                                <td>
                                    <c:if test="${u.userRole == 'ROLE_DOCTOR'}">
                                        Bác sĩ
                                    </c:if>
                                    <c:if test="${u.userRole=='ROLE_NURSE'}">
                                        Y tá
                                    </c:if>
                                    <c:if test="${u.userRole=='ROLE_PATIENT'}">
                                       Bệnh nhân
                                    </c:if>
                                </td>
                                <td>
                                    <button class="btn btn-primary"
                                            onclick="editAdminUserRole(${u.id})">
                                        Thiết lập quyền
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <nav aria-label="Page navigation example">
                    <ul class="pagination" style="justify-content: center">
                        <c:forEach begin="1" end="${Math.ceil(usersCounter/6)}" var="i">
                            <c:url value="/admin/edit-user-role" var="m">
                                <c:param value="${i}" name="page" />
                            </c:url>
                            <li class="page-item"><a class="page-link" href="${m}">${i}</a></li>
                            </c:forEach>
                    </ul>
                </nav>
            </div>
        </div>
    </div>

</div>