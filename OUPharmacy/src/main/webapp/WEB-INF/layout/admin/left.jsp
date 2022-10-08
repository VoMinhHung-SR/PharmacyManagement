<%-- 
    Document   : left
    Created on : Aug 4, 2022, 4:29:09 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<aside class="left-sidebar" data-sidebarbg="skin6">
    <!-- Sidebar scroll-->
    <div class="scroll-sidebar">
        <!-- Sidebar navigation-->
        <nav class="sidebar-nav">
            <ul id="sidebarnav">
                <!-- User Profile-->
                <li class="sidebar-item pt-2">
                    <a class="sidebar-link waves-effect waves-dark sidebar-link" href="<c:url value="/admin/dashboard"/>"
                       aria-expanded="false">
                        <i class="far fa-clock" aria-hidden="true"></i>
                        <span class="hide-menu">Thong Ke</span>
                    </a>
                </li>
                <li class="sidebar-item pt-2">
                    <a class="sidebar-link waves-effect waves-dark sidebar-link" 
                       href="<c:url value="/admin/patient-stats"/>"
                       aria-expanded="false">
                        <i class="far fa-clock" aria-hidden="true"></i>
                        <span class="hide-menu">Thong ke ben nhan</span>
                    </a>
                </li>
                <li class="sidebar-item pt-2">
                    <a class="sidebar-link waves-effect waves-dark sidebar-link" 
                       href="<c:url value="/admin/revenue-stats"/>"
                       aria-expanded="false">
                        <i class="far fa-clock" aria-hidden="true"></i>
                        <span class="hide-menu">Thong ke doanh thu</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a class="sidebar-link waves-effect waves-dark sidebar-link" 
                       href="<c:url value="/admin/add-schedule" />"
                       aria-expanded="false">
                        <i class="fas fa-calendar-plus" aria-hidden="true"></i>
                        <span class="hide-menu">Tao lich truc</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a class="sidebar-link waves-effect waves-dark sidebar-link" 
                       href="<c:url value="/admin/categories" />"
                       aria-expanded="false">
                        <i class="fas fa-list-ul" aria-hidden="true"></i>
                        <span class="hide-menu">Quan ly danh muc</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a class="sidebar-link waves-effect waves-dark sidebar-link" 
                       href="<c:url value="/admin/medicines" />"
                       aria-expanded="false">
                        <i class="fa fa-capsules" aria-hidden="true"></i>
                        <span class="hide-menu">Quan ly thuoc</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a class="sidebar-link waves-effect waves-dark sidebar-link" 
                       href="<c:url value="/admin/users/role-1" />"
                       aria-expanded="false">
                        <i class="fas fa-users" aria-hidden="true"></i>
                        <span class="hide-menu">Quan ly bac si</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a class="sidebar-link waves-effect waves-dark sidebar-link" 
                       href="<c:url value="/admin/users/role-2" />"
                       aria-expanded="false">
                        <i class="fas fa-users" aria-hidden="true"></i>
                        <span class="hide-menu">Quan ly y ta</span>
                    </a>
                </li>
                <c:if test="${currentUser.isSuperuser == 1}">
                    <li class="sidebar-item">
                        <a class="sidebar-link waves-effect waves-dark sidebar-link" 
                           href="<c:url value="/admin/edit-user-role" />"
                           aria-expanded="false">
                            <i class="fa fa-user" aria-hidden="true"></i>
                            <span class="hide-menu">Thiết lập quyền quản trị</span>
                        </a>
                    </li>
                </c:if>
                <li class="sidebar-item">
                    <a class="sidebar-link waves-effect waves-dark sidebar-link" 
                       href="<c:url value="/admin/chats" />"
                       aria-expanded="false">
                        <i class="fas fa-users" aria-hidden="true"></i>
                        <span class="hide-menu">Tro chuyen truc tuyen</span>
                    </a>
                </li>
                <li class="sidebar-item">
                    <a class="sidebar-link waves-effect waves-dark sidebar-link" 
                       href="<c:url value="/" />"
                       aria-expanded="false">
                        <i class="fas fa-home text-danger fw-bold" aria-hidden="true"></i>
                        <span class="hide-menu text-danger fw-bold">Tro ve trang chu</span>
                    </a>
                </li>
            </ul>

        </nav>
        <!-- End Sidebar navigation -->
    </div>
    <!-- End Sidebar scroll-->
</aside>
