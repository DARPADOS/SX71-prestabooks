package pe.edu.upc.prestabooks.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;

@Entity
@Table(name="employee")
public class Employee {
    
    // Campos

    @Id
    private Integer id;

    @Size(min = 8, max = 8, message = "El dni debe de tener 8 digitos.")
    @Pattern(regexp = "[^0]\\d+", message = "Ingrese DNI correctamente.")
    @Column(name = "dni")
    private String dni;

    @Size(max = 50)
    @Pattern(regexp = "^[^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:\\[\\]]{2,}$", message = "Ingrese el nombre correctamente")
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 50)
    @Pattern(regexp = "^[^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:\\[\\]]{2,}$", message = "Ingrese el apellido correctamente")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Ingrese una fecha de contratación.")
    @Column(name = "hire_date")
    @PastOrPresent
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    private Date hireDate;

    @Size(min = 9, max = 9, message = "El celular debe de tener 9 digitos.")
    @Pattern(regexp = "^[9]\\d+", message = "Ingrese el correctamente el celular.")
    @Column(name = "phone")
    private String phone; 
    
    // Relaciones


    @OneToOne(cascade = CascadeType.ALL)
    @MapsId("id")
    @JoinColumn(name="id")
    private User user;

    public Employee() {
        super();
    }

    public Employee(Integer id, String dni, String firstName, String lastName, Date hireDate, String phone, User user) {
        this.id = id;
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireDate = hireDate;
        this.phone = phone;

        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public  Employee setId(Integer id) {
        this.id = id;
        return this;
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