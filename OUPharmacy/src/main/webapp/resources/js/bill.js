/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */



let total = 0;
const payFunction = (id) => {
    let pay = document.querySelector(`.pay_${id}`);
    let w = document.querySelector(`.wage_${id}`);
    total = parseInt(w.innerText);
//    prescriptionPatientList.forEach(p => {
//        if (p.prescriptionId === id) {
//            if (p.prescriptionDetailContent.length === 0) {
//                pay.innerText = w.innerText;
//            } else {
//                total += p.prescriptionDetailContent[0].quantity * p.prescriptionDetailContent[0].medicineUnitId.price
//            }
//        }
//    });
    
    
    prescriptions.forEach(p => {
        if (p.examinationDetailId === id) {
            //==== BODY-CONTENT ====
            if (p.prescriptionContent.length === 0) {    
                pay.innerText = w.innerText;
            } else {
                prescriptionPatientList.forEach(prescriptionDetail =>{
                    if(prescriptionDetail.prescriptionId === p.prescriptionContent[0].id){   
                        total += prescriptionDetail.prescriptionDetailContent[0].quantity 
                                * prescriptionDetail.prescriptionDetailContent[0].medicineUnitId.price;
                    }
                });
            }
        }
    });
    pay.innerText = total.toLocaleString('it-IT', {style: 'currency', currency: 'VND'});
}

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
        console.info(prescriptions);
    });
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
                        "id" : d.medicineUnitId.medicineId.id,
                        "name": d.medicineUnitId.medicineId.name,
                        "effect": d.medicineUnitId.medicineId.effect,
                    }
                },
                "prescriptionId": {
                    "id": d.prescriptionId.id,
                    "examinationDetailId":{
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
        onloadPrescriptionByExaminationDetailId(parseInt(item.innerText)); 
    }
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
            showData(parseInt(item.innerText));
            payFunction(parseInt(item.innerText));
        }
    }, 1000);




};


let isLoading = false;
const showData = (id) => {
    prescriptions.forEach(p => {
        if (p.examinationDetailId === id) {
            //==== BODY-CONTENT ====
            let a = document.querySelector(`.p_${id}`);
            if (p.prescriptionContent.length === 0) {
                a.innerHTML = `
                <tr>
                    <td colspan="5" class="text-center">
                        Phiếu khám chưa được kê toa thuốc...
                    </td>
                </tr>`;
            } else {
                prescriptionPatientList.forEach(prescriptionDetail =>{
                    if(prescriptionDetail.prescriptionId === p.prescriptionContent[0].id){
                        a.innerHTML = a.innerHTML + `
                        <tr>
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
                        </tr>` ;
                    }
                })
                

            }
        }
    });


};


