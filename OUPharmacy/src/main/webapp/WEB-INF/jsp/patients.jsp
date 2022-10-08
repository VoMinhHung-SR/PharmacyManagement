<%-- 
    Document   : patients
    Created on : Aug 12, 2022, 8:23:22 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                    Danh sách bệnh nhân
                </h3>

                <div class="col-md-12 m-auto">
                    <c:url var="filter" value="/r-${option}/patients/">
                        <c:param name="kw" value="${kw}" />
                    </c:url>
                    <form id="form-filter" action="${filter}">
                        <input name="page" id="page" hidden/>
                        <div class="row justify-content-end mt-2">
                            <div class="col-md-2 col-sm-12">
                                <div class="form-group">
                                    <input class="form-control" type="text" 
                                           placeholder="Nhap tu khoa..." 
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
                                <th class="border-top-0">Họ</th>
                                <th class="border-top-0">Tên</th>
                                <th class="border-top-0">Ngày sinh</th>
                                <th class="border-top-0">SÐT</th>
                                <th class="border-top-0">Giới tính</th>
                                <th class="border-top-0">Chức năng</th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${patients.size() == 0}">
                                <tr>
                                    <td colspan="7">
                                        <h5 class="text-center text-danger">
                                            Hiện tại chưa có bệnh nhân đặt lịch khám!! 
                                        </h5>
                                    </td>
                                </tr>

                            </c:if>
                            <c:forEach var="p" items="${patients}">
                                <tr>

                                    <td>${p.id}</td>
                                    <td>${p.firstName}</td>
                                    <td>${p.lastName}</td>
                                    <td class="dob">
                                        <fmt:formatDate pattern = "dd-MM-yyyy" 
                                                        value = "${p.dateOfBirth}" />
                                    </td>
                                    <td>${p.phoneNumber}</td>
                                    <td>
                                        <c:if test="${p.gender == 0}">
                                            Nam
                                        </c:if>
                                        <c:if test="${p.gender==1}">
                                            Nữ
                                        </c:if>
                                        <c:if test="${p.gender==2}">
                                            Khác
                                        </c:if>

                                    </td>
                                    <td>
                                        <c:if test="${option == 1}">
                                            <a href="<c:url value="/patients/${p.id}/booking-list/"/>">
                                                <button type="button" class="btn btn-primary">

                                                    <i class="bi bi-clipboard2-plus"></i> Phiếu khám
                                                </button>
                                            </a>
                                        </c:if>
                                        <c:if test="${option == 2}">
                                            <a href="<c:url value="/patients/${p.id}/bill/"/>">
                                                <button type="button" class="btn btn-primary">
                                                    <i class="bi bi-clipboard2-plus">Thanh toán</i> 
                                                </button>
                                            </a>
                                        </c:if>
                                    </td>

                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>

                <nav aria-label="Page navigation example">
                    <ul class="pagination" style="justify-content: center">
                        <c:forEach begin="1" end="${Math.ceil(patientCounter/6)}" var="i">
                            <c:url value="/r-${option}/patients/" var="m">
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

<script>
    window.onload = () => {

//        let dates = document.querySelectorAll(".dob")
//        for (let i = 0; i < dates.length; i++) {
//            let d = dates[i];
//            d.innerText = moment(d.innerText).format('DD/MM/YYYY');
//        }
    }
</script>