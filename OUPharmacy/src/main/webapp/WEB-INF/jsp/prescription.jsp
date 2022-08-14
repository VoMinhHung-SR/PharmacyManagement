<%-- 
    Document   : prescription
    Created on : Aug 12, 2022, 10:22:39 PM
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
                <h3 class="box-title text-center pt-5 text-danger mb-3">
                    Chẩn đoán bệnh lý
                </h3>
                <div class="table-responsive mt-3">

                    <table class="table text-nowrap">
                        <thead>
                            <tr>
                                <th class="border-top-0">Họ</th>
                                <th class="border-top-0">Tên</th>
                                <th class="border-top-0">Ngày sinh</th>
                                <th class="border-top-0">SÐT</th>
                                <th class="border-top-0">Giới tính</th>
                                <th class="border-top-0 text-center">Chức năng xem</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>${patient.firstName}</td>
                                <td>${patient.lastName}</td>
                                <td class="dob">${patient.dateOfBirth}</td>
                                <td>${patient.phoneNumber}</td>
                                <td>
                                    <c:if test="${patient.gender == 0}">
                                        Nam
                                    </c:if>
                                    <c:if test="${patient.gender==1}">
                                        Nữ
                                    </c:if>
                                    <c:if test="${patient.gender==2}">
                                        Khác
                                    </c:if>

                                </td>
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

                        </tbody>
                    </table>
                </div>


            </div>

        </div>
    </div>
                                        
    <!--AFTER ADD-->  
    <div id="areaPrescription"></div>          
    <!--AFTER ADD--> 
    
    <form id="addPrescription">
        <section class="h-100">
            <div class="container py-2 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col">
                        <div class="card my-4">
                            <div class="row g-0">
                                <div class="col-xl-12">
                                    <div class="card-body p-md-5 text-black">
                                        <div class="form-outline mb-4">
                                            <label class="form-label" for="sign">Triệu chứng<span class="text-danger">(*)</span></label>
                                            <input type="text" id="sign" name="sign" class="form-control form-control-lg" />
                                        </div>

                                        <div class="form-outline mb-4">
                                            <label class="form-label" for="diagnosed">Chẩn đoán<span class="text-danger">(*)</span></label>
                                            <input type="textarea"  id="diagnosed"  name="diagnosed" class="form-control form-control-lg" />
                                        </div>
              
                                        <div style="text-align: end" >
                                            <input style="margin-left: auto" 
                                                   class="btn btn-success btn-lg ms-2"
                                                    type="button"
                                                    onclick="addPrescription(${patient.id},${currentUser.id})" 

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

</div>

<script>
    window.onload = () => {

        let dates = document.querySelectorAll(".dob")
        for (let i = 0; i < dates.length; i++) {
            let d = dates[i];
            d.innerText = moment(d.innerText).format('DD/MM/YYYY');
        }
    }
</script>
