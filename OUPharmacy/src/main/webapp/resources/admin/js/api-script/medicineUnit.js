/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


//Delete /api/medicines/{medicineId}
const deleteMedicineUnit = (endpoint) => {
    if (confirm('Ban co muon xoa khong?')) {
        const d = fetch(endpoint, {
            method: "DELETE", 
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(function (res){
            if(res.status === 204){
                console.log("Xóa khoa thành công");
            }
        }).catch(err => {
            console.log("Error!!");
            console.log(err);
        })

    } else {
        // Do nothing!
        console.log('BAN DA HUY!');
    }
}
