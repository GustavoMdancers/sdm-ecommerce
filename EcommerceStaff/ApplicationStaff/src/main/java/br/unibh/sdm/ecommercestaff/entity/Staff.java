package br.unibh.sdm.ecommercestaff.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "store-staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    @Column(length=100)
    @Size(min = 3, max = 100)
    private String name;
    
    @NotBlank
    @Column(length=100)
    @Size(min = 3, max = 100)
    private String position;
    
    @NotNull
    @Column(length=100)
    private double salary;

    @NotBlank
    @Column(name="cpf_staff", length=11, unique=true)
    @Size(min = 11, max = 11)
    private String cpfStaff;

    @NotBlank
    @Column(length=100)
    @Size(min = 3, max = 100)
    private String email;

    @NotBlank
    @Column(length=100)
    @Size(min = 3, max = 100)
    private String phone;

    @NotNull
    @Past
    @Column(name = "hire_date")
    private Date hireDate;

    @NotBlank
    @Column(length=50, unique=true)
    @Size(min = 3, max = 50)
    private String login;

    @NotBlank
    @Column(length=100)
    private String password;

    public Staff() {
        super();
    }

    public Staff(long id, @NotBlank @Size(min = 3, max = 100) String name,
            @NotBlank @Size(min = 3, max = 100) String position, @NotBlank @Size(min = 3, max = 100) double salary,
            @NotBlank @Size(min = 11, max = 11) String cpfStaff, @NotBlank @Size(min = 3, max = 100) String email,
            @NotBlank @Size(min = 3, max = 100) String phone, @NotNull @Past Date hireDate,
            @NotBlank @Size(min = 3, max = 50) String login, @NotBlank String password) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
        this.cpfStaff = cpfStaff;
        this.email = email;
        this.phone = phone;
        this.hireDate = hireDate;
        this.login = login;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getCpfStaff() {
        return cpfStaff;
    }

    public void setCpfStaff(String cpfStaff) {
        this.cpfStaff = cpfStaff;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Staff [id=" + id + ", name=" + name + ", position=" + position + ", salary=" + salary + ", cpfStaff="
                + cpfStaff + ", email=" + email + ", phone=" + phone + ", hireDate=" + hireDate + ", login=" + login
                + ", password=" + password + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((position == null) ? 0 : position.hashCode());
        long temp;
        temp = Double.doubleToLongBits(salary);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((cpfStaff == null) ? 0 : cpfStaff.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((hireDate == null) ? 0 : hireDate.hashCode());
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Staff other = (Staff) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (position == null) {
            if (other.position != null)
                return false;
        } else if (!position.equals(other.position))
            return false;
        if (Double.doubleToLongBits(salary) != Double.doubleToLongBits(other.salary))
            return false;
        if (cpfStaff == null) {
            if (other.cpfStaff != null)
                return false;
        } else if (!cpfStaff.equals(other.cpfStaff))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (phone == null) {
            if (other.phone != null)
                return false;
        } else if (!phone.equals(other.phone))
            return false;
        if (hireDate == null) {
            if (other.hireDate != null)
                return false;
        } else if (!hireDate.equals(other.hireDate))
            return false;
        if (login == null) {
            if (other.login != null)
                return false;
        } else if (!login.equals(other.login))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        return true;
    }

    
}
