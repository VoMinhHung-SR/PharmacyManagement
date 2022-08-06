<%-- 
    Document   : add-medicine.jsp
    Created on : Aug 5, 2022, 4:54:08 PM
    Author     : ASUS
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%--<c:if test="${errMgs != null}">
    <div class="text-align text-danger">${errMsg}</div>
</c:if>--%>

<c:url var="action" value="/admin/medicines/add-medicine-unit"/>

<form:form method="post" action="${action}" 
           enctype="multipart/form-data" modelAttribute="medicineUnit">

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

                                        <label class="form-label" for="medicine">San pham</label>
                                        <form:select id="medicine" path="medicineId" class="form-control">
                                            <c:forEach items="${medicines}" var="m">
                                                <option value="${m.id}">${m.name}</option>
                                            </c:forEach>
                                        </form:select>


                                    </div>

                                    <div class="row">
                                        <div class="col-md-3 mb-4">
                                            <div class="form-outline">
                                                <label class="form-label" for="quantity">So luong</label>
                                                <form:input type="text" path="inStock" id="quantity" class="form-control form-control-lg" />
                                            </div>
                                        </div>

                                        <div class="col-md-3 mb-4">
                                            <div class="form-outline">
                                                <label class="form-label" for="price">Gia <small>(VNÐ)</small></label>
                                                <form:input type="text" path="price" id="price" class="form-control form-control-lg" />
                                            </div>
                                        </div>
                                        <div class="col-md-6 mb-4">
                                            <div class="form-outline">
                                                <label class="form-label" for="cate">Danh muc</label>
                                                <form:select id="cate" path="categoryId" class="form-control">
                                                    <c:forEach items="${categories}" var="c">
                                                        <option value="${c.id}">${c.name}</option>
                                                    </c:forEach>
                                                </form:select>
                                            </div>
                                        </div>

                                    </div>


                                    <div class="form-outline mb-4">
                                        <label class="form-label" for="file">Anh san pham</label>
                                        <form:input type="file" path="file" id="file" class="form-control form-control-lg" />
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
