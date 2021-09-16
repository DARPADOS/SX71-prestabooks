package pe.edu.upc.prestabooks.security;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pe.edu.upc.prestabooks.entity.Author;
import pe.edu.upc.prestabooks.entity.Book;
import pe.edu.upc.prestabooks.entity.Employee;
import pe.edu.upc.prestabooks.entity.Reader;
import pe.edu.upc.prestabooks.entity.User;
import pe.edu.upc.prestabooks.repository.EmployeeRepository;
import pe.edu.upc.prestabooks.repository.ReaderRepository;
import pe.edu.upc.prestabooks.repository.UserRepository;
import pe.edu.upc.prestabooks.service.AuthorService;
import pe.edu.upc.prestabooks.service.BookService;
import pe.edu.upc.prestabooks.service.DetailAuthorBookService;
import pe.edu.upc.prestabooks.service.UserService;

@Service
public class initUserDB implements CommandLineRunner{
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private DetailAuthorBookService detailAuthorBookService;

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
            userEmployee.addAuthority("ROLE_EMPLOYEE");
            
            Employee employee = new Employee();
            employee.setId(userService.registerNewEmployeeAccount(userEmployee).getId());
            employee.setDni("12345678");
            employee.setFirstName("Luis");
            employee.setLastName("Lopez");
            employee.setHireDate(Calendar.getInstance().getTime());
            employee.setPhone("964282430");

            employeeRepository.save(employee);


            Reader reader = new Reader();
            reader.setDni("12345678");
            reader.setFirst_name("Emilio");
            reader.setLast_name("Montecarlo");
            reader.setEmail("emilio123@gmail.com");
            reader.setAddress("calle siempre viva");

            readerRepository.save(reader);

            Author author = new Author();
            author.setFirst_name("Stephen");
            author.setLast_name("King");
            author.setNationality("Estados Unidos");
            authorService.create(author);


            Book book = new Book();
            book.setTitle("It");
            book.setOriginal_language("Ingles");
            book.setPages(123);
            book.setStock(2);
            book.getAuthors().add(author);

            
            detailAuthorBookService.addAuthorsWithBook(bookService.create(book), book.getAuthors());
        }
    }
}