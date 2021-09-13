package pe.edu.upc.prestabooks.entity;

import java.io.Serializable;
import java.util.Objects;

public class DetailAuthorBookId implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Integer author;
    private Integer book;

    public DetailAuthorBookId(Integer author, Integer book) {
        this.author = author;
        this.book = book;
    }

    public DetailAuthorBookId() {
        super();
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public Integer getBook() {
        return book;
    }

    public void setBook(Integer book) {
        this.book = book;
    }

    @Override
	public int hashCode() {
		return Objects.hash(author, book);
	}

    @Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null || getClass() != obj.getClass())
			return false;
		
		DetailAuthorBookId detailAuthorBookId = (DetailAuthorBookId)obj;
		
		if(this.author != detailAuthorBookId.author)
			return false;
		if(this.book != detailAuthorBookId.book)
			return false;
		return true;
	}	
}