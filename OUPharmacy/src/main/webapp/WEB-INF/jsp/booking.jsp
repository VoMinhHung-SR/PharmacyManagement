<%-- 
    Document   : booking
    Created on : Aug 10, 2022, 3:29:36 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>


<section class="section-padding pt-lg-5" id="booking">
    <div class="container">
        <div class="row">

            <div class="col-lg-8 col-12 mx-auto">
                <div class="booking-form">

                    <h2 class="text-center mb-lg-3 mb-2 text-danger">Đặt lịch khám</h2>

                    <form role="form" method="post" 
                          modelAttribute="examination">
                        <div class="row">
                            <div class="col-12 mt-5">
                                <label for="description">Mô tả khái quát tình trạng<span class="text-danger">(*)</span>:</label>
                                <textarea class="form-control form-outline border-1" rows="5" 
                                          id="description"  name="description"
                                          style="border: 1px solid #eaeaea"
                                          name="description" placeholder="Nhap mo ta..." 
                                          required="true">
                                </textarea>
                            </div>


                            <div class="col-lg-6 col-12 mt-5">
                                <label for="createdDate">Ngày khám<span class="text-danger">(*)</span>:</label>
                                <input type="date" 
                                       name="createdDate" id="createdDate" 
                                       class="form-control border-1" 
                                       style="border: 1px solid #eaeaea"
                                       required="true" />
                            </div>
                            <c:url var="endpoint" value="/api/booking" />
                            <p class="mt-5 text-center">(<span class="text-danger">Lưu ý:</span>vui lòng kiểm tra lịch khám chính thức khi nhận được email)</p>
                            <div class="col-lg-3 col-md-4 col-6 mx-auto">
                                <button type="button" class="form-control" 
                                        id="submit-button" 
                                        onclick="addExamination(${currentUser.id})">
                                    Đặt lịch
                                </button>
                            </div>
                        </div>
                    </form>

                </div>
            </div>

        </div>
    </div>
</section>


