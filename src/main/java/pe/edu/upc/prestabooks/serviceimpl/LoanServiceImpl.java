package pe.edu.upc.prestabooks.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.prestabooks.entity.Loan;
import pe.edu.upc.prestabooks.repository.LoanRepository;
import pe.edu.upc.prestabooks.service.LoanService;

public class LoanServiceImpl implements LoanService{
	@Autowired
	private LoanRepository loanRepository;

	@Override
	public JpaRepository<Loan, Integer> getRepository() {
		// TODO Auto-generated method stub
		return loanRepository;
	}
}
