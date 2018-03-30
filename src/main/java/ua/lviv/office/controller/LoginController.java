package ua.lviv.office.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = {"/","/login"})
public class LoginController {

    final static Logger logger = Logger.getLogger(LoginController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error){
        ModelAndView modelAndView=new ModelAndView("login");
        if (error != null) {
            modelAndView.addObject("error", "Invalid username or password!");
        }
        logger.info("login page");
        return modelAndView;
    }
}
