/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


const addExamination = (currentUserId) => {
    let date = new Date(document.getElementById("createdDate").value);
    fetch("/OUPharmacy/api/booking", {
        method: "POST",
        body: JSON.stringify({
            "description": document.getElementById("description").value,
            "createdDate": date,
            "active": 1,
            "userExaminationId":currentUserId,
        }),
        headers: {
            "Content-Type": "application/json"
        }
    }).then((res) => {
        console.info(res);
        return res.json();
    }).then((data) => {
        console.info(data);
        alert("them thanh cong");
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
            if (res.status === 204) successfulAlert("Xóa khoa thành công", "Ok", () => location.reload());
        }).catch(err => {
            console.log(err);
            errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình xóa dữ liệu!", "Ok");
        });
    });
//    if (confirm('Ban co muon xoa khong?')) {
//        const d = fetch(endpoint, {
//            method: "DELETE",
//            headers: {
//                'Content-Type': 'application/json'
//            }
//        }).then(function (res) {
//            if (res.status === 204) {
//                alert("xoa thanh cong");
//                location.reload();
//            }
//        }).catch(err => {
//            console.log("Error!!");
//            console.log(err);
//        })
//
//    } else {
//        console.log('BAN DA HUY!');
//    }
}

//POST : /api/booking-list/nur-censored/{bookingId}
const sendEmailChecker = (endpoint) => {
    if (confirm('Ban co muon gui xac nhan chu?')) {
        const d = fetch(endpoint, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(function (res) {
            if (res.status === 200) {
                alert("gui thanh thanh cong");
                location.reload();
            }
        }).catch(err => {
            console.log("Error!!");
            console.log(err);
        })

    } else {
        console.log('BAN DA HUY!');
    }
}
