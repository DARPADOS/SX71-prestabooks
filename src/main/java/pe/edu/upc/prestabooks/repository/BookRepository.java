package pe.edu.upc.prestabooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.prestabooks.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

}
