package pe.edu.upc.prestabooks.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.prestabooks.entity.Author;
import pe.edu.upc.prestabooks.entity.Book;
import pe.edu.upc.prestabooks.entity.DetailAuthorBook;
import pe.edu.upc.prestabooks.entity.DetailAuthorBookId;
import pe.edu.upc.prestabooks.repository.DetailAuthorBookRepository;
import pe.edu.upc.prestabooks.service.DetailAuthorBookService;

@Service
public class DetailAuthorBookServiceImpl implements DetailAuthorBookService{
	@Autowired
	private DetailAuthorBookRepository detailAuthorBookRepository;

	@Override
	public JpaRepository<DetailAuthorBook, DetailAuthorBookId> getRepository() {
		return detailAuthorBookRepository;
	}

	@Override
	public void addAuthorsWithBook(Book book, List<Author> authors) throws Exception{
		for (Author author : authors) {
			this.create(new DetailAuthorBook(author, book));
		}		
	}

	@Override
	@Transactional
	public void updateAuthorsWithBook(Book book, List<Author> authors) throws Exception {
		if(!detailAuthorBookRepository.findByBook(book).isEmpty()){
			detailAuthorBookRepository.deleteByBookId(book.getId());
		}
		for (Author author : authors) {
			this.create(new DetailAuthorBook(author, book));
		}	
	}

}
