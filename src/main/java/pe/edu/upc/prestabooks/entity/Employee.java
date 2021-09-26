package pe.edu.upc.prestabooks.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;

@Entity
@Table(name="employee",
indexes = {@Index(columnList="last_name, first_name",name="employee_index_last_first_name")})
@SQLDelete(sql = "UPDATE employee set deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Employee {
    
    // Campos

    @Id
    private Integer id;

    @Pattern(regexp = "[^0]\\d{7}", message = "Ingrese DNI correctamente.")
    @Column(name = "dni")
    private String dni;

    @Pattern(regexp = "^[^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:\\[\\]]{2,}$", message = "Ingrese el nombre correctamente")
    @Column(name = "first_name")
    private String firstName;

    @Pattern(regexp = "^[^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:\\[\\]]{2,}$", message = "Ingrese el apellido correctamente")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Ingrese una fecha de contratación.")
    @Column(name = "hire_date")
    @PastOrPresent(message = "Ingrese una fecha de contratación.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    private Date hireDate;

    @Pattern(regexp = "^[9]\\d{8}", message = "Ingrese el correctamente el celular.")
    @Column(name = "phone")
    private String phone; 
    
    private Boolean deleted = Boolean.FALSE;

    // Relaciones


    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @OneToOne()
    @MapsId("id")
    @JoinColumn(name="id")
    private User user;

    public Employee() {
        super();
    }

    
    
    public Employee(Integer id, @Pattern(regexp = "[^0]\\d{7}", message = "Ingrese DNI correctamente.") String dni,
            @Pattern(regexp = "^[^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:\\[\\]]{2,}$", message = "Ingrese el nombre correctamente") String firstName,
            @Pattern(regexp = "^[^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:\\[\\]]{2,}$", message = "Ingrese el apellido correctamente") String lastName,
            @NotNull(message = "Ingrese una fecha de contratación.") @PastOrPresent(message = "Ingrese una fecha de contratación.") Date hireDate,
            @Pattern(regexp = "^[9]\\d{8}", message = "Ingrese el correctamente el celular.") String phone,
            Boolean deleted, User user) {
        this.id = id;
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireDate = hireDate;
        this.phone = phone;
        this.deleted = deleted;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}