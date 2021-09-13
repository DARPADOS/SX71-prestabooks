package pe.edu.upc.prestabooks.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import pe.edu.upc.prestabooks.entity.Loan;
import pe.edu.upc.prestabooks.repository.LoanRepository;
import pe.edu.upc.prestabooks.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService{
	@Autowired
	private LoanRepository loanRepository;

	@Override
	public JpaRepository<Loan, Integer> getRepository() {
		return loanRepository;
	}
}
