// Efstathios Iosifidis mai25017

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Branch {
    private String address;
    private List<Employee> employees;

    public Branch(String address) {
        this.address = address;
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        if (employees.size() < 100) {
            employees.add(employee);
        } else {
            System.out.println("Branch is full. Cannot add more employees.");
        }
    }

    public void setEmployeeHours() {
        Scanner scanner = new Scanner(System.in);
        for (Employee employee : employees) {
            System.out.print("Enter the number of hours for employee " + employee.getLastName() + ": ");
            int eHours = scanner.nextInt();
            employee.setHoursWorked(eHours);
        }
    }

    public void printPayroll(String month) {
        System.out.println("********** PAYROLL for " + month + " **********");
        System.out.println("Branch: " + address);
        System.out.println("\n");
        for (Employee employee : employees) {
            employee.printPayrollReport(month);
        }
    }
}
