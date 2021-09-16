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
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Book",
indexes = {@Index(columnList = "title",name = "book_index_title")})
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Ingrese título")
	@Column(name = "title", nullable =false , length=100)
	private String title;
	
	@NotEmpty(message = "Ingrese lenguaje original")
	@Column(name = "original_language", nullable =false , length=20)
	private String original_language;
	
	@NotNull(message = "Ingrese stock")
	@Column(name = "stock", nullable =false)
	private Integer stock;
	
	@NotNull(message = "Ingrese el número de páginas")
	@Column(name = "pages", nullable =false)
	private Integer pages;
	
	//Relaciones
	@OneToMany(mappedBy = "book", fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<DetailAuthorBook> detailAuthorBooks;

	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
	private List<Loan> loans;

	// No se guardan en la base de datos

	@NotEmpty
	@Transient
	private List<Author> authors;

	// Funciones

	// Constructores

	public Book() {
		super();
		detailAuthorBooks = new ArrayList<DetailAuthorBook>();
		loans = new ArrayList<Loan>();
		authors = new ArrayList<Author>();
	}

	public Book(int id, @NotEmpty(message = "Ingrese título") String title,
			@NotEmpty(message = "Ingrese lenguaje original") String original_language,
			@NotEmpty(message = "Ingrese stock") int stock,
			@NotEmpty(message = "Ingrese el número de páginas") int pages) {
		super();
		this.id = id;
		this.title = title;
		this.original_language = original_language;
		this.stock = stock;
		this.pages = pages;
	}

	// Getter and Setters

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public List<DetailAuthorBook> getDetailAuthorBooks() {
		return detailAuthorBooks;
	}

	public void setDetailAuthorBooks(List<DetailAuthorBook> detailAuthorBooks) {
		this.detailAuthorBooks = detailAuthorBooks;
	}

	public List<Loan> getLoans() {
		return loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginal_language() {
		return original_language;
	}

	public void setOriginal_language(String original_language) {
		this.original_language = original_language;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
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
		Book other = (Book) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
