/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

const config = {
    inline: true,
    dateFormat: "m-d-Y",
//    minDate: "today",
    onChange: function(selectedDates, dateStr, instance) {
//        console.log(selectedDates);
        console.log(dateStr);
//        console.log(instance);
        fetch('/OUPharmacy/api/schedules',{
            method: "POST",
            headers:{
                "Content-Type":"application/json"
            },
            body: JSON.stringify({
                "date":moment(dateStr).format('YYYY-MM-DD')
            })
        }).then(res=> res.json()).then(data =>{
            
            let btnAdd = document.getElementById("add-another");
            
            console.info(data);
            if(data.length !== 0){
                btnAdd.style.display = "none";
                
                uName1 = data[0].userOnCallId.firstName + " " + data[0].userOnCallId.lastName;
                uName2 = data[1].userOnCallId.firstName + " " + data[1].userOnCallId.lastName;
                
                
                triggerOnChangeScheduleArea(
                        data[0].createdDate,
                uName1, data[0].userOnCallId.email,
                uName2, data[1].userOnCallId.email);
            }else{
                let btnAdd = document.getElementById("add-another");
                btnAdd.style.display = "block";
                
                let contentArea = document.getElementById("main");
                contentArea.innerHTML = `
                <div  class="white-box">
                    <h3 class="text-danger text-center" >
                        Hiện tại chưa có lịch trực vào ngày 
                        ${moment(dateStr).format('DD-MM-YYYY')}
                    </h3>
                </div>`;
            }
        });
    }
};

flatpickr("input[type=date]", config);

const triggerOnChangeScheduleArea = (d = null, uName1, uEmail1,
                                        uName2, uEmail2) => {
    showLoading();
    moment.locale('vi');
    let contentArea = document.getElementById("main");
    let date;
    if (d !== null) {
        date = moment(d).format('DD-MM-YYYY');
    } else {
        date = moment(new Date()).format('DD-MM-YYYY');
        d = new Date();
    }


    contentArea.innerHTML = `
            <div  class="white-box">
                <h3 class="text-success text-center" >Đã có lịch trực vào ngày ${date}</h3>
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
                            <td>${uName1}</td>
                            <td id="e-1">${uEmail1}</td>
                            <td>1</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>${uName2}</td>
                            <td id="e-2">${uEmail2}</td>
                            <td>2</td>
                        </tr>
                    </tbody>
                </table></div>`;

    hideLoading();

};

