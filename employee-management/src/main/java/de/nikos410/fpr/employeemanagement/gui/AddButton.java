package de.nikos410.fpr.employeemanagement.gui;

import de.nikos410.fpr.employeemanagement.personal.EmployeeRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddButton extends JButton {
    private final AddDialog addDialog;

    public AddButton(EmployeeRepository employeeRepository, EmployeeTable employeeTable) {
        this.addDialog = new AddDialog(employeeRepository, employeeTable);

        setText("Add");
        addActionListener(this::pressed);
    }

    private void pressed(ActionEvent e) {
        addDialog.setVisible(true);
    }
}
