/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
window.onload = () => {

    let dates = document.querySelectorAll(".created-date");
    for (let i = 0; i < dates.length; i++) {
        let d = dates[i];
        d.innerText = moment(d.innerText).fromNow();
    }

    let edList = document.querySelectorAll(".examinationDetailId");
    for (let e of edList) {
        onLoadPrescriptionByExaminationDetail(parseInt(e.innerText));
    }
    onLoadAllPrescription();

};

let prescriptionByExaminationId = [];
const onLoadPrescriptionByExaminationDetail = (examinationDetailId) => {
    fetch(`/OUPharmacy/api/booking/${examinationDetailId}/prescription/`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).then(res => res.json()).then(data => {
        if (data.length !== 0) {
            data.forEach(d => {
                let temp = [];
                temp.push({
                    "id": d.id,
                    "createdDate": d.createdDate,
                    "examinationDetailId": d.examinationDetailId.id
                });
                prescriptionByExaminationId.push({
                    "examinationDetailId": d.examinationDetailId.id,
                    "prescriptionContent": temp
                });
                let btnArea = document.querySelector(`#btn_${d.examinationDetailId.id}`);
                let statusArea = document.querySelector(`#p_${d.examinationDetailId.id}`);
                statusArea.innerText = "Đã được chẩn đoán";
                statusArea.classList.add("text-success");
                btnArea.innerHTML = "";
            });

            console.log("===PRESCRIPTION===");
            console.info(prescriptionByExaminationId);
        } else {

            let statusArea = document.querySelector(`#p_${examinationDetailId}`);
            statusArea.innerText = "Chưa chẩn đoán";
            statusArea.classList.add("text-danger");

            prescriptionByExaminationId.push({
                "examinationDetailId": examinationDetailId,
                "prescriptionContent": []
            });
        }
    }).catch(err => {
        console.log(err);
    });
};

let patientId = parseInt(document.querySelector("#patientId").innerText);
let allPrescription = [];
const onLoadAllPrescription = () => {
    fetch('/OUPharmacy/api/prescriptions/', {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).then(res => res.json()).then(data => {
        console.info(data);
        data.forEach(d => {
            allPrescription.push(d);
        });
        //===allprescription====
        console.info(allPrescription);
        showData(patientId);
        
    });
};
let bookingChecked = [];
const checkPatientBooking = (patientId) => {
    allPrescription.forEach(p => {
        if (p.examinationDetailId.patientId.id === patientId) {
            bookingChecked.push(p);
            onloadPrescriptionDetail(p.id);
        }
    });
    if (bookingChecked.length === 0) {
        return false;
    }
    return true;
};
const showData = (patientId) => {
    let tbodyElement = document.querySelector(`.patient_${patientId}`);
    if (checkPatientBooking(patientId) === true) {
        console.log("====BookingChecked====")
        console.log(bookingChecked);
        bookingChecked.forEach(b => {
            tbodyElement.innerHTML =
                    `<tr>
                        <td class="">${b.id}</td>
                        <td>${b.sign}</td>
                        <td>${b.diagnosed}</td>
                        <td class="created-date">${b.createdDate}</td>
                        <td>${b.userId.username}</td>
                        <td class="status_${b.id}"></td>
                        <td>
                            <a href="/OUPharmacy/patients/${b.examinationDetailId.patientId.id}/booking/${b.examinationDetailId.examinationId.id}/prescriptions/${b.id}"/>
                                <button class="btn btn-primary btn_${b.id}"><i class="bi bi-card-list"></i>Kê toa</button>
                            </a>
                        </td>
                    </tr>` + tbodyElement.innerHTML;
        })
    } else {
        console.log("NONE");
        tbodyElement.innerHTML = `
                <tr>
                    <td colspan="7">
                        <h5 class="text-center text-danger">
                            Hiện tại bệnh nhân chưa được kê toa!!!
                        </h5>
                    </td>
                </tr>`
    }
    
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
        console.log("====prescriptionPatientList====")
        console.info(prescriptionPatientList);
        updateStatus();
    });
    
};

const updateStatus = () => {
    if (bookingChecked.length !== 0) {
        bookingChecked.forEach(b => {
            let statusArea = document.querySelector(`.status_${b.id}`);
            if(prescriptionPatientList.length !== 0){
                statusArea.innerHTML = `<td class="status_${b.id}">Ðã kê toa</td>`;
                statusArea.classList.add("text-success");
                document.querySelector(`.btn_${b.id}`).style.display = "none";
            }
            else{
                statusArea.innerHTML = `<td class="status_${b.id}">Chưa kê toa</td>`;
                statusArea.classList.add("text-danger");
            }
        });
    }
};
//    allPrescription.forEach(p=>{
//        if(p.examinationDetailId.patientId.id === patientId)
//            tbodyElement.innerHTML = `<tr>
//                                                <td class="">${p.id}</td>
//                                                <td>${p.sign}</td>
//                                                <td>${p.diagnosed}</td>
//                                                <td class="created-date">${p.createdDate}</td>
//                                                <td>${p.userId.username}</td>
//                                                <td>Chua ke toa</td>
//                                                <td>
//                                                    <a href="/OUPharmacy/patients/${p.examinationDetailId.patientId.id}/booking/${p.examinationDetailId.examinationId.id}/prescriptions/${p.id}"/>
//                                                        <button class="btn btn-primary"><i class="bi bi-card-list"></i>Kê toa</button>
//                                                    </a>
//                                                </td>
//                                            </tr>` + tbodyElement.innerHTML;   
//    });

//const statusLoad = (examinationDetailId) => {
//    prescriptionByExaminationId.forEach(s => {
//        let statusArea = document.querySelector(`#p_${examinationDetailId}`);
//        if (examinationDetailId === s.examinationDetailId &&
//                s.prescriptionContent.length === 0) {
//            statusArea.innerText = "Chưa chẩn đoán";
//        }
//        if (examinationDetailId === s.examinationDetailId &&
//                s.prescriptionContent.length !== 0) {
//            statusArea.innerText = "Đã được chẩn đoán";
//        }
//    });
//};