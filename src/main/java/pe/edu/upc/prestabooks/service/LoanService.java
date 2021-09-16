package pe.edu.upc.prestabooks.service;

import java.util.List;

import pe.edu.upc.prestabooks.entity.Loan;

public interface LoanService extends CrudService<Loan,Integer>{
    void returnLoan(Integer id) throws Exception;
    List<Loan> findByDniOrNameOrBook(String searchTerm, Boolean returned) throws Exception;
}
