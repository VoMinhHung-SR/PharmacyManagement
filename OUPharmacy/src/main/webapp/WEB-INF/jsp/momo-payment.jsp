<%-- 
    Document   : momo-payment
    Created on : Sep 8, 2022, 9:12:13 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${errMgs != null}">
    <h1 class="text-center text-danger">${errMgs}</h1>
</c:if>
<c:if test="${message != null}">
    <h1 class="text-center text-success">${message}</h1>
</c:if>