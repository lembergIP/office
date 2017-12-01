package ua.lviv.office.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.lviv.office.constans.EventConstants;
import ua.lviv.office.entity.Event;
import ua.lviv.office.entity.User;

import java.time.LocalDateTime;
import java.util.Set;

public interface EventRepository extends JpaRepository<Event,Long> {
    @Query("SELECT e.users FROM "+ EventConstants.Entity.TABLE_NAME_EVENT+" e WHERE e.id = ?1 ")
    Set<User> getUsersByEventId(@Param("id") long id_event);
    Set<Event> findByTimeFromBetween(LocalDateTime start, LocalDateTime finish);
    Set<Event> findByIsConfirmedAndTimeFromBetween(boolean isConfirmed, LocalDateTime start, LocalDateTime finish);
    Set<Event> findByIsConfirmed(boolean isConfirmed);
    @Query("SELECT e FROM "+ EventConstants.Entity.TABLE_NAME_EVENT+" e INNER JOIN e.users u WHERE u.id = ?3 " +
            "and e.timeFrom between ?1 and ?2 order by e.timeFrom")
    Set<Event> getEventsByUserIdAndTimeFromBetween(LocalDateTime start, LocalDateTime finish, long id_user);

}
