package br.unibh.sdm.ecommercestaff.rest;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.unibh.sdm.ecommercestaff.entity.Staff;
import br.unibh.sdm.ecommercestaff.persistance.StaffRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotNull;

/**
 * Classe contendo as definições de serviços REST/JSON para Staff
 * @author ecommerce-staff
 *
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "staff")
public class StaffController {

    private final StaffRepository staffRepository;

    public StaffController(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @GetMapping(value = "")
    public List<Staff> getStaffList() {
        return (List<Staff>) staffRepository.findAll();
    }

    @GetMapping(value = "{id}")
    public Object getStaffById(@PathVariable @NotNull Long id) throws Exception {
        if (staffRepository.existsById(id)) {
            return staffRepository.findById(id).get();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Collections.singletonMap("errorMessage", "Objeto não encontrado"));
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object createStaff(@RequestBody @NotNull Staff staff) throws Exception {
        try {
            return staffRepository.save(staff);
        } catch (TransactionSystemException e) {
            if (e.getRootCause() instanceof ConstraintViolationException) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Collections.singletonMap("errorMessage", e.getRootCause().getMessage()));
            } else {
                throw e;
            }
        }
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object updateStaff(@PathVariable Long id, @RequestBody @NotNull Staff staff) throws Exception {
        if (staffRepository.existsById(id)) {
            try {
                staff.setId(id);
                return staffRepository.save(staff);
            } catch (TransactionSystemException e) {
                if (e.getRootCause() instanceof ConstraintViolationException) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        Collections.singletonMap("errorMessage", e.getRootCause().getMessage()));
                } else {
                    throw e;
                }
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Collections.singletonMap("errorMessage", "Objeto não encontrado para alteração"));
        }
    }

    @DeleteMapping(value = "{id}")
    public Object deleteStaff(@PathVariable Long id) throws Exception {
        if (staffRepository.existsById(id)) {
            staffRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                Collections.singletonMap("message", "Objeto excluído com sucesso"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Collections.singletonMap("errorMessage", "Objeto não encontrado para exclusão"));
        }
    }

}
