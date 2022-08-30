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
            <c:if test="${option == 1}">
                <div class="form-group">
                    <label for="kw">Nhap ten benh nhan</label>
                    <input id="kw" type="text" class="form-control" placeholder="Nhap tu khoa..." name="kw" />
                </div>
            </c:if>
            <c:if test="${option == 1}">
                <div class="row">
                    <div class="form-group col-6">
                        <label for="fromDate">Tu ngay</label>
                        <input id="fromDate" type="date" 
                               class="form-control" name="fromDate" />
                    </div>
                    <div class="form-group col-6">
                        <label for="toDate">Den ngay</label>
                        <input id="toDate" type="date" 
                               class="form-control" name="toDate" />
                    </div>
                </div>

            </c:if>
            <c:if test="${option == 2}">
                <div class="row">
                    <div class="form-group col-4">
                        <label for="monthRevenue">Tháng</label>
                        <select class="form-control" name="month" id="monthRevenue">
                            <option value="0"></option>
                            <c:forEach begin="1" end="12" var="i">
                                <option value="${i}">${i}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group col-4">
                        <label for="quaterRevenue">Qúy</label>
                        <select class="form-control" name="quater" id="quaterRevenue">
                            <option value="0"></option>
                            <c:forEach begin="1" end="4" var="i">
                                <option value="${i}">${i}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group col-4">
                        <label for="yearRevenue">Năm</label>

                        <input type="text" name="year" id="yearRevenue" class="form-control"
                               oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*?)\..*/g, '$1').replace(/^0[^.]/, '0');" />
                    </div>
                </div>
            </c:if>
            <div>
                <button class="btn btn-success" type="submit"><i class="far fa-chart-bar"></i>Thong ke</button>
            </div>
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
        patientChart(ctx, patientData, patientLable, "bar", ${option});
        drawFunction(patientData, patientLable);
    </c:if>
    <c:if test="${option == 2}">
        let revenueData = [], revenueLable = [], revenueYear = [];
        <c:forEach items="${revenueDateStats}" var="r">
            revenueData.push(${r[2]});
            revenueLable.push('${r[0]}/${r[1]}');
            revenueYear.push(${r[1]});
        </c:forEach>
            let ctx = document.getElementById("revenueChart").getContext("2d");
            patientChart(ctx, revenueData, revenueLable, "bar",${option});
            drawFunction(revenueData, revenueLable);
//            function sort_unique(arr) {
//                if (arr.length === 0) return arr;
//                arr = arr.sort(function (a, b) { return a*1 - b*1; });
//                var ret = [arr[0]];
//                for (var i = 1; i < arr.length; i++) {
//                  if (arr[i-1] !== arr[i]) {
//                    ret.push(arr[i]);
//                  }
//                }
//            
//                return ret;
//            }
//            let temp = sort_unique(revenueYear);
//            let selectYearArea = document.getElementById("yearRevenue");
//            for(let t of temp){
//                console.log(t)
//                selectYearArea.innerHTML = '<option value="${t}">${t}</option>' 
//                + selectYearArea.innerHTML
//            }
    </c:if>

}
</script>