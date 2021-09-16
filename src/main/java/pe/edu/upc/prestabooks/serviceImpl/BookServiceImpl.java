package pe.edu.upc.prestabooks.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import pe.edu.upc.prestabooks.entity.Book;
import pe.edu.upc.prestabooks.repository.BookRepository;
import pe.edu.upc.prestabooks.service.BookService;

@Service
public class BookServiceImpl implements BookService{
	@Autowired
	private BookRepository bookRepository;
	
	
	@Override
	public JpaRepository<Book, Integer> getRepository() {
		return bookRepository;
	}


	@Override
	public List<Book> findByTitleOrAuthorName(String searchTerm) throws Exception {
		return bookRepository.findByTitleOrAuthorName(searchTerm);
	}

}
