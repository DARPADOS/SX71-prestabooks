package pe.edu.upc.prestabooks.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Reader")
public class Reader {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Ingrese DNI")
	@Pattern(regexp = "[^0]\\d+", message = "Ingrese DNI correctamente.")
	@Column(name = "dni", nullable =false , length=8)
	private String dni;
	
	@NotEmpty(message = "Ingrese nombres")
	@Pattern(regexp = "^[^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:\\[\\]]{2,}$", message = "Ingrese el nombre correctamente")
	@Column(name = "first_name", nullable =false , length=30)
	private String first_name;
	
	@NotEmpty(message = "Ingrese apellidos")
	@Pattern(regexp = "^[^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:\\[\\]]{2,}$", message = "Ingrese el apellido correctamente")
	@Column(name = "last_name", nullable =false , length=30)
	private String last_name;
	
	@NotEmpty(message = "Ingrese email")
	@Email
	@Column(name = "email", nullable =false , length=30)
	private String email;
	
	@NotEmpty(message = "Ingrese dirección")
	@Column(name = "address", nullable =false , length=150)
	private String address;
	
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
		this.first_name = first_name;
		this.last_name = last_name;
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

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
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
