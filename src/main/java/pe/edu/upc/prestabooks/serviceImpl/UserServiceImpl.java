package pe.edu.upc.prestabooks.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import pe.edu.upc.prestabooks.entity.User;
import pe.edu.upc.prestabooks.repository.UserRepository;
import pe.edu.upc.prestabooks.service.UserService;

public class UserServiceImpl implements UserService {

    @Autowired
	private UserRepository userRepository;

	//@Autowired
	//private PasswordEncoder passwordEncoder;

    @Override
    public User registerNewUserAccount(User user) throws Exception {
        //String encodedPass = passwordEncoder.encode(user.getPassword());

		User newUser = new User();

		//newUser.setPassword(encodedPass);
        newUser.setUsername(user.getUsername());
        return userRepository.save(newUser);
    }

    @Override
    public User AddAuthority(User user, String authName) throws Exception {
        user.addAuthority(authName);
        return userRepository.save(user);
    }
    
}
