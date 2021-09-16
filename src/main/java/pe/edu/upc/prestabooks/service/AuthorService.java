package pe.edu.upc.prestabooks.service;
import java.util.List;

import pe.edu.upc.prestabooks.entity.Author;

public interface AuthorService extends CrudService<Author,Integer>{
    List<Author> findByFirstnameOrLastName(String searchTerm) throws Exception;
}
