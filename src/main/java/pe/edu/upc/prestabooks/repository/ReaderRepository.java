package pe.edu.upc.prestabooks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.prestabooks.entity.Reader;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Integer>{
    @Query(value = "select r.* from reader r " +
    "where (UPPER(r.first_name) like UPPER(CONCAT('%', ?1, '%')) or " +
           "UPPER(r.last_name) like UPPER(CONCAT('%', ?1, '%')) or " +
           "UPPER(r.dni) like UPPER(CONCAT('%', ?1, '%'))) and " +
           "r.deleted = false" , nativeQuery = true)
    List<Reader> findByFirstNameOrLastName(String searchTerm);
}
