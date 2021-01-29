package de.nikos410.fpr.employeemanagement.gui;

import de.nikos410.fpr.employeemanagement.personal.Employee;
import de.nikos410.fpr.employeemanagement.personal.EmployeeRepository;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.text.MessageFormat;

public class IncreaseSalaryDialog extends JDialog {
    private final JTextField newSalaryInput = new JTextField();

    private final Employee employeeToChange;
    private final EmployeeRepository employeeRepository;
    private final EmployeeTable employeeTable;

    public IncreaseSalaryDialog(Employee employeeToChange, EmployeeRepository employeeRepository, EmployeeTable employeeTable) {
        this.employeeToChange = employeeToChange;
        this.employeeRepository = employeeRepository;
        this.employeeTable = employeeTable;

        addComponents();

        setTitle(MessageFormat.format("{0}, {1}: Increase salary.", employeeToChange.getLastName(), employeeToChange.getFirstName()));
        setModal(true);
        setVisible(true);
    }

    private void addComponents() {
        final JPanel dialogPanel = new JPanel();

        newSalaryInput.setText("Increase salary by (%)");
        dialogPanel.add(newSalaryInput);

        final JButton confirmButton = new JButton();
        confirmButton.setText("Confirm");
        confirmButton.addActionListener(this::confirmPressed);
        dialogPanel.add(confirmButton);

        add(dialogPanel);
        pack();
    }

    private void confirmPressed(ActionEvent e) {
        final BigDecimal by = new BigDecimal(newSalaryInput.getText());
        employeeToChange.increaseSalary(by);

        employeeRepository.update(employeeToChange);
        employeeTable.refresh();

        close();
    }

    private void close() {
        setVisible(false);
        dispose();
    }
}
