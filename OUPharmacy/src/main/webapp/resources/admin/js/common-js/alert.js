/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


const confirmAlert = (title, text, confirmButtonText, cancelButtonText, callback) => {
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
            confirmButton: 'btn btn-success ml-1', cancelButton: 'btn btn-danger  mr-1'
        }, buttonsStyling: false
    })

    swalWithBootstrapButtons.fire({
        title: title,
        text: text,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: confirmButtonText,
        cancelButtonText: cancelButtonText,
        reverseButtons: true,
    }).then(function (result) {
        if (result.isConfirmed)
            callback();
    })
}

const successfulAlert = (title, confirmButtonText, callback) => {
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
            confirmButton: 'btn btn-success',
        }, buttonsStyling: false
    })

    swalWithBootstrapButtons.fire({
        position: 'center', icon: 'success', title: title, showConfirmButton: true, confirmButtonText: confirmButtonText
    }).then(callback)
}

