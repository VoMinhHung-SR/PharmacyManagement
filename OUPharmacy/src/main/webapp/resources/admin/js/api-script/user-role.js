/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

const editAdminUserRole = (userId) =>{
    confirmAlert("Xác nhận thay đổi quyền thành quản trị viên",
    "Bạn sẽ không thể khôi phục sau khi thay đổi", "Có", "Không", () => {
        const response = fetch(`/OUPharmacy/api/users/${userId}/edit-user-role`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(function (res) {
            if (res.status === 200) successfulAlert("Chuyển đổi thành công", "Ok", () => location.reload());
        }).catch(err => {
            console.log(err);
            errorAlert("Đã có lỗi", "Đã có lỗi xảy ra trong quá trình xóa dữ liệu!", "Ok");
        });
    });
}