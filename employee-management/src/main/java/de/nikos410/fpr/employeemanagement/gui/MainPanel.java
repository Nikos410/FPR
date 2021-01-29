package de.nikos410.fpr.employeemanagement.gui;

import de.nikos410.fpr.employeemanagement.personal.EmployeeRepository;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainPanel extends JPanel {

    public MainPanel(EmployeeRepository employeeRepository) {
        final EmployeeTable employeeTable = new EmployeeTable(employeeRepository);

        addComponents(employeeRepository, employeeTable);
    }

    private void addComponents(EmployeeRepository employeeRepository, EmployeeTable employeeTable) {
        add(new JScrollPane(employeeTable));
        add(new DeleteButton(employeeRepository,employeeTable));
        add(new AddButton(employeeRepository, employeeTable));
        add(new SortBySalaryButton(employeeRepository, employeeTable));
        add(new IncreaseSalaryButton(employeeRepository, employeeTable));
    }
}
