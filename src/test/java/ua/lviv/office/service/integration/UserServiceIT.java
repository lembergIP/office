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
import java.util.Set;


@Category(IntegrationTest.class)
@ContextConfiguration(classes = TestBaseConfigClass.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class UserServiceIT {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Before
    public void init(){
        User user=new User();
        user.setEmail("test@mail.com");
        user.setPassword("123456");
        user.setFirstName("Ivan");
        user.setLastName("Test");

        userService.saveUser(user);

        Event event=new Event();
        LocalDate startDate= new Date(2020,12,12).toLocalDate();
        LocalDate finishDate=new Date(2020,12,12).toLocalDate();
        LocalTime startTime=LocalTime.of(10,00);
        LocalTime finishTime=LocalTime.of(18,00);
        event.setTimeFrom(LocalDateTime.of(startDate,startTime));
        event.setTimeEnd(LocalDateTime.of(finishDate,finishTime));
        event.setType(Type.WORK);
        eventService.createEvent(event);
        Set<User>users=new HashSet<>();
        users.add(userService.findUserByEmail("test@mail.com"));
        eventService.addUsersInEvent(event.getId(),users);
    }
    @After
    public void destroy(){
        userService.deleteAllUsers();
        eventService.deleteAllEvents();
    }

    @Test
    public void testUpdateUserInfo(){
        User user=userService.findUserByEmail("test@mail.com");
        String name="Petro";
        user.setFirstName(name);
        userService.updateUserInfo(user.getEmail(),user.getFirstName(),user.getLastName(),user.getPhoneNumber(), (Date) user.getDateBirth());

        Assert.assertEquals(name,userService.findUserByEmail("test@mail.com").getFirstName());
    }
    @Test
    public void testDeleteUserById(){
        User user=userService.findUserByEmail("test@mail.com");
        userService.deleteUser(user.getId());
        Assert.assertEquals(0,userService.findAllUsers().size());
    }
}
