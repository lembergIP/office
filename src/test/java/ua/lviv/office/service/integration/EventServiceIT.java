package ua.lviv.office.service.integration;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.lviv.office.categories.IntegrationTest;
import ua.lviv.office.config.TestBaseConfigClass;
import ua.lviv.office.entity.Event;
import ua.lviv.office.entity.Type;
import ua.lviv.office.entity.User;
import ua.lviv.office.service.EventService;
import ua.lviv.office.service.UserService;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Category(IntegrationTest.class)
@ContextConfiguration(classes = TestBaseConfigClass.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class EventServiceIT {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Before
    public void init() {
        User user = new User();
        user.setEmail("test@mail.com");
        user.setPassword("123456");
        user.setFirstName("Admin");
        user.setLastName("Test");
        userService.saveUser(user);

        Event event = new Event();
        LocalDate startDate = new Date(2022, 12, 12).toLocalDate();
        LocalDate finishDate = new Date(2022, 12, 12).toLocalDate();
        LocalTime startTime = LocalTime.of(10, 00);
        LocalTime finishTime = LocalTime.of(18, 00);
        event.setTimeFrom(LocalDateTime.of(startDate, startTime));
        event.setTimeEnd(LocalDateTime.of(finishDate, finishTime));
        event.setType(Type.WORK);
        eventService.createEvent(event);
        Set<User>users=new HashSet<>();
        users.add(userService.findUserByEmail("test@mail.com"));
        eventService.addUsersInEvent(event.getId(), users);
    }
    @After
    public void destroy(){
        userService.deleteAllUsers();
        eventService.deleteAllEvents();
    }

    @Test
    public void testUpdateEvent(){
        List<Event> events=eventService.allEvents();
        Event event= events.get(0);
        LocalDate startDate = new Date(2022, 12, 12).toLocalDate();
        LocalDate finishDate = new Date(2022, 12, 12).toLocalDate();
        LocalTime startTime = LocalTime.of(12, 00);
        LocalTime finishTime = LocalTime.of(13, 00);
        event.setTimeFrom(LocalDateTime.of(startDate,startTime));
        event.setTimeEnd(LocalDateTime.of(finishDate,finishTime));
        event.setType(Type.COFFEE_BREAK);
        eventService.updateEvent(event);

        Assert.assertEquals(LocalDateTime.of(startDate,startTime),eventService.findEvenById(event.getId()).getTimeFrom());

    }
    @Test
    public void testDeleteEvent(){
        List<Event> events=eventService.allEvents();
        Event event= events.get(0);
        eventService.deleteEvent(event.getId());
        Assert.assertEquals(true,eventService.allEvents().isEmpty());
    }
    @Test
    public void testDeleteUserFromEvent(){
        List<Event> events=eventService.allEvents();
        Event event= events.get(0);
        User user=userService.findUserByEmail("test@mail.com");
        eventService.deleteUserFromEvent(event.getId(),user.getId());

        Assert.assertEquals(0,eventService.findEvenById(event.getId()).getUsers().size());
    }
    @Test
    public void testFindAllUsersInEvent(){
        List<Event> events=eventService.allEvents();
        Event event= events.get(0);
       Set<User>users=event.getUsers();
        Assert.assertEquals(1,users.size());
    }
    @Test
    public void testAddUsersInEvent(){

        User user2 = new User();
        user2.setEmail("test2@mail.com");
        user2.setPassword("123456");
        user2.setFirstName("Anna");
        user2.setLastName("Poroshenko");
        userService.saveUser(user2);
        Set<User> users = new HashSet<>();
        users.add(userService.findUserByEmail("test2@mail.com"));
        List<Event> events=eventService.allEvents();

        eventService.addUsersInEvent(events.get(0).getId(), users);
        Event event= events.get(0);
        Assert.assertEquals(2,eventService.findEvenById(event.getId()).getUsers().size());
    }
    @Test
    public void testFindEventsBetweenDatesByUser(){
        User user=userService.findUserByEmail("test@mail.com");
        LocalDate startDate = new Date(2022, 12, 10).toLocalDate();
        LocalDate finishDate = new Date(2022, 12, 17).toLocalDate();
        LocalTime startTime = LocalTime.of(12, 00);
        LocalTime finishTime = LocalTime.of(13, 00);
        Assert.assertEquals(1,eventService.findEventsBetweenDatesByUser(LocalDateTime.of(startDate,startTime),LocalDateTime.of(finishDate,finishTime),user.getId()).size());

    }
    @Test
    public void testUsersIsNotAddInEvent(){
        List<Event> events=eventService.allEvents();
        Event event= events.get(0);
        Assert.assertEquals(true,eventService.usersWhoAreNotInEvent(event.getId()).isEmpty());
    }
}
