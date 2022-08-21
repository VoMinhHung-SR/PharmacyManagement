<%-- 
    Document   : examination
    Created on : Aug 20, 2022, 9:56:58 PM
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
                <h3 class="box-title text-center pt-5 text-danger">Tạo phiếu khám</h3>
            </div>

        </div>
    </div>
    <form id="addExaminationDetail">
        <section class="h-100">
            <div class="container py-2 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col">
                        <div class="card my-4">
                            <div class="row g-0">
                                <div class="col-xl-12">
                                    <div class="card-body p-md-5 text-black">
                                        <div class="text-danger text-center fw-bold" id="dateCounter"></div>
                                        <h5 class="mb-4 text-uppercase ">
                                            <div>Mã đăng kí 
                                                <span id="${examination.id}" class="text-danger">${examination.id}</span> 
                                            </div>
                                        </h5>

                                        <div class="col-md-12 mb-4">
                                            <div class="form-outline">
                                                <label class="form-label" for="wage">Phí dịch vụ/(VND) (<span class="text-danger">*</span>)</label>
                                                <input type="number" name="wage" id="wage"  class="form-control form-control-lg"/>
                                            </div>
                                        </div>
                                                <h5 class="mb-5 text-uppercase">Thông tin bệnh nhân (<span class="text-danger">*</span>)</h5>
                                        <div class="row">
                                            <div class="col-md-6 mb-4">
                                                <div class="form-outline">
                                                    <label class="form-label" for="firstName">Họ</label>
                                                    <input type="text" name="firstName" id="firstName" class="form-control form-control-lg" />
                                                </div>
                                            </div>
                                            <div class="col-md-6 mb-4">
                                                <div class="form-outline">
                                                    <label class="form-label" for="lastName">Tên</label>
                                                    <input type="text" name="lastName" id="lastName"  class="form-control form-control-lg" />
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-md-5 mb-4">
                                                <div class="form-outline">
                                                    <label class="form-label" for="dateOfBirth">Ngày sinh</label>
                                                    <input type="date" name="dateOfBirth" id="dateOfBirth" class="form-control form-control-lg" />
                                                </div>
                                            </div>
                                            <div class="col-md-5 mb-4">
                                                <div class="form-outline">
                                                    <label class="form-label" for="phoneNumber">Số điện thoại</label>
                                                    <input type="text" name="phoneNumber" id="phoneNumber" class="form-control form-control-lg" />
                                                </div>
                                            </div>
                                            <div class="col-md-2 mb-4">
                                                <label class="form-label" for="gender">Giới tính</label>
                                                <select id="gender" name="gender" class="form-control">
                                                    <option value="0">Nam</option>
                                                    <option value="1">Nữ</option>
                                                    <option value="2">Khác</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-outline mb-4">
                                            <label class="form-label" for="address">Địa chỉ</label>
                                            <input type="text" name="address" id="address" class="form-control form-control-lg" />
                                        </div>
                                        <div style="text-align: end" >
                                            <input style="margin-left: auto" 
                                                   class="btn btn-success btn-lg ms-2"
                                                   type="button"
                                                   onclick="triggerAddExaminationDetail(${examination.id})"
                                                   id ="addButton"
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

        let dates = document.querySelectorAll(".created-date")
        for (let i = 0; i < dates.length; i++) {
            let d = dates[i];
            d.innerText = moment(d.innerText).fromNow();
        }
    }
</script>