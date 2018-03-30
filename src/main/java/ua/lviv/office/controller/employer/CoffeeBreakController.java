package ua.lviv.office.controller.employer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.lviv.office.controller.manager.ManagerEventController;
import ua.lviv.office.entity.Event;
import ua.lviv.office.entity.Type;
import ua.lviv.office.entity.User;
import ua.lviv.office.service.EventService;
import ua.lviv.office.service.UserService;
import ua.lviv.office.util.DateValidate;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Controller
public class CoffeeBreakController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    final static Logger logger = Logger.getLogger(CoffeeBreakController.class);

    @RequestMapping(value = "/createCoffeeBreak",method = RequestMethod.GET)
    public ModelAndView createCoffeeBreakPage(){
        ModelAndView modelAndView=new ModelAndView("createCoffeeBreak");
        modelAndView.addObject("date", DateValidate.isStartDateBefore(LocalDateTime.of(LocalDate.now().minusDays(7), LocalTime.of(23,59))));
        return modelAndView;
    }

    @RequestMapping(value = "/createCoffeeBreak",method = RequestMethod.POST)
    public ModelAndView createCoffeeBreak(Principal principal, @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
                                          @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime,
                                          String description){
        ModelAndView modelAndView=new ModelAndView();
        User user=userService.findUserByEmail(principal.getName());
        if(DateValidate.isStartDateBefore(startTime)==true){
            modelAndView.addObject("error","event can not be created in the past");
            modelAndView.setViewName("eventUpdate");
            logger.info("DateValidate.isStartDateBefore(startTime) "+DateValidate.isStartDateBefore(startTime));
        }

        if(DateValidate.defferenceBetweeneLocalTimeInMinuts(startTime, endTime)<=0){
            modelAndView.addObject("error","Please enter the correct date ");
            modelAndView.setViewName("eventUpdate");
            logger.info("defferenceBetweeneLocalTimeInMinuts(startTime, endTime) <=0");
        }
        if((DateValidate.dateAndTimeValidatorInEvent(startTime,endTime)==true) &&
                (DateValidate.defferenceBetweeneLocalTimeInMinuts(startTime, endTime)<=60)) {
            Event event = new Event();
            event.setConfirmed(false);
            event.setTimeFrom(startTime);
            event.setTimeEnd(endTime);
            event.setDescription(description);
            event.setType(Type.COFFEE_BREAK);
            event.setConfirmed(false);
            event.setDirection(DateValidate.defferenceBetweeneLocalTimeInMinuts(startTime, endTime));
            eventService.createEvent(event);
            Set<User> userSet=new HashSet<>();
            userSet.add(user);
            event.setUsers(userSet);
            eventService.updateEvent(event);
            modelAndView.setViewName("redirect:/scheduleUserByWeek"+user.getId());
        }
        return modelAndView;

    }
}
