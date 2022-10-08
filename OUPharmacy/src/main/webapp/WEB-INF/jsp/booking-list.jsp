<%-- 
    Document   : booking-list
    Created on : Aug 11, 2022, 1:34:16 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${errMgs != null}">
    <h1 class="text-center text-danger">${errMgs}</h1>
</c:if>
<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <div class="white-box">
                <h3 class="box-title text-center pt-5 text-danger">Danh sách đặt lịch</h3>
                <div class="row">
                    <div class="col-md-6 mb-4">
                        <div class="btn btn-success mb-2" >
                            <a href="<c:url value="/booking"/>"
                               style="width: 10%; padding:10px 10x 5px 10px; color: black">
                                <i class="fa fa-plus"></i> Đặt lịch khám
                            </a>
                        </div>

                    </div>

                    <div class="col-md-6 mb-4">
                        <c:url var="filter" value="/admin/medicines/">
                            <c:param name="kw" value="${kw}" />
                        </c:url>
                        <form id="form-filter" action="${filter}">
                            <input name="page" id="page" hidden/>
                            <div class="row justify-content-end mt-2">
                                <div class="col-md-6 col-sm-12">
                                    <div class="form-group">
                                        <input class="form-control" type="text" 
                                               placeholder="Nhap ten thuoc..." 
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

                    <div class="table-responsive">

                        <table class="table text-nowrap">
                            <thead>
                                <tr>
                                    <th class="border-top-0">#</th>
                                    <th class="border-top-0">Ten khoan</th>
                                    <th class="border-top-0">Mô tả</th>
                                    <th class="border-top-0">Ngày tạo</th>
                                    <th class="border-top-0">Chức năng</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${bookingList.size() == 0}">

                                    <tr>
                                        <td colspan="5">
                                            <h5 class="text-center text-danger">
                                                Hiện tại người dùng chưa đặt lịch khám!! 
                                            </h5>
                                        </td>
                                    </tr>

                                </c:if>
                                <c:forEach var="b" items="${bookingList}">

                                    <tr>

                                        <td>${b.id}</td>
                                        <th class="border-top-0">${b.userExaminationId.username}</th>
                                        <td >${b.description}</td>
                                        <td class="created-date">${b.createdDate}</td>

                                        <td>

                                            <button type="button" class="btn btn-danger" 
                                                    onclick="deleteExamination('<c:url value="/api/booking/${b.id}"/>')">
                                                <i class="bi bi-trash"></i>
                                            </button>
                    
                                        </td>
                                    </tr>
                                </c:forEach> 
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>

        </div>
    </div>
</div>

<script>
    window.onload = () => {
        let dates = document.querySelectorAll(".created-date")
        for (let i = 0; i < dates.length; i++) {
            let d = dates[i];
            d.innerText = moment(d.innerText).fromNow();
        }
    }
</script>