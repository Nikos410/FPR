package de.nikos410.fpr.employeemanagement.gui;

import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;

public class EmployeeManagementGui extends javax.swing.JFrame {

    public EmployeeManagementGui() {
        super();
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
        getContentPane().add(new MainPanel(), BorderLayout.CENTER);
    }

    private void initializeWindow() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
    }
}
