/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */



let total = 0;
const payFunction = (id) => {
    let pay = document.querySelector(`.pay_${id}`);
    let w = document.querySelector(`.wage_${id}`);
    total = parseInt(w.innerText);
    prescriptionPatientList.forEach(p => {
        if (p.prescriptionId === id) {
            if (p.prescriptionDetail.length === 0) {
                pay.innerText = w.innerText;
            } else {
                total += p.prescriptionDetail[0].quantity * p.prescriptionDetail[0].medicineUnitId.price
            }
        }
    });
    pay.innerText = total.toLocaleString('it-IT', {style: 'currency', currency: 'VND'});
}

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
                "prescriptionDetail": []
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
                        "name": d.medicineUnitId.medicineId.name,
                        "effect": d.medicineUnitId.medicineId.effect,
                    }
                },
                "prescriptionId": d.prescriptionId.id
            });
            prescriptionPatientList.push({
                "prescriptionId": d.prescriptionId.id,
                "prescriptionDetail": temp,
            });
        });
        console.info(prescriptionPatientList);
    });
};

//Load all  prescriptionDetail
let prescriptionDetailsList = [];
$(document).ready(function () {
    // load list prescriptionDetail
    fetch('/OUPharmacy/api/prescription-detail/', {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).then(res => res.json()).then(data => {
        console.info(data);
        data.forEach(d => {

            prescriptionDetailsList.push({
                "id": d.id,
                "quantity": d.quantity,
                "uses": d.uses,
                "medicineUnitId": {
                    "id": d.medicineUnitId.id,
                    "price": d.medicineUnitId.price,
                    "medicineId": {
                        "name": d.medicineUnitId.medicineId.name,
                        "effect": d.medicineUnitId.medicineId.effect,
                    }
                },
                "prescriptionId": d.prescriptionId.id
            });
        });

        console.info(prescriptionDetailsList);
    });
    let bookingId = document.querySelectorAll(".booking");
    for (var item of bookingId) {
        onloadPrescriptionDetail(parseInt(item.innerText));
    }
});
let isLoading = false;
const showData = (id) => {
    prescriptionPatientList.forEach(p => {
        if (p.prescriptionId === id) {
            let a = document.querySelector(`.p_${id}`);
            if (p.prescriptionDetail.length === 0) {
                a.innerHTML = `
                <tr>
                    <td colspan="5" class="text-center">
                        Hiện tại bệnh nhân chưa có kê toa thuốc...
                    </td>
                </tr>`;
            } else {
                a.innerHTML = `
                    <tr>
                        <td>
                            ${p.prescriptionDetail[0].medicineUnitId.medicineId.name}
                        </td>
                        <td>
                            ${p.prescriptionDetail[0].quantity}
                        </td>
                        <td>
                            ${p.prescriptionDetail[0].uses}
                        </td>
                        <td>
                            ${p.prescriptionDetail[0].medicineUnitId.price}

                        </td>
                        <td class="total">
                           ${p.prescriptionDetail[0].quantity
                        * p.prescriptionDetail[0].medicineUnitId.price}
                        </td>
                    </tr>` + a.innerHTML;

            }
        }
    });


};

window.onload = () => {

    let dates = document.querySelectorAll(".created-date");
    for (let i = 0; i < dates.length; i++) {
        let d = dates[i];
        d.innerText = moment(d.innerText).fromNow();
    }

    let bookingId = document.querySelectorAll(".booking");

    setTimeout(() => {
        for (var item of bookingId) {
            showData(parseInt(item.innerText));
            payFunction(parseInt(item.innerText));
        }
    }, 1000);




};

