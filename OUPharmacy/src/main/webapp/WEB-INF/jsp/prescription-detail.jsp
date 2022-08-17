<%-- 
    Document   : prescription-detail
    Created on : Aug 13, 2022, 11:19:29 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${errMgs != null}">
    <h1 class="text-center text-danger">${errMgs}</h1>
</c:if>

<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <div class="white-box">
                <h3 class="box-title text-center pt-5 text-danger">Thông tin bệnh án</h3>
                <c:if test="${prescription != null && prescription.examinationDetailId.patientId.id == patient.id}">
                    <p class="fw-bold">Họ tên bệnh nhân: 
                        <span class="text-danger fw-bold">
                            ${prescription.examinationDetailId.patientId.firstName} ${prescription.examinationDetailId.patientId.lastName}
                        </span>
                    </p>
                </c:if>
                <div class="row">
                    <div class="table-responsive mt-5">
                        <table class="table text-nowrap">
                            <thead>
                                <tr>
                                    <th class="border-top-0">Triệu chứng</th>
                                    <th class="border-top-0">Chẩn đoán</th>
                                    <th class="border-top-0">Ngày chẩn đoán</th>
                                    <th class="border-top-0">Bác sĩ chẩn đoán</th>
                                    <th class="border-top-0 text-center">Chức năng</th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:if test="${prescription == null || prescription.examinationDetailId.patientId.id != patient.id}">
                                    <tr>
                                        <td colspan="5">
                                            <h5 class="text-center text-danger">
                                                Hiện tại bệnh nhân chưa có chẩn đoán bệnh!!!
                                            </h5>
                                        </td>
                                    </tr>
                                </c:if>
                                <c:if test="${prescription != null && prescription.examinationDetailId.patientId.id == patient.id}">
                                    <tr>
                                        <td>${prescription.sign}</td>
                                        <td>${prescription.diagnosed}</td>
                                        <td class="createdDate">${prescription.createdDate}</td>
                                        <td>${prescription.userId.username}</td>
                                        <td>
                                            <div class="text-center">
                                                <a href="<c:url value="/patients/${patient.id}/medical-records/"/>" target="_blank">
                                                    <button type="button" class="btn btn-primary">
                                                        <i class="bi bi-clipboard2-plus"></i>Bệnh án
                                                    </button>
                                                </a>
                                                <a href="<c:url value="/patients/${patient.id}/booking-list/"/>" target="_blank">
                                                    <button type="button" class="btn btn-primary">
                                                        <i class="bi bi-clipboard2-plus"></i>Lịch sử khám
                                                    </button>
                                                </a>
                                            </div>
                                        </td>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table>
                    </div>   
                </div>
                <c:if test="${prescription != null && prescription.examinationDetailId.patientId.id == patient.id}">
                    <form id="addPrescription">
                        <section class="h-100">
                            <div class="container py-2 h-100">
                                <div class="row d-flex justify-content-center align-items-center h-100">
                                    <div class="col">
                                        <div class="card my-4">
                                            <div class="row g-0">
                                                <div class="col-xl-12">
                                                    <div id="m-1" class="card-body p-md-4 text-black row">
                                                        <h5 class="mb-1 fw-bold text-center mb-5">Thêm thuốc vào toa</h5>
                                                        <div class="form-outline mb-4 col-6">
                                                            <label class="form-label" for="medicine">Tên thuốc<span class="text-danger">(*)</span></label>
                                                            <input type="text" id="medicine" name="medicine" class="form-control form-control-lg" placeholder="Nhap ten thuoc..."/>
                                                            <ul class="list"></ul>
                                                        </div>
                                                        <div class="form-outline mb-4 col-2">
                                                            <label class="form-label" for="quantity">Số lượng<span class="text-danger">(*)</span></label>
                                                            <input type="number"  id="quantity"  name="quantity" class="form-control form-control-lg" />
                                                        </div>
                                                        <div class="form-outline mb-4 col-3">
                                                            <label class="form-label" for="use">Liều dùng<span class="text-danger">(*)</span></label>
                                                            <input type="text"  id="use"  name="use" class="form-control form-control-lg" />
                                                        </div>
                                                        <div class="d-flex col-1" style="align-items: center; justify-content: center;">

                                                            <input
                                                                class="btn btn-success ms-2"
                                                                type="button"
                                                                onclick ="addRowPrescriptionDetail(${prescription.id})",
                                                                value="Thêm">  
                                                            </input>

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
                    <form id="addPrescription">
                        <section class="h-100">
                            <div class="container py-2 h-100">
                                <div class="row d-flex justify-content-center align-items-center h-100">
                                    <div class="col">
                                        <div class="card my-4">
                                            <div class="row g-0">
                                                <div class="col-xl-12">
                                                    <div id="m-1" class="card-body p-md-4 text-black row">
                                                        <h5 class="mb-1 fw-bold text-center mb-5">Toa thuốc</h5>

                                                        <table class="table table-bordered">
                                                            <thead>
                                                                <tr>
                                                                    <th scope="col">#</th>
                                                                    <th scope="col" class="col-6">Tên thuốc</th>
                                                                    <th scope="col" class="col-2">Số lượng</th>
                                                                    <th scope="col">Liều dùng</th>
                                                                    <th scope="col" class="col-1"></th>
                                                                </tr>
                                                            </thead>
                                                            <tbody id="prescriptionDetail">
                                             
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                    <div style="text-align: end" id="export" class="m-3">
                                                        <input style="margin-left: auto" 
                                                               class="btn btn-success btn-lg ms-2"
                                                               type="button"
                                                               onclick="exportPrescriptionDetail()"
                                                               value="Ra toa">  
                                                        </input>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
                    </form>
                </c:if>
            </div>

        </div>
    </div>
</div>

<script>
    window.onload = () => {    
        medicinesOnLoad();    
        let dates = document.querySelectorAll(".createdDate");
        for (let i = 0; i < dates.length; i++) {
            let d = dates[i];
            d.innerText = moment(d.innerText).format('DD/MM/YYYY');
        }
        if(document.getElementById("prescriptionDetail").innerText === ""){
            document.getElementById("prescriptionDetail").innerHTML =
                        `<tr> 
                            <th scope="row" colspan="5" class="text-center">
                                Hiện tại chưa có thuốc trong toa!!!
                            </th>
                        </tr>`
            document.getElementById("export").style.display ="none";
        }
    };
</script>
