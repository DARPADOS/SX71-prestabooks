package pe.edu.upc.prestabooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.prestabooks.entity.DetailAuthorBook;

@Repository
public interface DetailAuthorBookRepository extends JpaRepository<DetailAuthorBook, Integer>{

}
