package pe.edu.upc.prestabooks.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import pe.edu.upc.prestabooks.entity.DetailAuthorBook;
import pe.edu.upc.prestabooks.repository.DetailAuthorBookRepository;
import pe.edu.upc.prestabooks.service.DetailAuthorBookService;

@Service
public class DetailAuthorBookServiceImpl implements DetailAuthorBookService{
	@Autowired
	private DetailAuthorBookRepository detailAuthorBookRepository;

	@Override
	public JpaRepository<DetailAuthorBook, Integer> getRepository() {
		return detailAuthorBookRepository;
	}

}
