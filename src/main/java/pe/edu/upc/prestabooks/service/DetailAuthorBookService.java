package pe.edu.upc.prestabooks.service;

import java.util.List;

import pe.edu.upc.prestabooks.entity.Author;
import pe.edu.upc.prestabooks.entity.Book;
import pe.edu.upc.prestabooks.entity.DetailAuthorBook;
import pe.edu.upc.prestabooks.entity.DetailAuthorBookId;

public interface DetailAuthorBookService extends CrudService<DetailAuthorBook,DetailAuthorBookId> {
    
    void addAuthorsWithBook(Book book, List<Author> authors) throws Exception;

    void updateAuthorsWithBook(Book newBook, List<Author> authors) throws Exception;
    
}
