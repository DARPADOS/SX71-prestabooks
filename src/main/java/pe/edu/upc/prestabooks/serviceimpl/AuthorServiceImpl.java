package pe.edu.upc.prestabooks.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.prestabooks.entity.Author;
import pe.edu.upc.prestabooks.repository.AuthorRepository;
import pe.edu.upc.prestabooks.service.AuthorService;

public class AuthorServiceImpl implements AuthorService{
	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public JpaRepository<Author, Integer> getRepository() {
		// TODO Auto-generated method stub
		return authorRepository;
	}

}
