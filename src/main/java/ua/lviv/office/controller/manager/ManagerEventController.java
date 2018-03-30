package ua.lviv.office.controller.manager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.lviv.office.entity.Event;
import ua.lviv.office.entity.Type;
import ua.lviv.office.service.EventService;
import ua.lviv.office.util.DateValidate;

import java.time.LocalDateTime;

@Controller
public class ManagerEventController {

    final static Logger logger = Logger.getLogger(ManagerEventController.class);

    @Autowired
    private EventService eventService;


    @RequestMapping(method = RequestMethod.GET,value = "/mngs-createEvent")
    public ModelAndView createRequestEventPage(){
        ModelAndView modelAndView=new ModelAndView("eventCreate");
        modelAndView.addObject("type", Type.values());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/mngs-createEvent")
    public ModelAndView createRequestEvent(@RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
                                       @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime,
                                       @RequestParam String type, String description){
        ModelAndView modelAndView=new ModelAndView();
        logger.info("------------------------------------------------start time : "+startTime);
        logger.info("------------------------------------------------end time : "+endTime);
        logger.info("DateValidate.defferenceBetweeneLocalTimeInMinuts(startTime, endTime) : "+DateValidate.defferenceBetweeneLocalTimeInMinuts(startTime, endTime));
        if(DateValidate.isStartDateBefore(startTime)==true){
            modelAndView.addObject("error","event can not be created in the past");
            modelAndView.setViewName("eventCreate");
            logger.info("DateValidate.isStartDateBefore(startTime) "+DateValidate.isStartDateBefore(startTime));
            return modelAndView;
        }
        else if(DateValidate.defferenceBetweeneLocalTimeInMinuts(startTime, endTime)<=0){
            modelAndView.addObject("error","Please enter the correct date ");
            modelAndView.setViewName("eventCreate");
            logger.info("defferenceBetweeneLocalTimeInMinuts(startTime, endTime) <=0");
            return modelAndView;
        }

       else if(DateValidate.defferenceBetweeneLocalTimeInMinuts(startTime, endTime)>0) {
            Event event = new Event();
            event.setConfirmed(false);
            event.setTimeFrom(startTime);
            event.setTimeEnd(endTime);
            event.setDescription(description);
            event.setType(Type.valueOf(type));
            event.setDirection(DateValidate.defferenceBetweeneLocalTimeInMinuts(startTime, endTime));
            eventService.createEvent(event);
            modelAndView.setViewName("redirect:/mngs-addUsersInEvent"+event.getId());}
        return modelAndView;
    }

    @RequestMapping(value = "/mngs-deleteEvent{id_event}",method = RequestMethod.GET)
    public String deleteEvent(@PathVariable long id_event){
        eventService.deleteEvent(id_event);
        return "redirect:/eventsAll";
    }

    @RequestMapping(value = "/mngs-updateEvent{id_event}",method = RequestMethod.GET)
    public ModelAndView updateEventPage(@PathVariable long id_event){
        ModelAndView modelAndView=new ModelAndView("eventUpdate");
        Event event=eventService.findEvenById(id_event);
        modelAndView.addObject("event",event);
        modelAndView.addObject("type", Type.values());
        return modelAndView;
    }

    @RequestMapping(value = "/mngs-updateEvent{id_event}",method = RequestMethod.POST)
    public ModelAndView updateEvent(@PathVariable long id_event,@RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
                                    @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime,
                                    @RequestParam String type, String description){
        ModelAndView modelAndView=new ModelAndView();
        Event event=eventService.findEvenById(id_event);
        modelAndView.addObject("event",event);
        modelAndView.addObject("type", Type.values());
        logger.info("------------------------------------------------start time : "+startTime);
        logger.info("------------------------------------------------end time : "+endTime);
        logger.info("DateValidate.defferenceBetweeneLocalTimeInMinuts(startTime, endTime) : "+DateValidate.defferenceBetweeneLocalTimeInMinuts(startTime, endTime));
        if(DateValidate.isStartDateBefore(startTime)==true){
            modelAndView.addObject("error","event can not be created in the past");
            modelAndView.setViewName("eventUpdate");
            logger.info("DateValidate.isStartDateBefore(startTime) "+DateValidate.isStartDateBefore(startTime));
            return modelAndView;
        }

        else if(DateValidate.defferenceBetweeneLocalTimeInMinuts(startTime, endTime)<=0){
            modelAndView.addObject("error","Please enter the correct date ");
            modelAndView.setViewName("eventUpdate");
            logger.info("defferenceBetweeneLocalTimeInMinuts(startTime, endTime) <=0");
            return modelAndView;
        }
        else if(DateValidate.defferenceBetweeneLocalTimeInMinuts(startTime, endTime)>0) {
            event.setTimeFrom(startTime);
            event.setTimeEnd(endTime);
            event.setDescription(description);
            event.setType(Type.valueOf(type));
            event.setDirection(DateValidate.defferenceBetweeneLocalTimeInMinuts(startTime, endTime));
            eventService.createEvent(event);
            logger.info("Event was successful updated");
            modelAndView.setViewName("redirect:/mngs-updateEvent"+id_event);
        }
        return modelAndView;
    }
}
