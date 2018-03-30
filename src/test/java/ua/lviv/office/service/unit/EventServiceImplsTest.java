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
        LocalDateTime startDateTime = LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(12,15));
        LocalDateTime endDateTime = LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(13,00));
        event.setTimeFrom(startDateTime);
        event.setTimeEnd(endDateTime);
        event.setType(Type.COFFEE_BREAK);
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
        LocalDateTime startDateTime = LocalDateTime.of(LocalDate.now().plusDays(20), LocalTime.of(12,15));
        LocalDateTime endDateTime = LocalDateTime.of(LocalDate.now().plusDays(20), LocalTime.of(13,00));
        event2.setTimeFrom(startDateTime);
        event2.setTimeEnd(endDateTime);
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

        Assert.assertEquals(1,eventService.findEventsBetweenDates(Date.valueOf(LocalDate.now().plusDays(1)),Date.valueOf(LocalDate.now().plusDays(25))).size());
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
    @Test
    public void testAllEventsIsConfirmedByMonthTrue(){
        List<Event>events=eventService.allEvents();
        Long id= events.get(0).getId();
        eventService.toConfirmEvent(id);
        Assert.assertEquals(1,eventService.findAllEventsIsConfirmedByMonth(true).size());
    }
    @Test
    public void testAllEventsIsConfirmedByMonthFalse(){

        Assert.assertEquals(1,eventService.findAllEventsIsConfirmedByMonth(false).size());
    }
    @Test
    public void testFindNotConfirmedCoffeeBreakByWeek(){
        Assert.assertEquals(1,eventService.findNotConfirmedCoffeeBreakByWeek().size());
    }
}
