package de.nikos410.fpr.employeemanagement.personal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Manager extends Employee {
    private BigDecimal bonus;

    public Manager(String firstName, String lastName, BigDecimal salary, LocalDate hireDate, long staffId, BigDecimal bonus) {
        super(firstName, lastName, salary, hireDate, staffId);
        this.bonus = bonus;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public BigDecimal setBonus(BigDecimal bonus) {
        this.bonus = bonus;
        return calculateSalary();
    }

    @Override
    public BigDecimal calculateSalary() {
        return super.calculateSalary().add(bonus);
    }

    @Override
    public void cookCoffee() {
        System.out.println("Are you kidding me? Just ask my secretary!");
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
        Manager manager = (Manager) o;
        return bonus.equals(manager.bonus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bonus);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", hireDate=" + hireDate +
                ", staffId=" + staffId +
                ", bonus=" + bonus +
                '}';
    }
}
