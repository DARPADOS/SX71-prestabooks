package pe.edu.upc.prestabooks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.prestabooks.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer>{
    @Query(value ="select l.* from loan l " +
    "join book  b on b.id = l.book_id " +
    "join reader r on r.id = l.reader_id " +
    "where l.returned = ?2 and " + 
        "(UPPER(b.title) like UPPER(CONCAT('%', ?1, '%')) or " +
        "UPPER(r.first_name) like UPPER(CONCAT('%', ?1, '%')) or " + 
        "UPPER(r.last_name) like UPPER(CONCAT('%', ?1, '%')) or " +
        "UPPER(r.dni) like UPPER(CONCAT('%', ?1, '%'))) " +
    "order by l.loan_date DESC", nativeQuery = true)
    List<Loan> findByDniOrNameOrBook(String searchTerm, Boolean returned) throws Exception;
}
