<%-- 
    Document   : medicines
    Created on : Aug 10, 2022, 3:03:22 PM
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
                <c:if test="${option == 2}">
                    <h3 class="box-title text-center pt-5 text-danger">Danh sách lịch sử khám</h3>
                </c:if>
                <p class="fw-bold mt-2">Họ tên bệnh nhân: <span class="text-danger fw-bold">${patient.firstName} ${patient.lastName}</span></p>
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
                                            <td colspan="6">
                                                <h5 class="text-center text-danger">
                                                    Hiện tại bệnh nhân chưa có lịch khám!!!
                                                </h5>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:forEach var="p" items="${patientExaminationDeatails}">

                                        <tr>
                                            <td class="examinationDetailId">${p.examinationId.id}</td>
                                            <td>${p.examinationId.description}</td>
                                            <td class="created-date">${p.examinationId.createdDate}</td>
                                            <td >${p.examinationId.userExaminationId.username}</td>   
                                            <td id="p_${p.examinationId.id}"></td>
                                            <td id="btn_${p.examinationId.id}">
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


