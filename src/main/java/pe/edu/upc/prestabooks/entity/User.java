package pe.edu.upc.prestabooks.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {

    // Campos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Integer id;
    
    @NotBlank
    @Column(name="username", length=20, nullable=false)
	private String username;
	
    @Size(max = 60, min = 5, message = "La contraseña tiene que poseer entre 5 a 60 carácteres.")
    @NotBlank
	@Column(name="password",  length=60, nullable=false)
	private String password;

    // Relaciones

    @OneToMany(mappedBy="user", fetch=FetchType.LAZY)
    private List<Loan> loans;

    @OneToOne(mappedBy = "user")
    private Employee employee;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Authority> authorities;

    public User() {
		super();
		authorities = new ArrayList<Authority>();
        loans = new ArrayList<Loan>();
	}

    public User(Integer id, String username, String password, Employee employee, List<Authority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.employee = employee;
        this.authorities = authorities;
    }

    // Agregar el ROLE o ACCESS al usuario
	public void addAuthority(String authName) {
		Authority authority = new Authority();
		authority.setAuthority(authName) ;
		authority.setUser(this);
		
		this.authorities.add(authority);
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

}
