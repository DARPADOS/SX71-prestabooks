package pe.edu.upc.prestabooks.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Detail_Author_Book")
@IdClass(value = DetailAuthorBookId.class)
public class DetailAuthorBook {
	
	@Id
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "author_id", nullable = false)
	private Author author;
	
	@Id
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "book_id", nullable = false)
	private Book book;
	
	public DetailAuthorBook() {
		super();
	}

	public DetailAuthorBook(Author author, Book book) {
		super();
		this.author = author;
		this.book = book;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((book == null) ? 0 : book.hashCode());
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
			DetailAuthorBook other = (DetailAuthorBook) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		return true;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
}
