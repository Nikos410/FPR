package de.nikos410.fpr.employeemanagement.personal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryEmployeeRepository implements EmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();

    @Override
    public Optional<Employee> findByStaffId(long staffId) {
        return employees.stream()
                .filter(employee -> staffId == employee.getStaffId())
                .findFirst();
    }

    @Override
    public List<Employee> findAll() {
        return employees;
    }

    @Override
    public void save(Employee employee) {
        final Optional<Employee> existingEmployee = findByStaffId(employee.getStaffId());

        if (existingEmployee.isPresent()) {
            saveExistingEmployee(employee);
        } else {
            saveNewEmployee(employee);
        }
    }

    private void saveExistingEmployee(Employee employee) {
        final var existingEmployeeIndex = employees.indexOf(employee);
        employees.set(existingEmployeeIndex, employee);
    }

    private void saveNewEmployee(Employee employee) {
        employees.add(employee);
    }

    @Override
    public boolean delete(Employee employee) {
        return employees.remove(employee);
    }
}
