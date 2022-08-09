<%-- 
    Document   : users
    Created on : Aug 4, 2022, 9:35:45 PM
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
                <h3 class="box-title">Basic Table</h3>
                <p class="text-muted">User<code>.table</code></p>
                <div class="row">
                    <div class="col-md-6 mb-4">
                        <div class="btn btn-success mb-2" >
                            <a href="<c:url value="/admin/users/add-user/${thisRole}"/>"
                               style="width: 10%; padding:10px 10x 5px 10px; color: black">
                                <i class="fas fa-plus"></i> Them moi nguoi dung
                            </a>
                        </div>

                    </div>

                    <div class="col-md-6 mb-4">
                        <c:url var="filter" value="/admin/users/${thisRole}/">
                            <c:param name="name" value="${name}" />
                        </c:url>
                        <form id="form-filter" action="${filter}">
                            <input name="page" id="page" hidden/>
                            <div class="row justify-content-end mt-2">
                                <div class="col-md-6 col-sm-12">
                                    <div class="form-group">
                                        <input class="form-control" type="text" 
                                               placeholder="Nhap ten nguoi dung..." 
                                               name="name"
                                               aria-label="Search">
                                    </div>
                                </div>
                                <div class="col-md-3 col-sm-12">
                                    <button class="form-control ml-1 btn-warning btn" type="submit">
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
                                <th class="border-top-0">Anh </th>
                                <th class="border-top-0">Ten Tai Khoan</th>
                                <th class="border-top-0">Ho</th>
                                <th class="border-top-0">Ten</th>
                                <th class="border-top-0">Gioi Tinh</th>
                                <th class="border-top-0">SÐT</th>
                                <th class="border-top-0">Email</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${usersByUserRole.size() == null}">
                            <h1 class="text-center text-danger">
                                Hien tai chua co user phu hop. 
                                Vui long quay lai sau!!!
                            </h1>
                        </c:if>
                        <c:forEach var="u" items="${usersByUserRole}">
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
                                        Nu
                                    </c:if>
                                    <c:if test="${u.gender==2}">
                                        Khac
                                    </c:if>

                                </td>
                                <td>${u.phoneNumber}</td>
                                <td>${u.email}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <nav aria-label="Page navigation example">
                    <ul class="pagination" style="justify-content: center">
                        <c:forEach begin="1" end="${Math.ceil(usersCounter/6)}" var="i">
                            <c:url value="/admin/users/${thisRole}/" var="m">
                                <c:param value="${i}" name="page" />
                            </c:url>
                            <li class="page-item"><a class="page-link" href="${m}">${i}</a></li>
                            </c:forEach>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
    <!-- ============================================================== -->
    <!-- End PAge Content -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- Right sidebar -->
    <!-- ============================================================== -->
    <!-- .right-sidebar -->
    <!-- ============================================================== -->
    <!-- End Right sidebar -->
    <!-- ============================================================== -->
</div>
