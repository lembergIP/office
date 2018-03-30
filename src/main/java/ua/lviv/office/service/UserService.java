package ua.lviv.office.service;


import ua.lviv.office.entity.Role;
import ua.lviv.office.entity.User;

import java.sql.Date;
import java.util.List;
import java.util.Set;

public interface UserService {
    void saveUser(User user);
    User findUserByEmail(String email);
    List<User> findAllUsers();
    String updateUserInfo(String email, String firstName, String lastName, String phoneNumber, Date dateBirth);
    boolean updateUserPassword(String email, String password, String newPassword);
    void deleteUser(long id);
    User findUserById(long id);
    User findUserByLastName(String lastName);
    void updateUserPhoto(long id_user,byte[] image);
    void deleteAllUsers();
    Set<User> findUsersByRole(Role role);
    Set<User> usersNonConfirmedRole();
    void changeUserRole(long id_user,Role new_role);
    void confirmUserRoleTrue(long id_user);

}
