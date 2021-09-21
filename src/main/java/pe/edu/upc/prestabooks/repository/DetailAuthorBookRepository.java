package pe.edu.upc.prestabooks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.prestabooks.entity.Book;
import pe.edu.upc.prestabooks.entity.DetailAuthorBook;
import pe.edu.upc.prestabooks.entity.DetailAuthorBookId;

@Repository
public interface DetailAuthorBookRepository extends JpaRepository<DetailAuthorBook, DetailAuthorBookId>{

    @Modifying
    @Query(value = "delete from detail_author_book d where d.book_id=?1", nativeQuery = true)
    public void deleteByBookId(Integer id);

    public List<DetailAuthorBook> findByBook(Book book);
}
