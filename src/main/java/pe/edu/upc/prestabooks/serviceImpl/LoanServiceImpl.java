package pe.edu.upc.prestabooks.serviceImpl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import pe.edu.upc.prestabooks.entity.Author;
import pe.edu.upc.prestabooks.entity.Loan;
import pe.edu.upc.prestabooks.repository.LoanRepository;
import pe.edu.upc.prestabooks.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService {
	@Autowired
	private LoanRepository loanRepository;

	@Override
	public JpaRepository<Loan, Integer> getRepository() {
		return loanRepository;
	}

	@Override
	public void returnLoan(Integer id) throws Exception {
		Loan loan = loanRepository.getById(id);
		if (!loan.getReturned()) {
			loan.setReturnDate(Calendar.getInstance().getTime());
			loan.setReturned(true);
			loan.getBook().setStock(loan.getBook().getStock() + 1);
			loan.getBook().getAuthors().add(new Author());
			this.update(loan);
		}
	}

	@Override
	public Loan create(Loan loan) {
		loan.getBook().setStock(loan.getBook().getStock() - 1);
		loan.getBook().getAuthors().add(new Author());
		return loanRepository.save(loan);
	}

	@Override
	public List<Loan> findByDniOrNameOrBook(String searchTerm, Boolean returned) throws Exception {
		return loanRepository.findByDniOrNameOrBook(searchTerm, returned);
	}
	@Override
	public List<String[]> listReport1() {
		// TODO Auto-generated method stub
		return loanRepository.listReport1();
	}
	@Override
	public List<String[]> listReport2() {
		// TODO Auto-generated method stub
		return loanRepository.listReport2();
	}
	@Override
	public List<String[]> listReport3() {
		// TODO Auto-generated method stub
		return loanRepository.listReport3();
	}
	
}
