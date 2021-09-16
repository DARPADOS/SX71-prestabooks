package pe.edu.upc.prestabooks.service;

import java.util.List;

import pe.edu.upc.prestabooks.entity.Reader;

public interface ReaderService extends CrudService<Reader,Integer>{
    List<Reader> findByFirstNameOrLastName(String searchTerm) throws Exception;
}
