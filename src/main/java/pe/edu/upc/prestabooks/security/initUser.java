package pe.edu.upc.prestabooks.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pe.edu.upc.prestabooks.entity.User;
import pe.edu.upc.prestabooks.repository.UserRepository;

@Service
public class initUser implements CommandLineRunner{
    
    @Autowired
    private UserRepository userRepository;

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
        }
    }
}