package ua.lviv.office.service;


import ua.lviv.office.entity.Event;
import ua.lviv.office.entity.User;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface EventService {
    void createEvent(Event event);
    List<Event> allEvents();
    void updateEvent(Event event);
    void deleteEvent(long id);
    void addUsersInEvent(long id_event, Set<User> users);
    Event findEvenById(long id_event);
    void deleteUserFromEvent(long id_event, long id_user);
    Set<User> findAllUsersInEvent(long id_event);
    Set<Event> findEventsBetweenDates(Date start, Date finish);
    Set<Event> findEventsBetweenDatesByUser(LocalDateTime start, LocalDateTime finish, long id);
    List<User> usersWhoAreNotInEvent(long id_event);
    Set<Event> findIsConfirmedEvents(boolean isConfirmed);
    void toConfirmEvent(long id);
    void deleteAllEvents();
    Set<Event> findAllEventsIsConfirmedByMonth(boolean is_confirmed);
    Set<Event> findNotConfirmedCoffeeBreakByWeek();
}
