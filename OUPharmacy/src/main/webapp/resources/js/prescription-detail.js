/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


// remove member item
const removeMemberItem = (i) => {
    document.getElementById("member-area").removeChild(document.getElementById(`member-${i}`));

    memberArray.splice(memberArray.indexOf(i), 1);
};


let medicinesName = [];
let countMedicine = 0;
const medicinesOnLoad = () => {
    //Load medicine-list
    fetch('/OUPharmacy/api/medicines', {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).then(res => res.json()).then(data => {
        //  Autocompltete Suggestion
        console.info(data);
        data.forEach(d => medicinesName.push({"id": d.id, "name": d.name}));
        console.info(medicinesName);
        //Sort names in ascending order  
    });
};

const createDataRow = (data) => {
    let h = document.querySelector(`.m_${data.medicineUnit}`);
    if (h !== null && h !== undefined) {
        let q = document.querySelector(".quantity");
        q.innerText = parseInt(q.innerText) + parseInt(data.quantity);
        return false;
    } else {
        countMedicine++;
        let tbPrescriptionElement = document.getElementById("prescriptionDetail");
        let trPrescriptionElement = document.createElement("tr");
        let thPrescriptionElement = document.createElement("th");
        let tdPrescriptionElement1 = document.createElement("td");
        let tdPrescriptionElement2 = document.createElement("td");
        let tdPrescriptionElement3 = document.createElement("td");
        let tdPrescriptionElement4 = document.createElement("td");

        thPrescriptionElement.innerText = countMedicine;
        tdPrescriptionElement1.innerText = data.name;
        tdPrescriptionElement2.innerText = data.quantity;
        tdPrescriptionElement3.innerText = data.use;
        tdPrescriptionElement4.innerHTML = `<input type="button" value="X" class="btn btn-danger"/>`;

        trPrescriptionElement.classList.add(`m_${data.medicineUnit}`);
        tdPrescriptionElement2.classList.add(`quantity`);
        trPrescriptionElement.appendChild(thPrescriptionElement);
        trPrescriptionElement.appendChild(tdPrescriptionElement1);
        trPrescriptionElement.appendChild(tdPrescriptionElement2);
        trPrescriptionElement.appendChild(tdPrescriptionElement3);
        trPrescriptionElement.appendChild(tdPrescriptionElement4);
        tbPrescriptionElement.appendChild(trPrescriptionElement);
    }
    return true;
};

let prescriptionJson = [];
let currentMedicineTarget = [];
let currentMedicineIdTarget = 0;
const addRowPrescriptionDetail = () => {
    let prescriptionRowData = {
        name: document.getElementById("medicine").value,
        quantity: document.getElementById("quantity").value,
        use: document.getElementById("use").value,
        medicineUnit: currentMedicineIdTarget
    };
    let prescriptionDetail = {
        quantity: document.getElementById("quantity").value,
        use: document.getElementById("use").value,
        medicineUnit: currentMedicineIdTarget
    };
    
//    for(p in prescriptionJson){
//        if(p.medicineUnit === prescriptionDetail.medicineUnit)
//            p.quantity = parseInt(p.quantity) + parseInt(prescriptionDetail.quantity);
//     
//    }
      if(createDataRow(prescriptionRowData)){
        prescriptionJson.push(prescriptionDetail);    
      }else{
          for(p in prescriptionJson){
            if (p.medicineUnit === prescriptionDetail.medicineUnit)
                p.quantity = (parseInt(p.quantity) + parseInt(prescriptionDetail.quantity)).toString();
        }
      }
          
//    createDataRow(prescriptionRowData);
//    prescriptionJson.push(prescriptionDetail);
    console.info(prescriptionRowData);
    console.info(prescriptionJson);

//    Them thanh cong
    console.log("SUCCESS");
    currentMedicineIdTarget = 0;
    document.getElementById("medicine").value = "";
    document.getElementById("quantity").value = "";
    document.getElementById("use").value = "";
};

//let sortedNames = medicinesName.sort();
let input = document.getElementById("medicine");
input.addEventListener("keyup", (e) => {
    removeElements();
    for (let i = 0; i < medicinesName.length; i++) {
        if (medicinesName[i].name.toLowerCase().startsWith(input.value.toLowerCase())
                && input.value !== "") {
            let listItem = document.createElement("li");
            //One common class name
            listItem.classList.add("list-items");
            listItem.classList.add(`medicine-${medicinesName[i].id}`);
            listItem.style.cursor = "pointer";
            listItem.onclick = () => {
                currentMedicineIdTarget = medicinesName[i].id;
                console.log(currentMedicineIdTarget);
                currentMedicineTarget = medicinesName[i];
                displayNames(medicinesName[i].name);
            }
            //Display matched part in bold
            let word = "<b>" + medicinesName[i].name.substr(0, input.value.length) + "</b>";
            word += medicinesName[i].name.substr(input.value.length);
            //display the value in array
            listItem.innerHTML = word;
            document.querySelector(".list").appendChild(listItem);
        }
    }
});
//input.addEventListener("keyup", (e) => {
//    removeElements();
//    for (let i of sortedNames) {
//        if (i.toLowerCase().startsWith(input.value.toLowerCase()) && input.value !== "") {
//            let listItem = document.createElement("li");
//            //One common class name
//            listItem.classList.add("list-items");
//            listItem.style.cursor = "pointer";
//            listItem.setAttribute("onclick", "displayNames('" + i + "')");
//            //Display matched part in bold
//            let word = "<b>" + i.substr(0, input.value.length) + "</b>";
//            word += i.substr(input.value.length);
//            //display the value in array
//            listItem.innerHTML = word;
//            document.querySelector(".list").appendChild(listItem);
//        }
//    }
//});

const displayNames = (value) => {
    input.value = value;
    removeElements();
};

const removeElements = () => {
    let items = document.querySelectorAll(".list-items");
    items.forEach((item) => {
        item.remove();
    });
};




