/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


const momoPayment = (total, prescriptionId) => {
    fetch("/OUPharmacy/api/momo-payment", {
        method: "post",
        body: JSON.stringify({
            "total": total,
            "prescriptionId": prescriptionId
        }),
        headers: {
            "Content-Type": "application/json"
        }
    }).then(res => res.json()).then(data =>{
        window.location.replace(data.payUrl);
    });
}