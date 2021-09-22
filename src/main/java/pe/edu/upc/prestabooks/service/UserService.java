package pe.edu.upc.prestabooks.service;

import pe.edu.upc.prestabooks.entity.User;

public interface UserService {
    User registerNewEmployeeAccount(User user) throws Exception;
    User AddAuthority(User user, String authName) throws Exception;
    Boolean isUsernameExist(User user) throws Exception;
}
