/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


const addExamination = () => {
    fetch("/OUPharmacy/api/booking", {
        method: "POST",
        body: JSON.stringify({
            "description": document.getElementById("description").value
        }),
        headers: {
            "Content-Type": "application/json"
        }
    }).then((res) => {
        console.info(res);
        res.json();
    }).then((data) => {
        console.info(data);
        console.log("them thanh cong");
        //CALL BACK
    }).catch(err => {
        console.log("da co loi xay ra");
    });
};

//Delete /api/booking/{bookingId}
const deleteExamination = (endpoint) => {
    if (confirm('Ban co muon xoa khong?')) {
        const d = fetch(endpoint, {
            method: "DELETE",
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(function (res) {
            if (res.status === 204) {
                alert("xoa thanh cong");
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
