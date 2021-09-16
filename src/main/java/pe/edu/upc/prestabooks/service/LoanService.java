package pe.edu.upc.prestabooks.service;

import pe.edu.upc.prestabooks.entity.Loan;

public interface LoanService extends CrudService<Loan,Integer>{
    void returnLoan(Integer id) throws Exception;
}
