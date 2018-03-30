package ua.lviv.office.controller.employer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.lviv.office.service.UserService;

@Controller
public class FindUserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/findUserById{userId}")
    public ModelAndView findUserById(@PathVariable long userId){
        ModelAndView modelAndView =new ModelAndView("userPage");
        modelAndView.addObject("foundUser",userService.findUserById(userId));
        return modelAndView;
    }
    @RequestMapping(value = "/findUserByEmail")
    public ModelAndView findUserById(@RequestParam String findUserByEmail){
        ModelAndView modelAndView =new ModelAndView("userPage");
        modelAndView.addObject("foundUser",userService.findUserByEmail(findUserByEmail));
        return modelAndView;
    }

    @RequestMapping(value = "/findUserByLastName")
    public ModelAndView findUserByLastName(@RequestParam String findUserByLastName){
        ModelAndView modelAndView =new ModelAndView("userPage");
        modelAndView.addObject("foundUser",userService.findUserByLastName(findUserByLastName));
        return modelAndView;
    }
}
