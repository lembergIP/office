package ua.lviv.office.controller.employer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.lviv.office.service.EventService;
import ua.lviv.office.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Controller
@Secured("{ \"EMPLOYER\", \"MANAGER\", \"OFFICE_MANAGER\", \"ADMINISTRATOR\", \"ACCOUNTANT\" }")
public class UserScheduleController {


    @Autowired
    EventService eventService;

    @RequestMapping(value = "/scheduleUserByWeek{user_id}",method = RequestMethod.GET)
    public ModelAndView scheduleUserByWeek(@PathVariable long user_id){
        ModelAndView modelAndView=new ModelAndView("eventsList");
            modelAndView.addObject("flag","true");
            LocalTime startLocalTime = LocalTime.of(0, 0);
            LocalDateTime startDateTime = LocalDateTime.of(LocalDate.now(), startLocalTime);
            LocalDateTime endDateTime = LocalDateTime.of(LocalDate.now().plusDays(7), LocalTime.of(23, 59));
            modelAndView.addObject("events", eventService.findEventsBetweenDatesByUser(startDateTime, endDateTime, user_id));

        return modelAndView;
    }
}
