/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


const updateStatus = (userId) => {
    confirmAlert("Thay đổi trạng thái hoạt động?",
            "Nếu trạng thái đang tắt người dùng sẽ không thể sử dụng dịch vụ hệ thống!!!", "Có", "Không", () => {
        const response = fetch(`/OUPharmacy/api/users/${userId}/update-active`, {
            method: "PATCH",
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(function (res) {
            if (res.status === 200)
                successfulAlert("Chuyển đổi thành công", "Ok", () => location.reload());
        }).catch(err => {
            console.log(err);
            errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình xóa dữ liệu!", "Ok");
        });
    });
};

const loadUserById = (userId, callback) => {
    fetch(`/OUPharmacy/api/users/${userId}`, {
        method: 'GET', headers: {
            "Content-Type": "application/json"
        }
    }).then(res => res.json()).then(data => {
        console.info(data);
        callback(data);
    }).catch(err => {
        console.error(err);
        errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình tải dữ liệu!", "Ok");
    });
};

const showEditUserModal = (userId) => {

    loadUserById(userId, (data) => {

        document.getElementById("update-button").onclick = () => saveChange(userId);

        let form = document.forms['form-update-user'];
        form["firstName"].value = data.firstName;
        form["lastName"].value = data.lastName;
        form["gender"].value = data.gender;
        form["dateOfBirth"].value = new Date(data.dateOfBirth).toISOString().slice(0, 10);
        form["phoneNumber"].value = data.phoneNumber;
        form["email"].value = data.email;
        form["address"].value = data.address;
    });
};

const saveChange = (userId) => {
    let form = $("#form-update-user");
    let formData = {};
    var formDataSubmit = new FormData();

    form.serializeArray().forEach(item => {
        formData[item.name] = item.value;
    });
    var jsonSubmit = {
        id: userId,
        username: "jsonIgnore",
        password: "jsonIgnore",
        firstName: formData.firstName,
        lastName: formData.lastName,
        email: formData.email,
        phoneNumber: formData.phoneNumber,
        dateOfBirth: formData.dateOfBirth,
        gender: formData.gender,
        address: formData.address
    }
    console.log(jsonSubmit);
    var file = document.forms['form-update-user']['file'].files[0];
    formDataSubmit.append("avatarFile", file);

    if (formData.password === '') {
        $('input[name=' + "password" + ']').after('<span class="text-danger">' + "Mật khẩu không được trống" + '</span>');
    } else
    if (formData.password !== formData.confirmPassword) {
        $('input[name=' + "confirmPassword" + ']').after('<span class="text-danger">' + "Mật khẩu xác nhận không chính xác" + '</span>');
    } else {
        formDataSubmit.append("user", new Blob([JSON.stringify(jsonSubmit)], 
        {type: "application/json"}));
        fetch(`/OUPharmacy/api/users/${userId}`, {
            method: "POST",
            body: formDataSubmit
        }).then(res => res.json()).then(data => {
            console.info(data);
            if (Object.keys(data).length === 0) {
                // successful
                $('#update-user-modal').hide();
                successfulAlert("Cập nhật người dùng thành công", "Ok", () => location.reload());
            } else {
                // error
                $.each(data, function (key, value) {
                    if (key === "firstName" || key === "lastName" || key === "username" || key === "password" || key === "dateOfBirth" || key === "email") {
                        $('input[name=' + key + ']').after('<span class="text-danger">' + value + '</span>');
                    }
                });
            }
        }).catch(err => {
            console.error(err);
            errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình cập nhật!", "Ok");
        });
    }

};


