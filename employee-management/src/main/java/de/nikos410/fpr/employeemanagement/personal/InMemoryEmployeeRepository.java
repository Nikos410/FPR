package de.nikos410.fpr.employeemanagement.personal;

import de.nikos410.fpr.employeemanagement.util.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                .min(Comparator.naturalOrder())
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
    public Optional<Manager> findByLowestBonus() {
        return findManagers().min(Comparator.comparing(Manager::getBonus));
    }

    @Override
    public Optional<Manager> findByHighestBonus() {
        return findManagers().max(Comparator.comparing(Manager::getBonus));
    }

    private Stream<Manager> findManagers() {
        return employees.stream().filter(e -> e instanceof Manager).map(e -> (Manager) e);
    }

    @Override
    public Pair<Optional<Manager>> findMinMaxBonus() {
        return new Pair<>(findByLowestBonus(), findByHighestBonus());
    }

    @Override
    public void add(Employee newEmployee) {
        employees.add(newEmployee);
    }

    @Override
    public void update(Employee updatedEmployee) {
        final Optional<Employee> existingEmployee = employees.stream()
                .filter(e -> e.staffId == updatedEmployee.staffId)
                .findFirst();

        if (existingEmployee.isPresent()) {
            final int existingEmployeeIndex = employees.indexOf(existingEmployee.get());
            employees.set(existingEmployeeIndex, updatedEmployee);
        } else {
            throw new IllegalArgumentException("Employee does not exist yet.");
        }
    }

    @Override
    public boolean delete(Employee employee) {
        return employees.remove(employee);
    }

    @Override
    public void orderBySalary() {
        Collections.sort(employees);
    }
}
