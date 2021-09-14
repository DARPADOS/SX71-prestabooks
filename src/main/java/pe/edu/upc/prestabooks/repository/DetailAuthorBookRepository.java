package pe.edu.upc.prestabooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.prestabooks.entity.DetailAuthorBook;
import pe.edu.upc.prestabooks.entity.DetailAuthorBookId;

@Repository
public interface DetailAuthorBookRepository extends JpaRepository<DetailAuthorBook, DetailAuthorBookId>{

}
