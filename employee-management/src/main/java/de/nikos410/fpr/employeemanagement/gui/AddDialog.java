package de.nikos410.fpr.employeemanagement.gui;

import de.nikos410.fpr.employeemanagement.personal.Employee;
import de.nikos410.fpr.employeemanagement.personal.EmployeeRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AddDialog extends JDialog {
    private final EmployeeRepository employeeRepository;
    private final EmployeeTable employeeTable;

    private final JTextField staffIdInput = new JTextField();
    private final JTextField firstNameInput = new JTextField();
    private final JTextField lastNameInput = new JTextField();
    private final JTextField hireDateInput = new JTextField();
    private final JTextField salaryInput = new JTextField();

    public AddDialog(
            EmployeeRepository employeeRepository, EmployeeTable employeeTable) {
        this.employeeRepository = employeeRepository;
        this.employeeTable = employeeTable;

        setTitle("New employee");
        setModal(true);

        addComponents();
    }

    private void addComponents() {
        final JPanel dialogPanel = new JPanel();

        staffIdInput.setText("Staff id");
        staffIdInput.setVisible(true);
        dialogPanel.add(staffIdInput);

        firstNameInput.setText("First name");
        dialogPanel.add(firstNameInput);

        lastNameInput.setText("Last name");
        dialogPanel.add(lastNameInput);

        hireDateInput.setText("Hire date (blank = now)");
        dialogPanel.add(hireDateInput);

        salaryInput.setText("Salary");
        dialogPanel.add(salaryInput);

        final JButton confirmButton = new JButton();
        confirmButton.setText("Confirm");
        confirmButton.addActionListener(this::confirm);
        dialogPanel.add(confirmButton);

        add(dialogPanel);
        pack();
    }

    private void confirm(ActionEvent e) {
        final Employee employee = new Employee(firstNameInput.getText(), lastNameInput.getText(), new BigDecimal(salaryInput.getText()), getHireDateFromInput(), Long.parseLong(staffIdInput.getText()));
        employeeRepository.add(employee);
        employeeTable.refresh();
        setVisible(false);
    }

    private LocalDate getHireDateFromInput() {
        final String hireDateText = hireDateInput.getText();
        if (hireDateText.isBlank()) {
            return LocalDate.now();
        } else {
            return LocalDate.parse(hireDateText);
        }
    }
}
