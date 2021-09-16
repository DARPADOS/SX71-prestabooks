package pe.edu.upc.prestabooks.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import pe.edu.upc.prestabooks.entity.Employee;
import pe.edu.upc.prestabooks.repository.EmployeeRepository;
import pe.edu.upc.prestabooks.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public JpaRepository<Employee, Integer> getRepository() {
        return employeeRepository;
    }
}
