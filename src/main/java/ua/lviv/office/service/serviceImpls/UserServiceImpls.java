package ua.lviv.office.service.serviceImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.office.entity.User;
import ua.lviv.office.repository.UserRepository;
import ua.lviv.office.service.UserService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpls implements UserService{


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void saveUser(User user) {
        User findUser=userRepository.findByEmail(user.getEmail());
        if(findUser==null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.saveAndFlush(user);

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
        return "user not found";
    }

    @Override
    public boolean updateUserPassword(String email, String password, String newPassword) {
        User findUser=userRepository.findByEmail(email);
        if(passwordEncoder.matches(password,findUser.getPassword())){
            findUser.setPassword(passwordEncoder.encode(newPassword));
            userRepository.saveAndFlush(findUser);
            return true;
        }else {
            return false;
        }



    }

    @Transactional
    public void deleteUser(long id){
        userRepository.delete((Long)id);
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

    @Override
    public void updateUserPhoto(String email, byte[] photo) {
        User findUser=userRepository.findByEmail(email);
        findUser.setImage(photo);
        userRepository.saveAndFlush(findUser);
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}
