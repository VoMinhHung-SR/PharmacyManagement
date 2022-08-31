/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


// remove member item
const removeItem = (m) => {
    document.querySelector(`.m_${m}`).remove();

    for (let i = 0; i < prescriptionJson.length; i++) {
        if (prescriptionJson[i].medicineUnitId === m) {
            prescriptionJson.splice(i, 1);
            break;
        }
    }
    if (prescriptionJson.length === 0) {
        document.getElementById("prescriptionDetail").innerHTML =
                `<tr> 
                            <th scope="row" colspan="5" class="text-center">
                                Hiện tại chưa có thuốc trong toa!!!
                            </th>
                        </tr>`;
        document.getElementById("export").style.display = "none";
    }
    console.info(prescriptionJson);
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
        data.forEach(d => medicinesName.push({"id": d.id, "name": d.name, 
            "medicineUnitId": d.medicineUnitCollection[0].id}));
        console.info(medicinesName);
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
        if (countMedicine === 1 || prescriptionJson.length === 0) {
            document.getElementById("prescriptionDetail").innerText = "";
            document.getElementById("export").style.display = "block";
        }
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
        tdPrescriptionElement4.classList.add(`text-center`);
        tdPrescriptionElement4.onclick = () => {
            console.log(data.medicineUnit);
            removeItem(data.medicineUnit);
        }

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
const addRowPrescriptionDetail = (prescriptionId) => {
    let prescriptionRowData = {
        name: document.getElementById("medicine").value,
        quantity: document.getElementById("quantity").value,
        use: document.getElementById("use").value,
        medicineUnit: currentMedicineIdTarget
    };
    let prescriptionDetail = {
        quantity: document.getElementById("quantity").value,
        uses: document.getElementById("use").value,
        medicineUnitId: currentMedicineIdTarget,
        prescriptionId: prescriptionId
    };
    console.info(prescriptionJson);
    if (createDataRow(prescriptionRowData)) {
        prescriptionJson.push(prescriptionDetail);
    } else {
        for (let p = 0; p < prescriptionJson.length; p++) {
            if (prescriptionJson[p].medicineUnitId === prescriptionDetail.medicineUnit)
                prescriptionJson[p].quantity = (parseInt(prescriptionJson[p].quantity) +
                        parseInt(prescriptionDetail.quantity)).toString();
        }
    }

    console.info(prescriptionRowData);
    console.info(prescriptionJson);
//    Them thanh cong
    console.log("SUCCESS");
    currentMedicineIdTarget = 0;
    document.getElementById("medicine").value = "";
    document.getElementById("quantity").value = "";
    document.getElementById("use").value = "";
};
let input = document.getElementById("medicine");
input.addEventListener("keyup", (e) => {
    removeElements();
    for (let i = 0; i < medicinesName.length; i++) {
        if (medicinesName[i].name.toLowerCase().startsWith(input.value.toLowerCase())
                && input.value !== "") {
            let listItem = document.createElement("li");
            let j = 0;
            //One common class name
            listItem.classList.add("list-items");
            listItem.classList.add(`medicine-${medicinesName[i].medicineUnitId}`);
            listItem.style.cursor = "pointer";
            listItem.onclick = () => {
                currentMedicineIdTarget = medicinesName[i].medicineUnitId;
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


const exportPrescriptionDetail = () => {
    if (prescriptionJson.length !== 0) {
        prescriptionJson.forEach(p => {
            fetch('/OUPharmacy/api/prescription-detail', {
                method: "POST",
                body: JSON.stringify({
                    "quantity": parseInt(p.quantity),
                    "uses": p.uses,
                    "medicineUnitId": p.medicineUnitId,
                    "prescriptionId": p.prescriptionId
                }),
                headers: {
                    "Content-Type": "application/json"
                }
            }).then(res => {
                if(res.status === 201)
                    successfulAlert("Kê toa thành công", "Ok");
            });
        });
    }
};




