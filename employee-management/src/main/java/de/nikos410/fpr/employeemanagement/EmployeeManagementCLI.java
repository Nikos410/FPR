package de.nikos410.fpr.employeemanagement;

import de.nikos410.fpr.employeemanagement.personal.Employee;
import de.nikos410.fpr.employeemanagement.personal.EmployeeRepository;
import de.nikos410.fpr.employeemanagement.personal.InMemoryEmployeeRepository;
import de.nikos410.fpr.employeemanagement.personal.Manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EmployeeManagementCLI {
    private final BufferedReader bufferedStdInReader = new BufferedReader(new InputStreamReader(System.in));
    private final EmployeeRepository employeeRepository = new InMemoryEmployeeRepository();

    public static void main(String[] args) {
        new EmployeeManagementCLI().run();
    }

    public void run() {
        while (true) {
            System.out.println("Please tell me what you want to do.");
            System.out.println("exit | list | lowest-salary | highest-salary | duplicates | add | remove | change-salary");
            switch (readLine()) {
                case "exit":
                    System.out.println("Goodbye.");
                    return;
                case "list":
                    list();
                    break;
                case "lowest-salary":
                    lowestSalary();
                    break;
                case "highest-salary":
                    highestSalary();
                    break;
                case "duplicates":
                    duplicates();
                    break;
                case "add":
                    add();
                    break;
                case "remove":
                    remove();
                    break;
                case "change-salary":
                    changeSalary();
                    break;
                default:
                    System.err.println("Unknown command. Please try again.");
            }
        }
    }

    private void list() {
        System.out.println(employeeRepository.findAll());
    }

    private void lowestSalary() {
        System.out.println(employeeRepository.findByLowestSalary());
    }

    private void highestSalary() {
        System.out.println(employeeRepository.findByHighestSalary());
    }

    private void duplicates() {
        System.out.println(employeeRepository.findDuplicates());
    }

    private void add() {
        try {
            tryAdd();
        } catch (NumberFormatException e) {
            System.err.println("Invalid number entered.");
        } catch (DateTimeParseException e) {
            System.err.println("Invalid date entered.");
        }
    }

    private void tryAdd() {
        final String firstName = readLine("Enter first name.");
        final String lastName = readLine("Enter last name.");
        final String salaryInput = readLine("Enter salary.");
        final BigDecimal salary = new BigDecimal(salaryInput);
        final String hireDateInput = readLine("Enter hiring date. Use iso format (yyyy-MM-dd). Leave empty to use current date.");
        final LocalDate hireDate;
        if (hireDateInput.isBlank()) {
            hireDate = LocalDate.now();
        } else {
            hireDate = LocalDate.parse(hireDateInput);
        }

        final String staffIdInput = readLine("Enter staff id.");
        final long staffId = Long.parseLong(staffIdInput);
        final String manager = readLine("Is this employee a manager? (y/n)");

        final Employee newEmployee;
        if (manager.equalsIgnoreCase("y")) {
            final String bonusInput = readLine("Enter bonus.");
            final BigDecimal bonus = new BigDecimal(bonusInput);
            newEmployee = new Manager(firstName, lastName, salary, hireDate, staffId, bonus);
        } else {
            newEmployee = new Employee(firstName, lastName, salary, hireDate, staffId);
        }

        employeeRepository.add(newEmployee);
        System.out.println(newEmployee);
    }

    private void remove() {
        final String input = readLine("Enter staff id of employee to remove.");
        remove(input);
    }

    private void remove(String staffIdInput) {
        try {
            long staffId = Long.parseLong(staffIdInput);
            remove(staffId);
        } catch (NumberFormatException e) {
            System.err.println("Invalid staff id. Please try again.");
        }
    }

    private void remove(long staffId) {
        final var toDelete = employeeRepository.findByStaffID(staffId);
        if (toDelete.isPresent()) {
            employeeRepository.delete(toDelete.get());
        } else {
            System.err.println("Unknown employee.");
        }

    }

    private void changeSalary() {
        final String input = readLine("Enter staff id of employee.");
        changeSalary(input);
    }

    private void changeSalary(String staffIdInput) {
        try {
            long staffId = Long.parseLong(staffIdInput);
            changeSalary(staffId);
        } catch (NumberFormatException e) {
            System.err.println("Invalid staff id. Please try again.");
        }
    }

    private void changeSalary(long staffId) {
        final var employee = employeeRepository.findByStaffID(staffId);
        if (employee.isPresent()) {
            changeSalary(employee.get());
        } else {
            System.err.println("Unknown employee.");
        }
    }

    private void changeSalary(Employee employee) {
        System.out.println("Changing salary of employee " + employee);
        final String byPercentInput = readLine("By how much do you want to change the salary? (in percent)")
                .replaceAll("%", "");
        changeSalary(employee, byPercentInput);
    }

    private void changeSalary(Employee employee, String byPercentInput) {
        try {
            final BigDecimal byPercent = new BigDecimal(byPercentInput);
            changeSalary(employee, byPercent);
        } catch (NumberFormatException e) {
            System.err.println("Invalid number entered.");
        }
    }

    private void changeSalary(Employee employee, BigDecimal byPercent) {
        final BigDecimal newSalary = employee.increaseSalary(byPercent);
        employeeRepository.update(employee);
        System.out.println("New salary: " + newSalary);
    }

    private String readLine() {
        return readLine("");
    }

    private String readLine(String prompt) {
        if (!prompt.isBlank()) {
            System.out.println(prompt);
        }
        System.out.print("> ");

        try {
            return bufferedStdInReader.readLine();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
