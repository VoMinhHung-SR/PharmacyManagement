<%-- 
    Document   : bill
    Created on : Aug 16, 2022, 12:47:11 AM
    Author     : ASUS
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<c:if test="${errMgs != null}">
    <h1 class="text-center text-danger">${errMgs}</h1>
</c:if>
<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <div class="white-box">
                <h3 class="box-title text-center pt-5 text-danger">Tổng tiền phải trả</h3>
                <div class="row">


                    <c:if test="${bookingList.size() == 0}">
                        <tr>
                            <td colspan="5">
                                <h5 class="text-center text-danger">
                                    Hiện tại người dùng chưa đặt lịch khám!! 
                                </h5>
                            </td>
                        </tr>

                    </c:if>
                    <c:if test="${prescriptions.size() == 0}">
                        <tr>
                            <td colspan="5">
                                <h5 class="text-center text-danger">
                                    Hiện tại người dùng chưa được kê toa!! 
                                </h5>
                            </td>
                        </tr>

                    </c:if>

                    <c:forEach items="${bookingList}" var="b">
                        <div class="table-responsive mt-4">
                            <h5>Phiếu khám số: <span class="booking">${b.id}</span>
                                - Bệnh nhân: ${b.patientId.firstName} ${b.patientId.lastName}
                                <span class="text-success" id="recepit-${b.id}"></span>
                            </h5>
                            <table class="table text-nowrap">
                                <thead>
                                    <tr>
                                        <th class="border-top-0">Toa số</th>
                                        <th class="border-top-0">Tên thuốc</th>
                                        <th class="border-top-0">Số lượng</th>
                                        <th class="border-top-0">Liều dùng</th>
                                        <th class="border-top-0">Ðơn giá </th>
                                        <th class="border-top-0">Tổng(VNÐ)</sub></th>
                                    </tr>
                                </thead>
                                <tbody class="p_${b.id}">

                                </tbody>
                                <tfoot>
                                    <tr style="text-align: right">
                                        <td colspan="6" id="wage">Phí dịch vụ:<span class="wage_${b.id}">${b.wage}</span>VNÐ</td>
                                    </tr>
                                    <tr style="text-align: right">
                                        <td colspan="6"><h5>Tổng tiền: <span class="pay_${b.id}"></span></h5></td>
                                    </tr>
                                    <tr style="text-align: right">
                                        <td colspan="6">
                                            
                                            <input type="button" class="momo-payment-${b.id} btn btn-success" value="Thanh toán MoMo" />
                                            
                                            <input type="button" class="export-bill-${b.id} btn btn-success" value="Thanh toán" />
                                        </td>
                                    </tr>
                                </tfoot>

                            </table>
                        </div>
                    </c:forEach>



                </div>

            </div>
        </div>
    </div>

