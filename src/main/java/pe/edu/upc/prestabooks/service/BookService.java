package pe.edu.upc.prestabooks.service;
import java.util.List;

import pe.edu.upc.prestabooks.entity.Book;

public interface BookService extends CrudService<Book,Integer>{
    List<Book> findByTitleOrAuthorName(String searchTerm) throws Exception;
}
