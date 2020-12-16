package de.nikos410.fpr.employeemanagement.personal;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    Optional<Employee> findByStaffId(long id);

    List<Employee> findAll();

    void save(Employee employee);

    boolean delete(Employee employee);
}
