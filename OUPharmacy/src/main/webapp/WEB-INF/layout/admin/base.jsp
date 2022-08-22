<%-- 
    Document   : base.jsp
    Created on : Aug 4, 2022, 4:28:44 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<tiles:importAttribute name="javascripts" />

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">

        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- Tell the browser to be responsive to screen width -->

        <meta name="viewport" content="width=device-width, initial-scale=1">

        <meta name="keywords" content="wrappixel, admin dashboard, 
              html css dashboard, web dashboard, bootstrap 5 admin, bootstrap 5, 
              css3 dashboard, bootstrap 5 dashboard, 
              Ample lite admin bootstrap 5 dashboard, frontend, responsive bootstrap 
              5 admin template, Ample admin lite dashboard bootstrap 5 dashboard template">


        <meta name="description" content="Ample Admin Lite is powerful and clean 
              admin dashboard template, inpired from Bootstrap Framework">

        <meta name="robots" content="noindex,nofollow">

        <title><tiles:insertAttribute name="title" /></title>

        <link rel="canonical" href="https://www.wrappixel.com/templates/ample-admin-lite/" />



        <!-- Favicon icon -->
        <link rel="icon" type="image/png" sizes="16x16" href="<c:url value="/admin/plugins/images/favicon.png"/>">

        <!-- Custom CSS -->
        <link href="<c:url value="/admin/plugins/bower_components/chartist/dist/chartist.min.css"/>" rel="stylesheet">
        <link rel="stylesheet" href="<c:url value="/admin/plugins/bower_components/chartist-plugin-tooltips/dist/chartist-plugin-tooltip.css"/>">
        <link rel="stylesheet" href="<c:url value="/admin/css/sweet-alert2.css"/>">
        <!-- Custom CSS -->
        <link href="<c:url value="/admin/css/style.min.css"/>" rel="stylesheet">
        <!--SweetAlert2-->
        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.js"></script>
        <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css" id="theme-styles">
        <!--Jquery-->
        <!--CDN-->
        <link rel="stylesheet" href="//cdn.jsdelivr.net/chartist.js/latest/chartist.min.css">
        <script src="//cdn.jsdelivr.net/chartist.js/latest/chartist.min.js"></script>

    </head>
    <body><!-- ============================================================== -->
        <!-- Main wrapper - style you can find in pages.scss -->
        <!-- ============================================================== -->
        <div id="main-wrapper" data-layout="vertical" data-navbarbg="skin5" data-sidebartype="full"
             data-sidebar-position="absolute" data-header-position="absolute" data-boxed-layout="full">

            <!-- ============================================================== -->
            <!-- Topbar header - style you can find in pages.scss -->
            <!-- ============================================================== -->
            <tiles:insertAttribute name="topbar" />
            <!-- ============================================================== -->
            <!-- End Topbar header -->
            <!-- ============================================================== -->

            <!-- ============================================================== -->
            <!-- Left Sidebar - style you can find in sidebar.scss  -->
            <!-- ============================================================== -->
            <tiles:insertAttribute name="left" />
            <!-- ============================================================== -->
            <!-- End Left Sidebar - style you can find in sidebar.scss  -->
            <!-- ============================================================== -->

            <!-- ============================================================== -->
            <!-- Page wrapper  -->
            <!-- ============================================================== -->
            <div class="page-wrapper">
                <!-- ============================================================== -->
                <!-- Bread crumb and right sidebar toggle -->
                <!-- ============================================================== -->
                <div class="page-breadcrumb bg-white">
                    <div class="row align-items-center">
                        <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                            <h4 class="page-title"><tiles:insertAttribute name="pageName" /></h4>
                        </div>
                        <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                            <div class="d-md-flex">
                                <ol class="breadcrumb ms-auto">
                                    <li><a href="#" class="fw-normal"><tiles:insertAttribute name="pageName" /></a></li>
                                </ol>
                            </div>
                        </div>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- ============================================================== -->
                <!-- End Bread crumb and right sidebar toggle -->
                <!-- ============================================================== -->

                <!-- ============================================================== -->
                <!-- Container fluid  -->
                <!-- ============================================================== -->
                <tiles:insertAttribute name="content" />
                <!-- ============================================================== -->
                <!-- End Container fluid  -->
                <!-- ============================================================== -->
                <!-- ============================================================== -->
                <!-- footer -->
                <!-- ============================================================== -->
                <tiles:insertAttribute name="footer" />
                <!-- ============================================================== -->
                <!-- End footer -->
                <!-- ============================================================== -->
            </div>
            <!-- ============================================================== -->
            <!-- End Page wrapper  -->
            <!-- ============================================================== -->
        </div>
        <!-- ============================================================== -->
        <!-- End Wrapper -->
        <!-- ============================================================== -->


        <!-- ============================================================== -->
        <!-- All Jquery -->
        <!-- ============================================================== -->
        <script src="<c:url value="/admin/plugins/bower_components/jquery/dist/jquery.min.js"/>"></script>
        <!-- Bootstrap tether Core JavaScript -->
        <script src="<c:url value="/admin/bootstrap/dist/js/bootstrap.bundle.min.js"/>"></script>
        <script src="<c:url value="/admin/js/app-style-switcher.js"/>"></script>

        <!--Wave Effects -->
        <script src="<c:url value="/admin/js/waves.js"/>"></script>

        <!--Menu sidebar -->
        <script src="<c:url value="/admin/js/sidebarmenu.js"/>"></script>

        <!--Custom JavaScript -->
        <script src="<c:url value="/admin/js/custom.js"/>"></script>


        <c:forEach var="js" items="${javascripts}">
            <script src="<c:url value="${js}"/>"></script>
        </c:forEach>

        <!--chartis chart-->
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <!--SweetAlert2-->
        <script src="<c:url value="/admin/js/common-js/alert.js"/>"></script>



    </body>
</html>
