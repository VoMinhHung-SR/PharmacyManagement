<%-- 
    Document   : header
    Created on : Jul 30, 2022, 11:06:15 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<nav class="navbar navbar-expand-lg bg-light fixed-top shadow-lg">
    <div class="container">
        <a class="navbar-brand mx-auto d-lg-none" href="index.html">
            OUPharmacy
            <strong class="d-block">Health Specialist</strong>
        </a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav mx-auto">

                <sec:authorize access="hasRole('ROLE_NURSE')">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/examinations"/>">Tạo phiếu khám</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/booking-list/nur-censored"/>">Duyệt đơn</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_DOCTOR')">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/r-1/patients"/>">Kê toa</a>
                    </li>
                </sec:authorize>
                    

                <a class="navbar-brand d-none d-lg-block" href="<c:url value="/" />">
                    OUPharmacy
                    <strong class="d-block">Health Specialist</strong>
                </a>
                <sec:authorize access="hasRole('ROLE_NURSE')">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/r-2/patients"/>">Thanh toán</a>
                    </li>
                </sec:authorize>
     
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.name == null}">
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value="/login"/>">Login</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value="/register" />">Registry</a>
                        </li>
                    </c:when>
                    <c:when test="${pageContext.request.userPrincipal.name != null}">
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value="/booking"/>">Đặt lịch</a>
                        </li>
                        <div class="dropdown">

                            <div class="dropbtn" onclick="myFunction()">
                                <c:if test="${currentUser.avatar != null}">
                                    <img src="${currentUser.avatar}" 
                                         class="rounded-circle img-fluid " 
                                         style="width: 30px;"
                                         alt="${pageContext.request.userPrincipal.name}" />
                                    ${pageContext.request.userPrincipal.name}
                                </c:if>
                                <c:if test="${currentUser.avatar == null}">
                                    <i class="bi bi-person-fill"></i> ${pageContext.request.userPrincipal.name}
                                </c:if>
                            </div>

                            <div id="myDropdown" class="dropdown-content">
                                <sec:authorize access="hasRole('ADMIN')">
                                    <a href="<c:url value="/admin/dashboard"/>" >
                                        <i class="bi bi-person-fill"></i>
                                        Trang quản trị
                                    </a>
                                    <hr style="padding: 0;margin: 0"></hr>
                                </sec:authorize>
                                <a 
                                   href="<c:url value="/booking-list" />">
                                    <i class="bi bi-info-circle p-0"></i> Danh sách đặt lịch
                                </a>

                                <hr style="padding: 0;margin: 0"></hr>

                                <a class="text-danger"
                                   href="<c:url value="/logout" />">
                                    <i class="bi bi-box-arrow-in-right p-0"></i> Đăng xuất
                                </a>
                            </div>
                        </div>

                    </c:when>
                </c:choose>

            </ul>
        </div>

    </div>
</nav>

<script>
    function myFunction() {
        document.getElementById("myDropdown").classList.toggle("show");
    }

// Close the dropdown menu if the user clicks outside of it
    window.onclick = function (event) {
        if (!event.target.matches('.dropbtn')) {
            var dropdowns = document.getElementsByClassName("dropdown-content");
            var i;
            for (i = 0; i < dropdowns.length; i++) {
                var openDropdown = dropdowns[i];
                if (openDropdown.classList.contains('show')) {
                    openDropdown.classList.remove('show');
                }
            }
        }
    }
</script>