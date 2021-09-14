package pe.edu.upc.prestabooks.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "Author")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Ingrese nombres")
	@Column(name = "first_name", nullable =false , length=25)
	private String first_name;
	
	@NotEmpty(message = "Ingrese apellidos")
	@Column(name = "last_name", nullable =false , length=25)
	private String last_name;
	
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
		this.first_name = first_name;
		this.last_name = last_name;
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
