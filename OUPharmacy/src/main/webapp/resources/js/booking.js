/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
let userBooking = [];
const onloadUserBooking = () => {
    fetch('/OUPharmacy/api/examinations', {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).then(res => res.json()).then(data => {
        if (data.length === 0) {
            userBooking.push({
                "id": -1,
                "bookingListContent": []
            });
        }
        data.forEach(d => {
            let temp = [];
            temp.push(d);
            userBooking.push({
                "id": d.id,
                "bookingListContent": temp
            });
        });
        console.info(userBooking);
        hideLoading();
    });
};
const addExamination = (currentUserId) => {
    let date = new Date(document.getElementById("createdDate").value);
    fetch("/OUPharmacy/api/booking", {
        method: "POST",
        body: JSON.stringify({
            "description": document.getElementById("description").value,
            "createdDate": date,
            "active": 1,
            "userExaminationId": currentUserId,
        }),
        headers: {
            "Content-Type": "application/json"
        }
    }).then((res) => {
        console.info(res);
        return res.json();
    }).then((data) => {
        console.info(data);
        successfulAlert("Đặt lịch thành công", "Ok");
        //CALL BACK
    }).catch(err => {
        console.log("da co loi xay ra");
    });
};

//Delete /api/booking/{bookingId}
const deleteExamination = (endpoint) => {
    confirmAlert("Bạn có chắc chắn xóa?", "Bạn sẽ không thể khôi phục sau khi xóa!", "Có", "Không", () => {
        const response = fetch(endpoint, {
            method: "DELETE",
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(function (res) {
            if (res.status === 204)
                successfulAlert("Xóa thành công", "Ok", () => location.reload());
        }).catch(err => {
            console.log(err);
            errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình xóa dữ liệu!", "Ok");
        });
    });
};
let examinationDetail = [];
const onloadExaminationDetail = () =>{
    fetch('/OUPharmacy/api/examination-detail', {
        method:"GET",
        headers:{
           "Content-Type":"application/json"
        }
    }).then(res => res.json()).then(data=>{
        if(data.length === 0){
            examinationDetail.push({
                "id":-1,
                "examinationDetailContent":[]
            });
        }
        data.forEach(d =>{
            let temp = [];
            temp.push({
                "examinationId":d.examinationId.id
            });
            examinationDetail.push({
                "id": d.id,
                "examinationDetailContent": temp
            });
        });
        console.info(examinationDetail);
    }).catch(err =>{
        console.err(err);
    }).then(()=>{
        updateStatus();
    });
    
};

const sendEmailTrigger = (userId, examinationId) => {
    if (userBooking.length !== 0) {
        userBooking.forEach(b => {
            if (b.bookingListContent[0].userExaminationId.id === userId
                    && b.id === examinationId)
//                console.log(b.bookingListContent[0].userExaminationId.email);
                sendEmail(b.bookingListContent[0].userExaminationId.id);
        });
    }
};
// POST: /api/send-email
const sendEmail = (userId) => {
    let date = moment(new Date()).format('L');
    console.log(date);
    confirmAlert("Xác nhận gửi?", "", "Đồng ý", "Hủy", () => {
        showLoading();
        fetch('/OUPharmacy/api/send-email', {
            method: "POST",
            body: JSON.stringify({
                "userId": userId
            }),
            headers: {
                "Content-Type": "application/json"
            }
        }).then(res => {
            if (res.status === 200) {
                hideLoading();
                successfulAlert("Gửi thành công", "Ok");
            }
        }).catch(err => {
            console.err(err);
            errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình gửi mail!", "Ok");
        });
    });

};

const updateStatus = () =>{
    if(examinationDetail.length !== 0 && userBooking !== 0){
         userBooking.forEach(u =>{
             examinationDetail.forEach(e =>{
                 if(u.id === e.examinationDetailContent[0].examinationId){
                     let statusArea = document.querySelector(`#e_${u.id}`);
                     statusArea.innerText='Đã tạo phiếu khám';
                     statusArea.classList.add('text-success');
                     statusArea.classList.remove('text-danger');
             }});
         });
    }
};
