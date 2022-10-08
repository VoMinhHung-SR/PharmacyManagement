/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */



let total = 0;
const payFunction = (id) => {
    let pay = document.querySelector(`.pay_${id}`);
    let w = document.querySelector(`.wage_${id}`);
    total = parseInt(w.innerText);

    prescriptions.forEach(p => {
        if (p.examinationDetailId === id) {
            //==== BODY-CONTENT ====
            if (p.prescriptionContent.length === 0) {
                pay.innerText = w.innerText;
            } else {
                prescriptionPatientList.forEach(prescriptionDetail => {
                    if (prescriptionDetail.prescriptionDetailContent.length !== 0 &&
                            prescriptionDetail.prescriptionId === p.prescriptionContent[0].id) {
                        total += prescriptionDetail.prescriptionDetailContent[0].quantity
                                * prescriptionDetail.prescriptionDetailContent[0].medicineUnitId.price;
                    }
                });
            }
        }
    });
    pay.innerText = total.toLocaleString('it-IT', {style: 'currency', currency: 'VND'});
}

let receipt = [];
const onloadBill = (prescriptionId) => {
    fetch(`/OUPharmacy/api/prescriptions/${prescriptionId}/pay/`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).then(res => res.json()).then(data => {
        // if dont have receipt
        if (data.length === 0) {
            receipt.push({
                "prescriptionBill": prescriptionId,
                "prescriptionBillContent": []
            });
        }
        data.forEach(d => {
            let temp = [];
            temp.push({
                "id": d.id,
                "pay": d.pay,
                "prescriptionBillContent": d.prescriptionBillId.id
            });
            receipt.push({
                "prescriptionBill": d.prescriptionBillId.id,
                "prescriptionBillContent": temp
            });
            console.log("====RECEIPT====");
            console.info(receipt);
            console.log("====END-RECEIPT====");
        });
    });
};



let prescriptions = [];
const onloadPrescriptionByExaminationDetailId = (examinationDetailId) => {
    fetch(`/OUPharmacy/api/booking/${examinationDetailId}/prescription`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).then(res => res.json()).then(data => {
        if (data.length === 0) {
            prescriptions.push({
                "examinationDetailId": examinationDetailId,
                "prescriptionContent": []
            });
        }
        data.forEach(d => {
            // Load with each prescription-id
            onloadPrescriptionDetail(d.id);
            onloadBill(d.id);
            let temp = [];
            temp.push({
                "id": d.id,
                "sign": d.sign,
                "sign": d.diagnosed,
                "userId": d.userId.id,
                "examinationDetailId": d.examinationDetailId.id
            });
            prescriptions.push({
                "examinationDetailId": d.examinationDetailId.id,
                "prescriptionContent": temp
            });
        });

        console.log("====EXAMINATION-DETAIL====")
        console.info(prescriptions);
    });
};
//Load list prescriptionsDetail of patient if they have prescription
//With param = prescriptionDetailId 
const prescriptionPatientList = [];
const onloadPrescriptionDetail = (prescriptionDetailId) => {
    fetch(`/OUPharmacy/api/prescription-detail/${prescriptionDetailId}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).then(res => res.json()).then(data => {
        if (data.length === 0) {
            prescriptionPatientList.push({
                "prescriptionId": prescriptionDetailId,
                "prescriptionDetailContent": []
            });
        }
        data.forEach(d => {
            let temp = [];
            temp.push({
                "id": d.id,
                "quantity": d.quantity,
                "uses": d.uses,
                "medicineUnitId": {
                    "id": d.medicineUnitId.id,
                    "price": d.medicineUnitId.price,
                    "medicineId": {
                        "id": d.medicineUnitId.medicineId.id,
                        "name": d.medicineUnitId.medicineId.name,
                        "effect": d.medicineUnitId.medicineId.effect
                    }
                },
                "prescriptionId": {
                    "id": d.prescriptionId.id,
                    "examinationDetailId": {
                        "id": d.prescriptionId.examinationDetailId.id
                    }
                }
            });
            prescriptionPatientList.push({
                "prescriptionId": d.prescriptionId.id,
                "prescriptionDetailContent": temp
            });
        });
        console.info(prescriptionPatientList);
    });
};



$(document).ready(function () {
    let bookingId = document.querySelectorAll(".booking");
    // load prescription Examination-Detail-Id 

    for (var item of bookingId) {
        if (isLoading) {
            document.querySelector(`.p_${item.innerText}`).innerText =
                    `LOADING...`;
            document.querySelector(`.p_${item.innerText}`).classList.add("h5");
            document.querySelector(`.p_${item.innerText}`).classList.add("text-danger");
        }
        // LOAD-AREA
        onloadPrescriptionByExaminationDetailId(parseInt(item.innerText));
    }

    isLoading = false;
});

window.onload = () => {

    let dates = document.querySelectorAll(".created-date");
    for (let i = 0; i < dates.length; i++) {
        let d = dates[i];
        d.innerText = moment(d.innerText).fromNow();
    }

    let bookingId = document.querySelectorAll(".booking");

    setTimeout(() => {
        for (var item of bookingId) {

            document.querySelector(`.p_${item.innerText}`).innerText = "";
            document.querySelector(`.p_${item.innerText}`).classList.remove("h5");
            document.querySelector(`.p_${item.innerText}`).classList.remove("text-danger");
            // FUNCTION SHOW
            showData(parseInt(item.innerText));
            payFunction(parseInt(item.innerText));
        }
    }, 1000);

};

let isLoading = true;
const showData = (id) => {
    prescriptions.forEach(p => {
        if (p.examinationDetailId === id) {
            //==== BODY-CONTENT ====
            let momo = document.querySelector(`.momo-payment-${p.examinationDetailId}`);
            let bill = document.querySelector(`.export-bill-${p.examinationDetailId}`);
            let a = document.querySelector(`.p_${id}`);
            if (p.prescriptionContent.length === 0) {
                a.innerHTML = `
                <tr>
                    <td colspan="6" class="text-center">
                        Phiếu khám chưa được kê toa thuốc...
                    </td>
                </tr>`;
                momo.addEventListener("click", () => {
                    errorAlert("Đã có lỗi", "Phiếu khám phải được kê toa!!!", "Ok");
                });
                bill.addEventListener("click", () => {
                    errorAlert("Đã có lỗi", "Phiếu khám phải được kê toa!!!", "Ok");
                });
            } else {
                let skip = 0;
                prescriptionPatientList.forEach(prescriptionDetail => {
                    // If prescriptionDetailContent have content and
                    // equal with prescription.ExaminaionDetailId
                    if (prescriptionDetail.prescriptionDetailContent.length !== 0 &&
                            prescriptionDetail.prescriptionId === p.prescriptionContent[0].id) {


                        //EXPORT-BILL-AREA
                        if (skip === 0) {
                            skip++;
                            //=== add event momo and Pay button ===                        
                            momo.addEventListener("click", () => {
                                let total = parseFloat(document.querySelector(`.pay_${p.examinationDetailId}`).innerText) * 1000;
                                momoPayment(total, prescriptionDetail.prescriptionId);
                            });
                            bill.addEventListener("click", () => {
                                pay(p.examinationDetailId, prescriptionDetail.prescriptionId);
                            });
                            //=== END add event momo and Pay button === 

                            //Show export-bill-btn
                            receipt.forEach(r => {
                                let btn = document.querySelector(`.export-bill-${p.examinationDetailId}`);
                                let btn2 = document.querySelector(`.momo-payment-${p.examinationDetailId}`);
                                if (r.prescriptionBillContent.length !== 0
                                        && r.prescriptionBill === prescriptionDetail.prescriptionId) {
                                    document.querySelector(`#recepit-${p.examinationDetailId}`).innerHTML =
                                            `<i class="bi bi-check-circle"></i> Ðã thanh toán`;
                                    btn.style.display = "none";
                                    btn2.style.display = "none";
                                }
                            });
                            //EndShow export-bill-btn

                        }
                        //EXPORT-BILL-AREA

                        a.innerHTML = a.innerHTML + `
                        <tr>
                            <td>${prescriptionDetail.prescriptionId}</td>
                            <td>
                                ${prescriptionDetail.prescriptionDetailContent[0].medicineUnitId.medicineId.name}
                            </td>
                            <td>
                                ${prescriptionDetail.prescriptionDetailContent[0].quantity}
                            </td>
                            <td>
                                ${prescriptionDetail.prescriptionDetailContent[0].uses}
                            </td>
                            <td>
                                ${prescriptionDetail.prescriptionDetailContent[0].medicineUnitId.price}

                            </td>
                            <td class="total">
                               ${prescriptionDetail.prescriptionDetailContent[0].quantity
                                * prescriptionDetail.prescriptionDetailContent[0].medicineUnitId.price}
                            </td>
                        </tr>`;
                    }
                })
            }
        }
    });
};

const pay = (examinationDetailId, pId) => {
    let money = parseFloat(document.querySelector(`.pay_${examinationDetailId}`).innerText) * 1000;

    confirmAlert("Xác nhận thanh toán!", "", "Đồng ý", "Hủy", () => {
        fetch(`/OUPharmacy/api/prescriptions/${pId}/pay/`, {
            method: "POST",
            body: JSON.stringify({
                "pay": money,
                "prescriptionBillId": pId
            }),
            headers: {
                "Content-Type": "application/json"
            }
        }).then(res => {
            console.log(res);
            return res.json();
        }).then(data => {
            console.info(data);
            successfulAlert("Thanh toán thành công!!", "Ok", () => location.reload());
        }).catch(err => {
            console.log(err);
        });
    });


};


