package de.nikos410.fpr.employeemanagement.personal;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryEmployeeRepository implements EmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();

    @Override
    public List<Employee> findAll() {
        return employees;
    }

    @Override
    public Optional<Employee> findByStaffID(long staffId) {
        return employees.stream()
                .filter(e -> staffId == e.staffId)
                .findFirst();
    }

    @Override
    public Employee findByHighestSalary() {
        return employees.stream()
                .max(Comparator.comparing(Employee::calculateSalary))
                .orElseThrow(() -> new IllegalStateException("No employees known."));
    }

    @Override
    public Employee findByLowestSalary() {
        return employees.stream()
                .min(Comparator.comparing(Employee::calculateSalary))
                .orElseThrow(() -> new IllegalStateException("No employees known."));
    }

    @Override
    public List<Employee> findDuplicates() {
        final Set<Employee> tempSet = new HashSet<>();

        return employees.stream()
                .filter(e -> !tempSet.add(e))
                .collect(Collectors.toList());
    }

    @Override
    public void save(Employee toSave) {
        final Optional<Employee> existingEmployee = employees.stream()
                .filter(e -> e.staffId == toSave.staffId)
                .findFirst();

        if (existingEmployee.isPresent()) {
            final int existingEmployeeIndex = employees.indexOf(existingEmployee.get());
            employees.set(existingEmployeeIndex, toSave);
        } else {
            employees.add(toSave);
        }
    }

    @Override
    public boolean delete(Employee employee) {
        return employees.remove(employee);
    }
}
