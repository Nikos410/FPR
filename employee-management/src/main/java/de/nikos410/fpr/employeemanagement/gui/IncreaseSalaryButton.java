package de.nikos410.fpr.employeemanagement.gui;

import de.nikos410.fpr.employeemanagement.personal.Employee;
import de.nikos410.fpr.employeemanagement.personal.EmployeeRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Optional;

public class IncreaseSalaryButton extends JButton {
    private final EmployeeRepository employeeRepository;
    private final EmployeeTable employeeTable;

    public IncreaseSalaryButton(EmployeeRepository employeeRepository, EmployeeTable employeeTable) {
        this.employeeRepository = employeeRepository;
        this.employeeTable = employeeTable;

        setText("Increase salary");
        addActionListener(this::pressed);
    }

    private void pressed(ActionEvent e) {
        final Optional<Employee> selectedEmployee = employeeTable.getSelectedEmployee();
        selectedEmployee.ifPresent(employee -> new IncreaseSalaryDialog(employee, employeeRepository, employeeTable));
    }
}
