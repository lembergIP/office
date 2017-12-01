package ua.lviv.office.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.lviv.office.entity.Salary;

public interface SalaryRepository extends JpaRepository<Salary,Long> {
}
