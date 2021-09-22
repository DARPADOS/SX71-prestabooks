package pe.edu.upc.prestabooks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.prestabooks.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {
	@Query(value = "select l.* from loan l " + "join book  b on b.id = l.book_id "
			+ "join reader r on r.id = l.reader_id " + "where l.returned = ?2 and "
			+ "(UPPER(b.title) like UPPER(CONCAT('%', ?1, '%')) or "
			+ "UPPER(r.first_name) like UPPER(CONCAT('%', ?1, '%')) or "
			+ "UPPER(r.last_name) like UPPER(CONCAT('%', ?1, '%')) or "
			+ "UPPER(r.dni) like UPPER(CONCAT('%', ?1, '%'))) " + "order by l.loan_date DESC", nativeQuery = true)
	List<Loan> findByDniOrNameOrBook(String searchTerm, Boolean returned) throws Exception;

	@Query(value = "select u.user_id, concat(e.first_name,' ',e.last_name), count(l.id) from users u join loan l on l.user_id=u.user_id join employee e on e.id=u.user_id where u.user_id!=1 group by u.user_id, concat(e.first_name,' ',e.last_name) order by count(l.id) desc", nativeQuery = true)
	public List<String[]> listReport1();

	@Query(value = "select b.title, count(l.id) from book b join loan l on l.book_id=b.id group by b.title order by count(l.id) desc", nativeQuery = true)
	public List<String[]> listReport2();

	@Query(value = "select r.id, concat(r.first_name,' ', r.last_name), count(l.id) from reader r join loan l on l.reader_id=r.id group by r.id,concat(r.first_name,' ', r.last_name) order by count(l.id) desc", nativeQuery = true)
	public List<String[]> listReport3();
}
