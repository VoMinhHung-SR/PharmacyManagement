<%-- 
    Document   : header
    Created on : Jul 30, 2022, 11:06:15 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <li class="nav-item active">
                    <a class="nav-link" href="#hero">Home</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="#about">About</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="#timeline">Timeline</a>
                </li>

                <div class="btn-group">
                    <button type="button" class="btn dropdown-toggle" data-toggle="dropdown">
                        Sony
                    </button>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#">Tablet</a>
                        <a class="dropdown-item" href="#">Smartphone</a>
                    </div>
                </div>
                
                <c:forEach var="c" items="${categories}">
                    <li class="nav-item">
                        <c:url value="/" var="catePath">
                            <c:param name="cateId" value="${c.id}"/>
                        </c:url>
                        <a class="nav-link" href="${catePath}">${c.name}</a>
                    </li>
                </c:forEach>

                <li class="nav-item">
                    <a class="nav-link" href="#timeline">Thuốc</a>
                </li>

                <a class="navbar-brand d-none d-lg-block" href="<c:url value="/" />">
                    OUPharmacy
                    <strong class="d-block">Health Specialist</strong>
                </a>

                <li class="nav-item">
                    <a class="nav-link" href="#reviews">Testimonials</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="#booking">Đặt lịch</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="#contact">Contact</a>
                </li>
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
                            <a class="nav-link text-success" href="#">${pageContext.request.userPrincipal.name}</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-danger" href="<c:url value="/logout" />">Logout</a>
                        </li>
                    </c:when>
                </c:choose>

            </ul>
        </div>

    </div>
</nav>