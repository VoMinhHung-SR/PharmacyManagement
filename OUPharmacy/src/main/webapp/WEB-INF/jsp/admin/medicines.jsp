<%-- 
    Document   : medicines
    Created on : Aug 4, 2022, 9:12:51 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div id="loading" class="text-center" style="position: fixed;width: 100%;
     height: 100%;z-index: 999;
     background-color: rgba(0,0,0,0.3);">
    <div class="spinner-border" style="position: absolute;top: 40%;left: 45%" role="status">
        <span class="sr-only"></span>
    </div>
</div>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12">
            <div class="white-box">
                <h3 class="box-title">Basic Table</h3>
                <p class="text-muted">medicine_unit<code>.table</code></p>
                <div class="row">
                    <div class="col-md-6 mb-4">
                        <div class="btn btn-success mb-2" >
                            <a href="<c:url value="/admin/medicines/add-medicine"/>"
                               style="width: 10%; padding:10px 10x 5px 10px; color: black">
                                <i class="fas fa-plus"></i> Them moi san pham
                            </a>
                        </div>

                    </div>

                    <div class="col-md-6 mb-4">
                        <c:url var="filter" value="/admin/medicines/">
                            <c:param name="kw" value="${kw}" />
                        </c:url>
                        <form id="form-filter" action="${filter}">
                            <input name="page" id="page" hidden/>
                            <div class="row justify-content-end mt-2">
                                <div class="col-md-6 col-sm-12">
                                    <div class="form-group">
                                        <input class="form-control" type="text" 
                                               placeholder="Nhap ten thuoc..." 
                                               name="kw"
                                               aria-label="Search">
                                    </div>
                                </div>
                                <div class="col-md-3 col-sm-12">
                                    <button class="form-control ml-1 btn-primary btn" type="submit">
                                        <i class="fa fa-search " aria-hidden="true"></i> Tìm kiếm
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
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
                            <c:if test="${medicines.size() == 0}">
                            <h1 class="text-center text-danger">
                                KHONG CO SAN PHAM PHU HOP!!!
                            </h1>
                        </c:if>
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
                                    <button type="button" class="btn btn-success" 
                                            data-bs-toggle="modal"
                                            data-bs-target="#update-medicine-modal"
                                            onclick="showUpdateModal(${m.id})">
                                        <i class="fas fa-pencil-alt"></i>
                                    </button>
                                    <button type="button" class="btn btn-danger" 
                                            onclick="deleteMedicineUnit('<c:url value="/api/medicines/medicine-unit/${m.id}"/>')">
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
                                <c:param value="${i}" name="page" />
                            </c:url>
                            <li class="page-item"><a class="page-link" href="${m}">${i}</a></li>
                            </c:forEach>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>


<div id="update-medicine-modal" class="modal fade" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog" style="max-width: 60% !important">
        <div class="modal-content">
            <div class="modal-body">
                <form id="form-medicine-unit" class="ps-3 pe-3 text-start" action="#">
                    <div class="text-center mt-2 mb-4">  
                        <h3 class="text-danger">Cập nhật thông tin thuốc</h3>
                    </div>
                    <section class="h-100 bg-info">
                        <div class="container h-100">
                            <div class="row d-flex justify-content-center align-items-center h-100">
                                <div class="col">
                                    <div class="card card-registration my-4">
                                        <div class="row g-0">
                                            <div class="col-xl-12">
                                                <div class="card-body p-md-5 text-black">   
                                                    <div class="row">
                                                        <div class="form-outline mb-4">
                                                            <label class="form-label" for="medicine">San pham</label>
                                                            <select id="medicine" name="medicineId" class="form-control">
                                                                <c:forEach items="${medicines}" var="m">
                                                                    <option value="${m.medicineId.id}">${m.medicineId.name}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>

                                                    </div>

                                                    <div class="row">
                                                        <div class="col-md-3 mb-4">
                                                            <div class="form-outline">
                                                                <label class="form-label" for="inStock">So luong</label>
                                                                <input type="text" id="inStock" name="inStock" class="form-control form-control-lg" />
                                                            </div>
                                                        </div>

                                                        <div class="col-md-3 mb-4">
                                                            <div class="form-outline">
                                                                <label class="form-label" for="price">Gia <small>(VNÐ)</small></label>
                                                                <input type="text" name="price" id="price" class="form-control form-control-lg" />
                                                            </div>
                                                        </div>

                                                        <div class="col-md-6 mb-4">
                                                            <div class="form-outline">
                                                                <label class="form-label" for="cate">Danh muc</label>
                                                                <select id="cate" name="categoryId" class="form-control">
                                                                    <c:forEach items="${categories}" var="c">
                                                                        <option value="${c.id}">${c.name}</option>
                                                                    </c:forEach>
                                                                </select> 
                                                            </div>
                                                        </div>
                                                        <div class="form-outline mb-12">
                                                            <label class="form-label" for="file">Anh san pham</label>
                                                            <input type="file" id="file" name="image"class="form-control form-control-lg" />
                                                        </div>
                                                        <div class="d-flex justify-content-end pt-3">
                                                            <button type="button" id="update-button" class="btn btn-success btn-lg ms-2">Cap nhat</button>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                    </section>
                </form>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

</div>