/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

//Get /api/medicines/{medicineUnitId}
const getDetailMedicineUnitById = (endpoint, callback) => {
    fetch(endpoint, {
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
const showUpdateModal = (endpoint, medicineUnitId) => {
    getDetailMedicineUnitById(endpoint, (data) => {
  
        $('#modalEditMedicineUnit').modal()
        
        let form = document.forms['formUpdateMedicineUnit']
        form["medicineId"].value = data.medicineId;
        form["inStock"].value = data.inStock;
        form["price"].value = data.inStock;
        form["inStock"].value = data.inStock;
        form["categoryId"].value = data.categoryId;
        form["image"].value = data.image;
        
        document.getElementById("btn-submit-form").onclick = () => saveChange(endpoint, medicineUnitId)
    })
}
//Delete /api/medicines/{medicineUnitId}
const deleteMedicineUnit = (endpoint) => {
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


const saveChange = (endpoint, medicineUnitId) => {
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




