package ua.lviv.office.controller.admin;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import ua.lviv.office.controller.LoginController;
import ua.lviv.office.entity.Role;
import ua.lviv.office.entity.User;
import ua.lviv.office.service.UserService;

import java.sql.Date;

@Controller
@RequestMapping("/adm-registration")
@SessionAttributes("roleList")
public class RegistrationController {

    final static Logger logger = Logger.getLogger(RegistrationController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView registrationPage(){
        ModelAndView modelAndView=new ModelAndView("userRegistration");
        modelAndView.addObject("roleList", Role.values());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView registration(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email,
                                     @RequestParam String password, @RequestParam Date dateBirth, @RequestParam String phoneNumber, @RequestParam String role){
        ModelAndView model=new ModelAndView();
        User user=userService.findUserByEmail(email);
        if(user.getEmail()==null) {
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(password);
            user.setRole(Role.valueOf(role));
            user.setFirstName(firstName);
            user.setDateBirth(dateBirth);
            user.setPhoneNumber(phoneNumber);
            user.setRoleConfirmed(false);
            userService.saveUser(user);
            model.setViewName("redirect:/userList");

            return model;
        }
        else{
            model.addObject("error", "email exist,please try another");
            model.setViewName("userRegistration");
            return model;
        }
    }
}
