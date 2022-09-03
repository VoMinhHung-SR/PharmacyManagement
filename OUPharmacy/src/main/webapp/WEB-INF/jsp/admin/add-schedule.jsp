<%-- 
    Document   : add-schedule
    Created on : Sep 3, 2022, 8:20:36 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div class="container-fluid">
    <div id="loading" class="text-center" style="position: fixed;width: 100%;
         height: 100%;z-index: 999;
         background-color: rgba(0,0,0,0.3);">
        <div class="spinner-border" style="position: absolute;top: 40%;left: 45%" role="status">
            <span class="sr-only"></span>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="white-box">   
                <c:if test="${errMgs != null}">
                    <h1 class="text-center text-danger">${errMgs}</h1>
                </c:if>
                <c:if test="${todaySchedule == null}">
                    <h3 class="text-center">Hom nay chua co lich truc cua nhan vien</h3>
                    <div class="d-flex" style="justify-content: right">
                        <button class="btn-primary" onclick="triggerAddScheduleArea()">
                            <i class="fas fa-plus"></i> 
                            Them lich truc hom nay
                        </button>
                    </div>
                </c:if>
                <c:if test="${todaySchedule != null}">
                    <h3 class="text-center">Hom nay da co lich truc cua nhan vien</h3>
                    <div class="d-flex" style="justify-content: right" >
                        <button class="btn-primary" >
                            <i class="fas fa-plus"></i>
                            <!-- comment -->Them lich truc ngay khac
                        </button>
                    </div>
                </c:if>
            </div>

            <div id="main">   

            </div>
        </div>
    </div>
</div>

