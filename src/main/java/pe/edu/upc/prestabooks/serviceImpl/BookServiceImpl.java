package pe.edu.upc.prestabooks.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import pe.edu.upc.prestabooks.entity.Book;
import pe.edu.upc.prestabooks.entity.DetailAuthorBook;
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

	@Override
	public Optional<Book> findById(Integer id){
		Optional<Book> book = bookRepository.findById(id);
		if(book.isPresent()){
			for (DetailAuthorBook detail : book.get().getDetailAuthorBooks()) {
				book.get().getAuthors().add(detail.getAuthor());
			}
		}

		return book;
	}

}
