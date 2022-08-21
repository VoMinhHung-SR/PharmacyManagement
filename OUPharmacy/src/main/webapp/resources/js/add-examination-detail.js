/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

let dateCounterArea = document.getElementById("dateCounter");
let today = moment(new Date()).format('DD-MM-YYYY');
let dateCounter = 0;
const dateCounterFunction = () =>{
    examinationDetailList.forEach(e =>{
        let date = moment(e.createdDate).format('DD-MM-YYYY')
        if(date === today)
            dateCounter++;
    });
    console.log(dateCounter);
};

window.onload = () => {
    onLoadAllExaminationDetail();
    onLoadPatients();
};
let examinationDetailList = [];
const onLoadAllExaminationDetail = () => {
    fetch('/OUPharmacy/api/examination-detail', {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).then(res => res.json()).then(data =>{
        if(data.length === 0){
            examinationDetailList.push({
                "id": -1,
                "createdDate": null,
                "examinationDetailContent": []
            });
        }
        data.forEach( d => {
            let temp = [];
            temp.push(d);
            examinationDetailList.push({
                "id": d.id,
                "createdDate": d.createdDate,
                "examinationDetailContent": temp
            });  
        });  
    }).then(()=>{
        console.log("====EXAMINATION-DETAIL====");        
        console.info(examinationDetailList);
        dateCounterFunction();
        if(dateCounter === 40){
            dateCounterArea.innerHTML = `Hệ thống đã tiếp nhận đủ đơn trong một ngày.<br/> Xin lỗi bạn vì điều bất tiện này`
            document.querySelector("#addButton").style.display = "none";
        }
    }).catch(err =>{
        console.log(err);
    });

};
let patientList = []
const onLoadPatients = () =>{
    fetch('/OUPharmacy/api/patients',{
        method:"GET",
        headers:{
            "Content-Type":"application/json"
        }
    }).then(res=> res.json()).then(data =>{
        patientList = data;
        console.log("====Patients====");
        console.log(patientList);   
    }).catch(err => console.log(err));
};

let exist = false;
const triggerAddExaminationDetail = (examinationId) =>{
    let form = $("#addExaminationDetail");
    let formData = {};
    let patientId = 0;
    
    form.serializeArray().forEach(item => formData[item.name] = item.value);
    console.info(formData);

    patientList.forEach(p =>{
        if(p.phoneNumber === formData.phoneNumber){
            exist = true;
            patientId = p.id;
        }    
    });
    if(exist === true){
        addExamination(parseInt(formData.wage),examinationId,patientId);
    }else
        // add new patient
        fetch('/OUPharmacy/api/patient', {
            method:"POST",
            body:JSON.stringify({
                "firstName":formData.firstName,
                "lastName":formData.lastName,
                "dateOfBirth":formData.dateOfBirth,
                "gender": formData.gender,
                "phoneNumber": formData.phoneNumber,
                "address": formData.address,
                "active": 1
            }),
            headers:{
                "Content-Type":"application/json"
            }
        }).then(res =>res.json()).then(data =>{
            console.log("====NEW-Patient====");
            console.info(data);
            patientId = data.id;
        }).catch(err =>{
            console.error(err);
        }).then(() =>{
            // Add new examination-Detail            
            addExamination(parseInt(formData.wage),examinationId,patientId);
        });
};
const addExamination = (wage, examainationId, patientId) => {
    fetch('/OUPharmacy/api/examination-detail', {
        method:"POST",
        body:JSON.stringify({
            "wage":wage,
            "createdDate": new Date(),
            "examinationId":examainationId,
            "patientId":patientId
        }),
        headers:{
            "Content-Type":"application/json"
        }
    }).then(res=>{
        successfulAlert("Tao phiếu khám thành công", "Ok");
        res.json();
    }).then(data => {
        console.info(data);
    }).catch(err => console.err(err));
};