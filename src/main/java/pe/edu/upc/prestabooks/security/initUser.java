package pe.edu.upc.prestabooks.security;

import java.util.Calendar;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pe.edu.upc.prestabooks.entity.Employee;
import pe.edu.upc.prestabooks.entity.User;
import pe.edu.upc.prestabooks.repository.EmployeeRepository;
import pe.edu.upc.prestabooks.repository.UserRepository;
import pe.edu.upc.prestabooks.service.UserService;

@Service
public class initUser implements CommandLineRunner{
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        if(!this.userRepository.findByUsername("admin").isPresent()){
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.addAuthority("ROLE_ADMIN");
            this.userRepository.save(admin);

            User userEmployee = new User();
            userEmployee.setUsername("employee1");
            userEmployee.setPassword("123456");
            
            Employee employee = new Employee();
            employee.setId(userService.registerNewEmployeeAccount(userEmployee).getId());
            employee.setDni("12345678");
            employee.setFirstName("Luis");
            employee.setLastName("Lopez");
            employee.setHireDate(Calendar.getInstance().getTime());
            employee.setPhone("964282430");

            employeeRepository.save(employee);

        }
    }
}