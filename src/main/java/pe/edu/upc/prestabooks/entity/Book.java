package pe.edu.upc.prestabooks.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Book")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Ingrese título")
	@Column(name = "title", nullable =false , length=100)
	private String title;
	
	@NotEmpty(message = "Ingrese lenguaje original")
	@Column(name = "original_language", nullable =false , length=20)
	private String original_language;
	
	@NotEmpty(message = "Ingrese stock")
	@Column(name = "stock", nullable =false)
	private int stock;
	
	@NotEmpty(message = "Ingrese el número de páginas")
	@Column(name = "pages", nullable =false)
	private int pages;
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
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
