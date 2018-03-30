package ua.lviv.office.controller.employer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.lviv.office.service.EventService;

import java.sql.Date;
import java.time.LocalDateTime;


@Controller

public class ListEventController {

    @Autowired
    private EventService eventService;

    @RequestMapping(method = RequestMethod.GET,value = "/eventsAll")
    public ModelAndView allEvents(){
        ModelAndView modelAndView=new ModelAndView("eventsList");
        modelAndView.addObject("events",eventService.allEvents());
        modelAndView.addObject("flag","true");
        return modelAndView;
    }
    @RequestMapping(value = "/eventsFindBetweenDates",method = RequestMethod.POST)
    public ModelAndView findEventsBetweenDates(@RequestParam("start") Date start,
                                               @RequestParam("finish") Date finish){
        ModelAndView modelAndView=new ModelAndView("eventsList");
        modelAndView.addObject("flag","true");
        modelAndView.addObject("events",eventService.findEventsBetweenDates(start, finish));
        return modelAndView;
    }
    @RequestMapping(value = "/eventsFindBetweenDatesByUser",method = RequestMethod.POST)
    public ModelAndView findEventsBetweenDatesByUser(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                                     @RequestParam("finish") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finish, @RequestParam("userId") long userId){
        ModelAndView modelAndView=new ModelAndView("eventsList");
        modelAndView.addObject("flag","true");
        modelAndView.addObject("events",eventService.findEventsBetweenDatesByUser(start,finish,userId));
        return modelAndView;
    }

}
