package ua.lviv.office.service.serviceImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.office.entity.Event;
import ua.lviv.office.entity.Type;
import ua.lviv.office.entity.User;
import ua.lviv.office.repository.EventRepository;
import ua.lviv.office.repository.UserRepository;
import ua.lviv.office.service.EventService;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;


@Service(value = "eventService")
public class EventServiceImpls implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventService eventService;


    @Transactional
    public void createEvent(Event event)
    {
       eventRepository.saveAndFlush(event);

    }
    @Transactional
    public void addUsersInEvent(long id_event,Set<User> users){
        Event event=eventRepository.findOne(id_event);
        Set<User> userSet=new HashSet<>();
        userSet.addAll(eventService.findAllUsersInEvent(id_event));
        userSet.addAll(users);
        event.setUsers(userSet);
        eventRepository.saveAndFlush(event);
    }
    @Transactional
    public void deleteUserFromEvent(long id_event,long id_user){
        User user=userRepository.findOne(id_user);
        Event event=eventRepository.findOne(id_event);
        event.getUsers().remove(user);
        eventRepository.saveAndFlush(event);

    }
    @Transactional
    public void updateEvent(Event event){
        eventRepository.saveAndFlush(event);
    }

    @Transactional
    public List<Event> allEvents() {

        return eventRepository.findAll(sortByEventStart());
    }

    @Transactional
    public void deleteEvent(long id) {
        eventRepository.delete(id);
    }

    @Transactional
    public Event findEvenById(long id_event) {
        if(eventRepository.findOne(id_event)==null){
            return new Event();
        }
        return eventRepository.findOne(id_event);
    }

    private Sort sortByEventStart() {

        return new Sort(Sort.Direction.ASC, "timeFrom");
    }
    @Transactional
    public Set<User> findAllUsersInEvent(long id_event){
        Set<User> users=eventRepository.getUsersByEventId(id_event);
        if(users==null){
        return new HashSet<>();
                       }
        return users;
    }
    @Transactional
    public Set<Event> findEventsBetweenDates(Date start, Date finish){
        LocalDate startDate=start.toLocalDate();
        LocalDate finishDate=finish.toLocalDate();
        LocalTime startTime=LocalTime.of(0,0);
        LocalTime finishTime=LocalTime.of(23,59);
Set<Event>events=eventRepository.findByTimeFromBetween(LocalDateTime.of(startDate,startTime), LocalDateTime.of(finishDate,finishTime));
    if(events==null){
                    return new HashSet<>();
                    }

                    return events;
    }
    @Transactional
    public Set<Event> findEventsBetweenDatesByUser(LocalDateTime start, LocalDateTime finish, long id){

        Set<Event>events=eventRepository.getEventsByUserIdAndTimeFromBetween(start, finish,id);
        if(events==null){
            return new HashSet<>();
        }

        return events;
    }

    @Transactional
    public List<User> usersWhoAreNotInEvent(long id_event){
        List<User> allUsers=userRepository.findAll(sortByUserId());
        List<User> usersInEvent=new ArrayList<User>(eventService.findAllUsersInEvent(id_event));
        for (User u:usersInEvent
             ) {
            allUsers.remove(u);

        }

        return  allUsers;
    }

    @Transactional
    public Set<Event> findIsConfirmedEvents(boolean isConfirmed) {
        Set<Event>events=eventRepository.findByIsConfirmed(isConfirmed);
        if(events.isEmpty()){
            return new HashSet<>();
        }
        return events;
    }
    @Transactional
    public void toConfirmEvent(long id){
        Event event=eventRepository.findOne(id);
        event.setConfirmed(true);
        eventRepository.saveAndFlush(event);
    }

    @Transactional
    public void deleteAllEvents() {
        eventRepository.deleteAll();
    }

    private Sort sortByUserId() {

        return new Sort(Sort.Direction.ASC, "id");
    }
    @Transactional
    public Set<Event> findAllEventsIsConfirmedByMonth(boolean is_confirmed){
        Set<Event> events=new HashSet<>();
        LocalTime startLocalTime = LocalTime.of(0, 0);
        LocalDateTime startDateTime = LocalDateTime.of(LocalDate.now(), startLocalTime);
        LocalDateTime endDateTime = LocalDateTime.of(LocalDate.now().plusDays(30), LocalTime.of(23, 59));
        events.addAll(eventRepository.findByIsConfirmedAndTimeFromBetween(is_confirmed,startDateTime,endDateTime));
        return events;
    }
    @Transactional
    public Set<Event> findNotConfirmedCoffeeBreakByWeek(){
        Set<Event> events=new HashSet<>();
        LocalTime startLocalTime = LocalTime.of(0, 0);
        LocalDateTime startDateTime = LocalDateTime.of(LocalDate.now(), startLocalTime);
        LocalDateTime endDateTime = LocalDateTime.of(LocalDate.now().plusDays(7), LocalTime.of(23, 59));
        events.addAll(eventRepository.findByIsConfirmedAndTypeAndTimeFromBetween(false, Type.COFFEE_BREAK,startDateTime,endDateTime));
        return events;
    }
}
