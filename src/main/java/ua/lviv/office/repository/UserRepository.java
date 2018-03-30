package ua.lviv.office.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.lviv.office.constans.UserConstants;
import ua.lviv.office.entity.Event;
import ua.lviv.office.entity.Role;
import ua.lviv.office.entity.User;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    User findByEmail(String email);
    List<User> findByRole(Role role);
    List<User> findByIsRoleConfirmedFalse();
    User findByLastName(String lastName);
    @Query("SELECT u.events FROM "+ UserConstants.Entity.TABLE_NAME_USER+" u WHERE u.id = :id")
    Set<Event> getEventsByUserId(@Param("id") long id);


}
