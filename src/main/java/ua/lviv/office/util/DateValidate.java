package ua.lviv.office.util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * calculate defference betweene time
 * time validate
 */
public class DateValidate {
   /*Validate Local date and time are  not before local time now and return false
   * if before return true*/
    public static boolean isStartDateBefore(LocalDateTime startTime){
        if(defferenceBetweeneLocalTimeInMinuts(LocalDateTime.now(),startTime)<=0){
            return false;
        }
        return true;
    }
    public static long defferenceBetweeneLocalTimeInHours(LocalDateTime startTime,LocalDateTime endTime) {


        LocalDateTime tempDateTime = LocalDateTime.from( startTime );

        long years = tempDateTime.until( endTime, ChronoUnit.YEARS);
        tempDateTime = tempDateTime.plusYears( years );

        long months = tempDateTime.until( endTime, ChronoUnit.MONTHS);
        tempDateTime = tempDateTime.plusMonths( months );

        long days = tempDateTime.until( endTime, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusDays( days );


        long hours = tempDateTime.until( endTime, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours( hours );

        return ((years*8760)+((months*720)+(days*24)+hours));

    }

    public static long defferenceBetweeneLocalTimeInMinuts(LocalDateTime startTime,LocalDateTime endTime) {


        LocalDateTime tempDateTime = LocalDateTime.from( startTime );

        long years = tempDateTime.until( endTime, ChronoUnit.YEARS);
        tempDateTime = tempDateTime.plusYears( years );

        long months = tempDateTime.until( endTime, ChronoUnit.MONTHS);
        tempDateTime = tempDateTime.plusMonths( months );

        long days = tempDateTime.until( endTime, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusDays( days );


        long hours = tempDateTime.until( endTime, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours( hours );

        long minutes = tempDateTime.until( endTime, ChronoUnit.MINUTES);
        tempDateTime = tempDateTime.plusMinutes( minutes );

        return ((years*525600)+((months*43200)+(days*1440)+(hours*60)+minutes));

    }
    public static boolean dateAndTimeValidatorInEvent(LocalDateTime startTime,LocalDateTime endTime){
        if(defferenceBetweeneLocalTimeInMinuts(startTime,endTime)<=0){
            return false;
        }
        return true;
    }
}
