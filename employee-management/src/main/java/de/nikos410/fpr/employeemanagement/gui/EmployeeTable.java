package de.nikos410.fpr.employeemanagement.gui;

import de.nikos410.fpr.employeemanagement.personal.Employee;
import de.nikos410.fpr.employeemanagement.personal.EmployeeRepository;
import de.nikos410.fpr.employeemanagement.personal.Manager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class EmployeeTable extends JTable {
    private static final String[] COLUMNS = {"staff id", "last name", "first name", "salary", "manager", "bonus", "calculated salary"};

    private final EmployeeRepository employeeRepository;

    public EmployeeTable(EmployeeRepository employeeRepository) {
        super(new EmployeeTableModel());
        this.employeeRepository = employeeRepository;

        setFillsViewportHeight(true);
        refresh();
    }

    public void refresh() {
        refresh(employeeRepository.findAll());
    }

    private void refresh(List<Employee> employees) {
        final DefaultTableModel model = (DefaultTableModel) getModel();
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        for (Employee employee : employees) {
            model.addRow(employeeToRowData(employee));
        }
    }

    private String[] employeeToRowData (Employee employee) {
        final String[] rowData = new String[COLUMNS.length];

        rowData[0] = Long.toString(employee.getStaffId());
        rowData[1] = employee.getLastName();
        rowData[2] = employee.getFirstName();
        rowData[3] = employee.getSalary().toPlainString();

        if (employee instanceof Manager) {
            final Manager manager = (Manager) employee;
            rowData[4] = "yes";
            rowData[5] = manager.getBonus().toPlainString();
        } else {
            rowData[4] = "no";
            rowData[5] = "-";
        }

        rowData[6] = employee.calculateSalary().toPlainString();

        return rowData;
    }

    public Optional<Employee> getSelectedEmployee() {
        final int selectedRow = getSelectedRow();
        if (selectedRow >= 0) {
            return Optional.of(employeeRepository.findAll().get(getSelectedRow()));
        } else {
            return Optional.empty();
        }
    }

    private static class EmployeeTableModel extends DefaultTableModel {
        private EmployeeTableModel() {
            for (String column : COLUMNS) {
                addColumn(column);
            }
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
}
