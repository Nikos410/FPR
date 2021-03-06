package de.nikos410.fpr.employeemanagement.gui;

import de.nikos410.fpr.employeemanagement.personal.EmployeeRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DeleteButton extends JButton {
    private final EmployeeRepository employeeRepository;
    private final EmployeeTable employeeTable;
    public DeleteButton(EmployeeRepository employeeRepository, EmployeeTable employeeTable) {
        this.employeeRepository = employeeRepository;
        this.employeeTable = employeeTable;

        setText("Delete");
        addActionListener(this::pressed);
    }

    private void pressed(ActionEvent event) {
        employeeTable.getSelectedEmployee().ifPresent(employeeRepository::delete);
        employeeTable.refresh();
    }
}
