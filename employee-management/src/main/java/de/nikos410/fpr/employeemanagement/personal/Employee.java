package de.nikos410.fpr.employeemanagement.personal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

public class Employee extends AbstractPerson {
    private BigDecimal salary;
    private LocalDate hireDate;
    private long staffId;

    public Employee(String firstName, String lastName, BigDecimal salary, LocalDate hireDate, long staffId) {
        super(firstName, lastName);
        this.salary = salary;
        this.hireDate = hireDate;
        this.staffId = staffId;
    }

    public BigDecimal increaseSalary(BigDecimal byPercent) {
        final BigDecimal currentSalary = getSalary();

        final BigDecimal difference = currentSalary
                .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP)
                .multiply(byPercent);

        final BigDecimal newSalary = currentSalary.add(difference);
        this.setSalary(newSalary);
        return newSalary;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Employee employee = (Employee) o;
        return staffId == employee.staffId && salary.equals(employee.salary) && hireDate.equals(employee.hireDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), salary, hireDate, staffId);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", salary=" + getSalary() +
                ", hireDate=" + getHireDate() +
                ", staffId=" + getStaffId() +
                '}';
    }
}
