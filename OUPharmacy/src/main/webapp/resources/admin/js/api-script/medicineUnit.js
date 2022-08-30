/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
window.onload = () =>{
    hideLoading();
}
//Get /api/medicines/{medicineUnitId}
const getDetailMedicineUnitById = (medicineUnitId, callback) => {
    fetch(`/OUPharmacy/api/medicines/medicine-unit/${medicineUnitId}`, {
        method: "GET",
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => res.json()).then(data => {
        callback(data);
    }).catch(err => {
        Console.log(err);
    })
}
const showUpdateModal = (medicineUnitId) => {
    getDetailMedicineUnitById(medicineUnitId, (data) => {
  
     
        let form = document.forms['form-medicine-unit'];
        form["medicineId"].value = data.medicineId.id;
        form["inStock"].value = data.inStock;
        form["price"].value = data.price;
        form["categoryId"].value = data.categoryId.id;
//        form["image"].value = data.image;
        
        document.getElementById("update-button").onclick = () => saveChange(medicineUnitId)
    })
}
//Delete /api/medicines/{medicineUnitId}
const deleteMedicineUnit = (endpoint) => {
    confirmAlert("Bạn có chắc chắn xóa?", "Bạn sẽ không thể khôi phục sau khi xóa!", "Có", "Không", () => {
       const d = fetch(endpoint, {
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


const saveChange = (medicineUnitId) => {
    let form = $("#formUpdateMedicineUnit")
    let formData = {}

    form.serializeArray().forEach(item => {
        formData[item.name] = item.value
    })

    // UPDATE
    fetch(endpoint, {
        method: "PATCH", body: JSON.stringify({  
            "inStock": formData.inStock,
            "price": formData.price,
            "categoryId": formData.categoryId,
            "medicineId": formData.medicineId,
        }), headers: {
            "Content-Type": "application/json"
        }
    }).then(res => res.json()).then(data => {
        if (Object.keys(data).length === 0) {
            // successful
            $('#modalEditMedicineUnit').hide();
            successfulAlert("cap nhat thanh cong", "Ok", () => location.reload());
        } else {
            // error
            $.each(data, function (key, value) {
                if (key === "file") {
                    console.log("tính sau")
                } else if (key === "code" || key === "fullName" || key === "phone" || key === "email" || key === "birthday" || key === "address") {
                    $('input[name=' + key + ']').after('<span class="text-danger">' + value + '</span>');
                }
            });
        }
    }).catch(err => {
        console.log(err);
    })

}




