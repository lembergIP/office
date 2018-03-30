package ua.lviv.office.until;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.lviv.office.categories.IntegrationTest;
import ua.lviv.office.categories.UnitTest;
import ua.lviv.office.config.TestBaseConfigClass;
import ua.lviv.office.util.DateValidate;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Category(UnitTest.class)
@ContextConfiguration(classes = TestBaseConfigClass.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class DateValidateTest {

    @Test
    public void testIsStartDateBefore(){
        LocalDate startDate = new Date(2018, 1, 1).toLocalDate();
        LocalTime startTime = LocalTime.of(10, 00);
        Assert.assertEquals(true, DateValidate.isStartDateBefore(LocalDateTime.of(startDate,startTime)));
    }
    @Test
    public void testDefferenceBetweeneLocalTimeInMinuts(){
        LocalDate startDate = new Date(2018, 1, 1).toLocalDate();
        LocalDate finishDate = new Date(2018, 1, 1).toLocalDate();
        LocalTime startTime = LocalTime.of(10, 00);
        LocalTime finishTime = LocalTime.of(9, 00);
        Assert.assertEquals(-60, DateValidate.defferenceBetweeneLocalTimeInMinuts(LocalDateTime.of(startDate,startTime),LocalDateTime.of(finishDate,finishTime)));
    }
    @Test
    public void testDateAndTimeValidatorInEvent(){
        LocalDate startDate = new Date(2018, 1, 1).toLocalDate();
        LocalDate finishDate = new Date(2018, 1, 1).toLocalDate();
        LocalTime startTime = LocalTime.of(10, 00);
        LocalTime finishTime = LocalTime.of(9, 00);
        Assert.assertEquals(false,DateValidate.dateAndTimeValidatorInEvent(LocalDateTime.of(startDate,startTime),LocalDateTime.of(finishDate,finishTime)));
    }
    @Test
    public void testDefferenceBetweeneLocalTimeInHours(){
        LocalDate startDate = new Date(2018, 1, 1).toLocalDate();
        LocalDate finishDate = new Date(2018, 1, 1).toLocalDate();
        LocalTime startTime = LocalTime.of(10, 00);
        LocalTime finishTime = LocalTime.of(18, 00);
        Assert.assertEquals(8,DateValidate.defferenceBetweeneLocalTimeInHours(LocalDateTime.of(startDate,startTime),LocalDateTime.of(finishDate,finishTime)));

    }
}
