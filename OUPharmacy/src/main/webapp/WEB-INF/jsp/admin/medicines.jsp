<%-- 
    Document   : medicines
    Created on : Aug 4, 2022, 9:12:51 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${medicines.size() == 0}">
    <p><em>KHONG CO SAN PHAM NAO!!!</em></p>
</c:if>
<div class="container-fluid">
    <!-- ============================================================== -->
    <!-- Start Page Content -->
    <!-- ============================================================== -->
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
                                <th class="border-top-0">Anh Sp</th>
                                <th class="border-top-0">Ten Sp</th>
                                <th class="border-top-0">Ton Kho</th>
                                <th class="border-top-0">Gia Sp <small>(VNÐ)</small></th>
                                <th class="border-top-0">Loai Sp</th>
                                <th class="border-top-0">Chuc Nang</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="m" items="${medicines}">
                                <tr>
                                    <td>${m.id}</td>
                                    <td  class="col-1">
                                        <img src="${m.image}" witdh="50" height="50"/>
                                    </td>
                                    <td class="col-3">${m.medicineId.name}</td>
                                    <td>${m.inStock}</td>
                                    <td>
                                        <fmt:formatNumber value="${m.price}" 
                                                          maxFractionDigits="3" 
                                                          type="number"/>
                                    </td>
                                    <td>${m.categoryId.name}</td>
                                    <td>
                                        <button type="button" class="btn btn-success"><i class="fas fa-pencil-alt"></i></button>
                                        <button type="button" class="btn btn-danger" 
                                                onclick="deleteMedicineUnit('<c:url value="/api/medicines/${m.id}"/>')">
                                            <i class="far fa-trash-alt"></i>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach> 
                        </tbody>
                    </table>
                </div>
                <nav aria-label="Page navigation example">
                    <ul class="pagination" style="justify-content: center">
                        <c:forEach begin="1" end="${Math.ceil(medicinesCouter/6)}" var="i">
                            <c:url value="/admin/medicines/" var="m">
                                <c:param value="${i}" name="page"  />
                            </c:url>
                            <li class="page-item"><a class="page-link" href="${m}">${i}</a></li>
                        </c:forEach>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
    <!-- ============================================================== -->
    <!-- End PAge Content -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- Right sidebar -->
    <!-- ============================================================== -->
    <!-- .right-sidebar -->
    <!-- ============================================================== -->
    <!-- End Right sidebar -->
    <!-- ============================================================== -->
</div>
