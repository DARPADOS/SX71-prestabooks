package pe.edu.upc.prestabooks.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pe.edu.upc.prestabooks.entity.User;
import pe.edu.upc.prestabooks.repository.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> search = this.userRepository.findByUsername(username);

        if(search.isPresent()){
            UserDetails userDetails = new MyUserDetails(search.get());
            return userDetails;
        }
        throw new UsernameNotFoundException("Invalid User");
    }
    
}
