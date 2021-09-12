package pe.edu.upc.prestabooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.prestabooks.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Integer>{
    
}
