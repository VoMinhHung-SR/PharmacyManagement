<%-- 
    Document   : patient-stats
    Created on : Aug 22, 2022, 12:43:26 AM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="container-fluid">

    <!-- ============================================================== -->
    <!-- Three charts -->
    <!-- ============================================================== -->
    <div class="row justify-content-center">
        <div class="col-lg-4 col-md-12">
            <div class="white-box analytics-info">
                <h3 class="box-title">Total Visit</h3>
                <ul class="list-inline two-part d-flex align-items-center mb-0">
                    <li>
                        <div id="sparklinedash"><canvas width="67" height="30"
                                                        style="display: inline-block; width: 67px; height: 30px; vertical-align: top;"></canvas>
                        </div>
                    </li>
                    <li class="ms-auto"><span class="counter text-success">659</span></li>
                </ul>
            </div>
        </div>
        <div class="col-lg-4 col-md-12">
            <div class="white-box analytics-info">
                <h3 class="box-title">Total Page Views</h3>
                <ul class="list-inline two-part d-flex align-items-center mb-0">
                    <li>
                        <div id="sparklinedash2"><canvas width="67" height="30"
                                                         style="display: inline-block; width: 67px; height: 30px; vertical-align: top;"></canvas>
                        </div>
                    </li>
                    <li class="ms-auto"><span class="counter text-purple">869</span></li>
                </ul>
            </div>
        </div>
        <div class="col-lg-4 col-md-12">
            <div class="white-box analytics-info">
                <h3 class="box-title">Unique Visitor</h3>
                <ul class="list-inline two-part d-flex align-items-center mb-0">
                    <li>
                        <div id="sparklinedash3"><canvas width="67" height="30"
                                                         style="display: inline-block; width: 67px; height: 30px; vertical-align: top;"></canvas>
                        </div>
                    </li>
                    <li class="ms-auto"><span class="counter text-info">911</span>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <c:if test="${option == 1}">
        <div class="white-box analytics-info">
            <h3 class="box-title">Thống kê bệnh nhân</h3>
            <table class="table">
                <tr>
                    <th>Mã bệnh nhân</th>
                    <th>Họ bệnh nhân</th>
                    <th>Tên bệnh nhân</th>
                    <th>Số lần khám</th>
                </tr>
                <c:if test="${patientStats == null}">
                    <h5 class="text-danger text-center">Hiện tại chưa có bênh nhân có phiếu khám!!</h5>
                </c:if>
                <c:forEach items="${patientStats}" var="p">
                    <tr>
                        <td>${p[0]}</td>
                        <td>${p[1]}</th>
                        <td>${p[2]}</td>
                        <td>${p[3]}</th>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>



    <div class="white-box analytics-info">
        <form>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Nhap tu khoa..." name="kw" />
            </div>
            <div class="form-group">
                <input type="date" class="form-control" name="fromDate" />
            </div>
            <div class="form-group">
                <input type="date" class="form-control" name="toDate" />
            </div>
            <input type="submit" value="Loc du lieu" class="btn btn-info" />
        </form>
    </div>
    <c:if test="${option == 1}">
        <div class="white-box analytics-info">
            <h3 class="box-title">Thống kê bệnh nhân theo tháng</h3>
            <table class="table">
                <tr>
                    <th>Tháng</th>
                    <th>Số lần khám</th>
                </tr>
                <c:forEach items="${patientDateStats}" var="p">
                    <tr>
                        <td>${p[0]}/${p[1]}</td>
                        <td>${p[2]}</th>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="row">
            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                <div class="white-box">
                    <h3 class="box-title">Thống kê số lượt bệnh nhân đến khám</h3>
                    <div class="w-50 m-auto">
                        <canvas id="patientChart" width="100" height="100"></canvas>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                <div class="white-box">
                    <h3 class="box-title">Thống kê số lượt bệnh nhân đến khám</h3>
                    <div class="d-md-flex">
                        <ul class="list-inline d-flex ms-auto">
                            <li class="ps-3">
                                <h5><i class="fa fa-circle me-1 text-info"></i>Mac</h5>
                            </li>
                            <li class="ps-3">
                                <h5><i class="fa fa-circle me-1 text-inverse"></i>Windows</h5>
                            </li>
                        </ul>
                    </div>
                    <div id="ct-visits" style="height: 405px;">
                        <div class="chartist-tooltip" style="top: -17px; left: -12px;"><span
                                class="chartist-tooltip-value">6</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
    <c:if test="${option == 2}">
        <div class="white-box analytics-info">
            <h3 class="box-title">Thống kê doanh thu theo tháng</h3>
            <table class="table">
                <tr>
                    <th>Tháng</th>
                    <th>Doanh thu</th>
                </tr>
                <c:forEach items="${revenueDateStats}" var="r">
                    <tr>
                        <td>${r[0]}/${r[1]}</td>
                        <td>${r[2]}</th>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="row">
            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                <div class="white-box">
                    <h3 class="box-title">Thống kê doanh thu theo tháng</h3>
                    <div class="w-50 m-auto">
                        <canvas id="revenueChart" width="100" height="100"></canvas>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
                <div class="white-box">
                    <h3 class="box-title">Thống kê doanh thu theo tháng</h3>
                    <div id="ct-visits" style="height: 405px;">
                        <div class="chartist-tooltip" style="top: -17px; left: -12px;"><span
                                class="chartist-tooltip-value">6</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:if>


</div>
<script>
    window.onload = () => {

    <c:if test="${option == 1}">
        let patientData = [], patientLable = [];
        <c:forEach items="${patientDateStats}" var="p">
        patientData.push(${p[2]});
        patientLable.push('${p[0]}/${p[1]}');
        </c:forEach>
                let ctx = document.getElementById("patientChart").getContext("2d");
                patientChart(ctx, patientData, patientLable, "bar");
                drawFunction(patientData, patientLable);
    </c:if>
    <c:if test="${option == 2}">
                let revenueData = [], revenueLable = [];
        <c:forEach items="${revenueDateStats}" var="r">
                revenueData.push(${r[2]});
                revenueLable.push('${r[0]}/${r[1]}');
        </c:forEach>
                        let ctx = document.getElementById("revenueChart").getContext("2d");
                        patientChart(ctx, revenueData, revenueLable, "bar");
                        drawFunction(revenueData, revenueLable);
    </c:if>
                    };
</script>