package pe.edu.upc.prestabooks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.prestabooks.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    @Query(value = "select e.* from employee e " +
    "where (UPPER(e.first_name) like UPPER(CONCAT('%', ?1, '%')) or " +
           "UPPER(e.last_name) like UPPER(CONCAT('%', ?1, '%')) or " +
           "UPPER(e.dni) like UPPER(CONCAT('%', ?1, '%')))" , nativeQuery = true)
    List<Employee> findByFirstNameLikeOrLastNameLike(String searchTerm);
}
