<%-- 
    Document   : base
    Created on : Jul 30, 2022, 11:06:43 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<tiles:importAttribute name="javascripts" />
<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <meta name="description" content="">
        <meta name="author" content="">

        <title><tiles:insertAttribute name="title" /></title>

        <!-- CSS FILES -->        
        <link rel="preconnect" href="https://fonts.googleapis.com">

        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

        <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;600;700&display=swap" rel="stylesheet">

        <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">

        <link href="<c:url value="/css/bootstrap-icons.css"/>" rel="stylesheet">

        <link href="<c:url value="/css/owl.carousel.min.css"/>" rel="stylesheet">

        <link href="<c:url value="/css/owl.theme.default.min.css"/>" rel="stylesheet">

        <link href="<c:url value="/css/templatemo-medic-care.css"/>" rel="stylesheet">

        <link href="<c:url value="/css/custom.css"/>" rel="stylesheet">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">

         <!--SweetAlert2-->
        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.js"></script>
        
        <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css" id="theme-styles">
    
    </head>

    <body id="top">
        <main>
            <!-- Header -->
            <tiles:insertAttribute name="header" />
            <!-- content -->
            <tiles:insertAttribute name="content" />
        </main>

        <!-- footer -->
        <tiles:insertAttribute name="footer" />


        <!-- JAVASCRIPT FILES -->
        <script src="<c:url value="/js/jquery.min.js"/>"></script>
        <script src="<c:url value="/js/owl.carousel.min.js"/>"></script>
        <script src="<c:url value="/js/scrollspy.min.js"/>"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js"></script>
       
        
        <!--SweetAlert2-->
        <script src="<c:url value="/js/alert.js"/>"></script>
        
        <c:forEach var="js" items="${javascripts}">
            <script src="<c:url value="${js}"/>"></script>
        </c:forEach>


    </body>
</html>
