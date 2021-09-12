package pe.edu.upc.prestabooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.prestabooks.entity.Reader;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Integer>{

}
