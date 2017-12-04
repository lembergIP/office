package ua.lviv.office.controller;





import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import ua.lviv.office.entity.Type;
import ua.lviv.office.entity.User;
import ua.lviv.office.service.UserService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Controller
@RequestMapping(value = {"/profile"})
@SessionAttributes({"correctUser","type"})
public class ProfileController {
    @Autowired
    private UserService userService;

    final static Logger logger = Logger.getLogger(LoginController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView home(Principal principal){
        ModelAndView modelAndView =new ModelAndView("profile");
        User user=userService.findUserByEmail(principal.getName());
        modelAndView.addObject("correctUser",user);
        modelAndView.addObject("type", Type.values());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        logger.info("success profile page load by user id :"+user.getId()+" date : "+ LocalDateTime.now().format(formatter));

        return modelAndView;
    }
}
