<%-- 
    Document   : censored
    Created on : Aug 11, 2022, 10:05:54 PM
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
                <h3 class="box-title text-center pt-5 text-danger mb-3">
                    Xác nhận đặt lịch người dùng
                </h3>

                <div class="col-md-12 m-auto">
                    <c:url var="filter" value="/admin/medicines/">
                        <c:param name="kw" value="${kw}" />
                    </c:url>
                    <form id="form-filter" action="${filter}">
                        <input name="page" id="page" hidden/>
                        <div class="row justify-content-end mt-2">
                            <div class="col-md-2 col-sm-12">
                                <div class="form-group">
                                    <input class="form-control" type="text" 
                                           placeholder="Nhap ten tai khoan..." 
                                           name="kw"
                                           aria-label="Search">
                                </div>
                            </div>
                            <div class="col-md-2 col-sm-12">
                                <button class="form-control ml-1 btn-warning btn" type="submit">
                                    <i class=" fa fa-search" aria-hidden="true"></i> Tìm kiếm
                                </button>
                            </div>
                        </div>
                    </form>
                </div>



                <div class="table-responsive mt-3">

                    <table class="table text-nowrap">
                        <thead>
                            <tr>
                                <th class="border-top-0">#</th>
                                <th class="border-top-0">Ten tai khoan</th>
                                <th class="border-top-0">Mô tả</th>
                                <th class="border-top-0">Ngày tạo</th>
                                <th class="border-top-0">Trạng thái</th>
                                <th class="border-top-0 text-center">Chức năng</th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${bookingList.size() == 0}">
                                <tr>
                                    <td colspan="6">
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
                                    <td class="text-danger" id="e_${b.id}">Chờ xác nhận</td>
                                    <td class="text-center">
                                        <button type="button" class="btn btn-info"
                                                onclick="sendEmailTrigger(${b.userExaminationId.id},
                                                            ${b.id})">
                                            <i class="bi bi-envelope-plus-fill"></i> Gửi email
                                        </button>
                                        <a href="<c:url value="/booking/${b.id}/examination-detail"/>">
                                            <button type="button" class="btn btn-primary" >
                                                <i class="bi bi-clipboard-plus"></i> Tạo phiếu khám
                                            </button>
                                        </a>
                                    </td>

                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>

                <p class="text-center">(<span class="text-danger">Lưu ý:</span> Hệ thống chỉ nhận tối đa 40 đơn khám / 1 ngày!!)</p>
            </div>

        </div>
    </div>
</div>

<script>
    window.onload = () => {

        let dates = document.querySelectorAll(".created-date");
        for (let i = 0; i < dates.length; i++) {
            let d = dates[i];
            d.innerText = moment(d.innerText).fromNow();
        }
       onloadUserBooking();
       onloadExaminationDetail();
    };
</script>