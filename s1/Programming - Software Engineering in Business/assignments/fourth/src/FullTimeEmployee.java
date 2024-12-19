// Efstathios Iosifidis mai25017

public class FullTimeEmployee extends Employee {
    private int monthlySalary;
    private int overtimeHours;

    public FullTimeEmployee(String lastName, String taxId, int monthlySalary) {
        super(lastName, taxId);
        this.monthlySalary = monthlySalary;
        this.overtimeHours = 0;
    }

    public void setHoursWorked(int hours) {
        overtimeHours = hours;
    }

    public double calculateSalary() {
        return monthlySalary + (overtimeHours * 0.005 * monthlySalary);
    }

    public void printPayrollReport(String month) {
        super.printPayrollReport(month);
        System.out.println("PAYMENT RECORD FOR A FULL-TIME EMPLOYEE");
        System.out.println("Employee's last name: " + lastName);
        System.out.println("Tax ID number: " + taxId);
        System.out.println("Hours: " + overtimeHours);
        System.out.println("Monthly total: " + calculateSalary() + " Euro");
        System.out.println("Remarks");
        System.out.println("Work hours refer to overtime work.");
        System.out.println("Monthly salary = " + monthlySalary + " Euro");
        System.out.println("\n");
    }

    public int getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(int monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public int getOvertimeHours() {
        return overtimeHours;
    }
}
