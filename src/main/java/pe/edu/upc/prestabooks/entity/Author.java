package pe.edu.upc.prestabooks.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@Entity
@Table(name = "Author",
indexes = {@Index(columnList="last_name, first_name",name="author_index_last_first_name")})
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Ingrese nombres")
	@Pattern(regexp = "^[^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:\\[\\]]{2,}$", message = "Ingrese el nombre correctamente")
	@Column(name = "first_name", nullable =false , length=25)
	private String firstName;
	
	@NotEmpty(message = "Ingrese apellidos")
	@Pattern(regexp = "^[^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:\\[\\]]{2,}$", message = "Ingrese el nombre correctamente")
	@Column(name = "last_name", nullable =false , length=25)
	private String lastName;
	
	@NotEmpty(message = "Ingrese nacionalidad")
	@Column(name = "nationality", nullable =false , length=50)
	private String nationality;

	// Relaciones
	@OneToMany(mappedBy = "author")
	private List<DetailAuthorBook> detailAuthorBooks;

	public Author() {
		super();
		detailAuthorBooks = new ArrayList<DetailAuthorBook>();
	}

	public Author(int id, @NotEmpty(message = "Ingrese nombres") String first_name,
			@NotEmpty(message = "Ingrese apellidos") String last_name,
			@NotEmpty(message = "Ingrese nacionalidad") String nationality) {
		super();
		this.id = id;
		this.firstName = first_name;
		this.lastName = last_name;
		this.nationality = nationality;
	}

	public List<DetailAuthorBook> getDetailAuthorBooks() {
		return detailAuthorBooks;
	}

	public void setDetailAuthorBooks(List<DetailAuthorBook> detailAuthorBooks) {
		this.detailAuthorBooks = detailAuthorBooks;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
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
		Author other = (Author) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
