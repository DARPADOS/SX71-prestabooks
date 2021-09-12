package pe.edu.upc.prestabooks.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="employee")
public class Employee {
    
    // Campos

    @Id
    private Integer id;

    @Min(10000000)
    @Max(99999999)
    @NotNull(message = "Ingrese un DNI.")
    @Pattern(regexp = "\\d+", message = "Ingrese el correctamente el campo DNI.")
    @Column(name = "dni")
    private Integer dni;

    @Size(max = 20)
    @NotNull(message = "Ingrese un nombre.")
    @NotBlank(message = "Ingrese un nombre.")
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 20)
    @NotNull(message = "Ingrese un Apellido.")
    @NotBlank(message = "Ingrese un Apellido.")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Ingrese una fecha de contratación.")
    @NotBlank(message = "Ingrese una fecha de contratación.")
    @Column(name = "hire_date")
    @Temporal(TemporalType.TIME)
    private Date hireDate;

    @NotNull(message = "Ingrese un celular.")
    @NotBlank(message = "Ingrese un celular.")
    @Min(100000000)
    @Max(999999999)
    @Pattern(regexp = "\\d+", message = "Ingrese el correctamente el celular.")
    @Column(name = "phone")
    private Integer phone; 
    
    // Relaciones
    //@OneToMany(mappedBy="employee", fetch=FetchType.LAZY)
    //private List<Loan> loans;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId("id")
    @JoinColumn(name="id")
    private User user;

    public Employee() {
        super();
        //loans = new ArrayList<Loan>();
    }

    public Employee(Integer id, Integer dni, String firstName, String lastName, Date hireDate, Integer phone,
            /*List<Loan> loans,*/ User user) {
        this.id = id;
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireDate = hireDate;
        this.phone = phone;
        //this.loans = loans;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
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

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }
/*
    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }
*/
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}