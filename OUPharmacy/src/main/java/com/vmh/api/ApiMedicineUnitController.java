/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.api;

import com.vmh.pojo.MedicineUnit;
import com.vmh.pojo.User;
import com.vmh.service.MedicineUnitService;
import com.vmh.validator.WebAppValidator;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping("/api")
public class ApiMedicineUnitController {

    @Autowired
    private MedicineUnitService medicineUnitService;

    @Autowired
    private WebAppValidator medicineUnitValidator;

    @InitBinder
    public void InitBinder(WebDataBinder binder) {
        binder.setValidator(medicineUnitValidator);
    }
    
    @GetMapping(path = "/medicines/medicine-unit/",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<MedicineUnit>> loadMedicineUnit(
            @RequestParam Map<String, String> params) {
        try {
            return new ResponseEntity<>(this.medicineUnitService.getMedicineUnits(params, 0)
                    , HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping(path = "/medicines/medicine-unit/{medicineUnitId}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MedicineUnit> loadMedicineUnit(
            @PathVariable(value = "medicineUnitId") int medicineUnitId) {
        try {
            MedicineUnit medicineUnit = this.medicineUnitService.getMedicineUnitDetail(medicineUnitId);
            return new ResponseEntity<>(medicineUnit, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/medicines/medicine-unit/{medicineId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMedicine(@PathVariable(value = "medicineId") int id) {
        this.medicineUnitService.deleteMedicine(id);
    }

    @PostMapping(path = "/medicines/medicine-unit/{medicineUnitId}",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> updateMedicineUnit(
            @PathVariable("medicineUnitId") int medicineUnitId,
            @RequestPart(value = "imgFile", required = false) MultipartFile file,
            @RequestPart("medicineUnit") @Valid MedicineUnit medicineUnit,
            BindingResult result) throws IOException {

        Map<String, String> errorMessages = new HashMap<>();
        HttpStatus status = null;
        
        if (!result.hasErrors()) {
            if (this.medicineUnitService.updateMedicineUnit(medicineUnit, medicineUnitId, file)) {
                status = HttpStatus.OK;
            } else {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            return new ResponseEntity<>(errorMessages, status);
        }

        List<FieldError> errors = result.getFieldErrors();
        for (FieldError error : errors) {
            errorMessages.put(error.getField(), error.getDefaultMessage());
        }
        status = HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(errorMessages, status);
    }

}
