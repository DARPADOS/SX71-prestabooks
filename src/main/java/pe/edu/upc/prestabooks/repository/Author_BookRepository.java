package pe.edu.upc.prestabooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.prestabooks.entity.Author_Book;

@Repository
public interface Author_BookRepository extends JpaRepository<Author_Book, Integer>{

}
