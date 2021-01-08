package de.nikos410.fpr.employeemanagement.personal;

import de.nikos410.fpr.employeemanagement.personal.properties.CoffeeCooker;
import de.nikos410.fpr.employeemanagement.personal.properties.CoffeeDrinking;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Employee extends AbstractPerson implements Comparable<Employee>, CoffeeDrinking, CoffeeCooker {
    protected BigDecimal salary;
    protected LocalDate hireDate;
    protected long staffId;

    private LocalDateTime drankLastCoffeeAt = null;

    public Employee(String firstName, String lastName, BigDecimal salary, LocalDate hireDate, long staffId) {
        super(firstName, lastName);
        this.salary = salary;
        this.hireDate = hireDate;
        this.staffId = staffId;
    }

    public BigDecimal increaseSalary(BigDecimal byPercent) {
        final BigDecimal difference = salary
                .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP)
                .multiply(byPercent);

        this.salary = salary.add(difference);
        return calculateSalary();
    }

    public BigDecimal calculateSalary() {
        return salary;
    }

    @Override
    public void drinkCoffee() {
        if (drankLastCoffeeAt != null && drankLastCoffeeAt.until(LocalDateTime.now(), ChronoUnit.MINUTES) < 15) {
            throw new IllegalStateException("I just finished my last coffee!");
        }

        System.out.println("Aah, that's good.");
        drankLastCoffeeAt = LocalDateTime.now();
    }

    @Override
    public void cookCoffee() {
        if (calculateSalary().compareTo(BigDecimal.valueOf(10_000)) > 0) {
            System.out.println("No, do it yourself!");
        } else {
            System.out.println("Ok.");
        }
    }

    @Override
    public int compareTo(Employee other) {
        return calculateSalary().compareTo(other.calculateSalary());
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
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", hireDate=" + hireDate +
                ", staffId=" + staffId +
                '}';
    }
}
