package pe.edu.upc.prestabooks.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.prestabooks.entity.Book;
import pe.edu.upc.prestabooks.repository.BookRepository;
import pe.edu.upc.prestabooks.service.BookService;

public class BookServiceImpl implements BookService{
	@Autowired
	private BookRepository bookRepository;
	
	
	@Override
	public JpaRepository<Book, Integer> getRepository() {
		// TODO Auto-generated method stub
		return bookRepository;
	}

}
