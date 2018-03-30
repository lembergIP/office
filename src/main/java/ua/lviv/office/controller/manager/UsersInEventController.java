package ua.lviv.office.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.lviv.office.entity.User;
import ua.lviv.office.service.EventService;
import ua.lviv.office.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Controller
public class UsersInEventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @RequestMapping(value ="/usersInEvent{id_event}", method = RequestMethod.GET)
    public ModelAndView usersInEvent(@PathVariable long id_event){
        ModelAndView modelAndView=new ModelAndView("usersInEvent");
        modelAndView.addObject("users",eventService.findAllUsersInEvent(id_event));
        modelAndView.addObject("eventID",id_event);
        return modelAndView;
    }
    @RequestMapping(value = "/mngs-addUsersInEvent{id_event}",method = RequestMethod.GET)
    public ModelAndView updateUsersInEventPage(@PathVariable long id_event){

        ModelAndView modelAndView=new ModelAndView("eventAddUsers");
        modelAndView.addObject("eventID",id_event);
        modelAndView.addObject("users",eventService.usersWhoAreNotInEvent(id_event));
        return modelAndView;
    }

    @RequestMapping(value = "/mngs-addUsersInEvent{id_event}",method = RequestMethod.POST)
    public ModelAndView updateUsersInEvent(HttpServletRequest request, @PathVariable long id_event){
        ModelAndView modelAndView=new ModelAndView("redirect:/usersInEvent"+id_event);

        String[] users=request.getParameterValues("userEmail");

        Set<User> addUsers=new HashSet<>();
        for (String u: users
                ) {
            addUsers.add(userService.findUserByEmail(u));
        }
        eventService.addUsersInEvent(id_event,addUsers);
        return modelAndView;
    }
@RequestMapping(value = "/mngs-deleteUser{id_user}FromEvent{id_event}",method = RequestMethod.GET)
    public ModelAndView deleteUserFromEvent (@PathVariable long id_user, @PathVariable long id_event){
        ModelAndView modelAndView=new ModelAndView("redirect:/usersInEvent"+id_event);
        eventService.deleteUserFromEvent(id_event,id_user);
        return modelAndView;
}
}
