package ua.lviv.office.controller.office_manager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.lviv.office.service.EventService;
import ua.lviv.office.service.UserService;

@Controller
public class ScheduleController {

    final static Logger logger = Logger.getLogger(ScheduleController.class);


    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/confirmedScheduleByAllUsers",method = RequestMethod.GET)
    public ModelAndView scheduleAllUsers(){
        ModelAndView modelAndView=new ModelAndView("eventIsConfirmedList");
        modelAndView.addObject("events",eventService.findAllEventsIsConfirmedByMonth(true));
        return modelAndView;
    }

    @RequestMapping(value = "/mngs-notConfirmedEvents",method = RequestMethod.GET)
    public ModelAndView notConfirmedEvents(){
        ModelAndView modelAndView=new ModelAndView("eventsList");
        modelAndView.addObject("events",eventService.findAllEventsIsConfirmedByMonth(false));
        return modelAndView;
    }
    @RequestMapping(value = "/mngOf-confirmEvent{id_event}",method = RequestMethod.GET)
    public ModelAndView confirmEvent(@PathVariable long id_event){
        ModelAndView modelAndView =new ModelAndView("redirect:/mngs-notConfirmedEvents");
        eventService.toConfirmEvent(id_event);
        return modelAndView;

    }
    @RequestMapping(value = "/mngOf-notConfirmedCoffeeBreakList",method = RequestMethod.GET)
    public ModelAndView notConfirmedCoffeeBreakList(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("events",eventService.findNotConfirmedCoffeeBreakByWeek());
        return modelAndView;
    }
}
