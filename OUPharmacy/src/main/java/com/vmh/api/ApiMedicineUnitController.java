/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vmh.api;

import com.vmh.pojo.MedicineUnit;
import com.vmh.service.MedicineUnitService;
import com.vmh.validator.WebAppValidator;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    
    @GetMapping(path = "/medicines/medicine-unit/{medicineUnitId}")
    public ResponseEntity<MedicineUnit> loadMedicineUnit(@PathVariable(value = "medicineUnitId") int medicineUnitId) {
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

    @PatchMapping(path = "/medicines/medicine-unit/{medicineId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> updateMedicineUnit(
            @PathVariable("medicineId") int medicineUnitId,
            @Valid @RequestBody MedicineUnit medicineUnit,
            BindingResult result) {
        
        Map<String, String> mapError = new HashMap<>();
        HttpStatus status = null;
        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                mapError.put("field", error.getField());
                mapError.put("message", error.getDefaultMessage());
            }
            status = HttpStatus.BAD_REQUEST;
        } else {
            if (this.medicineUnitService.updateMedicineUnit(medicineUnit, medicineUnitId)) {
                status = HttpStatus.OK;
            } else {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }

        return new ResponseEntity<>(mapError, status);
    }

}
