package ua.lviv.office.controller.employer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.lviv.office.service.UserService;

@Controller
@RequestMapping(value = "/userList")
public class UserListController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView usersList(){
        ModelAndView modelAndView=new ModelAndView("usersList");
        modelAndView.addObject("usersList",userService.findAllUsers());
        return modelAndView;
    }

}
