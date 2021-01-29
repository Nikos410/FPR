package de.nikos410.fpr.employeemanagement.gui;

import de.nikos410.fpr.employeemanagement.personal.EmployeeRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SortBySalaryButton extends JButton {
    private final EmployeeRepository employeeRepository;
    private final EmployeeTable employeeTable;


    public SortBySalaryButton(EmployeeRepository employeeRepository, EmployeeTable employeeTable) {
        this.employeeRepository = employeeRepository;
        this.employeeTable = employeeTable;

        setText("Sort by salary");
        addActionListener(this::pressed);
    }

    private void pressed(ActionEvent e) {
        employeeRepository.orderBySalary();
        employeeTable.refresh();
    }
}
