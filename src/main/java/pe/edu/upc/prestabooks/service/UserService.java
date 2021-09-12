package pe.edu.upc.prestabooks.service;

import pe.edu.upc.prestabooks.entity.User;

public interface UserService {
    User registerNewUserAccount(User user) throws Exception;
    User AddAuthority(User user, String authName) throws Exception;
}
