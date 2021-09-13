package pe.edu.upc.prestabooks.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pe.edu.upc.prestabooks.entity.User;
import pe.edu.upc.prestabooks.repository.UserRepository;
import pe.edu.upc.prestabooks.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

    @Override
    public User registerNewEmployeeAccount(User user) throws Exception {
        String encodedPass = passwordEncoder.encode(user.getPassword());

		User newUser = new User();

		newUser.setPassword(encodedPass);
        newUser.setUsername(user.getUsername());
        newUser.addAuthority("ROLE_EMPLOYEE");
        return userRepository.save(newUser);
    }

    @Override
    public User AddAuthority(User user, String authName) throws Exception {
        user.addAuthority(authName);
        return userRepository.save(user);
    }
    
}
