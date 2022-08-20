/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


const addPrescription = (patientId, examinationDetailId, userId) => {
    let form = $("#addPrescription");
    let formData = {};

    form.serializeArray().forEach(item => {
        formData[item.name] = item.value;
    });
    let date = new Date();
    fetch('/OUPharmacy/api/prescription', {
        method: 'POST',
        body: JSON.stringify({
            "sign": formData.sign,
            "diagnosed": formData.diagnosed,
            "createdDate": date,
            "active": 1,
            "examinationDetailId": examinationDetailId,
            "userId": {
                "id":userId,
                "username":userName
            }
        }),
        headers: {
            "Content-Type": "application/json"
        }
    }).then(res => res.json()).then(data => {
        console.info(data);
        
        
        let areaPrescription = document.getElementById("areaPrescription");
        
        document.getElementById("sign").value = '';
        document.getElementById("diagnosed").value = '';
         
        areaPrescription.innerHTML = `
                        <div class="m-5">
                            <p class="text-center text-danger">Thông tin bệnh án mới</p>
                            <table class="table text-nowrap">
                                <thead>
                                    <tr>
                                        <th class="border-top-0">#</th>
                                        <th class="border-top-0">Triệu chứng</th>
                                        <th class="border-top-0">Chẩn đoán</th>
                                        <th class="border-top-0">Ngày chẩn đoán</th>
                                        <th class="border-top-0">Bác sĩ chẩn đoán</th>
                                        <th class="border-top-0">Chức năng</th>
                                    </tr>
                                </thead>

                                <tbody>
                                        <tr>
                                            <td>${data.id}</td>
                                            <td>${data.sign}</td>
                                            <td>${data.diagnosed}</td>
                                            <td class="created-date">${moment(data.createdDate).fromNow()}</td>
                                            <td>
                                                ${data.userId.username}
                                            </td>
                                            <td>
                                                <a href="/OUPharmacy/patients/${patientId}/booking/${data.examinationDetailId.id}/prescriptions/${data.id}">
                                                    <button type="button" class="btn btn-primary">
                                                        <i class="bi bi-clipboard2-plus"></i>Kê toa
                                                    </button>
                                                </a>
                                            </td>
                                        </tr>
                                </tbody>
                            </table>
                        </div>
                            ` + areaPrescription.innerHTML

    }).catch(err => {
        console.log(err);
    });
};