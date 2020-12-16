package de.nikos410.fpr.employeemanagement.personal;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    List<Employee> findAll();

    Employee findByHighestSalary();

    Employee findByLowestSalary();

    List<Employee> findDuplicates();

    void save(Employee toSave);

    boolean delete(Employee employee);
}
