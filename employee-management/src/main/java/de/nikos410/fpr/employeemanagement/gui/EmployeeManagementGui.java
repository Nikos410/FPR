package de.nikos410.fpr.employeemanagement.gui;

import de.nikos410.fpr.employeemanagement.personal.EmployeeRepository;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;

public class EmployeeManagementGui extends javax.swing.JFrame {
    private final EmployeeRepository employeeRepository;

    public EmployeeManagementGui(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    public void initialize() {
        SwingUtilities.invokeLater(this::doInitialize);
    }

    private void doInitialize() {
        initializeContent();
        initializeWindow();
    }

    private void initializeContent() {
        setTitle("Employee Management 47-11");
        final JPanel mainPanel = new MainPanel(employeeRepository);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    private void initializeWindow() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(500, 600);
        setVisible(true);
    }
}
