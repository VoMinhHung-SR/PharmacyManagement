<%-- 
    Document   : add-medicine
    Created on : Aug 6, 2022, 5:00:13 PM
    Author     : ASUS
--%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<c:url var="action" value="/admin/medicines/add-medicine"/>

<form:form method="post" action="${action}" 
           enctype="multipart/form-data" modelAttribute="medicine">

    <form:errors path="*" element="div" cssClass="alert alert-danger" />

    <section class="h-100">
        <div class="container py-2 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col">
                    <div class="card card-registration my-4">
                        <div class="row g-0">
                            <div class="col-xl-12">
                                <div class="card-body p-md-5 text-black">
                                    <h3 class="mb-5 text-uppercase">Them san pham</h3>

                                    <div class="form-outline mb-4">
                                        <label class="form-label" for="name">Ten thuoc</label>
                                        <form:input type="text" path="name" id="name" class="form-control form-control-lg" />
                                        <form:errors path="name" element="div" cssClass="text-danger" />
                                    </div>

                                    <div class="form-outline mb-4">
                                        <label class="form-label" for="effect">Cong dung</label>
                                        <form:input type="textarea" path="effect" id="effect"  class="form-control form-control-lg" />
                                        <form:errors path="effect" element="div" cssClass="text-danger" />
                                    </div>

                                    <div class="d-flex justify-content-end pt-3">
                                        <button type="submit" class="btn btn-success btn-lg ms-2">Them</button>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</form:form>
