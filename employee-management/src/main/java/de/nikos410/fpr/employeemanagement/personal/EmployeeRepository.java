package de.nikos410.fpr.employeemanagement.personal;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    List<Employee> findAll();

    Optional<Employee> findByStaffID(long staffId);

    Employee findByHighestSalary();

    Employee findByLowestSalary();

    List<Employee> findDuplicates();

    void add(Employee newEmployee);

    void update(Employee updatedEmployee);

    boolean delete(Employee employee);

    void orderBySalary();
}
