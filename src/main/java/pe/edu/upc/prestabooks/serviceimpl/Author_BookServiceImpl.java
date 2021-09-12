package pe.edu.upc.prestabooks.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.prestabooks.entity.Author_Book;
import pe.edu.upc.prestabooks.repository.Author_BookRepository;
import pe.edu.upc.prestabooks.service.Author_BookService;

public class Author_BookServiceImpl implements Author_BookService{
	@Autowired
	private Author_BookRepository author_bookRepository;

	@Override
	public JpaRepository<Author_Book, Integer> getRepository() {
		// TODO Auto-generated method stub
		return author_bookRepository;
	}

}
