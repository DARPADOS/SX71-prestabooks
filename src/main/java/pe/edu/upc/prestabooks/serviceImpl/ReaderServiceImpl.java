package pe.edu.upc.prestabooks.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import pe.edu.upc.prestabooks.entity.Reader;
import pe.edu.upc.prestabooks.repository.ReaderRepository;
import pe.edu.upc.prestabooks.service.ReaderService;

@Service
public class ReaderServiceImpl implements ReaderService{

    @Autowired
    private ReaderRepository readerRepository;

    @Override
    public JpaRepository<Reader, Integer> getRepository() {
        return readerRepository;
    }

    @Override
    public List<Reader> findByFirstNameOrLastName(String searchTerm) throws Exception {
        return readerRepository.findByFirstNameOrLastName(searchTerm);
    }
    
}
