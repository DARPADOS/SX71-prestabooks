package pe.edu.upc.prestabooks.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "Reader",
indexes = {@Index(columnList="last_name, first_name",name="reader_index_last_first_name")})
@SQLDelete(sql = "UPDATE reader set deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Reader {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
    @Pattern(regexp = "[^0]\\d{7}", message = "Ingrese DNI correctamente.")
	@Column(name = "dni", nullable =false , length=8)
	private String dni;
	
	@Pattern(regexp = "^[^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:\\[\\]]{2,50}$", message = "Ingrese el nombre correctamente.")
	@Column(name = "first_name", nullable =false , length=50)
	private String firstName;
	
	@Pattern(regexp = "^[^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:\\[\\]]{2,50}$", message = "Ingrese el apellido correctamente.")
	@Column(name = "last_name", nullable =false , length=50)
	private String lastName;
	
	@Pattern(regexp = "[a-zA-Z0+9_.-]{2,20}@[a-zA-Z0-9]{2,20}\\.[a-zA-Z]{2,10}", message = "Ingrese el email correctamente.")
	@Column(name = "email", nullable =false , length=50)
	private String email;
	
	@Pattern(regexp = "^[a-zA-Z].{9,149}", message = "Ingrese la dirección correctamente")
	@Column(name = "address", nullable =false , length=150)
	private String address;
	
	private Boolean deleted = Boolean.FALSE;

	// Relaciones
	@OneToMany(mappedBy = "reader", fetch = FetchType.LAZY)
	private List<Loan> loans;

	public Reader() {
		super();
		loans = new ArrayList<Loan>();
	}

	public Reader(int id, @NotEmpty(message = "Ingrese DNI") String dni,
			@NotEmpty(message = "Ingrese nombres") String first_name,
			@NotEmpty(message = "Ingrese apellidos") String last_name,
			@NotEmpty(message = "Ingrese email") @Email String email,
			@NotEmpty(message = "Ingrese dirección") String address) {
		super();
		this.id = id;
		this.dni = dni;
		this.firstName = first_name;
		this.lastName = last_name;
		this.email = email;
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Reader other = (Reader) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public List<Loan> getLoans() {
		return loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
