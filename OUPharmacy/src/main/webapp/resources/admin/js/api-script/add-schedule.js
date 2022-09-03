/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
const usersAvailable = [];
window.onload = () => {
    onloadUser();
    hideLoading();
};
const onloadUser = () => {
    fetch('/OUPharmacy/api/users/roles/r-1-2').then(res => res.json()).then(data => {
        data.forEach(d => {
            let fullname = d.firstName + " " + d.lastName;
            usersAvailable.push({
                "id": d.id,
                "fullName": fullname,
                "email": d.email
            });
        });
    });
    console.info(usersAvailable);
};
const onChangeUser = (change, uId) => {
    if (change === 1) {
        usersAvailable.forEach(u => {
            if (u.id === parseInt(uId))
                document.getElementById("e-1").innerHTML = `${u.email}`;
        });
    }
    if (change === 2) {
        usersAvailable.forEach(u => {
            if (u.id === parseInt(uId))
                document.getElementById("e-2").innerHTML = `${u.email}`;
        });
    }
};
const showUserAvailable = () => {
    let shiftOneArea = document.querySelector('#shift-1');
    let shiftTwoArea = document.querySelector('#shift-2');
    shiftOneArea.onchange = () => {
        onChangeUser(1, this.event.target.value);
    };
    shiftTwoArea.onchange = () => {
        onChangeUser(2, this.event.target.value);
    };
    document.getElementById("add-schedule").onclick = () =>{
        addSchedule();
    };
    usersAvailable.forEach(u => {
        shiftOneArea.innerHTML += `<option value=${u.id}>${u.fullName}</option>`;
        shiftTwoArea.innerHTML += `<option value=${u.id}>${u.fullName}</option>`;
    });
};

const triggerAddScheduleArea = () => {
    showLoading();
    moment.locale('vi');
    let contentArea = document.getElementById("main");
    let date = moment(new Date()).format('L');

    contentArea.innerHTML = `
            <div  class="white-box">
                <h3 class="text-danger text-center" >Lich truc ngay ${date}</h3>
                <table data-bs-toggle="table"
                       class="table">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Ten bac si (y ta)</th>
                            <th>Email</th>
                            <th>Ca truc</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>
                                <select id="shift-1" name="fullName">            
                                </select>
                            </td>
                            <td id="e-1">doctorA@gmail.com</td>
                            <td>1</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>
                               <select id="shift-2" name="fullName"></select>
                            </td>
                            <td id="e-2">doctorA@gmail.com</td>
                            <td>2</td>
                        </tr>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="4">
                                <div class="d-flex" style="justify-content: right">
                                    <button class="btn-success" id="add-schedule">
                                        <i class="fas fa-plus"></i> 
                                        Them lich truc
                                    </button>
                                </div>
                            </td>
                        </tr>
                      
                    </tfoot>
                </table></div>`;
    showUserAvailable();
    hideLoading();

};

const addSchedule = () => {
    let date = new Date()
    let jsonSubmit = [{
            "shift": 1,
            "userOnCallId": parseInt(document.querySelector('#shift-1').value)
        }, {
            "shift": 2,
            "userOnCallId": parseInt(document.querySelector('#shift-2').value)
        }]
    console.log(jsonSubmit);
    confirmAlert("Xác nhận tạo lịch", "", "Đồng ý", "Hủy", () => {
        showLoading();
        jsonSubmit.forEach(d => {
            fetch('/OUPharmacy/api/add-schedule', {
                method: "POST",
                body: JSON.stringify({
                    "createdDate":date,
                    "shift": d.shift,
                    "userOnCallId": d.userOnCallId
                }),
                headers: {
                    "Content-Type": "application/json"
                }
            }).then(res => {
                if (res.status === 200) {
                    hideLoading();
                    successfulAlert("Tạo thành công", "Ok");
                }
            }).catch(err => {
                console.err(err);
                errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình gửi tạo!", "Ok");
            });
        });
    });
};

