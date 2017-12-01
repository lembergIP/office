package ua.lviv.office.service.unit;

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
import ua.lviv.office.categories.UnitTest;
import ua.lviv.office.config.TestBaseConfigClass;
import ua.lviv.office.entity.Event;
import ua.lviv.office.entity.Type;
import ua.lviv.office.service.EventService;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


@Category(UnitTest.class)
@ContextConfiguration(classes = TestBaseConfigClass.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class EventServiceImplsTest {

    @Autowired
    private EventService eventService;

    @Before
    public void init(){
        Event event=new Event();
        LocalDate startDate= new Date(2022,12,12).toLocalDate();
        LocalDate finishDate=new Date(2022,12,12).toLocalDate();
        LocalTime startTime=LocalTime.of(10,00);
        LocalTime finishTime=LocalTime.of(18,00);
        event.setTimeFrom(LocalDateTime.of(startDate,startTime));
        event.setTimeEnd(LocalDateTime.of(finishDate,finishTime));
        event.setType(Type.WORK);
        event.setConfirmed(false);
        eventService.createEvent(event);

    }
    @After
    public void destroy(){
        eventService.deleteAllEvents();
    }
    @Test
    public void testFindEvenById(){
        List<Event>events=eventService.allEvents();
        Long id= events.get(0).getId();
        Assert.assertEquals(id,eventService.findEvenById(id).getId());
    }
    @Test
    public void testCreateEvent(){
        Event event2=new Event();
        LocalDate startDate= new Date(2021,12,12).toLocalDate();
        LocalDate finishDate=new Date(2021,12,12).toLocalDate();
        LocalTime startTime=LocalTime.of(12,15);
        LocalTime finishTime=LocalTime.of(13,00);
        event2.setTimeFrom(LocalDateTime.of(startDate,startTime));
        event2.setTimeEnd(LocalDateTime.of(finishDate,finishTime));
        event2.setType(Type.MEETING);
        eventService.createEvent(event2);

        Assert.assertEquals(2,eventService.allEvents().size());
    }

    @Test
    public void testAllEvents(){
        Assert.assertEquals(1,eventService.allEvents().size());
    }

    @Test
    public void testFindEventsBetweenDates(){

        Assert.assertEquals(1,eventService.findEventsBetweenDates(new Date(2022,12,10),new Date(2022,12,20)).size());
    }
    @Test
    public void testFindIsConfirmedEvents(){
        Assert.assertEquals(1,eventService.findIsConfirmedEvents(false).size());
    }
    @Test
    public void testToConfirmEvent(){
        List<Event>events=eventService.allEvents();
        Long id= events.get(0).getId();
        eventService.toConfirmEvent(id);
        Assert.assertEquals(1,eventService.findIsConfirmedEvents(true).size());
    }
}
