package pe.edu.upc.prestabooks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.prestabooks.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>{
    @Query(value = "select a.* from author a " +
    "where (UPPER(a.first_name) like UPPER(CONCAT('%', ?1, '%')) or " +
           "UPPER(a.last_name) like UPPER(CONCAT('%', ?1, '%')))", nativeQuery = true)
    List<Author> findByFirstNameLikeOrLastNameLike(String searchTerm);
}
