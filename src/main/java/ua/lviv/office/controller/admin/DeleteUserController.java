package ua.lviv.office.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.lviv.office.service.UserService;

@Controller
@RequestMapping(value = "/deleteUser{id}")
public class DeleteUserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String deleteUser(@PathVariable long id){
        userService.deleteUser(id);
        return "redirect:/userList";
    }
}
