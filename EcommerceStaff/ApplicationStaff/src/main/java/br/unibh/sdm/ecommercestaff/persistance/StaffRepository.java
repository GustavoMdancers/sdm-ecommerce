package br.unibh.sdm.ecommercestaff.persistance;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.unibh.sdm.ecommercestaff.entity.Staff;

public interface StaffRepository extends CrudRepository<Staff, Long> {

    List<Staff> findByName(String name);
    List<Staff> findByPosition(String position);
    List<Staff> findByCpfStaff(String cpfStaff);
    List<Staff> findByEmail(String email);
    List<Staff> findByPhone(String phone);
    List<Staff> findByHireDate(java.util.Date hireDate);
    List<Staff> findByLogin(String login);

}

