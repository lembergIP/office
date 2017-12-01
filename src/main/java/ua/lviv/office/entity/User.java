package ua.lviv.office.entity;


import org.hibernate.annotations.GenericGenerator;
import ua.lviv.office.constans.UserConstants;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity(name = UserConstants.Entity.TABLE_NAME_USER)
public class User {
    
    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = UserConstants.Entity.ID_USER,nullable = false)
    private Long id;

    @Column(length = 50, unique = true,name = UserConstants.Entity.EMAIL,nullable = false)
    private String email;

    @Column(name = UserConstants.Entity.PASSWORD,nullable = false)
    private String password;

    @Column(name = UserConstants.Entity.FIRST_NAME)
    private String firstName;

    @Column(name = UserConstants.Entity.LAST_NAME)
    private String lastName;

    @Temporal(value = TemporalType.DATE)
    @Column(name = UserConstants.Entity.DATE_OF_BIRTH)
    private Date dateBirth;

    @Column(length = 10,name = UserConstants.Entity.PHONE)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = UserConstants.Entity.ROLE)
    private Role role;

    @Lob
    @Column(name = UserConstants.Entity.PROFILE_IMG)
    private byte[] image;

    @Column(name = UserConstants.Entity.IS_RORE_CONFIRMED)
    private Boolean isRoleConfirmed;

    @ManyToMany(fetch = FetchType.LAZY, cascade =
            {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            })
    @JoinTable(name = "User_has_Event",  inverseJoinColumns = { @JoinColumn(name = "event_Id", nullable = false, updatable = false) }, joinColumns = { @JoinColumn(name = "user_Id", nullable = false, updatable = false) })

    private Set<Event> events=new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Salary> salaries;
    public User() {
    }

    public Boolean getRoleConfirmed() {
        return isRoleConfirmed;
    }

    public void setRoleConfirmed(Boolean roleConfirmed) {
        isRoleConfirmed = roleConfirmed;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Set<Salary> getSalaries() {
        return salaries;
    }

    public void setSalaries(Set<Salary> salaries) {
        this.salaries = salaries;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
