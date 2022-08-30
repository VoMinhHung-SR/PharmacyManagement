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
                                <th class="border-top-0">Ten tai khoan</th>
                                <th class="border-top-0">Ho</th>
                                <th class="border-top-0">Ten</th>
                                <th class="border-top-0">Gioi tinh</th>
                                <th class="border-top-0">SÐT</th>
                                <th class="border-top-0">Email</th>
                                <th class="border-top-0 text-center">Hoat dong</th>
                                <th class="border-top-0 text-center">Chuc nang</th>
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
                                <td class="text-center">
                                    <c:if test="${u.isActive == 1}">
                                        <span class="text-success"><i class="fas fa-check-circle"></i></span>
                                        </c:if>
                                        <c:if test="${u.isActive == 0}">
                                        <span class="text-danger"><i class="fas fa-window-close"></i></span>
                                        </c:if>
                                </td>
                                <td class="text-center">
                                    <button type="button" class="btn btn-info" 
                                            data-bs-toggle="modal"
                                            data-bs-target="#update-user-modal"
                                            onclick="showEditUserModal(${u.id})"
                                            ><i class="fas fa-pencil-alt"></i></button>                             
                                    <button type="button" class="btn btn-danger"
                                            onclick="updateStatus(${u.id})">
                                        <i class="far fa-trash-alt"></i>
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
</div>

<div id="update-user-modal" class="modal fade" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog" style="max-width: 60% !important">
        <div class="modal-content">
            <div class="modal-body">
                <div class="text-center mt-2 mb-4">  
                    <h3 class="text-danger">Cập nhật thông tin người dùng</h3>
                </div>
                <form id="form-update-user" class="ps-3 pe-3 text-start" action="#">
                    <section class="h-100 bg-info">
                        <div class="container h-100">
                            <div class="row d-flex justify-content-center align-items-center h-100">
                                <div class="col">
                                    <div class="card card-registration my-4">
                                        <div class="row g-0">
                                            <div class="col-xl-12">
                                                <div class="card-body p-md-5 text-black">   
                                                    <div class="row">
                                                        <div class="col-md-4 mb-4">
                                                            <div class="form-outline">
                                                                <label class="form-label" for="firstName">Họ</label>
                                                                <input type="text" name="firstName" id="firstName" class="form-control form-control-lg" />
                                                                
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4 mb-4">
                                                            <div class="form-outline">
                                                                <label class="form-label" for="lastName">Tên</label>
                                                                <input type="text" name="lastName" id="lastName"  class="form-control form-control-lg" />
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4 mb-4">
                                                            <div class="form-outline">
                                                                <label class="form-label" for="dateOfBirth">Ngày sinh</label>
                                                                <input type="date" name="dateOfBirth" id="dateOfBirth" class="form-control form-control-lg" />
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col-md-5 mb-4">
                                                            <div class="form-outline">
                                                                <label class="form-label" for="email">Email</label>
                                                                <input type="email" id="email" name="email" class="form-control form-control-lg" />   
                                                            </div>

                                                        </div>
                                                        <div class="col-md-5 mb-4">
                                                            <div class="form-outline">
                                                                <label class="form-label" for="phoneNumber">Số điện thoại</label>
                                                                <input type="text" name="phoneNumber" id="phoneNumber" class="form-control form-control-lg" />
                                                            </div>
                                                        </div>
                                                        <div class="col-md-2 mb-4">
                                                            <label class="form-label" for="gender">Giới tính</label>
                                                            <select id="gender" name="gender" class="form-control">
                                                                <option value="0">Nam</option>
                                                                <option value="1">Nữ</option>
                                                                <option value="2">Khác</option>
                                                            </select>
                                                        </div>
                                                    </div>

                                                    <div class="form-outline mb-4">
                                                        <label class="form-label" for="address">Địa chỉ</label>
                                                        <input type="text" name="address" id="address" class="form-control form-control-lg" />
                                                    </div>

                                                    <div class="form-outline mb-4">
                                                        <label class="form-label" for="avatar">Ảnh đại diện</label>
                                                        <input type="file" name="file" id="avatar" class="form-control form-control-lg" />
                                                    </div>


                                                    <div class="d-flex justify-content-end pt-3">
                                                        <button type="button" id="update-button" class="btn btn-success btn-lg ms-2">Cap nhat</button>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </form>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

</div>
