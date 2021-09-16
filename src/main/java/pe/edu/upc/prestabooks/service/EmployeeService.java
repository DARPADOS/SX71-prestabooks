package pe.edu.upc.prestabooks.service;

import java.util.List;

import pe.edu.upc.prestabooks.entity.Employee;

public interface EmployeeService extends CrudService<Employee, Integer> {
    List<Employee> findByFirstNameLikeOrLastNameLike(String searchTerm) throws Exception;
}
