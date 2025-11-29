package br.unibh.sdm.ecommercestaff.service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.unibh.sdm.ecommercestaff.entity.Staff;
import br.unibh.sdm.ecommercestaff.persistance.StaffRepository;

/**
 * Classe contendo a lógica de negócio para Staff
 * @author ecommerce-staff
 *
 */
@Service
public class StaffService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final StaffRepository staffRepo;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepo = staffRepository;
    }

    public List<Staff> getStaffList() {
        if (logger.isInfoEnabled()) {
            logger.info("Buscando todos os objetos de Staff");
        }
        Iterable<Staff> lista = this.staffRepo.findAll();
        if (lista == null) {
            return new ArrayList<Staff>();
        }
        return IteratorUtils.toList(lista.iterator());
    }

    public Staff getStaffById(Long id) {
        if (logger.isInfoEnabled()) {
            logger.info("Buscando Staff com o código {}", id);
        }
        Optional<Staff> retorno = this.staffRepo.findById(id);
        if (!retorno.isPresent()) {
            throw new RuntimeException("Staff com o id " + id + " não encontrado");
        }
        return retorno.get();
    }

    public List<Staff> getStaffByCpf(String cpf) {
        if (logger.isInfoEnabled()) {
            logger.info("Buscando Staff por CPF");
        }
        Iterable<Staff> lista = this.staffRepo.findByCpfStaff(cpf);
        if (lista == null) {
            return new ArrayList<Staff>();
        }
        return IteratorUtils.toList(lista.iterator());
    }

    public List<Staff> getStaffByName(String name) {
        if (logger.isInfoEnabled()) {
            logger.info("Buscando todos os Staff por nome");
        }
        Iterable<Staff> lista = this.staffRepo.findByName(name);
        if (lista == null) {
            return new ArrayList<Staff>();
        }
        return IteratorUtils.toList(lista.iterator());
    }

    public List<Staff> getStaffByPosition(String position) {
        if (logger.isInfoEnabled()) {
            logger.info("Buscando todos os Staff por posição");
        }
        Iterable<Staff> lista = this.staffRepo.findByPosition(position);
        if (lista == null) {
            return new ArrayList<Staff>();
        }
        return IteratorUtils.toList(lista.iterator());
    }

    public List<Staff> getStaffByEmail(String email) {
        if (logger.isInfoEnabled()) {
            logger.info("Buscando todos os Staff por email");
        }
        Iterable<Staff> lista = this.staffRepo.findByEmail(email);
        if (lista == null) {
            return new ArrayList<Staff>();
        }
        return IteratorUtils.toList(lista.iterator());
    }

    public List<Staff> getStaffByPhone(String phone) {
        if (logger.isInfoEnabled()) {
            logger.info("Buscando todos os Staff por telefone");
        }
        Iterable<Staff> lista = this.staffRepo.findByPhone(phone);
        if (lista == null) {
            return new ArrayList<Staff>();
        }
        return IteratorUtils.toList(lista.iterator());
    }

    public List<Staff> getStaffByLogin(String login) {
        if (logger.isInfoEnabled()) {
            logger.info("Buscando todos os Staff por login");
        }
        Iterable<Staff> lista = this.staffRepo.findByLogin(login);
        if (lista == null) {
            return new ArrayList<Staff>();
        }
        return IteratorUtils.toList(lista.iterator());
    }

    public Staff saveStaff(Staff staff) {
        if (logger.isInfoEnabled()) {
            logger.info("Salvando Staff com os detalhes {}", staff.toString());
        }
        return this.staffRepo.save(staff);
    }

    public void deleteStaff(Long id) {
        if (logger.isInfoEnabled()) {
            logger.info("Excluindo Staff com id {}", id);
        }
        this.staffRepo.deleteById(id);
    }

    public boolean staffExists(Long id) {
        Optional<Staff> retorno = this.staffRepo.findById(id);
        return retorno.isPresent() ? true : false;
    }

}
