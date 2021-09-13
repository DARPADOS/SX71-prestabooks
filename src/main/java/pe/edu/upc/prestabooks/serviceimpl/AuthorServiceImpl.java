package pe.edu.upc.prestabooks.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import pe.edu.upc.prestabooks.entity.Author;
import pe.edu.upc.prestabooks.repository.AuthorRepository;
import pe.edu.upc.prestabooks.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService{
	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public JpaRepository<Author, Integer> getRepository() {
		return authorRepository;
	}

}
