<%-- 
    Document   : medical-records
    Created on : Aug 13, 2022, 1:06:14 AM
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
                <c:if test="${option == 1}">
                    <h3 class="box-title text-center pt-5 text-danger">Danh sách tiền án bệnh</h3>
                </c:if>
                <c:if test="${option == 2}">
                    <h3 class="box-title text-center pt-5 text-danger">Danh sách lịch sử khám</h3>
                </c:if>
                <p class="fw-bold">Họ tên bệnh nhân: <span class="text-danger fw-bold">${patient.firstName} ${patient.lastName}</span></p>
                <div class="row">

                    <div class="table-responsive mt-5">

                        

                        <c:if test="${option == 2}">
                            <table class="table text-nowrap mt-3">
                                <thead>
                                    <tr>
                                        <th class="border-top-0">#</th>
                                        <th class="border-top-0">Mô tả</th>
                                        <th class="border-top-0">Ngày tạo</th>
                                        <th class="border-top-0">Tài khoản đăng kí</th>
                                        <th class="border-top-0">Trạng thái</th>
                                        <th class="border-top-0">Chức năng</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <c:if test="${patientExaminationDeatails.size() == 0}">
                                        <tr>
                                            <td colspan="5">
                                                <h5 class="text-center text-danger">
                                                    Hiện tại bệnh nhân chưa có lịch khám!!!
                                                </h5>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:forEach var="p" items="${patientExaminationDeatails}">

                                        <tr>
                                            <td>${p.examinationId.id}</td>
                                            <td>${p.examinationId.description}</td>
                                            <td class="created-date">${p.examinationId.createdDate}</td>
                                            <td >${p.examinationId.userExaminationId.username}</td>   
                                            <td class="text-danger">Chưa kê toa</td>
                                            <td>
                                                <a href="<c:url value="/patients/${p.patientId.id}/booking/${p.examinationId.id}/add-prescription/"/>">
                                                    <button class="btn btn-primary"><i class="bi bi-card-list"></i>Chẩn đoán</button>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach> 
                                </tbody>
                            </table>
                        </c:if>
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