package ua.lviv.office.controller.manager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.lviv.office.service.UserService;

@Controller
public class RoleNotConfirmedListController {

    final static Logger logger = Logger.getLogger(RoleNotConfirmedListController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/mng-roleNotConfirmedList",method = RequestMethod.GET)
    public ModelAndView roleNotConfirmedList(){
        ModelAndView modelAndView=new ModelAndView("usersList");
        modelAndView.addObject("usersList",userService.usersNonConfirmedRole());
        return modelAndView;
    }
    @RequestMapping(value = "/mng-confirmUserRolebyUser{id_user}",method = RequestMethod.POST)
    public ModelAndView confirmUserRolebyUser(@PathVariable long id_user){
        ModelAndView modelAndView=new ModelAndView("usersList");
        modelAndView.addObject("usersList",userService.usersNonConfirmedRole());
        return modelAndView;
    }
}
