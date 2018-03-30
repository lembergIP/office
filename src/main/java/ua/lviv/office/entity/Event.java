package ua.lviv.office.entity;

import org.hibernate.annotations.GenericGenerator;
import ua.lviv.office.constans.EventConstants;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = EventConstants.Entity.TABLE_NAME_EVENT)
public class Event {
    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = EventConstants.Entity.ID_EVENT,unique = true)
    private Long id;

    @Column(name = EventConstants.Entity.START_TIME)
    private LocalDateTime timeFrom;

    @Column(name = EventConstants.Entity.END_TIME)
    private LocalDateTime timeEnd;

    @Column(length = 250,name = EventConstants.Entity.DESCRIPTION)
    private String description;

    @Column(name = EventConstants.Entity.IS_CONFIRMED)
    private boolean isConfirmed;

    @ManyToMany(fetch = FetchType.EAGER,cascade =
            {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            })
    @JoinTable(name = "User_has_Event", joinColumns = { @JoinColumn(name = "event_Id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "user_Id", nullable = false, updatable = false) })
    private Set<User> users=new HashSet<User>();

    @Column(name = EventConstants.Entity.TYPE)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = EventConstants.Entity.DIRECTION)
    private Long direction;


    public Event() {
    }

    public Long getDirection() {
        return direction;
    }

    public void setDirection(Long direction) {
        this.direction = direction;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


    public Long getId() {
        return id;
    }

    public LocalDateTime getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(LocalDateTime timeFrom) {
        this.timeFrom = timeFrom;
    }

    public LocalDateTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalDateTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }


}
