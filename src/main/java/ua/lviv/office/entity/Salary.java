package ua.lviv.office.entity;

import org.hibernate.annotations.GenericGenerator;
import ua.lviv.office.constans.SalaryConstants;

import javax.persistence.*;
import java.time.Month;

@Entity(name = SalaryConstants.Entity.TABLE_NAME_SALARY)
public class Salary {
    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = SalaryConstants.Entity.ID_SALARY,nullable = false,unique = true)
    private Integer id;

    @Column(name = SalaryConstants.Entity.YEAR)
    private Integer year;

    @Column(name = SalaryConstants.Entity.MONTH)
    private Month month;

    @Column(name = SalaryConstants.Entity.AMOUNT)
    private Float amount;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "user_Id")
    private User user;

    public Salary() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
