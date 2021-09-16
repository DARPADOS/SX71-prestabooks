package pe.edu.upc.prestabooks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.prestabooks.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
    @Query(value = "select b.* from book b " +
    "join detail_author_book det on b.id = det.book_id " +
    "join author a on a.id = det.author_id " +
    "where (UPPER(b.title) like UPPER(CONCAT('%', ?1, '%')) or " + 
           "UPPER(a.first_name) like UPPER(CONCAT('%', ?1, '%')) or " +
           "UPPER(a.last_name) like UPPER(CONCAT('%', ?1, '%'))) " +
    "GROUP BY b.id", nativeQuery = true)
    List<Book> findByTitleOrAuthorName(String searchTerm);
}
