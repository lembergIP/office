package ua.lviv.office.service.serviceImpls;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.office.controller.LoginController;
import ua.lviv.office.entity.Role;
import ua.lviv.office.entity.User;
import ua.lviv.office.repository.UserRepository;
import ua.lviv.office.service.UserService;

import java.sql.Date;
import java.util.*;

@Service("userService")
public class UserServiceImpls implements UserService{


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    final static Logger logger = Logger.getLogger(LoginController.class);

    @Transactional
    public void saveUser(User user) {
        User findUser=userRepository.findByEmail(user.getEmail());
        if(findUser==null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.saveAndFlush(user);
            logger.info("new user "+user.getEmail()+" was successful created");
        }
        else{
            logger.info("user email exist :"+user.getEmail());
        }

    }
    @Transactional
    public User findUserByEmail(String email){
        User user=userRepository.findByEmail(email);
        if(user==null){
            return new User();
        }
        return user;
    }
    @Transactional
    public List<User> findAllUsers(){
        List<User>users=userRepository.findAll(sortByIdEmail());
        if(users!=null) {
            return users;
        }
        return new ArrayList<>();
    }
    private Sort sortByIdEmail() {

        return new Sort(Sort.Direction.ASC, "lastName");
    }

    @Transactional
    public String updateUserInfo(String email,String firstName,String lastName,String phoneNumber,Date dateBirth){
        User findUser=userRepository.findByEmail(email);
        if(findUser!=null){
            findUser.setFirstName(firstName);
            findUser.setLastName(lastName);
            findUser.setPhoneNumber(phoneNumber);
            findUser.setDateBirth(dateBirth);
            userRepository.saveAndFlush(findUser);
            return "success";

        }
        logger.info("cannot update user info because user "+email +" not found");
        return "user not found";

    }

    @Transactional
    public boolean updateUserPassword(String email, String password, String newPassword) {
        User findUser=userRepository.findByEmail(email);
        if(passwordEncoder.matches(password,findUser.getPassword())){
            findUser.setPassword(passwordEncoder.encode(newPassword));
            userRepository.saveAndFlush(findUser);
            logger.info("user "+ email+ "password was successful changed");
            return true;
        }else {
            logger.info("user "+ email+ "password and password user does not match ");
            return false;
        }



    }

    @Transactional
    public void deleteUser(long id){
        userRepository.delete((Long)id);
        logger.info("user with id "+id+"was successful deleted");
    }

    @Transactional
    public User findUserById(long id) {
        User user=userRepository.findOne(id);
        if(user==null) {
            return new User();
        }
        return user;
    }

    @Transactional
    public User findUserByLastName(String lastName){
        User user=userRepository.findByLastName(lastName);
        if(user==null) {
            return new User();
        }
        return user;
    }

    @Transactional
    public void updateUserPhoto(String email, byte[] photo) {
        User findUser=userRepository.findByEmail(email);
        findUser.setImage(photo);
        userRepository.saveAndFlush(findUser);
    }

    @Transactional
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @Transactional(readOnly = true)
    public Set<User> findUsersByRole(Role role) {
        List<User>users=userRepository.findByRole(role);
        Set<User>usersByRole=new HashSet<>(users);
        if(!usersByRole.isEmpty()){
            return usersByRole;
        }
        return new HashSet<>();
    }

    @Transactional(readOnly = true)
    public Set<User> usersNonConfirmedRole() {
        List<User>users=userRepository.findByIsRoleConfirmedFalse();
        Set<User>usersByRole=new HashSet<>(users);
        if(!usersByRole.isEmpty()){
            return usersByRole;
        }
        return new HashSet<>();
    }

    @Transactional
    public void changeUserRole(long id_user, Role new_role) {
     User user=findUserById(id_user);
     if(user!=null){
         user.setRole(new_role);
         userRepository.saveAndFlush(user);
     }
     else
         logger.info("user role does not change because user not found");
    }
}
