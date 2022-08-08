<%-- 
    Document   : categories
    Created on : Aug 7, 2022, 12:53:56 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12">
            <div class="white-box">
                <h3 class="box-title">Basic Table</h3>
                <p class="text-muted">medicine_unit<code>.table</code></p>

                <div class="btn btn-success mb-2" >
                    <a href="<c:url value="/admin/medicines/add-medicine"/>"
                       style="width: 10%; padding:10px 10x 5px 10px; color: black">
                        <i class="fas fa-plus"></i> Them moi san pham
                    </a>
                </div>
                <div class="table-responsive">
                    <table class="table text-nowrap">
                        <thead>
                            <tr>
                                <th class="border-top-0">#</th>
                                <th class="border-top-0">Ten danh muc</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="c" items="${categories}">
                                <tr>
                                    <td>${c.id}</td>
                                    <td class="col-3">${c.name}</td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                <nav aria-label="Page navigation example">
                    <ul class="pagination" style="justify-content: center">
                        <c:forEach begin="1" end="${Math.ceil(cateCounter/6)}" var="i">
                            <c:url value="/admin/categories/" var="m">
                                <c:param value="${i}" name="page"  />
                            </c:url>
                            <li class="page-item"><a class="page-link" href="${m}">${i}</a></li>
                        </c:forEach>
                    </ul>
                </nav>
            </div>
        </div>
    </div>

</div>
