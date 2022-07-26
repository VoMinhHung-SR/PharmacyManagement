<%-- 
    Document   : dashboard
    Created on : Aug 4, 2022, 5:46:54 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<div class="container-fluid">

    <!-- ============================================================== -->
    <!-- Three charts -->
    <!-- ============================================================== -->
    <div class="row justify-content-center">
        <div class="col-lg-4 col-md-12">
            <div class="white-box analytics-info">
                <h3 class="box-title">Người dùng hệ thống</h3>
                <ul class="list-inline two-part d-flex align-items-center mb-0">
                    <li>
                        <div id="sparklinedash"><canvas width="67" height="30"
                                                        style="display: inline-block; width: 67px; height: 30px; vertical-align: top;"></canvas>
                        </div>
                    </li>
                    <li class="ms-auto"><span class="counter text-success">${countUser}</span></li>
                </ul>
            </div>
        </div>
        <div class="col-lg-4 col-md-12">
            <div class="white-box analytics-info">
                <h3 class="box-title">Tổng số lượng thuốc</h3>
                <ul class="list-inline two-part d-flex align-items-center mb-0">
                    <li>
                        <div id="sparklinedash2"><canvas width="67" height="30"
                                                         style="display: inline-block; width: 67px; height: 30px; vertical-align: top;"></canvas>
                        </div>
                    </li>
                    <li class="ms-auto"><span class="counter text-purple">${countMedicineUnit}</span></li>
                </ul>
            </div>
        </div>
        <div class="col-lg-4 col-md-12">
            <div class="white-box analytics-info">
                <h3 class="box-title">Tổng số bệnh nhân</h3>
                <ul class="list-inline two-part d-flex align-items-center mb-0">
                    <li>
                        <div id="sparklinedash3">
                            <canvas width="67" height="30"
                                    style="display: inline-block; width: 67px; height: 30px; vertical-align: top;">

                            </canvas>
                        </div>
                    </li>
                    <li class="ms-auto"><span class="counter text-info">${countPatient}</span>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <div class="white-box analytics-info">
        <h3 class="box-title">Thống kê tần suất sử dụng thuốc</h3>
        <table class="table">
            <tr>
                <th>Tên thuốc</th>
                <th>Tần suất sử dụng</th>
            </tr>
            <c:forEach items="${medicineDateStats}" var="m">
                <tr>
                    <td>${m[0]}</td>
                    <td>${m[1]}</th>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="white-box analytics-info">
        <form>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Nhap tu khoa..." name="kw" />
            </div>
            <div>
                <button class="btn btn-success" type="submit"><i class="far fa-chart-bar"></i>Thong ke</button>
            </div>

        </form>
    </div>
    <div class="row">
        <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
            <div class="white-box">
                <h3 class="box-title">Thống kê tần suất sử dụng thuốc</h3>
                <div class="w-50 m-auto">
                    <canvas id="medicineChart" width="100" height="100"></canvas>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
            <div class="white-box">
                <h3 class="box-title">Thống kê tần suất sử dụng thuốc</h3>
                <div id="ct-visits" style="height: 405px;">
                    <div class="chartist-tooltip" style="top: -17px; left: -12px;"><span
                            class="chartist-tooltip-value">6</span>
                    </div>
                </div>
            </div>
        </div>
    </div>

<!--     ============================================================== 
     RECENT SALES 
     ============================================================== 
    <div class="row">
        <div class="col-md-12 col-lg-12 col-sm-12">
            <div class="white-box">
                <div class="d-md-flex mb-3">
                    <h3 class="box-title mb-0">Recent sales</h3>
                    <div class="col-md-3 col-sm-4 col-xs-6 ms-auto">
                        <select class="form-select shadow-none row border-top">
                            <option>March 2021</option>
                            <option>April 2021</option>
                            <option>May 2021</option>
                            <option>June 2021</option>
                            <option>July 2021</option>
                        </select>
                    </div>
                </div>
                <div class="table-responsive">
                    <table class="table no-wrap">
                        <thead>
                            <tr>
                                <th class="border-top-0">#</th>
                                <th class="border-top-0">Name</th>
                                <th class="border-top-0">Status</th>
                                <th class="border-top-0">Date</th>
                                <th class="border-top-0">Price</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>1</td>
                                <td class="txt-oflo">Elite admin</td>
                                <td>SALE</td>
                                <td class="txt-oflo">April 18, 2021</td>
                                <td><span class="text-success">$24</span></td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td class="txt-oflo">Real Homes WP Theme</td>
                                <td>EXTENDED</td>
                                <td class="txt-oflo">April 19, 2021</td>
                                <td><span class="text-info">$1250</span></td>
                            </tr>
                            <tr>
                                <td>3</td>
                                <td class="txt-oflo">Ample Admin</td>
                                <td>EXTENDED</td>
                                <td class="txt-oflo">April 19, 2021</td>
                                <td><span class="text-info">$1250</span></td>
                            </tr>
                            <tr>
                                <td>4</td>
                                <td class="txt-oflo">Medical Pro WP Theme</td>
                                <td>TAX</td>
                                <td class="txt-oflo">April 20, 2021</td>
                                <td><span class="text-danger">-$24</span></td>
                            </tr>
                            <tr>
                                <td>5</td>
                                <td class="txt-oflo">Hosting press html</td>
                                <td>SALE</td>
                                <td class="txt-oflo">April 21, 2021</td>
                                <td><span class="text-success">$24</span></td>
                            </tr>
                            <tr>
                                <td>6</td>
                                <td class="txt-oflo">Digital Agency PSD</td>
                                <td>SALE</td>
                                <td class="txt-oflo">April 23, 2021</td>
                                <td><span class="text-danger">-$14</span></td>
                            </tr>
                            <tr>
                                <td>7</td>
                                <td class="txt-oflo">Helping Hands WP Theme</td>
                                <td>MEMBER</td>
                                <td class="txt-oflo">April 22, 2021</td>
                                <td><span class="text-success">$64</span></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>-->
    
</div>
<script>
    window.onload = () => {



        let medicineData = [], medicineLable = [];
    <c:forEach items="${medicineDateStats}" var="m">
        medicineData.push(${m[1]});
        medicineLable.push('${m[0]}');
    </c:forEach>
        let ctx = document.getElementById("medicineChart").getContext("2d");
        patientChart(ctx, medicineData, medicineLable, "bar");
        drawFunction(medicineData, medicineLable);

    };
</script>